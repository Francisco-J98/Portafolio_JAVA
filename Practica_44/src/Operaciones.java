import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Operaciones
{
    private static final Path RUTA_TIENDA = Path.of("Tienda.xml");

    public void guardarAlmacenDeEjemplo() throws IOException
	{
        Almacen almacen = new Almacen();
        almacen.getPrendas().addAll(List.of( new Ropa(1, 5, "Verde"), new Ropa(2, 10, "Azul"), new Ropa(3, 15, "Rojo")));
        guardarAlmacen(almacen, RUTA_TIENDA);
    }

    public void mostrarIdPrimeraPrenda() throws IOException
	{
        Almacen almacen = leerAlmacen(RUTA_TIENDA);
        almacen.getPrendas().stream().findFirst().ifPresentOrElse(primeraPrenda -> System.out.println(primeraPrenda.getId()),() -> System.out.println("El almacén no contiene prendas."));
    }

    private void guardarAlmacen(Almacen almacen, Path ruta) throws IOException
	{
        XStream xstream = crearXStream();
        try (OutputStream salida = Files.newOutputStream(ruta))
		{
            xstream.toXML(almacen, salida);
        }
    }

    private Almacen leerAlmacen(Path ruta) throws IOException
	{
        XStream xstream = crearXStream();
        try (InputStream entrada = Files.newInputStream(ruta))
		{
            return (Almacen) xstream.fromXML(entrada);
        }
    }

    private XStream crearXStream()
	{
        XStream xstream = new XStream(new DomDriver());
        xstream.allowTypes(new Class<?>[]{Almacen.class, Ropa.class});
        xstream.alias("almacen", Almacen.class);
        xstream.alias("ropa", Ropa.class);
        xstream.addImplicitCollection(Almacen.class, "prendas");
        return xstream;
    }
}