import java.util.Locale;

public final class Cesar
{
    public static final String ABECEDARIO = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789";

    private Cesar()
    {
        // Clase de utilidades: no se instancia
    }

    public static String cifrar(String mensaje, int clave)
    {
        return desplazar(mensaje, clave);
    }

    public static String descifrar(String mensaje, int clave)
    {
        return desplazar(mensaje, -clave);
    }

    /** Desplaza cada carácter del alfabeto; los que no pertenecen a él (espacios, signos...) se dejan igual. */
    private static String desplazar(String mensaje, int desplazamiento)
    {
        StringBuilder resultado = new StringBuilder(mensaje.length());

        for (char caracter : mensaje.toUpperCase(Locale.ROOT).toCharArray())
        {
            int posicion = ABECEDARIO.indexOf(caracter);

            if (posicion == -1)
                resultado.append(caracter);
            else
            {
                int posicionNueva = Math.floorMod(posicion + desplazamiento, ABECEDARIO.length());
                resultado.append(ABECEDARIO.charAt(posicionNueva));
            }
        }
        return resultado.toString();
    }
}