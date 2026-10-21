import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Operaciones
{
    private static final String SEPARADOR_CAMPOS = ";";
    private static final int CAMPOS_POR_REGISTRO = 3;
    private static final int VALOR_INVALIDO = 0;

    private static final int OPCION_AGREGAR = 1;
    private static final int OPCION_MODIFICAR = 2;
    private static final int OPCION_ELIMINAR = 3;
    private static final int OPCION_CONSULTAR = 4;
    private static final int OPCION_SALIR = 5;

    private static final int CONSULTA_PRIMERO = 1;
    private static final int CONSULTA_ULTIMO = 2;
    private static final int CONSULTA_POR_ID = 3;

    private static final String MENSAJE_ID_NO_ENCONTRADO = "No se ha encontrado ningún registro con ese ID, pruebe con otro.";

    private final Path fichero;
    private final Scanner teclado = new Scanner(System.in);
    private final List<Ropa> registros = new ArrayList<>();

    public Operaciones(Path fichero)
    {
        this.fichero = fichero;
    }

    public void menuPrincipal()
    {
        cargar();
        boolean hayCambios = false;
        int opcion;

        do
        {
            mostrarMenuPrincipal();
            opcion = leerEntero();

            switch (opcion)
            {
                case OPCION_AGREGAR ->
                {
                    System.out.println("Ha escogido: Agregar registro.");
                    agregar();
                    hayCambios = true;
                }
                case OPCION_MODIFICAR ->
                {
                    System.out.println("Ha escogido: Modificar registro.");
                    modificar();
                    hayCambios = true;
                }
                case OPCION_ELIMINAR ->
                {
                    System.out.println("Ha escogido: Eliminar registro.");
                    borrar();
                    hayCambios = true;
                }
                case OPCION_CONSULTAR ->
                {
                    System.out.println("Ha escogido: Consultar registro.");
                    menuConsultar();
                }
                case OPCION_SALIR -> salir(hayCambios);
                default -> System.out.println("Error al recibir el dato por pantalla.");
            }
        } while (opcion != OPCION_SALIR);
    }

    private void mostrarMenuPrincipal()
    {
        System.out.println("----------MENU----------");
        System.out.println("|1)Agregar registro.   |");
        System.out.println("|2)Modificar registro. |");
        System.out.println("|3)Eliminar registro.  |");
        System.out.println("|4)Consultar registro. |");
        System.out.println("|5)Salir.              |");
        System.out.println("|______________________|");
        System.out.println("Escoge una (pulse 5 para salir): ");
    }

    private void salir(boolean hayCambios)
    {
        if (hayCambios)
        {
            System.out.println("Se van a guardar los cambios en el fichero.");
            guardar();
        }
        System.out.println("Ha salido del programa.");
    }

    private void agregar()
    {
        int talla = pedirEnteroPositivo("Introduzca el valor de la talla: ");

        System.out.println("Introduzca el color: ");
        String color = teclado.nextLine();

        registros.add(new Ropa(talla, color));
        renumerarRegistros();
    }

    private void modificar()
    {
        int id = pedirEnteroPositivo("Introduzca el ID para modificar: ");
        Optional<Ropa> registro = buscarPorId(id);

        if (registro.isPresent())
        {
            int nuevaTalla = pedirEnteroPositivo("Introduzca el nuevo valor de la talla: ");

            System.out.println("Introduzca el nuevo color: ");
            String nuevoColor = teclado.nextLine();

            registro.get().setTalla(nuevaTalla);
            registro.get().setColor(nuevoColor);

            System.out.println("--------REGISTRO No. " + id + "--------");
            System.out.println("Registro modificado con éxito.");
        }
        else
            System.out.println(MENSAJE_ID_NO_ENCONTRADO);
        System.out.println();
    }

    private void borrar()
    {
        int id = pedirEnteroPositivo("Introduzca el ID para borrar: ");

        if (registros.removeIf(registro -> registro.getId() == id))
        {
            System.out.println("--------REGISTRO No. " + id + "--------");
            System.out.println("Registro borrado con éxito.");
            renumerarRegistros();
        }
        else
            System.out.println(MENSAJE_ID_NO_ENCONTRADO);
        System.out.println();
    }

    private void menuConsultar()
    {
        int opcion;

        do
        {
            System.out.println("---------------MENU---------------");
            System.out.println("|1)Consultar primer registro.    |");
            System.out.println("|2)Consultar ultimo registro.    |");
            System.out.println("|3)Consultar cualquier registro. |");
            System.out.println("|________________________________|");
            System.out.println("Escoge una (1~3): ");
            opcion = leerEntero();
        } while (opcion < CONSULTA_PRIMERO || opcion > CONSULTA_POR_ID);

        consultar(opcion);
    }

    private void consultar(int tipoConsulta)
    {
        if (registros.isEmpty())
        {
            System.out.println("No hay registros en el fichero. Añada nuevas entradas antes, por favor.");
            return;
        }

        switch (tipoConsulta)
        {
            case CONSULTA_PRIMERO ->
            {
                System.out.println("Ha escogido: Primer registro.");
                System.out.println(registros.get(0));
            }
            case CONSULTA_ULTIMO ->
            {
                System.out.println("Ha escogido: Ultimo registro.");
                System.out.println(registros.get(registros.size() - 1));
            }
            default -> consultarPorId();
        }
        System.out.println();
    }

    private void consultarPorId()
    {
        System.out.println("Ha escogido: Cualquier registro.");
        int id = pedirEnteroPositivo("Introduzca el ID para consultar: ");

        Optional<Ropa> registro = buscarPorId(id);
        if (registro.isPresent())
        {
            System.out.println("--------REGISTRO No. " + id + "--------");
            System.out.println(registro.get());
        }
        else
            System.out.println(MENSAJE_ID_NO_ENCONTRADO);
    }

    private void cargar()
    {
        if (!Files.exists(fichero))
        {
            System.out.println("El fichero no existe, se creará uno nuevo.");
            try
            {
                Files.createFile(fichero);
            }
            catch (IOException excepcion)
            {
                System.err.println("Error al crear el fichero: " + excepcion.getMessage());
            }
            return;
        }

        try
        {
            for (String linea : Files.readAllLines(fichero))
            {
                if (!linea.isBlank())
                    cargarLinea(linea);
            }
            renumerarRegistros();
            System.out.println("Fichero cargado con éxito.");
        }
        catch (IOException excepcion)
        {
            System.err.println("Error al cargar el fichero: " + excepcion.getMessage());
        }
    }

    private void cargarLinea(String linea)
    {
        String[] campos = linea.split(SEPARADOR_CAMPOS, CAMPOS_POR_REGISTRO);

        try
        {
            if (campos.length != CAMPOS_POR_REGISTRO)
                throw new NumberFormatException("faltan campos");

            registros.add(new Ropa(Integer.parseInt(campos[1].trim()), campos[2]));
        }
        catch (NumberFormatException excepcion)
        {
            System.err.println("Línea ignorada por formato incorrecto: " + linea);
        }
    }

    private void guardar()
    {
        List<String> lineas = registros.stream().map(registro -> registro.getId() + SEPARADOR_CAMPOS + registro.getTalla() + SEPARADOR_CAMPOS + registro.getColor()).collect(Collectors.toList());

        try
        {
            Files.write(fichero, lineas);
            System.out.println("Cambios guardados.");
        }
        catch (IOException excepcion)
        {
            System.err.println("Error al guardar el fichero: " + excepcion.getMessage());
        }
    }

    private void renumerarRegistros()
    {
        for (int posicion = 0; posicion < registros.size(); posicion++)
            registros.get(posicion).setId(posicion + 1);
    }

    private Optional<Ropa> buscarPorId(int id)
    {
        return registros.stream().filter(registro -> registro.getId() == id).findFirst();
    }

    /** Lee una línea y la convierte a entero; devuelve VALOR_INVALIDO si no es un número. */
    private int leerEntero()
    {
        try
        {
            return Integer.parseInt(teclado.nextLine().trim());
        }
        catch (NumberFormatException excepcion)
        {
            return VALOR_INVALIDO;
        }
    }

    private int pedirEnteroPositivo(String mensaje)
    {
        int valor;
        do
        {
            System.out.println(mensaje);
            valor = leerEntero();
        } while (valor <= 0);
        return valor;
    }
}