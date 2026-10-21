import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

public class Ventana2 {

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(Ventana2::crearVentana);
    }

    private static void crearVentana()
    {
        JFrame ventana = new JFrame("Ejercicio2");
        ventana.setSize(500, 370);
        ventana.setLocation(300, 200);
        ventana.getContentPane().setLayout(new BorderLayout(0, 0));

        JLayeredPane panelCapas = new JLayeredPane();
        ventana.getContentPane().add(panelCapas);

        JLabel etiquetaDerecho = new JLabel("Derecho:");
        etiquetaDerecho.setBounds(93, 103, 77, 14);
        panelCapas.add(etiquetaDerecho);

        JTextField campoDerecho = new JTextField();
        campoDerecho.setBounds(84, 128, 86, 20);
        panelCapas.add(campoDerecho);

        JLabel etiquetaReves = new JLabel("Del revés:");
        etiquetaReves.setBounds(318, 103, 77, 14);
        panelCapas.add(etiquetaReves);

        JTextField campoReves = new JTextField();
        campoReves.setBounds(309, 128, 86, 20);
        panelCapas.add(campoReves);

        JButton botonDerechoAReves = new JButton(">");
        botonDerechoAReves.setBounds(197, 159, 89, 23);
        botonDerechoAReves.addActionListener(evento -> campoReves.setText(invertir(campoDerecho.getText())));
        panelCapas.add(botonDerechoAReves);

        JButton botonRevesADerecho = new JButton("<");
        botonRevesADerecho.setBounds(197, 99, 89, 23);
        botonRevesADerecho.addActionListener(evento -> campoDerecho.setText(invertir(campoReves.getText())));
        panelCapas.add(botonRevesADerecho);

        ventana.setVisible(true);
    }

    private static String invertir(String texto)
    {
        return new StringBuilder(texto).reverse().toString();
    }
}