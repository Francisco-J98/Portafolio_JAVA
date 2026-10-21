import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Operando
{
    private static final String SEPARADOR_CAMPOS = "=";
    private static final String CLAVE_COMPRA = "Compra";
    private static final String CLAVE_VENTA = "Venta";

    public static void main(String[] argumentos)
    {
        if (argumentos.length < 1)
        {
            System.err.println("Debe indicar el nombre del fichero a procesar.");
            System.exit(1);
        }

        Path rutaFichero = Path.of("..", argumentos[0]);

        try
        {
            int beneficio = calcularBeneficio(rutaFichero);
            System.out.println("Los beneficios son:" + beneficio);
        }
        catch (IOException | NumberFormatException excepcion)
        {
            System.err.println("No se pudo procesar " + rutaFichero + ": " + excepcion.getMessage());
            System.exit(1);
        }
    }

    private static int calcularBeneficio(Path rutaFichero) throws IOException
    {
        int totalCompras = 0;
        int totalVentas = 0;

        try (BufferedReader lector = Files.newBufferedReader(rutaFichero))
        {
            String linea;
            while ((linea = lector.readLine()) != null)
            {
                String[] campos = linea.split(SEPARADOR_CAMPOS, 2);

                if (campos.length != 2)
                    continue;

                int importe = Integer.parseInt(campos[1].trim());

                if (CLAVE_COMPRA.equals(campos[0]))
                    totalCompras += importe;
                else if (CLAVE_VENTA.equals(campos[0]))
                    totalVentas += importe;
            }
        }

        return totalVentas - totalCompras;
    }
}