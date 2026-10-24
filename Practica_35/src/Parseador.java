import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;


public class Parseador
{
    private static final String CLAVE_CANTIDAD = "cantidad";
    private static final String COMENTARIO_FICHERO = "Fichero de cantidades";

    public void incrementarCantidad(Path rutaConfiguracion)
	{
        try
		{
            Properties configuracion = cargar(rutaConfiguracion);
            System.out.println("Éxito al rescatar la cantidad");

            int cantidadNueva = leerCantidad(configuracion) + 1;
            System.out.println("Sumado +1 a la cantidad actual...");

            configuracion.setProperty(CLAVE_CANTIDAD, String.valueOf(cantidadNueva));
            guardar(configuracion, rutaConfiguracion);
            System.out.println("Éxito al actualizar el archivo .conf");
        }
		catch (IOException e)
		{
            System.out.println("Error al acceder al fichero: " + e.getMessage());
        }
		catch (IllegalArgumentException e)
		{
            System.out.println("La propiedad '" + CLAVE_CANTIDAD + "' no existe o no es un número entero válido.");
        }
    }

    private Properties cargar(Path ruta) throws IOException
	{
        Properties configuracion = new Properties();
        
		try (InputStream entrada = Files.newInputStream(ruta))
		{
            configuracion.load(entrada);
        }
        return configuracion;
    }

    /** @throws IllegalArgumentException si la propiedad falta o no es un entero (NumberFormatException es subclase) */
    private int leerCantidad(Properties configuracion)
	{
        String valor = configuracion.getProperty(CLAVE_CANTIDAD);
        if (valor == null)
            throw new IllegalArgumentException("Falta la propiedad " + CLAVE_CANTIDAD);
		
        return Integer.parseInt(valor.trim());
    }

    private void guardar(Properties configuracion, Path ruta) throws IOException
	{
        try (OutputStream salida = Files.newOutputStream(ruta))
		{
            configuracion.store(salida, COMENTARIO_FICHERO);
        }
    }
}