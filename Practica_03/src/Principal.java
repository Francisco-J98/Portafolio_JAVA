import java.util.Scanner;

public class Principal
{
    public static void main(String[] args)
    {
        Scanner leer = new Scanner(System.in);
        Factorial factorial = new Factorial();

        int opcion = 0;
        long num = 0, resultado = 0;

        do
        {
            try
            {
                System.out.println("----MENU DEL FACTORIAL----");
                System.out.println("|1)Método iterativo.     |");
                System.out.println("|2)Método recursivo.     |");
                System.out.println("|3)Salir.                |");
                System.out.println("|________________________|");
                System.out.println("Escoge una opción(1~3): ");
                opcion = leer.nextInt();
            }
            catch(Exception e)
            {
                System.out.print("\n");
                leer.nextLine();
            }
        }while(opcion < 1 || opcion > 3);

        do
        {
            try
            {
                System.out.println("Introduce un número: ");
                num = leer.nextLong();
            }
            catch(Exception e)
            {
                System.out.print("\n");
                num = -1;
                leer.nextLine();
            }
        }while(num < 0);

        switch(opcion)
        {
            case 1:
            {
                factorial.setNumero(num);

                if(num == 0 || num == 1)
                    System.out.println(num + "! = 1 por convenio.");
                else
                {
                    resultado = factorial.factorialIterativo();
                    System.out.println("Por el método iterativo, "+num+"! = "+resultado);
                }
            }break;

            case 2:
            {
                factorial.setNumero(num);

                if(num == 0|| num == 1)
                    System.out.println(num+"! = 1 por convenio.");
                else
                {
                    resultado = factorial.factorialRecursivo(num);
                    System.out.println("Por el método recursivo, "+factorial.getNumero()+"! = "+resultado);
                }
            }break;

            case 3:System.out.println("Has salido del programa.");break;
        }
    }
}