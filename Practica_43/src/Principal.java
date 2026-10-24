import objetos.ConfiguracionOracle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Principal
{
    private static final int PUERTO = 80;
    private static final Path RUTA_CONFIGURACION = Paths.get("parametros.conf");

    public static void main(String[] argumentos)
	{
        ControladorOracle controlador;
        try
		{
            controlador = new ControladorOracle(ConfiguracionOracle.cargarDesde(RUTA_CONFIGURACION));
            System.out.println("Éxito al rescatar los parámetros para Oracle...");
        }
		catch (IOException | IllegalArgumentException e)
		{
            System.err.println("No se pudo leer " + RUTA_CONFIGURACION + ": " + e.getMessage());
            return;
        }

        try (ServerSocket servidor = new ServerSocket(PUERTO))
		{
            System.out.println("Servidor arrancado en el puerto " + PUERTO);
            while (true)
			{
                Socket socketCliente = servidor.accept();
                System.out.println("Conexion entrante");
                new Thread(new Peticion(socketCliente, controlador)).start();
            }
        }
		catch (IOException e)
		{
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}