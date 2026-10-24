import java.io.*;
import java.util.Scanner;

public class Sustituyetexto
{
	public static void main(String[] args) throws IOException
	{
		String texto="",aux="";
		char car,car2;
		int i,act;
		
		Scanner leerparrafo=new Scanner(System.in);
		System.out.println("Introduce texto/palabra:");
		texto=leerparrafo.nextLine();
		
		
		do
		{
			System.out.println("Introduzca un carácter del texto: ");
			car=leerparrafo.next().charAt(0);
			leerparrafo.nextLine();
			act=1;
			for(i=0;i<texto.length();i++)
			{
				if(texto.charAt(i)==car)
					act=0;
			}			
		}while(act!=0);
		
		
		System.out.println("A continuación, introduzca otro carácter(cualquiera) con el que desea sustituir: ");
		car2=leerparrafo.next().charAt(0);
		leerparrafo.nextLine();
		
		for(i=0;i<texto.length();i++)
		{
			if(texto.charAt(i)==car)
				aux=texto.replace(car, car2);
		}
		System.out.println("El nuevo texto/palabra: "+aux);
	}

}
