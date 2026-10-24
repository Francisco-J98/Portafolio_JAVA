import java.io.IOException;

public class Principal
{
    public static void main(String[] args)
	{
        Operaciones operaciones = new Operaciones();
        try
		{
            operaciones.mostrarMediasEstacion();
        }
		catch (IOException e)
		{
            System.err.println("No se pudo leer registros.xml: " + e.getMessage());
        }
    }
}