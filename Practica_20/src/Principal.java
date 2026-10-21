import java.io.IOException;

public class Principal
{
    private static final String RUTA_FIREFOX = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";

    public static void main(String[] argumentos)
    {
        lanzarFirefox();
    }

    private static void lanzarFirefox()
    {
        ProcessBuilder constructorProceso = new ProcessBuilder(RUTA_FIREFOX);

        try
        {
            constructorProceso.start();
        }
        catch (IOException excepcion)
        {
            System.err.println("Proceso no realizado: " + excepcion.getMessage());
        }
    }
}