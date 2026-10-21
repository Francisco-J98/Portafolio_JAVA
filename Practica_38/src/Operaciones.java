import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Operaciones implements AutoCloseable
{
    private static final String NOMBRE_FICHERO_TEMPORAL = "auxiliar.dat";
    private static final String MENSAJE_ID_NO_ENCONTRADO = "No se ha encontrado ningún registro con ese ID, pruebe con otro.";

    private static final String MENU_PRINCIPAL = """
            ----------MENU----------
            |1)Agregar registro.   |
            |2)Modificar registro. |
            |3)Eliminar registro.  |
            |4)Consultar registro. |
            |5)Salir.              |
            |______________________|""";

    private static final String MENU_MODIFICAR = """
            ----------MENÚ----------
            |1)Modificar talla.    |
            |2)Modificar color.    |
            |3)Salir.              |
            |______________________|""";

    private static final String MENU_CONSULTAR = """
            ---------------MENU---------------
            |1)Consultar primer registro.    |
            |2)Consultar ultimo registro.    |
            |3)Consultar cualquier registro. |
            |4)Consultar todos los registros.|
            |________________________________|""";

    private final File ficheroRegistros;
    private final Scanner teclado = new Scanner(System.in);
    private List<Ropa> prendas = new ArrayList<>();

    public Operaciones(File ficheroRegistros)
    {
        this.ficheroRegistros = ficheroRegistros;
    }

    // ------------------------------------------------------------------
    // Menú principal
    // ------------------------------------------------------------------

    public void menuPrincipal()
    {
        boolean hayCambiosPendientes = false;
        cargar();

        int opcion;
        do
        {
            opcion = leerOpcion(MENU_PRINCIPAL, "Escoge una (pulse 5 para salir): ");

            // Agregar, modificar y borrar implican cambios que habrá que guardar
            if (opcion >= 1 && opcion <= 3)
                hayCambiosPendientes = true;

            switch (opcion)
            {
                case 1 ->
                {
                    System.out.println("Ha escogido: Agregar registro.");
                    agregar();
                }
                case 2 ->
                {
                    System.out.println("Ha escogido: Modificar registro.");
                    menuModificar();
                }
                case 3 ->
                {
                    System.out.println("Ha escogido: Eliminar registro.");
                    borrar();
                }
                case 4 ->
                {
                    System.out.println("Ha escogido: Consultar registro.");
                    menuConsultar();
                }
                case 5 ->
                {
                    if (hayCambiosPendientes)
                    {
                        System.out.println("Se van a guardar los cambios en el fichero.");
                        guardar();
                        System.out.println("Cambios guardados.");
                    }
                    System.out.println("Ha salido del programa.");
                }
                default -> System.out.println("Error al recibir el dato por pantalla.");
            }
        } while (opcion != 5);
    }

    // ------------------------------------------------------------------
    // Operaciones sobre los registros
    // ------------------------------------------------------------------

    private void agregar()
    {
        int talla = leerEnteroPositivo("Introduzca el valor de la talla: ");

        System.out.println("Introduzca el color: ");
        String color = teclado.nextLine();

        prendas.add(new Ropa(talla, color));
        actualizarIds();
    }

    private void borrar()
    {
        int id = leerEnteroPositivo("Introduzca el ID para borrar: ");

        Optional<Ropa> prenda = buscarPorId(id);
        if (prenda.isPresent())
        {
            prendas.remove(prenda.get());
            System.out.println("--------REGISTRO No. " + id + "--------");
            System.out.println("Registro borrado con éxito.");
        }
        else
            System.out.println(MENSAJE_ID_NO_ENCONTRADO);

        System.out.println();
        actualizarIds();
    }

    private void menuModificar()
    {
        int opcion = leerOpcionEnRango(MENU_MODIFICAR, "Escoge una opción(1~3): ", 1, 3);
        if (opcion != 3)
            modificar(opcion);
    }

    private void modificar(int opcionModificar)
    {
        int id = leerEnteroPositivo("Introduzca el ID para modificar: ");

        Optional<Ropa> prenda = buscarPorId(id);
        if (prenda.isPresent())
        {
            switch (opcionModificar)
            {
                case 1 -> prenda.get().setTalla(leerEnteroPositivo("Introduzca el nuevo valor de la talla: "));
                case 2 ->
                {
                    System.out.println("Introduzca el nuevo color: ");
                    prenda.get().setColor(teclado.nextLine());
                }
                default -> throw new IllegalArgumentException("Opción no válida: " + opcionModificar);
            }
            System.out.println("--------REGISTRO No. " + id + "--------");
            System.out.println("Registro modificado con éxito.");
        }
        else
            System.out.println(MENSAJE_ID_NO_ENCONTRADO);

        System.out.println();
    }

    private void menuConsultar()
    {
        int opcion = leerOpcionEnRango(MENU_CONSULTAR, "Escoge una (1~4): ", 1, 4);
        consultar(opcion);
    }

    private void consultar(int opcionConsulta)
    {
        if (prendas.isEmpty())
        {
            System.out.println("No hay registros en el fichero. Añada nuevas entradas antes, por favor.");
            return;
        }

        switch (opcionConsulta)
        {
            case 1 ->
            {
                System.out.println("Ha escogido: Primer registro.");
                mostrarRegistro(prendas.get(0));
                System.out.println();
            }
            case 2 ->
            {
                System.out.println("Ha escogido: Ultimo registro.");
                mostrarRegistro(prendas.get(prendas.size() - 1));
                System.out.println();
            }
            case 3 ->
            {
                System.out.println("Ha escogido: Cualquier registro.");
                int id = leerEnteroPositivo("Introduzca el ID para consultar: ");

                Optional<Ropa> prenda = buscarPorId(id);
                if (prenda.isPresent())
                {
                    System.out.println("--------REGISTRO No. " + id + "--------");
                    mostrarRegistro(prenda.get());
                }
                else
                    System.out.println(MENSAJE_ID_NO_ENCONTRADO);
                System.out.println();
            }
            case 4 ->
            {
                for (int indice = 0; indice < prendas.size(); indice++)
                {
                    System.out.println("--------REGISTRO No. " + (indice + 1) + "--------");
                    mostrarRegistro(prendas.get(indice));
                }
            }
            default -> throw new IllegalArgumentException("Opción no válida: " + opcionConsulta);
        }
    }

    private void mostrarRegistro(Ropa prenda)
    {
        System.out.println("ID: " + prenda.getId() + " Talla: " + prenda.getTalla() + " Color: " + prenda.getColor());
    }

    private Optional<Ropa> buscarPorId(int id)
    {
        return prendas.stream().filter(prenda -> prenda.getId() == id).findFirst();
    }

    /** Reasigna los ID de forma consecutiva (1..n) según la posición en la lista. */
    private void actualizarIds()
    {
        for (int indice = 0; indice < prendas.size(); indice++)
            prendas.get(indice).setId(indice + 1);
    }

    // ------------------------------------------------------------------
    // Persistencia
    // ------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    private void cargar()
    {
        if (!ficheroRegistros.exists())
        {
            System.out.println("El fichero no existe, se creará uno nuevo.");
            return;
        }

        try (ObjectInputStream entrada = new ObjectInputStream(Files.newInputStream(ficheroRegistros.toPath())))
        {
            prendas = (List<Ropa>) entrada.readObject();
            System.out.println("Fichero cargado con éxito.");
        }
        catch (IOException | ClassNotFoundException | ClassCastException e)
        {
            System.out.println("Error al cargar el fichero.");
            e.printStackTrace();
        }
    }

    /** Escribe primero en un fichero temporal y luego lo mueve sobre el original. */
    private void guardar()
    {
        Path destino = ficheroRegistros.toPath();
        Path temporal = destino.resolveSibling(NOMBRE_FICHERO_TEMPORAL);

        try
        {
            try (ObjectOutputStream salida = new ObjectOutputStream(Files.newOutputStream(temporal)))
            {
                salida.writeObject(prendas);
            }
            Files.move(temporal, destino, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e)
        {
            System.out.println("Error al guardar el fichero.");
            e.printStackTrace();
        }
    }

    // ------------------------------------------------------------------
    // Lectura de datos por teclado
    // ------------------------------------------------------------------

    /** Lee un entero; si la entrada no es válida devuelve 0. */
    private int leerEntero(String mensaje)
    {
        System.out.println(mensaje);
        try
        {
            return Integer.parseInt(teclado.nextLine().trim());
        }
        catch (NumberFormatException e)
        {
            System.out.println();
            return 0;
        }
    }

    private int leerEnteroPositivo(String mensaje)
    {
        int valor;

        do
        {
            valor = leerEntero(mensaje);
        } while (valor <= 0);
        return valor;
    }

    private int leerOpcion(String menu, String pregunta)
    {
        System.out.println(menu);
        return leerEntero(pregunta);
    }

    private int leerOpcionEnRango(String menu, String pregunta, int minimo, int maximo)
    {
        int opcion;

        do
        {
            opcion = leerOpcion(menu, pregunta);
        } while (opcion < minimo || opcion > maximo);
        return opcion;
    }

    @Override
    public void close()
    {
        teclado.close();
    }
}