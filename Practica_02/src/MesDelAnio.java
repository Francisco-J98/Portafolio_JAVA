import java.util.Scanner;

public class MesDelAnio
{
    public static void main (String[] args)
    {
        //1=Enero 2=Febrero 3=Marzo 4=Abril 5=Mayo 6=Junio 7=Julio 8=Agosto 9=Septiembre 10=Octubre 11=Noviembre 12=Diciembre
        int mesanio;
        Scanner leer = new Scanner(System.in);

        do
        {
            try
            {
                System.out.println("Introduce un mes del año(1~12): ");
                mesanio = leer.nextInt();
            }
            catch (Exception e)
            {
                mesanio = 0;
                leer.nextLine();
            }
        }while(mesanio == 0);
        System.out.println("Has escogido: "+mesanio);

        switch(mesanio)
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
}