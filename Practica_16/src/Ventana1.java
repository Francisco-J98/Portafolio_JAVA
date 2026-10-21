import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

public class Ventana1
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(Ventana1::crearVentana);
    }

    private static void crearVentana()
    {
        JFrame ventana = new JFrame("Ejercicio1");
        ventana.setSize(500, 370);
        ventana.setLocation(300, 200);

        JLayeredPane panelCapas = new JLayeredPane();
        ventana.getContentPane().add(panelCapas, BorderLayout.CENTER);

        JLabel etiquetaOrigen = new JLabel("Texto a copiar:");
        etiquetaOrigen.setBounds(63, 67, 97, 14);
        panelCapas.add(etiquetaOrigen);

        JTextField campoOrigen = new JTextField();
        campoOrigen.setBounds(63, 92, 114, 20);
        panelCapas.add(campoOrigen);

        JLabel etiquetaDestino = new JLabel("Texto copiado:");
        etiquetaDestino.setBounds(290, 67, 95, 14);
        panelCapas.add(etiquetaDestino);

        JTextField campoDestino = new JTextField();
        campoDestino.setBounds(290, 92, 114, 20);
        panelCapas.add(campoDestino);

        JButton botonCopiar = new JButton("Copiar");
        botonCopiar.setBounds(193, 133, 89, 23);
        botonCopiar.addActionListener(evento -> {campoDestino.setText(campoOrigen.getText());campoOrigen.setText("");});
        panelCapas.add(botonCopiar);

        ventana.setVisible(true);
    }
}