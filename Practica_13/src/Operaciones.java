import java.util.ArrayList;
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
            } catch (NumberFormatException e)
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

    /** Pide nombre y apellidos y devuelve el nombre completo con el formato "Apellido1 Apellido2 Nombre". */
    private static String leerNombreCompleto(Scanner teclado)
    {
        String nombre = leerTextoSinNumeros(teclado, "Introduzca Nombre (sensible a tildes): ");
        String primerApellido = leerTextoSinNumeros(teclado, "Introduzca Apellido1 (sensible a tildes): ");
        String segundoApellido = leerTextoSinNumeros(teclado, "Introduzca Apellido2 (sensible a tildes): ");
        return String.join(" ", primerApellido, segundoApellido, nombre);
    }

    // ---------- Carga inicial ----------

    public static List<EmpleadoBanco> rellenarDatos(Scanner teclado, int totalEmpleados)
    {
        List<EmpleadoBanco> empleados = new ArrayList<>(totalEmpleados);

        for (int numero = 1; numero <= totalEmpleados; numero++)
        {
            String nombre = leerTextoSinNumeros(teclado, "Nombre empleado " + numero + " :");
            String primerApellido = leerTextoSinNumeros(teclado, "Apellido1 empleado " + numero + " :");
            String segundoApellido = leerTextoSinNumeros(teclado, "Apellido2 empleado " + numero + " :");
            int sucursal = leerEnteroEnRango(teclado, "Sucursal empleado " + numero + " :", 1, Integer.MAX_VALUE);

            empleados.add(new EmpleadoBanco(nombre, primerApellido, segundoApellido, sucursal));
        }
        return empleados;
    }

    // ---------- Opciones del menú ----------

    /** Opción 1: empleados de una sucursal. */
    public static void listarEmpleadosSucursal(List<EmpleadoBanco> empleados, Scanner teclado)
    {
        int sucursal = leerEnteroEnRango(teclado, "De qué sucursal buscas empleados (1-N): ", 1, Integer.MAX_VALUE);

        empleados.stream().filter(empleado -> empleado.sucursal() == sucursal).forEach(empleado -> System.out.print(empleado.nombreCompleto() + "\t"));
        System.out.println();
    }

    /** Opción 2: sucursal a la que pertenece un empleado. */
    public static void buscarEmpleado(List<EmpleadoBanco> empleados, Scanner teclado)
    {
        String nombreCompleto = leerNombreCompleto(teclado);
        List<EmpleadoBanco> coincidencias = empleados.stream().filter(empleado -> empleado.nombreCompleto().equals(nombreCompleto)).toList();

        if (coincidencias.isEmpty())
        {
            System.out.println("Esta persona no está en ninguna sucursal o se ha introducido mal el dato.");
            return;
        }

        coincidencias.forEach(empleado -> System.out.println(empleado.nombreCompleto() + " pertenece a la sucursal " + empleado.sucursal()));
    }

    /** Opción 3: empleados que comparten primer apellido. */
    public static void contarPorPrimerApellido(List<EmpleadoBanco> empleados, Scanner teclado)
    {
        String primerApellido = leerTextoSinNumeros(teclado, "Introduzca Apellido1 (sensible a tildes): ");
        List<EmpleadoBanco> coincidencias = empleados.stream().filter(empleado -> empleado.primerApellido().equals(primerApellido)).toList();

        if (coincidencias.isEmpty())
        {
            System.out.println("No hay ningún empleado con ese apellido o se ha introducido mal el dato.");
            return;
        }

        coincidencias.forEach(empleado -> System.out.println(empleado.nombreCompleto()));
        System.out.println("Hay " + coincidencias.size() + " empleado(s) con el apellido " + primerApellido);
    }

    /** Opción 4: todos los empleados del banco. */
    public static void listarTodosLosEmpleados(List<EmpleadoBanco> empleados)
    {
        System.out.println("--------------LISTA DE EMPLEADOS--------------");
        empleados.forEach(empleado -> System.out.println(empleado.nombreCompleto()));
    }

    /** Opción 5: todos los empleados ordenados alfabéticamente (por primer apellido). */
    public static void listarEmpleadosOrdenados(List<EmpleadoBanco> empleados)
    {
        empleados.stream().map(EmpleadoBanco::nombreCompleto).sorted().forEach(System.out::println);
    }
}