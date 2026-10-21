import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Operaciones
{

    private static final int OPCION_SUMA = 1;
    private static final int OPCION_RESTA = 2;
    private static final int OPCION_COCIENTE = 3;
    private static final int OPCION_FACTORIAL = 4;
    private static final Map<Integer, String> NOMBRES_OPERACIONES = Map.of(OPCION_SUMA, "OP1 SUMA", OPCION_RESTA, "OP2 DIFERENCIA", OPCION_COCIENTE, "OP3 COCIENTE", OPCION_FACTORIAL, "OP4 FACTORIAL");
    private static final File DIRECTORIO_PROGRAMAS = new File("C:\\Users\\Franchu\\Desktop\\PSP\\ProgramasParam\\bin");
    private final Scanner teclado = new Scanner(System.in);

    protected void menu()
    {
        int opcionElegida = pedirOpcion();
        ejecutarOperacion(opcionElegida);
    }

    private int pedirOpcion()
    {
        int opcion = 0;

        while (opcion < OPCION_SUMA || opcion > OPCION_FACTORIAL)
        {
            System.out.println("-----MENU-----");
            System.out.println("|1) SUMA     |");
            System.out.println("|2) RESTA    |");
            System.out.println("|3) COCIENTE |");
            System.out.println("|4) FACTORIAL|");
            System.out.println("|____________|");
            System.out.println("Escoge una opción (1~4): ");

            try
            {
                opcion = teclado.nextInt();
            }
            catch (InputMismatchException excepcion)
            {
                opcion = 0;
            }
            finally
            {
                teclado.nextLine();
            }
        }

        return opcion;
    }

    private void ejecutarOperacion(int opcion)
    {
        System.out.println(NOMBRES_OPERACIONES.get(opcion));

        int primerOperando = pedirNumero("Introduzca un valor: ");
        String[] argumentos;

        if (opcion == OPCION_FACTORIAL)
            argumentos = new String[]{String.valueOf(primerOperando)};
        else
        {
            int segundoOperando = pedirNumero("Introduzca otro valor: ");
            argumentos = new String[]{String.valueOf(primerOperando), String.valueOf(segundoOperando)};
        }

        lanzarProceso(opcion, argumentos);
    }

    private int pedirNumero(String mensaje)
    {
        while (true)
        {
            System.out.println(mensaje);
            try
            {
                return teclado.nextInt();
            }
            catch (InputMismatchException excepcion)
            {
                System.out.println("Debe introducir un número entero.");
            }
            finally
            {
                teclado.nextLine();
            }
        }
    }

    private void lanzarProceso(int opcion, String[] argumentos)
    {
        String claseProceso = "p" + opcion + "/P" + opcion + "IngInv";

        String[] comando = new String[2 + argumentos.length];
        comando[0] = "java";
        comando[1] = claseProceso;
        System.arraycopy(argumentos, 0, comando, 2, argumentos.length);

        ProcessBuilder constructorProceso = new ProcessBuilder(comando).directory(DIRECTORIO_PROGRAMAS).redirectErrorStream(true);

        try
        {
            Process proceso = constructorProceso.start();

            try (BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream())))
            {
                String linea;
                while ((linea = lector.readLine()) != null)
                {
                    System.out.println(linea);
                }
            }
            proceso.waitFor();
        }
        catch (IOException excepcion)
        {
            System.err.println("Error al lanzar el proceso: " + excepcion.getMessage());
        }
        catch (InterruptedException excepcion)
        {
            Thread.currentThread().interrupt();
            System.err.println("Espera del proceso interrumpida.");
        }
    }
}