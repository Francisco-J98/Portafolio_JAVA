import java.util.Scanner;

public class ParOImpar
{
    public static void main (String[] args)
    {
        int num1 = 0;
        Scanner leer = new Scanner(System.in);
        System.out.println("Introduce un número: ");

        num1 = leer.nextInt();

        if(num1 %2 == 0)
            System.out.println(num1+" ES PAR");
        else
            System.out.println(num1+ " ES IMPAR");
    }
}