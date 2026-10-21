import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Ventanas1 extends JFrame
{

    public Ventanas1()
    {
        setTitle("Primera Ventana en Java");
        setBounds(0, 0, 300, 300);
        setIconImage(Recursos.cargarIcono());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new LaminaTexto());

        setVisible(true);
    }

    private static class LaminaTexto extends JPanel
    {
        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawString("Texto de Prueba 001", 50, 50);
        }
    }
}