import java.io.File;
import java.io.IOException;

public class Principal
{
    private static final String NOMBRE_FICHERO_REGISTROS = "RegistroTiendaRAF.dat";

    public static void main(String[] argumentos)
    {
        try (Operaciones operaciones = new Operaciones(new File(NOMBRE_FICHERO_REGISTROS)))
        {
            operaciones.menuPrincipal();
        }
        catch (IOException e)
        {
            System.out.println("Error al acceder al fichero de registros: " + e.getMessage());
        }
    }
}