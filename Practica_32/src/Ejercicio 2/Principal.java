import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class Principal
{
    private static final String NOMBRE_FICHERO = "MICONFIGURACION.conf";
    private static final String CLAVE_NOMBRE = "minombre";
    private static final String NOMBRE_USUARIO = "Fran";
    private static final String COMENTARIO_FICHERO = "Mi primer archivo config";

    public static void main(String[] argumentos)
	{
        Path rutaConfiguracion = Path.of(System.getProperty("user.home"), NOMBRE_FICHERO);

        Properties configuracion = new Properties();
        configuracion.setProperty(CLAVE_NOMBRE, NOMBRE_USUARIO);

        try (OutputStream salida = Files.newOutputStream(rutaConfiguracion))
		{
            configuracion.store(salida, COMENTARIO_FICHERO);
            System.out.println("Éxito al crear el archivo .conf en " + rutaConfiguracion);
        }
		catch (IOException e)
		{
            System.out.println("Error al crear el fichero: " + e.getMessage());
        }
    }
}