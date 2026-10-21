import java.nio.file.Path;

public class Principal
{
    private static final Path FICHERO_REGISTRO = Path.of("RegistroTienda.txt");

    public static void main(String[] argumentos)
    {
        Operaciones operaciones = new Operaciones(FICHERO_REGISTRO);
        operaciones.menuPrincipal();
    }
}