import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Graphics;

public class Ventanas2 extends JFrame
{

    public Ventanas2()
    {
        setTitle("Segunda Ventana en Java");
        setBounds(450, 200, 400, 400);
        setIconImage(Recursos.cargarIcono());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new LaminaMisterio());

        setVisible(true);
    }

    private static class LaminaMisterio extends JPanel
    {
        private final JTextField campoTexto = new JTextField("Conseguido!");

        LaminaMisterio()
        {
            JButton botonMisterio = new JButton("Qué pasará, qué misterio habrá...");
            botonMisterio.addActionListener(evento -> mostrarCampoTexto());
            add(botonMisterio);
        }

        private void mostrarCampoTexto()
        {
            campoTexto.setBounds(200, 100, 100, 20);
            add(campoTexto);
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawString("Texto de Prueba 002", 50, 50);
        }
    }
}