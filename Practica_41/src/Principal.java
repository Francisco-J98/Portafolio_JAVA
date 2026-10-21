import java.nio.file.Path;

public class Principal
{
    public static void main(String[] args)
    {
        Path rutaFichero = Path.of(System.getProperty("user.home"), "ListaMultasDGT.txt");
        new Operaciones(rutaFichero).menuGeneral();
    }
}