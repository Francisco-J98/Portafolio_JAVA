import java.util.Scanner;

public class Reloj
{
	public static void main(String[] args)
	{
		
		int tiempo=0, num1=0,num2=0,result=0;
		String hora,aux="";
		Scanner leer=new Scanner(System.in);
		
		tiempo=24*60;
		
		do
		{
			System.out.println("Hora[1]: ");
			hora=leer.next();
		}while(hora.charAt(0)<'0'||hora.charAt(0)>'2');
		
		aux+=hora.charAt(0);		
		leer.nextLine();
		
		if(hora.charAt(0)=='0'||hora.charAt(0)=='1')
		{
			do
			{
				System.out.println("Hora[2]: ");
				hora=leer.next();
			}while(hora.charAt(0)<'0'||hora.charAt(0)>'9');			
		}
		else
		{
			do
			{
				System.out.println("Hora[2]: ");
				hora=leer.next();
			}while(hora.charAt(0)<'0'||hora.charAt(0)>'3');			
		}
		
		aux+=hora.charAt(0);
		num1 = Integer.parseInt(aux);	
		aux+=":";
		
		leer.nextLine();
		
		do
		{
			System.out.println("Hora[3]: ");
			hora=leer.next();
		}while(hora.charAt(0)<'0'||hora.charAt(0)>'5');
		
		aux+=hora.charAt(0);
		
		leer.nextLine();

		do
		{
			System.out.println("Hora[4]: ");
			hora=leer.next();
		}while(hora.charAt(0)<'0'||hora.charAt(0)>'9');
		
		aux+=hora.charAt(0);	
		leer.nextLine();
		
		hora=aux.substring(aux.indexOf(':')+1, aux.length());
		num2 = Integer.parseInt(hora);		
		
		System.out.println("Son las: "+aux);
		result=(num1*60)+num2;
		tiempo-=result;
		System.out.println("Queda(n) "+tiempo+" minuto(s).");
	}

}