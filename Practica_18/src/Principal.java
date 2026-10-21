import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Principal
{

    private static final String RUTA_CONTACTOS = "contactos.xml";
    private static final String RESPUESTA_SALIR = "1";

    public static void main(String[] args)
    {
        Parseador parseador;

        try
        {
            parseador = new Parseador(Path.of(RUTA_CONTACTOS));
        }
        catch (IOException | SAXException | ParserConfigurationException e)
        {
            System.err.println("Error al leer " + RUTA_CONTACTOS + ": " + e.getMessage());
            return;
        }

        try (Scanner teclado = new Scanner(System.in))
        {
            String respuesta;

            do
            {
                System.out.println("Introduce un nombre: ");
                String nombre = teclado.nextLine().strip();

                parseador.buscarTelefono(nombre).ifPresentOrElse(telefono -> System.out.println(nombre + ": " + telefono), () -> System.out.println("No se encuentra o se ha introducido mal el nombre."));

                System.out.println("¿Continuar? (1 para salir): ");
                respuesta = teclado.nextLine().strip();
            } while (!respuesta.equals(RESPUESTA_SALIR));
        }
    }
}