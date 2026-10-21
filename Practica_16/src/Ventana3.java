import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Ventana3
{
    private static final int CANTIDAD_NUMEROS = 6;
    private static final int NUMERO_MINIMO = 1;
    private static final int NUMERO_MAXIMO = 49;

    // Posición horizontal de cada casilla; la última es la del complementario
    private static final int[] POSICIONES_X = {60, 105, 149, 192, 238, 338};
    private static final int POSICION_Y_CAMPOS = 91;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(Ventana3::crearVentana);
    }

    private static void crearVentana()
    {
        JFrame ventana = new JFrame("Ejercicio 3");
        ventana.setSize(500, 370);
        ventana.setLocation(300, 200);

        JLayeredPane panelCapas = new JLayeredPane();
        ventana.getContentPane().add(panelCapas, BorderLayout.CENTER);

        JTextField[] camposNumero = crearCamposNumero(panelCapas);

        JLabel etiquetaComplementario = new JLabel("Complementario:");
        etiquetaComplementario.setBounds(327, 54, 111, 14);
        panelCapas.add(etiquetaComplementario);

        JButton botonGenerar = new JButton("Generar combinación");
        botonGenerar.setBounds(192, 169, 173, 23);
        botonGenerar.addActionListener(evento -> mostrarCombinacion(camposNumero));
        panelCapas.add(botonGenerar);

        ventana.setVisible(true);
    }

    private static JTextField[] crearCamposNumero(JLayeredPane panel)
    {
        JTextField[] campos = new JTextField[CANTIDAD_NUMEROS];

        for (int i = 0; i < CANTIDAD_NUMEROS; i++)
        {
            campos[i] = new JTextField();
            campos[i].setBounds(POSICIONES_X[i], POSICION_Y_CAMPOS, 35, 20);
            panel.add(campos[i]);
        }
        return campos;
    }

    private static void mostrarCombinacion(JTextField[] campos)
    {
        int[] combinacion = generarCombinacion();

        Arrays.sort(combinacion);

        for (int i = 0; i < campos.length; i++)
            campos[i].setText(String.valueOf(combinacion[i]));
    }

    private static int[] generarCombinacion()
    {
        // El límite superior de ints() es exclusivo, por eso se suma 1
        return ThreadLocalRandom.current().ints(NUMERO_MINIMO, NUMERO_MAXIMO + 1).distinct().limit(CANTIDAD_NUMEROS).toArray();
    }
}