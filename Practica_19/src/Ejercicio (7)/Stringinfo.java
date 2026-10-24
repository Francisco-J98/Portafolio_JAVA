import java.util.Scanner;

public class Stringinfo
{
	public static void main(String[] args)
	{
		Scanner leer=new Scanner(System.in);
		String palabra;
		int conta=0;
		
		System.out.println("Introduzca texto/palabra: ");
		palabra=leer.nextLine();
		
		conta=palabra.length();
		if(conta<5)
		{
			if(palabra.charAt(0)=='a')
				System.out.println("La cadena comienza por 'a', pero tiene menos de 5 caracteres.");
			else
				System.out.println("La cadena no comienza por 'a' y tiene menos de 5 caracteres.");
		}
		else if(conta>=5&&conta<=15)
		{
			if(palabra.charAt(0)=='a')
				System.out.println("La cadena tiene entre 5 y 15 caracteres, y comienza por 'a'.");
			else
				System.out.println("La cadena tiene entre 5 y 15 caracteres, pero no comienza por 'a'.");
		}
		else
		{
			if(palabra.charAt(0)=='a')
				System.out.println("La cadena tiene más de 15 caracteres, y comienza por 'a'.");
			else
				System.out.println("La cadena tiene más de 15 caracteres, pero no comienza por 'a'.");			
		}
	}

}
