import java.util.Scanner;

public class Principal
{
    public static void main(String[] Args)
    {
        int opcion = 0;
        LectEscr N1 = new LectEscr();
        Scanner leer = new Scanner (System.in);

        do
        {
            try
            {
                System.out.println("--------MENU--------");
                System.out.println("|1) LEER FICHERO   |");
                System.out.println("|2) COPIAR FICHERO |");
                System.out.println("|3) SALIR          |");
                System.out.println("|__________________|");
                System.out.println("Escoja una opción (1-3): ");
                opcion = leer.nextInt();
                leer.nextLine();

                switch(opcion)
                {
                    case 1:N1.Leer();break;
                    case 2:N1.Escribir();break;
                    case 3: System.out.println("Ha salido del programa.");break;
                }
            }
            catch (Exception e)
            {
                System.out.print("\n");
                leer.nextLine();
                opcion = 0;
            }
        }while(opcion != 3);
    }
}