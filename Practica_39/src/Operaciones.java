import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Operaciones implements AutoCloseable
{
    /** Bytes reservados para el color (el texto se rellena con espacios hasta este tamaño). */
    private static final int TAMANO_COLOR = 18;

    /** id (int) + talla (int) + prefijo de longitud de writeUTF (short) + color. */
    private static final int TAMANO_REGISTRO = Integer.BYTES + Integer.BYTES + Short.BYTES + TAMANO_COLOR;

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
            |1)Consultar todos los registros.|
            |2)Consultar primer registro.    |
            |3)Consultar ultimo registro.    |
            |4)Consultar cualquier registro. |
            |________________________________|""";

    private final RandomAccessFile ficheroAleatorio;
    private final Scanner teclado = new Scanner(System.in);

    public Operaciones(File ficheroRegistros) throws IOException
    {
        if (!ficheroRegistros.exists())
            System.out.println("No existe el fichero, se creará uno nuevo.");

        this.ficheroAleatorio = new RandomAccessFile(ficheroRegistros, "rw");
    }

    // ------------------------------------------------------------------
    // Menú principal
    // ------------------------------------------------------------------

    public void menuPrincipal()
    {
        int opcion;
        do
        {
            opcion = leerOpcion(MENU_PRINCIPAL, "Escoge una (pulse 5 para salir): ");

            try
            {
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
                    case 5 -> System.out.println("Ha salido del programa.");
                    default -> System.out.println("Error al leer el dato por pantalla.");
                }
            }
            catch (IOException e)
            {
                System.out.println("Error al acceder al fichero: " + e.getMessage());
            }
        } while (opcion != 5);
    }

    // ------------------------------------------------------------------
    // Operaciones sobre los registros
    // ------------------------------------------------------------------

    private void agregar() throws IOException
    {
        int talla = leerEnteroPositivo("Introduzca el valor de la talla: ");
        String color = leerColor("Introduzca el color: ");

        int nuevoId = contarRegistros() + 1;
        escribirRegistro(new Ropa(nuevoId, talla, color));
        System.out.println("Se han guardado los datos en el fichero.");
    }

    private void borrar() throws IOException
    {
        int totalRegistros = contarRegistros();
        if (totalRegistros == 0)
        {
            System.out.println("El fichero está vacío, no hay datos para borrar.");
            return;
        }

        int id = leerEnteroPositivo("Introduzca el ID para borrar: ");
        if (!existeId(id))
        {
            System.out.println("El registro indicado no existe o ya se ha borrado.");
            return;
        }

        // Desplaza una posición hacia atrás los registros posteriores, renumerando su ID
        for (int idPosterior = id + 1; idPosterior <= totalRegistros; idPosterior++)
        {
            Ropa prenda = leerRegistro(idPosterior);
            escribirRegistro(new Ropa(idPosterior - 1, prenda.getTalla(), prenda.getColor()));
        }

        ficheroAleatorio.setLength((long) (totalRegistros - 1) * TAMANO_REGISTRO);
        System.out.println("Registro borrado con éxito.");
    }

    private void menuModificar() throws IOException
    {
        if (contarRegistros() == 0)
        {
            System.out.println("No hay registros para modificar porque el fichero está vacío.");
            return;
        }

        int opcion = leerOpcionEnRango(MENU_MODIFICAR, "Escoge una opción(1~3): ", 1, 3);

        if (opcion != 3)
            modificar(opcion);
    }

    private void modificar(int opcionModificar) throws IOException
    {
        int id = leerEnteroPositivo("Introduzca ID que desea buscar: ");
        if (!existeId(id))
        {
            System.out.println("No se ha encontrado el registro para modificarlo.");
            return;
        }

        Ropa prenda = leerRegistro(id);
        switch (opcionModificar)
        {
            case 1 -> prenda.setTalla(leerEnteroPositivo("Introduce el nuevo valor de la talla: "));
            case 2 -> prenda.setColor(leerColor("Introduce nuevo color: "));
            default -> throw new IllegalArgumentException("Opción no válida: " + opcionModificar);
        }
        escribirRegistro(prenda);
        System.out.println("Registro modificado con éxito.");
    }

    private void menuConsultar() throws IOException
    {
        if (contarRegistros() == 0)
        {
            System.out.println("El fichero está vacío, introduzca datos antes para poder consultarlos.");
            return;
        }

        int opcion = leerOpcionEnRango(MENU_CONSULTAR, "Escoge una (1~4): ", 1, 4);
        consultar(opcion);
    }

    private void consultar(int opcionConsulta) throws IOException
    {
        int totalRegistros = contarRegistros();

        switch (opcionConsulta)
        {
            case 1 ->
            {
                for (int id = 1; id <= totalRegistros; id++)
                    mostrarRegistro(leerRegistro(id));
            }
            case 2 -> mostrarRegistro(leerRegistro(1));
            case 3 -> mostrarRegistro(leerRegistro(totalRegistros));
            case 4 ->
            {
                int id = leerEnteroPositivo("Introduzca ID que desea buscar: ");
                if (existeId(id))
                    mostrarRegistro(leerRegistro(id));
                else
                    System.out.println("No se ha encontrado el registro.");
            }
            default -> throw new IllegalArgumentException("Opción no válida: " + opcionConsulta);
        }
    }

    private void mostrarRegistro(Ropa prenda)
    {
        System.out.println(prenda.getId() + " " + prenda.getTalla() + " " + prenda.getColor());
    }

    // ------------------------------------------------------------------
    // Acceso al fichero (registros de tamaño fijo: el ID n está en la posición (n-1) * TAMANO_REGISTRO)
    // ------------------------------------------------------------------

    private int contarRegistros() throws IOException
    {
        return (int) (ficheroAleatorio.length() / TAMANO_REGISTRO);
    }

    private boolean existeId(int id) throws IOException
    {
        return id >= 1 && id <= contarRegistros();
    }

    private Ropa leerRegistro(int id) throws IOException
    {
        ficheroAleatorio.seek((long) (id - 1) * TAMANO_REGISTRO);
        int idLeido = ficheroAleatorio.readInt();
        int talla = ficheroAleatorio.readInt();
        String color = ficheroAleatorio.readUTF().stripTrailing();
        return new Ropa(idLeido, talla, color);
    }

    private void escribirRegistro(Ropa prenda) throws IOException
    {
        ficheroAleatorio.seek((long) (prenda.getId() - 1) * TAMANO_REGISTRO);
        ficheroAleatorio.writeInt(prenda.getId());
        ficheroAleatorio.writeInt(prenda.getTalla());
        ficheroAleatorio.writeUTF(rellenarColor(prenda.getColor()));
    }

    /** Completa el color con espacios para que siempre ocupe TAMANO_COLOR bytes en el fichero. */
    private String rellenarColor(String color)
    {
        return color + " ".repeat(TAMANO_COLOR - longitudUtf(color));
    }

    /** Bytes que ocupará el texto al escribirlo con writeUTF (UTF-8 modificado). */
    private static int longitudUtf(String texto)
    {
        return texto.chars().map(caracter -> caracter >= 0x0001 && caracter <= 0x007F ? 1 : caracter <= 0x07FF ? 2 : 3).sum();
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

    private String leerColor(String mensaje)
    {
        while (true)
        {
            System.out.println(mensaje);
            String color = teclado.nextLine();

            if (longitudUtf(color) <= TAMANO_COLOR)
                return color;

            System.out.println("El color es demasiado largo (máximo " + TAMANO_COLOR + " caracteres).");
        }
    }

    @Override
    public void close() throws IOException
    {
        try
        {
            ficheroAleatorio.close();
        }
        finally
        {
            teclado.close();
        }
    }
}