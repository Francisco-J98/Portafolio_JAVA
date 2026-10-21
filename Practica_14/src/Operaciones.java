import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/** Operaciones de entrada de datos y consulta sobre la lista de empleados. */
public final class Operaciones
{
    private Operaciones()
    {
        // Clase de utilidades: no se instancia
    }

    // ---------- Lectura de datos ----------

    /** Pide un entero hasta que esté dentro del rango [minimo, maximo]. */
    public static int leerEnteroEnRango(Scanner teclado, String mensaje, int minimo, int maximo)
    {
        while (true)
        {
            System.out.println(mensaje);
            try
            {
                int valor = Integer.parseInt(teclado.nextLine().trim());
                if (valor >= minimo && valor <= maximo)
                    return valor;
            }
            catch (NumberFormatException e)
            {
                // Se ignora: se avisa abajo y se vuelve a pedir
            }
            System.out.println("Entrada no válida.\n");
        }
    }

    /** Pide un texto no vacío y sin dígitos, y lo devuelve con la primera letra en mayúscula. */
    public static String leerTextoSinNumeros(Scanner teclado, String mensaje)
    {
        while (true)
        {
            System.out.println(mensaje);
            String texto = teclado.nextLine().strip();

            if (!texto.isEmpty() && texto.chars().noneMatch(Character::isDigit))
                return capitalizar(texto);

            System.out.println("Entrada no válida: no puede estar vacío ni contener números.\n");
        }
    }

    private static String capitalizar(String texto)
    {
        return Character.toUpperCase(texto.charAt(0)) + texto.substring(1);
    }

    // ---------- Opciones del menú ----------

    /** Opción 1: añade un empleado a la lista. */
    public static void anadirEmpleado(List<EmpleadoBanco> empleados, Scanner teclado)
    {
        String nombre = leerTextoSinNumeros(teclado, "Nombre empleado: ");
        String primerApellido = leerTextoSinNumeros(teclado, "Apellido1 empleado: ");
        String segundoApellido = leerTextoSinNumeros(teclado, "Apellido2 empleado: ");
        int sucursal = leerEnteroEnRango(teclado, "Sucursal empleado: ", 1, Integer.MAX_VALUE);

        empleados.add(new EmpleadoBanco(nombre, primerApellido, segundoApellido, sucursal));
    }

    /** Opción 2: empleados de una sucursal. */
    public static void listarEmpleadosSucursal(List<EmpleadoBanco> empleados, Scanner teclado)
    {
        int sucursal = leerEnteroEnRango(teclado, "Sucursal deseada: ", 1, Integer.MAX_VALUE);

        System.out.println("--------Lista de Empleados de la sucursal " + sucursal + "--------");
        empleados.stream().filter(empleado -> empleado.sucursal() == sucursal).forEach(System.out::println);
        System.out.println();
    }

    /** Opción 3: sucursal a la que pertenece un empleado. */
    public static void buscarSucursalDeEmpleado(List<EmpleadoBanco> empleados, Scanner teclado)
    {
        String nombre = leerTextoSinNumeros(teclado, "Introduzca el nombre: ");
        String primerApellido = leerTextoSinNumeros(teclado, "A continuación el primer apellido: ");
        String segundoApellido = leerTextoSinNumeros(teclado, "Y ahora el segundo apellido: ");
        String nombreCompleto = String.join(" ", primerApellido, segundoApellido, nombre);

        List<EmpleadoBanco> coincidencias = empleados.stream().filter(empleado -> empleado.nombreCompleto().equalsIgnoreCase(nombreCompleto)).toList();

        if (coincidencias.isEmpty())
        {
            System.out.println("Esta persona no está en ninguna sucursal o se ha introducido mal el dato.");
            return;
        }

        coincidencias.forEach(empleado -> System.out.println(nombreCompleto + " pertenece a la sucursal " + empleado.sucursal()));
    }

    /** Opción 4: empleados que comparten primer apellido. */
    public static void contarPorPrimerApellido(List<EmpleadoBanco> empleados, Scanner teclado)
    {
        String primerApellido = leerTextoSinNumeros(teclado, "Introduzca el Apellido: ");

        List<EmpleadoBanco> coincidencias = empleados.stream().filter(empleado -> empleado.primerApellido().equalsIgnoreCase(primerApellido)).toList();

        if (coincidencias.isEmpty())
        {
            System.out.println("No hay empleados con ese apellido o se ha introducido mal el dato.");
            return;
        }

        coincidencias.forEach(System.out::println);
        System.out.println("Hay " + coincidencias.size() + " empleado(s) con el apellido " + primerApellido);
    }

    /** Opción 5: todos los empleados del banco. */
    public static void listarTodosLosEmpleados(List<EmpleadoBanco> empleados)
    {
        empleados.forEach(System.out::println);
    }

    /** Opción 6: todos los empleados ordenados alfabéticamente (por primer apellido). */
    public static void listarEmpleadosOrdenados(List<EmpleadoBanco> empleados)
    {
        empleados.stream().sorted(Comparator.comparing(EmpleadoBanco::nombreCompleto)).forEach(System.out::println);
    }
}