import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

final class Recursos
{

    private static final String RUTA_ICONO = "/Logo.png";

    private Recursos()
    {
    }

    /**
     * @return el icono de la aplicación, o null si no se encuentra el archivo
     */
    static Image cargarIcono()
    {
        URL url = Recursos.class.getResource(RUTA_ICONO);
        return url == null ? null : Toolkit.getDefaultToolkit().getImage(url);
    }
}