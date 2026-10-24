package ejercicio1;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class Principal
{

    private static final Path RUTA_CONFIGURACION = Path.of("miconfig.conf");
    private static final String CLAVE_NOMBRE = "minombre";
    private static final String NOMBRE_USUARIO = "Fran";
    private static final String COMENTARIO_FICHERO = "Mi primer archivo config";

    public static void main(String[] argumentos)
	{
        Properties configuracion = new Properties();
        configuracion.setProperty(CLAVE_NOMBRE, NOMBRE_USUARIO);

        try (OutputStream salida = Files.newOutputStream(RUTA_CONFIGURACION))
		{
            configuracion.store(salida, COMENTARIO_FICHERO);
            System.out.println("Éxito al crear el archivo .conf");
        }
		catch (IOException e)
		{
            System.out.println("Error al crear el fichero: " + e.getMessage());
        }
    }
}