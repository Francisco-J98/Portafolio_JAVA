import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Parámetros de conexión a Oracle, leídos de un fichero .conf (formato Properties).
 */
public class ConfiguracionOracle
{

    private final String ip;
    private final int puerto;
    private final String servicio;
    private final String usuario;
    private final String contrasena;

    public ConfiguracionOracle(String ip, int puerto, String servicio, String usuario, String contrasena)
	{
        this.ip = ip;
        this.puerto = puerto;
        this.servicio = servicio;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    /**
     * @throws IOException              si no se puede leer el fichero
     * @throws IllegalArgumentException si falta alguna propiedad o el puerto no es un número
     */
    public static ConfiguracionOracle cargarDesde(Path ruta) throws IOException
	{
        Properties propiedades = new Properties();
        
		try (InputStream entrada = Files.newInputStream(ruta))
		{
            propiedades.load(entrada);
        }
		
        return new ConfiguracionOracle(leerObligatoria(propiedades, "IP"), Integer.parseInt(leerObligatoria(propiedades, "puerto").trim()), leerObligatoria(propiedades, "ServiceName"), leerObligatoria(propiedades, "Usuario"), leerObligatoria(propiedades, "Contrasena"));
    }

    private static String leerObligatoria(Properties propiedades, String clave)
	{
        String valor = propiedades.getProperty(clave);
        if (valor == null)
            throw new IllegalArgumentException("Falta la propiedad '" + clave + "' en el fichero de configuración");
		
        return valor;
    }

    /** Ejemplo: jdbc:oracle:thin:@10.0.2.15:1521:XE */
    public String construirUrlJdbc()
	{
        return "jdbc:oracle:thin:@" + ip + ":" + puerto + ":" + servicio;
    }

    public String getUsuario()
	{
        return usuario;
    }

    public String getContrasena()
	{
        return contrasena;
    }
}