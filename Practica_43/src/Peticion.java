import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Peticion implements Runnable
{
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Scanner CONSOLA = new Scanner(System.in);
    private static final String RUTA_ALUMNOS = "/alumnos";
    private static final String RUTA_ASIGNATURAS = "/asignaturas";
    private static final String RUTA_NOTAS = "/notas";
    private static final String RUTA_NOTAS_ALUMNO = "/notas/alumnos";

    private static final class Respuesta
	{
        private final int codigo;
        private final String texto;
        private final String cuerpo;

        private Respuesta(int codigo, String texto, String cuerpo)
		{
            this.codigo = codigo;
            this.texto = texto;
            this.cuerpo = cuerpo;
        }
    }

    private final Socket socketCliente;
    private final ControladorOracle controlador;

    public Peticion(Socket socketCliente, ControladorOracle controlador)
	{
        this.socketCliente = socketCliente;
        this.controlador = controlador;
    }

    @Override
    public void run()
	{
        try (Socket socket = socketCliente; BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)); OutputStream salida = socket.getOutputStream())
		{
            String lineaPeticion = entrada.readLine();
            
			if (lineaPeticion == null)
                return;
			
            descartarCabeceras(entrada);
            enviar(salida, atender(lineaPeticion));
        }
		catch (IOException e)
		{
            System.err.println("Error al atender al cliente: " + e.getMessage());
        }
    }

    private void descartarCabeceras(BufferedReader entrada) throws IOException
	{
        String linea;
        while ((linea = entrada.readLine()) != null && !linea.isEmpty())
		{
            // Cabecera ignorada
        }
    }

    private Respuesta atender(String lineaPeticion)
	{
        String[] partes = lineaPeticion.split(" ");
        
		if (partes.length < 2)
            return error(400, "Bad Request", "Petición mal formada");
		
        if (!"GET".equals(partes[0]))
            return error(405, "Method Not Allowed", "Solo se admite el método GET");

        String destino = partes[1];
        int posicionInterrogacion = destino.indexOf('?');
        String ruta = posicionInterrogacion < 0 ? destino : destino.substring(0, posicionInterrogacion);

        try
		{
            switch (ruta)
			{
                case RUTA_ALUMNOS:
                    return ok(controlador.consultarAlumnos());
                case RUTA_ASIGNATURAS:
                    return ok(controlador.consultarAsignaturas());
                case RUTA_NOTAS:
                    return ok(controlador.consultarNotas());
                case RUTA_NOTAS_ALUMNO:
                    return atenderNotasDeAlumno();
                default:
                    return error(404, "Not Found", "Ruta no encontrada");
            }
        }
		catch (SQLException e)
		{
            System.err.println("Error en la base de datos: " + e.getMessage());
            return error(500, "Internal Server Error", "Error al consultar la base de datos");
        }
    }

    private Respuesta atenderNotasDeAlumno() throws SQLException
	{
        String dni;
        try
		{
            dni = pedirDniPorConsola();
        }
		catch (NoSuchElementException | IllegalStateException e)
		{
            return error(500, "Internal Server Error", "No se pudo leer el DNI por la consola del servidor");
        }
        return ok(controlador.consultarNotasDeAlumno(dni));
    }

    private static String pedirDniPorConsola()
	{
        synchronized (CONSOLA)
		{
            System.out.println("Introduce DNI del Alumno: ");
            return CONSOLA.nextLine().trim();
        }
    }

    private static Respuesta ok(Object datos)
	{
        return new Respuesta(200, "OK", GSON.toJson(datos));
    }

    private static Respuesta error(int codigo, String texto, String mensaje)
	{
        return new Respuesta(codigo, texto, GSON.toJson(Collections.singletonMap("error", mensaje)));
    }

    private void enviar(OutputStream salida, Respuesta respuesta) throws IOException
	{
        byte[] cuerpo = respuesta.cuerpo.getBytes(StandardCharsets.UTF_8);
        String cabeceras = "HTTP/1.1 " + respuesta.codigo + " " + respuesta.texto + "\r\n"+ "Content-Type: application/json; charset=UTF-8\r\n"+ "Content-Length: " + cuerpo.length + "\r\n"+ "\r\n";
        salida.write(cabeceras.getBytes(StandardCharsets.US_ASCII));
        salida.write(cuerpo);
        salida.flush();
    }
}