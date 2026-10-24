import java.io.IOException;

public class Principal
{

    public static void main(String[] args)
	{
        Operaciones operaciones = new Operaciones();
        try
		{
            operaciones.guardarAlmacenDeEjemplo();
            operaciones.mostrarIdPrimeraPrenda();
        }
		catch (IOException e)
		{
            System.err.println("Error al acceder a Tienda.xml: " + e.getMessage());
        }
    }
}