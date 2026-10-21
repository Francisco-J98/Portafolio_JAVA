import java.util.Scanner;

/* Todos los métodos de negocio de este programa pertenecen a "Operaciones.java" */

public class Principal
{

    private static final int OPCION_SALIR = 6;

    public static void main(String[] args)
    {
        try (Scanner teclado = new Scanner(System.in))
        {
            String[][] banco = crearBanco(teclado);
            Operaciones.rellenarDatos(banco, teclado);

            int opcion;
            do
            {
                mostrarMenu();
                opcion = leerEnteroEnRango(teclado, "Introduzca una opción (1~6): ", 1, OPCION_SALIR);
                ejecutarOpcion(opcion, banco, teclado);
            } while (opcion != OPCION_SALIR);
        }
    }

    private static String[][] crearBanco(Scanner teclado)
    {
        int totalSucursales = leerEnteroEnRango(teclado, "Cuántas sucursales hay(n>=1): ", 1, Integer.MAX_VALUE);
        String[][] banco = new String[totalSucursales][];

        for (int sucursal = 0; sucursal < totalSucursales; sucursal++)
        {
            int totalEmpleados = leerEnteroEnRango(teclado, "Cuántos empleados hay(n>=1) en la Sucursal " + (sucursal + 1) + ": ", 1, Integer.MAX_VALUE);
            banco[sucursal] = new String[totalEmpleados];
        }
        return banco;
    }

    private static void ejecutarOpcion(int opcion, String[][] banco, Scanner teclado)
    {
        switch (opcion)
        {
            case 1 ->
            {
                int numeroSucursal = leerEnteroEnRango(teclado, "Número de la sucursal deseada: ", 1, banco.length);
                Operaciones.listarEmpleadosSucursal(banco, numeroSucursal);
            }
            case 2 ->
            {
                String nombre = Operaciones.leerTextoCapitalizado(teclado, "Nombre (sensible a tildes)");
                String primerApellido = Operaciones.leerTextoCapitalizado(teclado, "Apellido 1 (sensible a tildes)");
                String segundoApellido = Operaciones.leerTextoCapitalizado(teclado, "Apellido 2 (sensible a tildes)");
                Operaciones.buscarEmpleado(banco, Operaciones.componerNombreCompleto(primerApellido, segundoApellido, nombre));
            }
            case 3 ->
            {
                String primerApellido = Operaciones.leerTextoCapitalizado(teclado, "Introduzca Apellido 1 (sensible a tildes)");
                long coincidencias = Operaciones.contarPorPrimerApellido(banco, primerApellido);
                System.out.println("Hay " + coincidencias + " empleado(s) con el apellido " + primerApellido);
            }
            case 4 ->
            {
                System.out.println("-----------------BANCO DAM-----------------");
                for (String[] sucursal : banco)
                {
                    System.out.println(String.join("\t", sucursal));
                }
            }
            case 5 ->
            {
                System.out.println("-----------------BANCO DAM-----------------");
                System.out.println(".................EMPLEADOS.................");
                Operaciones.mostrarEmpleadosOrdenados(banco);
            }
            case OPCION_SALIR -> System.out.println("Has salido del programa.");
            default -> { }
        }
    }

    private static void mostrarMenu()
    {
        System.out.println("""
                ---------------------------MENÚ-BANCO----------------------------------
                |1) Mostrar todos los empleados de una de las sucursales.             |
                |2) Mostrar a qué sucursal pertenece un empleado.                     |
                |3) Mostrar cuántos empleados hay con el mismo primer apellido.       |
                |4) Mostrar todos los empleados del Banco por sucursal.               |
                |5) Mostrar todos los empleados del centro ordenados alfabéticamente. |
                |6) Salir.                                                            |
                |_____________________________________________________________________|""");
    }

    /** Pide un entero hasta que esté dentro del rango [minimo, maximo]. */
    private static int leerEnteroEnRango(Scanner teclado, String mensaje, int minimo, int maximo)
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
}