import java.util.Scanner;

public class MenuInicial
{
    private static void numeroParOImpar()
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

    private static void mesanioBuscar()
    {
        int mesAnio;
        Scanner leer = new Scanner(System.in);

        do
        {
            try
            {
                System.out.println("Introduce un mes del año(1~12): ");
                mesAnio = leer.nextInt();
            }
            catch (Exception e)
            {
                mesAnio = 0;
                leer.nextLine();
            }
        }while(mesAnio == 0);
        System.out.println("Has escogido: "+mesAnio);

        switch(mesAnio)
        {
            case 1: System.out.println("ENERO");break;
            case 2: System.out.println("FEBRERO");break;
            case 3: System.out.println("MARZO");break;
            case 4: System.out.println("ABRIL");break;
            case 5: System.out.println("MAYO");break;
            case 6: System.out.println("JUNIO");break;
            case 7: System.out.println("JULIO");break;
            case 8: System.out.println("AGOSTO");break;
            case 9: System.out.println("SEPTIEMBRE");break;
            case 10: System.out.println("OCTUBRE");break;
            case 11: System.out.println("NOVIEMBRE");break;
            case 12: System.out.println("DICIEMBRE");break;
            default: System.out.println("MES NO VÁLIDO");break;
        }
    }

    private static void saludoEnHora()
    {
        int hora = 0;
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

    public static void main (String[] args)
    {
        int opcion, hora, mesanio, num1;
        Scanner leer = new Scanner(System.in);

        do
        {
            try
            {
                System.out.println("\n1)CALCULO HORA");
                System.out.println("2)CALCULO MES");
                System.out.println("3)PAR IMPAR");
                System.out.println("4)SALIR");
                System.out.println("SELECCIONA UNA OPCIÓN[1~4]: ");
                opcion = leer.nextInt();
                leer.nextLine();
                System.out.println("HAS ESCOGIDO LA OPCIÓN NÚMERO: "+opcion);

                switch(opcion)
                {
                    case 1:saludoEnHora();break;
                    case 2:mesanioBuscar();break;
                    case 3:numeroParOImpar();break;
                    case 4:System.out.println("Has salido del programa");break;
                    default:System.out.println("Valor erróneo.");break;
                }
            }
            catch (Exception e)
            {
                opcion = -1;
                leer.nextLine();
            }
        }while(opcion!=4);
    }
}