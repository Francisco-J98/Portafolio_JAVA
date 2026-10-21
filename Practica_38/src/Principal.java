import java.io.File;

public class Principal
{
    private static final String NOMBRE_FICHERO_REGISTROS = "RegistroTienda.dat";

    public static void main(String[] argumentos)
    {
        try (Operaciones operaciones = new Operaciones(new File(NOMBRE_FICHERO_REGISTROS)))
        {
            operaciones.menuPrincipal();
        }
    }
}