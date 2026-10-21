import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class EscrituraBuffer
{
    private static final String NOMBRE_FICHERO = "holamundo.txt";
    private static final String MENSAJE = "hola mundo";

    public void escribir()
    {
        Path rutaFichero = Path.of(System.getProperty("user.home"), "Desktop", NOMBRE_FICHERO);
        System.out.println("El mensaje a introducir va a ser '" + MENSAJE + "'.");

        try (BufferedWriter escritor = Files.newBufferedWriter(rutaFichero, StandardCharsets.UTF_8))
        {
            escritor.write(MENSAJE);
            escritor.newLine();
        }
        catch (IOException e)
        {
            System.out.println("Error al guardar/escribir el fichero: " + e.getMessage());
        }
    }
}