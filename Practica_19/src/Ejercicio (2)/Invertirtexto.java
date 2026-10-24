import java.util.Scanner;

public class Invertirtexto
{
	public static void main(String[] args)
	{
		int i;
		String texto,result;
		Scanner leertexto=new Scanner(System.in);

		result="";
		System.out.println("Introduzca texto/palabra: ");
		texto=leertexto.nextLine();
		
		for(i=texto.length()-1;i>=0;i--)
			result+=texto.charAt(i);
		System.out.println("El mensaje invertido es: "+result);
	}
		

}
