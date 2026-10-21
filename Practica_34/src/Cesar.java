/** Cifrado César sobre las letras A-Z. El resto de caracteres se conserva tal cual. */

public final class Cesar
{
    private static final int LETRAS_ALFABETO = 26;

    private Cesar()
    {
        // Clase de utilidades: no se instancia
    }

    public static String cifrar(String mensaje, int desplazamiento)
    {
        return desplazar(mensaje, desplazamiento);
    }

    public static String descifrar(String mensaje, int desplazamiento)
    {
        return desplazar(mensaje, -desplazamiento);
    }

    private static String desplazar(String mensaje, int desplazamiento)
    {
        StringBuilder resultado = new StringBuilder(mensaje.length());

        for (char caracter : mensaje.toCharArray())
        {
            if (caracter >= 'A' && caracter <= 'Z')
            {
                int posicion = Math.floorMod(caracter - 'A' + desplazamiento, LETRAS_ALFABETO);
                resultado.append((char) ('A' + posicion));
            }
            else
                resultado.append(caracter);
        }
        return resultado.toString();
    }
}