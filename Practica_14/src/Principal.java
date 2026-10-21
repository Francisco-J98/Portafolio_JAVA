import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Programa del Banco con ArrayList */

public class Principal
{

    private static final int OPCION_SALIR = 7;

    public static void main(String[] args)
    {
        List<EmpleadoBanco> empleados = new ArrayList<>();

        try (Scanner teclado = new Scanner(System.in))
        {
            int opcion;

            do
            {
                mostrarMenu();
                opcion = Operaciones.leerEnteroEnRango(teclado, "Escoge una opción(1-7): ", 1, OPCION_SALIR);
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
            case 1 -> Operaciones.anadirEmpleado(empleados, teclado);
            case 2 -> Operaciones.listarEmpleadosSucursal(empleados, teclado);
            case 3 -> Operaciones.buscarSucursalDeEmpleado(empleados, teclado);
            case 4 -> Operaciones.contarPorPrimerApellido(empleados, teclado);
            case 5 -> Operaciones.listarTodosLosEmpleados(empleados);
            case 6 -> Operaciones.listarEmpleadosOrdenados(empleados);
            default -> { }
        }
    }

    private static void mostrarMenu()
    {
        System.out.println("""
                --------------------MENÚ PRINCIPAL--------------------
                |1) Añadir datos.                                    |
                |2) Lista empleados de una determinada sucursal.     |
                |3) Sucursal a la que pertenece determinado empleado.|
                |4) Cantidad de empleados con el mismo apellido.     |
                |5) Lista de todos los empleados del Banco.          |
                |6) Lista de empleados ordenados alfabéticamente.    |
                |7) Salir del programa.                              |
                |____________________________________________________|""");
    }
}