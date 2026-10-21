import javax.swing.SwingUtilities;

public class Principal
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            new Ventanas1();
            new Ventanas2();
        });
    }
}