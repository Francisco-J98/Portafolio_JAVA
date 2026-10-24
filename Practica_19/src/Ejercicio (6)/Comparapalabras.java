import java.util.Scanner;
import java.io.*;

public class Comparapalabras
{
	public static void main(String[] args)
	{
		Scanner leer=new Scanner(System.in);
		
		String palabra1;
		String palabra2;
		String seguir;
		
		System.out.println("Introduce texto/palabra: ");
		palabra1=leer.nextLine();

		do
		{
			System.out.println("Introduzca otro texto/palabra: ");
			palabra2=leer.nextLine();
			
			if(palabra1.compareToIgnoreCase(palabra2)>0)
				System.out.println(palabra1+" y "+palabra2+ " ; Los textos/palabras no son iguales.");
			else if(palabra1.compareToIgnoreCase(palabra2)<0)
				System.out.println(palabra1+" y "+palabra2+ " ; Los textos/palabras no son iguales.");
			else
				System.out.println(palabra1+" y "+palabra2+ " ; Los textos/palabras son iguales");
			palabra1=palabra2;
			
		System.out.println("¿Desea seguir?");
		System.out.println("Sí = S/s, No = N/n (o cualquier otra cosa): ");
		
		leer.nextLine();
		seguir=leer.nextLine();
		
		}while(seguir.charAt(0)=='S'||seguir.charAt(0)=='s');
		

	}
}
