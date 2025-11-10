import java.util.Scanner;

public class Saludo
{
    public static void main(String[] args)
    {
        int hora;
        Scanner leer = new Scanner(System.in);

        do
        {
            try
            {
                System.out.println("Introduce una hora (0~23): ");
                hora = leer.nextInt();
            }
            catch (Exception e)
            {
                hora = -1;
                leer.nextLine();
            }
        }while(hora < 0);

        if( hora >= 0 && hora <= 12)
            System.out.println("¡¡¡BUENOS DIAS!!!");
        else if (hora > 12 && hora < 21)
            System.out.println("¡¡¡BUENAS TARDES!!!");
        else if (hora >= 21 && hora < 24)
            System.out.println("¡¡¡BUENAS NOCHES!!!");
        else
            System.out.println("HORA INVÁLIDA");
    }
}