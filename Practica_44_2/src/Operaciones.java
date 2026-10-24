import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.OptionalDouble;

public class Operaciones
{
    private static final Path RUTA_REGISTROS = Path.of("registros.xml");

    public void mostrarMediasEstacion() throws IOException
	{
        Estacion estacion = leerEstacion(RUTA_REGISTROS);
        List<Registro> registros = estacion.getRegistros();

        if (registros.isEmpty())
		{
            System.out.println("No hay registros para calcular medias.");
            return;
        }

        double temperaturaMedia = calcularMedia(registros, Registro::getTemperatura);
        double humedadMedia = calcularMedia(registros, Registro::getHumedad);

        System.out.printf("Temperatura media: %.2f%n", temperaturaMedia);
        System.out.printf("Humedad media: %.2f%n", humedadMedia);
    }

    private Estacion leerEstacion(Path ruta) throws IOException
	{
        XStream xstream = crearXStream();
        try (InputStream entrada = Files.newInputStream(ruta))
		{
            return (Estacion) xstream.fromXML(entrada);
        }
    }

    private XStream crearXStream()
	{
        XStream xstream = new XStream(new DomDriver());
        xstream.allowTypes(new Class<?>[]{Estacion.class, Registro.class});
        xstream.alias("estacion", Estacion.class);
        xstream.alias("registro", Registro.class);
        xstream.addImplicitCollection(Estacion.class, "registros");
        return xstream;
    }

    private double calcularMedia(List<Registro> registros, java.util.function.ToIntFunction<Registro> extractor)
	{
        OptionalDouble media = registros.stream().mapToInt(extractor).average();
        return media.orElse(0.0);
    }
}