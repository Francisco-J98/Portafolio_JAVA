import java.util.List;
import java.util.Scanner;

public class Principal
{
    private static final int OPCION_SALIR = 6;

    public static void main(String[] args)
    {
        try (Scanner teclado = new Scanner(System.in))
        {
            int totalEmpleados = Operaciones.leerEnteroEnRango(teclado, "Introduce total empleados: ", 1, Integer.MAX_VALUE);
            List<EmpleadoBanco> empleados = Operaciones.rellenarDatos(teclado, totalEmpleados);

            int opcion;
            do
            {
                mostrarMenu();
                opcion = Operaciones.leerEnteroEnRango(teclado, "Escoge una opción(1-6): ", 1, OPCION_SALIR);
                ejecutarOpcion(opcion, empleados, teclado);
            } while (opcion != OPCION_SALIR);
        }
    }

    private static void ejecutarOpcion(int opcion, List<EmpleadoBanco> empleados, Scanner teclado)
    {
        if (opcion == OPCION_SALIR)
        {
            System.out.println("Ha salido del programa.");
            return;
        }

        System.out.println("Ha escogido la opción " + opcion);
        switch (opcion)
        {
            case 1 -> Operaciones.listarEmpleadosSucursal(empleados, teclado);
            case 2 -> Operaciones.buscarEmpleado(empleados, teclado);
            case 3 -> Operaciones.contarPorPrimerApellido(empleados, teclado);
            case 4 -> Operaciones.listarTodosLosEmpleados(empleados);
            case 5 -> Operaciones.listarEmpleadosOrdenados(empleados);
            default -> { }
        }
    }

    private static void mostrarMenu()
    {
        System.out.println("""
                --------------------MENÚ PRINCIPAL--------------------
                |1) Lista empleados de una determinada sucursal.     |
                |2) Sucursal a la que pertenece determinado empleado.|
                |3) Cantidad de empleados con el mismo apellido.     |
                |4) Lista de todos los empleados del Banco.          |
                |5) Lista de empleados ordenados alfabéticamente.    |
                |6) Salir del programa.                              |
                |____________________________________________________|""");
    }
}