import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Ventana4
{
    private static final String MENSAJE_CORRECTO = "Correcto";
    private static final String MENSAJE_ERRONEO = "Erróneo";
    private static final String MENSAJE_SIN_PLATO = "No se ha seleccionado el plato.";

    // Ingredientes en el orden en que se colocan en la cuadrícula (por columnas, 3 filas)
    private static final String[] INGREDIENTES = {
            "Lentejas", "Chorizo", "Cebolla",
            "Garbanzos", "Morcilla", "Zanahoria",
            "Judías", "Colorante", "Pescado",
            "Nuez Moscada", "Laurel", "Pimentón"
    };

    // Receta de cada plato: conjunto exacto de ingredientes que debe estar marcado
    private static final Map<String, Set<String>> RECETAS = new LinkedHashMap<>();

    static
    {
        RECETAS.put("Cocido", Set.of("Chorizo", "Morcilla", "Garbanzos", "Zanahoria"));
        RECETAS.put("Lentejas", Set.of("Chorizo", "Zanahoria", "Cebolla", "Lentejas", "Pimentón"));
        RECETAS.put("Judías", Set.of("Chorizo", "Cebolla", "Judías", "Colorante", "Nuez Moscada", "Laurel"));
    }

    private static final int[] COLUMNAS_X = {18, 138, 251, 365};
    private static final int FILA_INICIAL_Y = 81;
    private static final int SEPARACION_FILAS = 26;
    private static final int FILAS_POR_COLUMNA = 3;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(Ventana4::crearVentana);
    }

    private static void crearVentana()
    {
        JFrame ventana = new JFrame("Chef 4000");
        ventana.setSize(500, 370);
        ventana.setLocation(300, 200);

        JLayeredPane panelCapas = new JLayeredPane();
        ventana.getContentPane().add(panelCapas, BorderLayout.CENTER);

        Map<String, JRadioButton> botonesPlato = crearBotonesPlato(panelCapas);
        Map<String, JCheckBox> casillasIngrediente = crearCasillasIngrediente(panelCapas);

        JLabel etiquetaMensaje = new JLabel("");
        etiquetaMensaje.setBounds(122, 245, 237, 29);
        panelCapas.add(etiquetaMensaje);

        JButton botonComprobar = new JButton("Comprobar");
        botonComprobar.setBounds(183, 211, 125, 23);
        botonComprobar.addActionListener(evento -> etiquetaMensaje.setText(evaluarSeleccion(botonesPlato, casillasIngrediente)));
        panelCapas.add(botonComprobar);

        ventana.setVisible(true);
    }

    private static Map<String, JRadioButton> crearBotonesPlato(JLayeredPane panel)
    {
        Map<String, JRadioButton> botones = new LinkedHashMap<>();
        // ButtonGroup garantiza la exclusión mutua sin listeners manuales
        ButtonGroup grupo = new ButtonGroup();
        int posicionX = 70;

        for (String plato : RECETAS.keySet())
        {
            JRadioButton boton = new JRadioButton(plato);
            boton.setBounds(posicionX, 20, 109, 23);
            grupo.add(boton);
            panel.add(boton);
            botones.put(plato, boton);
            posicionX += 129;
        }
        return botones;
    }

    private static Map<String, JCheckBox> crearCasillasIngrediente(JLayeredPane panel)
    {
        Map<String, JCheckBox> casillas = new LinkedHashMap<>();

        for (int i = 0; i < INGREDIENTES.length; i++)
        {
            JCheckBox casilla = new JCheckBox(INGREDIENTES[i]);
            int posicionX = COLUMNAS_X[i / FILAS_POR_COLUMNA];
            int posicionY = FILA_INICIAL_Y + SEPARACION_FILAS * (i % FILAS_POR_COLUMNA);
            casilla.setBounds(posicionX, posicionY, 113, 23);
            panel.add(casilla);
            casillas.put(INGREDIENTES[i], casilla);
        }
        return casillas;
    }

    private static String evaluarSeleccion(Map<String, JRadioButton> botonesPlato, Map<String, JCheckBox> casillasIngrediente)
    {
        // Aunque los ingredientes sean correctos, sin plato elegido se considera error
        String platoElegido = botonesPlato.entrySet().stream().filter(entrada -> entrada.getValue().isSelected()).map(Map.Entry::getKey).findFirst().orElse(null);

        if (platoElegido == null)
            return MENSAJE_SIN_PLATO;

        Set<String> ingredientesMarcados = casillasIngrediente.entrySet().stream().filter(entrada -> entrada.getValue().isSelected()).map(Map.Entry::getKey).collect(Collectors.toSet());
        return RECETAS.get(platoElegido).equals(ingredientesMarcados) ? MENSAJE_CORRECTO : MENSAJE_ERRONEO;
    }
}