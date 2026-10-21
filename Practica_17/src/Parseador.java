import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/** Carga un XML de contactos una sola vez y permite consultar el teléfono por nombre. */
public class Parseador
{

    private static final String ETIQUETA_CONTACTO = "contacto";
    private static final String ATRIBUTO_NOMBRE = "nombre";

    private final Map<String, String> telefonosPorNombre = new HashMap<>();

    public Parseador(Path ruta) throws IOException, SAXException, ParserConfigurationException
    {
        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
        fabrica.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        try (InputStream entrada = Files.newInputStream(ruta))
        {
            Element raiz = fabrica.newDocumentBuilder().parse(entrada).getDocumentElement();
            cargarContactos(raiz);
        }
    }

    private void cargarContactos(Element raiz)
    {
        NodeList hijos = raiz.getChildNodes();
        for (int posicion = 0; posicion < hijos.getLength(); posicion++)
        {
            if (hijos.item(posicion) instanceof Element contacto && ETIQUETA_CONTACTO.equals(contacto.getTagName()) && contacto.hasAttribute(ATRIBUTO_NOMBRE))
                telefonosPorNombre.put(contacto.getAttribute(ATRIBUTO_NOMBRE), contacto.getTextContent().strip());
        }
    }

    /** Devuelve el teléfono del contacto, o vacío si no existe. */
    public Optional<String> buscarTelefono(String nombre)
    {
        return Optional.ofNullable(telefonosPorNombre.get(nombre));
    }
}