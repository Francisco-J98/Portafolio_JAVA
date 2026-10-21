import java.util.Scanner;

public class Principal
{

    public static void main(String[] args)
    {
        try (Scanner teclado = new Scanner(System.in))
        {
            new Menu(teclado).ejecutar();
        }
    }
}