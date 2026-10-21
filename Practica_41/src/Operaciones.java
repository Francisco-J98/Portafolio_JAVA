import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

public class Operaciones
{
    private static final String SEPARADOR_CAMPOS = ";";
    private static final int OPCION_INVALIDA = -1;
    private static final String MENSAJE_ENTRADA_INVALIDA = "No se ha reconocido lo introducido por teclado.";
    private static final String MENSAJE_PEDIR_ID = "Introduzca el ID de la multa que está buscando: ";

    private static final String MENU_GENERAL = """
            ------MENÚ MULTAS DE LA Dirección General de Tráfico (DGT)------
            | 1) Agregar multas.                                            |
            | 2) Otras operaciones.                                         |
            | 3) Salir.                                                     |
            |_______________________________________________________________|
            """;

    private static final String MENU_OPERACIONES = """
            ------SUBMENÚ DE LA Dirección General de Tráfico (DGT)------
            | 1) Borrar multas.                                        |
            | 2) Modificar multas.                                     |
            | 3) Consultar multas.                                     |
            | 4) Cancelar.                                             |
            |__________________________________________________________|
            """;

    private static final String MENU_CONSULTAR = """
            -----------SUBMENÚ CONSULTAR-----------
            | 1) Consultar una multa.             |
            | 2) Consultar multas por agente.     |
            | 3) Consultar multas por localidad.  |
            | 4) Consultar todas las multas.      |
            | 5) Cancelar.                        |
            |_____________________________________|
            """;

    private static final String MENU_MODIFICAR = """
            -----------SUBMENÚ MODIFICAR-----------
            | 1) Cambiar localidad.               |
            | 2) Cambiar precio multa.            |
            | 3) Cambiar agente (a cargo).        |
            | 4) Pago de multas.                  |
            | 5) Cancelar.                        |
            |_____________________________________|
            """;

    private static final String MENU_PAGO = """
            --------------SUBMENÚ PAGAR--------------
            | 1) La multa no está pagada (poner a N).|
            | 2) La multa está pagada (poner a S).   |
            | 3) Cancelar.                           |
            |________________________________________|
            """;

    private final Path ficheroMultas;
    private final Scanner teclado = new Scanner(System.in);
    private final List<Multas> listaMultas = new ArrayList<>();
    private boolean hayCambiosSinGuardar = false;

    public Operaciones(Path ficheroMultas)
    {
        this.ficheroMultas = ficheroMultas;
    }

    // ------------------------------------------------------------------
    // Menús
    // ------------------------------------------------------------------

    public void menuGeneral()
    {
        cargarMultas();
        int opcion;

        do
        {
            System.out.print(MENU_GENERAL);
            opcion = leerOpcion("Seleccione una opción (3 para salir): ");

            switch (opcion)
            {
                case 1 ->
                {
                    System.out.println("Ha escogido: Agregar Multas.");
                    agregarMulta();
                }
                case 2 ->
                {
                    System.out.println("Ha escogido: Otras opciones.");
                    mostrarSubmenuOperaciones();
                }
                case 3 -> salir();
                default -> { }
            }
        } while (opcion != 3);
    }

    private void salir()
    {
        if (hayCambiosSinGuardar)
        {
            guardarMultas();
            System.out.println("Los datos se han guardado.");
            System.out.println("Ha salido del programa.");
        }
        else
            System.out.println("Ha escogido: Salir del Programa.");
    }

    private void mostrarSubmenuOperaciones()
    {
        if (listaMultas.isEmpty())
        {
            System.out.println("Para acceder a este menú, debe tener registros de multas disponibles.");
            return;
        }

        int opcion;
        do
        {
            System.out.print(MENU_OPERACIONES);
            opcion = leerOpcion("Seleccione una opción (4 para regresar al menú anterior): ");

            switch (opcion)
            {
                case 1 ->
                {
                    System.out.println("Ha escogido: Borrar multas.");
                    borrarMulta();
                }
                case 2 ->
                {
                    System.out.println("Ha escogido: Modificar multas.");
                    mostrarMenuModificar();
                }
                case 3 ->
                {
                    System.out.println("Ha escogido: Consultar multas.");
                    mostrarMenuConsultar();
                }
                default -> { }
            }
        } while (opcion != 4);
    }

    private void mostrarMenuConsultar()
    {
        int opcion;
        do
        {
            System.out.print(MENU_CONSULTAR);
            opcion = leerOpcion("Escoge una opción (1~5): ");
            consultar(opcion);
        } while (opcion != 5);
    }

    private void mostrarMenuModificar()
    {
        int opcion;
        do
        {
            System.out.print(MENU_MODIFICAR);
            opcion = leerOpcion("Escoge una opción (1~5): ");
            modificar(opcion);
        } while (opcion != 5);
    }

    // ------------------------------------------------------------------
    // Operaciones sobre las multas
    // ------------------------------------------------------------------

    private void agregarMulta()
    {
        int placaAgente = leerEnteroPositivo("Introduzca su número de placa de policía: ");
        String localidad = leerLocalidad("A continuación, introduzca la localidad en donde se ha producido la amonestación: ");
        double importe = leerImportePositivo("Introduzca el coste de la multa, en €: ");
        char estadoPago = leerEstadoPago();

        listaMultas.add(new Multas(placaAgente, localidad, importe, estadoPago));
        reasignarIds();
        hayCambiosSinGuardar = true;
    }

    private void borrarMulta()
    {
        if (listaMultas.isEmpty())
        {
            System.out.println("No hay registros disponibles para borrar.");
            return;
        }

        Multas multa = pedirMultaExistente("Introduzca el ID de la multa para borrar: ");
        listaMultas.remove(multa);

        System.out.println("--------REGISTRO No. " + multa.getId() + "--------");
        System.out.println("Registro borrado con éxito.");
        System.out.println();

        reasignarIds();
        hayCambiosSinGuardar = true;
    }

    private void consultar(int opcion)
    {
        if (listaMultas.isEmpty())
        {
            System.out.println("No hay registros disponibles para consultar.");
            return;
        }

        switch (opcion)
        {
            case 1 -> consultarPorId();
            case 2 -> consultarPorAgente();
            case 3 -> consultarPorLocalidad();
            case 4 ->
            {
                System.out.println("--------REGISTRO de Multas--------");
                mostrarMultas(multa -> true, "");
            }
            default -> { }
        }
    }

    private void consultarPorId()
    {
        Multas multa = pedirMultaExistente("Introduzca el ID de la multa para consultar: ");
        System.out.println("--------REGISTRO No. " + multa.getId() + "--------");
        System.out.println(multa);
        System.out.println();
    }

    private void consultarPorAgente()
    {
        int placaAgente = leerEnteroPositivo("Introduzca el número de placa del agente para consultar: ");
        System.out.println("--------REGISTRO DE MULTAS--------");
        mostrarMultas(multa -> multa.getPlacaAgente() == placaAgente, "Este policía no ha puesto ninguna multa.");
    }

    private void consultarPorLocalidad()
    {
        String localidad = leerLocalidad("Introduzca la localidad deseada: ");
        System.out.println("--------REGISTRO de Multas--------");
        mostrarMultas(multa -> multa.getLocalidad().equalsIgnoreCase(localidad), "No se ha encontrado ninguna multa con esa localidad o se ha introducido erróneamente.");
    }

    private void mostrarMultas(Predicate<Multas> filtro, String mensajeSinResultados)
    {
        List<Multas> multasFiltradas = listaMultas.stream().filter(filtro).toList();

        if (multasFiltradas.isEmpty())
            System.out.println(mensajeSinResultados);
        else
            multasFiltradas.forEach(System.out::println);

        System.out.println();
    }

    private void modificar(int opcion)
    {
        if (listaMultas.isEmpty())
        {
            System.out.println("No hay registros disponibles para modificar.");
            return;
        }

        switch (opcion)
        {
            case 1 -> cambiarLocalidad();
            case 2 -> cambiarImporte();
            case 3 -> cambiarAgente();
            case 4 -> gestionarPago(pedirMultaExistente(MENSAJE_PEDIR_ID));
            default -> { }
        }
    }

    private void cambiarLocalidad()
    {
        Multas multa = pedirMultaExistente(MENSAJE_PEDIR_ID);
        System.out.println("--------REGISTRO No. " + multa.getId() + "--------");

        multa.setLocalidad(leerLocalidad("A continuación, introduzca la localidad en donde se ha producido la amonestación: "));
        hayCambiosSinGuardar = true;
        System.out.println();
    }

    private void cambiarImporte()
    {
        Multas multa = pedirMultaExistente(MENSAJE_PEDIR_ID);
        System.out.println("--------REGISTRO No. " + multa.getId() + "--------");

        multa.setImporte(leerImportePositivo("Introduzca el nuevo coste de la multa, en €: "));
        hayCambiosSinGuardar = true;
        System.out.println();
    }

    private void cambiarAgente() {
        Multas multa = pedirMultaExistente(MENSAJE_PEDIR_ID);
        System.out.println("--------REGISTRO No. " + multa.getId() + "--------");

        multa.setPlacaAgente(leerEnteroPositivo("Introduzca el número de placa del nuevo policía a cargo: "));
        hayCambiosSinGuardar = true;
        System.out.println();
    }

    private void gestionarPago(Multas multa)
    {
        int opcion;
        do
        {
            System.out.print(MENU_PAGO);
            opcion = leerOpcion("Escoge una opción (1~3): ");

            switch (opcion)
            {
                case 1 -> cambiarEstadoPago(multa, Multas.NO_PAGADA);
                case 2 -> cambiarEstadoPago(multa, Multas.PAGADA);
                default -> { }
            }
        } while (opcion != 3);
        System.out.println();
    }

    private void cambiarEstadoPago(Multas multa, char nuevoEstado)
    {
        String descripcion = (nuevoEstado == Multas.PAGADA) ? "PAGADA" : "NO PAGADA";

        System.out.println("--------MULTA No. " + multa.getId() + "--------");
        System.out.println("El estado de la multa es: " + multa.getEstadoPago());

        if (multa.getEstadoPago() == nuevoEstado)
            System.out.println("La multa sigue en " + descripcion + ".");
        else
        {
            multa.setEstadoPago(nuevoEstado);
            hayCambiosSinGuardar = true;
            System.out.println("El estado de la multa ha cambiado a " + descripcion + ".");
        }
    }

    private Optional<Multas> buscarPorId(int id)
    {
        return listaMultas.stream().filter(multa -> multa.getId() == id).findFirst();
    }

    private Multas pedirMultaExistente(String mensaje)
    {
        while (true)
        {
            Optional<Multas> multa = buscarPorId(leerEnteroPositivo(mensaje));

            if (multa.isPresent())
                return multa.get();

            System.out.println("No existe ninguna multa con ese ID.");
        }
    }

    private void reasignarIds()
    {
        for (int indice = 0; indice < listaMultas.size(); indice++)
            listaMultas.get(indice).setId(indice + 1);
    }

    // ------------------------------------------------------------------
    // Lectura y validación de teclado
    // ------------------------------------------------------------------

    private int leerOpcion(String mensaje)
    {
        System.out.println(mensaje);
        try
        {
            return Integer.parseInt(teclado.nextLine().trim());
        }
        catch (NumberFormatException e)
        {
            return OPCION_INVALIDA;
        }
    }

    private int leerEnteroPositivo(String mensaje)
    {
        while (true)
        {
            System.out.println(mensaje);
            try
            {
                int valor = Integer.parseInt(teclado.nextLine().trim());

                if (valor > 0)
                    return valor;

            }
            catch (NumberFormatException e)
            {
                System.out.println(MENSAJE_ENTRADA_INVALIDA);
            }
        }
    }

    private double leerImportePositivo(String mensaje)
    {
        while (true)
        {
            System.out.println(mensaje);
            try
            {
                double valor = Double.parseDouble(teclado.nextLine().trim().replace(',', '.'));

                if (valor > 0)
                    return valor;
            }
            catch (NumberFormatException e)
            {
                System.out.println(MENSAJE_ENTRADA_INVALIDA);
            }
        }
    }

    private String leerLocalidad(String mensaje)
    {
        while (true)
        {
            System.out.println(mensaje);
            String texto = teclado.nextLine().trim();

            if (!texto.isEmpty() && texto.chars().noneMatch(Character::isDigit))
                return capitalizar(texto);
        }
    }

    private char leerEstadoPago()
    {
        while (true)
        {
            System.out.println("¿Multa pagada?");
            System.out.println("Escoja una (S=Sí N=No): ");
            String texto = teclado.nextLine().trim().toUpperCase();

            if (texto.equals(String.valueOf(Multas.PAGADA)) || texto.equals(String.valueOf(Multas.NO_PAGADA)))
                return texto.charAt(0);
        }
    }

    private String capitalizar(String texto)
    {
        return texto.substring(0, 1).toUpperCase() + texto.substring(1);
    }

    // ------------------------------------------------------------------
    // Persistencia
    // ------------------------------------------------------------------

    /** Sobrescribe el fichero con el contenido actual de la lista. */
    private void guardarMultas()
    {
        List<String> lineas = listaMultas.stream().map(this::convertirALinea).toList();
        try
        {
            Files.write(ficheroMultas, lineas, StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            System.err.println("Error al guardar el fichero: " + e.getMessage());
        }
    }

    private String convertirALinea(Multas multa)
    {
        return String.join(SEPARADOR_CAMPOS, String.valueOf(multa.getId()), multa.getLocalidad(), String.valueOf(multa.getImporte()), String.valueOf(multa.getPlacaAgente()), String.valueOf(multa.getEstadoPago()));
    }

    private void cargarMultas()
    {
        if (Files.notExists(ficheroMultas))
        {
            System.out.println("El fichero no existe, se creará uno nuevo.");
            try
            {
                Files.createFile(ficheroMultas);
            }
            catch (IOException e)
            {
                System.out.println("Error al crear el fichero.");
            }
            return;
        }

        try
        {
            for (String linea : Files.readAllLines(ficheroMultas, StandardCharsets.UTF_8))
            {
                if (linea.isBlank())
                    continue;
                try
                {
                    listaMultas.add(convertirAMulta(linea));
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println("Línea ignorada por formato incorrecto: " + linea);
                }
            }
            reasignarIds();
            System.out.println("Fichero cargado con éxito.");
        }
        catch (IOException e)
        {
            System.out.println("Error al cargar el fichero.");
        }
    }

    private Multas convertirAMulta(String linea)
    {
        String[] campos = linea.split(SEPARADOR_CAMPOS);

        if (campos.length < 5 || campos[4].isBlank())
            throw new IllegalArgumentException("Faltan campos en la línea");

        String localidad = campos[1];
        double importe = Double.parseDouble(campos[2].trim());
        int placaAgente = Integer.parseInt(campos[3].trim());
        char estadoPago = campos[4].trim().charAt(0);

        return new Multas(placaAgente, localidad, importe, estadoPago);
    }
}