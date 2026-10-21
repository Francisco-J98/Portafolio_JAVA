import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Operaciones sobre la matriz de empleados del banco.
 * Cada fila es una sucursal y cada elemento "PrimerApellido SegundoApellido Nombre".
 */
 
public final class Operaciones
{

    private Operaciones()
    {
        // Clase de utilidades: no se instancia
    }

    public static String capitalizar(String texto)
    {
        if (texto == null || texto.isBlank())
            return "";

        String textoLimpio = texto.strip();
        return Character.toUpperCase(textoLimpio.charAt(0)) + textoLimpio.substring(1);
    }

    public static String leerTextoCapitalizado(Scanner teclado, String etiqueta)
    {
        System.out.println(etiqueta + ": ");
        return capitalizar(teclado.nextLine());
    }

    public static String componerNombreCompleto(String primerApellido, String segundoApellido, String nombre)
    {
        return String.join(" ", primerApellido, segundoApellido, nombre);
    }

    public static void rellenarDatos(String[][] banco, Scanner teclado)
    {
        for (int sucursal = 0; sucursal < banco.length; sucursal++)
        {
            for (int empleado = 0; empleado < banco[sucursal].length; empleado++)
            {
                System.out.println("Sucursal " + (sucursal + 1) + " Empleado " + (empleado + 1) + ": ");
                String nombre = leerTextoCapitalizado(teclado, "Nombre");
                String primerApellido = leerTextoCapitalizado(teclado, "Apellido 1");
                String segundoApellido = leerTextoCapitalizado(teclado, "Apellido 2");

                banco[sucursal][empleado] = componerNombreCompleto(primerApellido, segundoApellido, nombre);
            }
        }
    }

    /** Muestra los empleados de una sucursal (numeración desde 1). */
    public static void listarEmpleadosSucursal(String[][] banco, int numeroSucursal)
    {
        System.out.println("----------Lista de Empleados----------");
        Arrays.stream(banco[numeroSucursal - 1]).forEach(System.out::println);
        System.out.println();
    }

    /** Indica en qué sucursal(es) aparece el empleado. */
    public static void buscarEmpleado(String[][] banco, String nombreCompleto)
    {
        boolean encontrado = false;

        for (int sucursal = 0; sucursal < banco.length; sucursal++)
        {
            if (Arrays.asList(banco[sucursal]).contains(nombreCompleto))
            {
                System.out.println(nombreCompleto + " pertenece a la sucursal: " + (sucursal + 1));
                encontrado = true;
            }
        }

        if (!encontrado)
            System.out.println("La persona no se encuentra en el registro o sus datos se han introducido erróneamente.");
    }

    public static long contarPorPrimerApellido(String[][] banco, String primerApellido)
    {
        String prefijo = primerApellido + " ";
        return todosLosEmpleados(banco).filter(nombreCompleto -> nombreCompleto.startsWith(prefijo)).count();
    }

    /** Muestra todos los empleados ordenados alfabéticamente (por primer apellido). */
    public static void mostrarEmpleadosOrdenados(String[][] banco)
    {
        todosLosEmpleados(banco).sorted().forEach(System.out::println);
    }

    private static Stream<String> todosLosEmpleados(String[][] banco)
    {
        return Arrays.stream(banco).flatMap(Arrays::stream);
    }
}