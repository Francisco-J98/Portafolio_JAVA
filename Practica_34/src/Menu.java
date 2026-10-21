import java.util.Scanner;

public class Menu
{
    private static final int OPCION_CIFRAR = 1;
    private static final int OPCION_DESCIFRAR = 2;
    private static final int OPCION_SALIR = 3;
    private static final int DESPLAZAMIENTO_MINIMO = 1;
    private static final int DESPLAZAMIENTO_MAXIMO = 26;

    private final Scanner teclado;

    public Menu(Scanner teclado)
    {
        this.teclado = teclado;
    }

    public void ejecutar()
    {
        int opcion;

        do
        {
            mostrarMenu();
            opcion = leerEnteroEnRango("Escoge una opción (1~3): ", OPCION_CIFRAR, OPCION_SALIR);

            switch (opcion)
            {
                case OPCION_CIFRAR ->
                {
                    String mensaje = leerMensaje();
                    int desplazamiento = leerDesplazamiento();
                    System.out.println("El mensaje cifrado es: " + Cesar.cifrar(mensaje, desplazamiento));
                }
                case OPCION_DESCIFRAR ->
                {
                    String mensaje = leerMensaje();
                    int desplazamiento = leerDesplazamiento();
                    System.out.println("El mensaje descifrado es: " + Cesar.descifrar(mensaje, desplazamiento));
                }
                case OPCION_SALIR -> System.out.println("Has salido del programa.");
            }
        } while (opcion != OPCION_SALIR);
    }

    private void mostrarMenu()
    {
        System.out.println("""
                ---CIFRADO CÉSAR---
                |1) CIFRAR        |
                |2) DESCIFRAR     |
                |3) SALIR         |
                |_________________|""");
    }

    /** Pide el mensaje, lo pasa a mayúsculas y le quita las tildes de las vocales. */
    private String leerMensaje()
    {
        System.out.println("Introduce el mensaje: ");
        return teclado.nextLine()
                .toUpperCase()
                .replace('Á', 'A')
                .replace('É', 'E')
                .replace('Í', 'I')
                .replace('Ó', 'O')
                .replace('Ú', 'U');
    }

    private int leerDesplazamiento()
    {
        return leerEnteroEnRango("A continuación, la constante de desplazamiento: ",
                DESPLAZAMIENTO_MINIMO, DESPLAZAMIENTO_MAXIMO);
    }

    /** Pide un entero hasta que esté dentro del rango [minimo, maximo]. */
    private int leerEnteroEnRango(String mensaje, int minimo, int maximo)
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