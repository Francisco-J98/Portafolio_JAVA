import java.util.Scanner;

public class RestaConsecutiva
{
    public static void main (String[] args)
    {
        int num1 = 0, num2 = 0, result = 0, act = 0;
        Scanner leer = new Scanner(System.in);

        System.out.println("Introduce un número: ");
        num1 = leer.nextInt();
        act = num1;
        System.out.println("Introduce un segundo número: ");
        num2 = leer.nextInt();

        for(; act - num2 >= 0; act = result)
        {
            result = act - num2;
            System.out.println(act+" - "+num2+" = "+result);
            System.out.println("Resta consecutiva: Introduce otro número: ");
            num2 = leer.nextInt();
            leer.nextLine();
            System.out.println("\n");
        }
    }
}