package Ejercicio3;

import java.util.Scanner;

public class Password
{
	public static void main(String[] args)
	{
		int intentos=3,activador;
		boolean victoria=false;
		
		String clave;
		String contra="EmperadorCarlosI";
		Scanner leerpasswd=new Scanner(System.in);
		
		do	
		{
			System.out.println("Usuario: USUARIO001");
			System.out.println("Contraseña: ");
			clave=leerpasswd.next();
			leerpasswd.nextLine();
			
			activador=clave.compareTo(contra);
			if(activador==0)
				victoria=true;
			else
			{
				intentos--;
				if(intentos>0)
				{
					System.out.println("Error al introducir la contraseña. Le queda(n) "+intentos+" intento(s).");
				}
				else
				{
					System.out.println("No le quedan más intentos.");
				}
			}
		}while(intentos>0&&victoria==false);
		
		if(victoria==true)
			System.out.println("Bienvenido, USUARIO001.");
		else
			System.out.println("USUARIO BLOQUEADO.");
	}
}
