import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Principal
{
	public static void main(String[] Args)
	{
		Properties config=new Properties();
		String nombre="";
		String ruta=System.getProperty("user.home")+System.getProperty("file.separator")+"MICONFIGURACION.conf";
		try
		{
			FileInputStream F1 = new FileInputStream(ruta);
			try 
			{
				config.load(F1);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			nombre=config.getProperty("minombre");
			System.out.println("Éxito al leer el archivo .conf");
			System.out.println("El nombre guardado es: "+nombre);
			F1.close();
		}
		catch(IOException e)
		{
			System.out.println("Error al abrir el fichero.");
		}
	}
}