import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class Operaciones
{
    private static final File DIRECTORIO_FACTORIAL = new File("/media/fran/FRAN/PSP/Procesos4Fact");
    private static final String CLASE_FACTORIAL = "Factorial";
    private static final int PRIMER_NUMERO = 15;
    private static final int SEGUNDO_NUMERO = 14;

    protected void calcularSumaFactoriales()
    {
        long instanteInicial = System.nanoTime();

        try
        {
            Process procesoPrimero = lanzarFactorial(PRIMER_NUMERO);
            Process procesoSegundo = lanzarFactorial(SEGUNDO_NUMERO);

            long primerFactorial = leerResultado(procesoPrimero);
            System.out.println(PRIMER_NUMERO + "! = " + primerFactorial);

            long segundoFactorial = leerResultado(procesoSegundo);
            System.out.println(SEGUNDO_NUMERO + "! = " + segundoFactorial);

            System.out.println(PRIMER_NUMERO + "! + " + SEGUNDO_NUMERO + "! = " + (primerFactorial + segundoFactorial));
        }
        catch (IOException | NumberFormatException excepcion)
        {
            System.err.println("Error al calcular los factoriales: " + excepcion.getMessage());
            return;
        }
        catch (InterruptedException excepcion)
        {
            Thread.currentThread().interrupt();
            System.err.println("Espera del proceso interrumpida.");
            return;
        }

        long duracionMilisegundos = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - instanteInicial);
        System.out.println("La ejecución ha tardado: " + duracionMilisegundos + " milisegundo(s).");
    }

    private Process lanzarFactorial(int numero) throws IOException
    {
        return new ProcessBuilder("java", CLASE_FACTORIAL, String.valueOf(numero)).directory(DIRECTORIO_FACTORIAL).redirectError(ProcessBuilder.Redirect.INHERIT).start();
    }

    private long leerResultado(Process proceso) throws IOException, InterruptedException
    {
        String ultimaLinea = "";

        try (BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream())))
        {
            String linea;
            while ((linea = lector.readLine()) != null)
            {
                if (!linea.isBlank())
                    ultimaLinea = linea;
            }
        }

        proceso.waitFor();
        return Long.parseLong(ultimaLinea.trim());
    }
}