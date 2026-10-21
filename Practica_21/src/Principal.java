import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Principal
{

    private static final int MINIMO_FICHEROS = 1;
    private static final int MAXIMO_FICHEROS = 5;
    private static final String SEPARADOR_SALIDA = ":";
    private static final Path DIRECTORIO_CLASES = Path.of(System.getProperty("user.home"), "eclipse-workspace", "Procesos", "bin");

    public static void main(String[] argumentos)
    {
        if (argumentos.length < MINIMO_FICHEROS || argumentos.length > MAXIMO_FICHEROS)
        {
            System.out.println("Parámetros incorrectos.");
            return;
        }

        long instanteInicial = System.nanoTime();
        List<Process> procesos = lanzarProcesos(argumentos);

        int beneficioTotal = 0;

        for (Process proceso : procesos)
            beneficioTotal += recogerBeneficio(proceso);

        long duracionMilisegundos = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - instanteInicial);

        System.out.println("Los beneficios totales son: " + beneficioTotal + "€");
        System.out.println("El programa ha tardado: " + duracionMilisegundos + " milisegundo(s)");
    }

    private static List<Process> lanzarProcesos(String[] ficheros
    {
        List<Process> procesos = new ArrayList<>();

        for (String fichero : ficheros)
        {
            ProcessBuilder constructorProceso = new ProcessBuilder("java", "paquete.Operando", fichero).directory(DIRECTORIO_CLASES.toFile()).redirectError(ProcessBuilder.Redirect.INHERIT);
            try
            {
                procesos.add(constructorProceso.start());
            }
            catch (IOException excepcion)
            {
                System.err.println("No se pudo lanzar el proceso para " + fichero + ": " + excepcion.getMessage());
            }
        }

        return procesos;
    }

    private static int recogerBeneficio(Process proceso)
    {
        int beneficio = 0;

        try (BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream())))
        {
            String linea;
            while ((linea = lector.readLine()) != null)
            {
                String[] partes = linea.split(SEPARADOR_SALIDA);
                if (partes.length == 2)
                    beneficio += Integer.parseInt(partes[1].trim());
            }
            proceso.waitFor();
        }
        catch (IOException | NumberFormatException excepcion)
        {
            System.err.println("Error al leer la salida del proceso: " + excepcion.getMessage());
        }
        catch (InterruptedException excepcion)
        {
            Thread.currentThread().interrupt();
            System.err.println("Espera del proceso interrumpida.");
        }
        return beneficio;
    }
}