import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Menu
{
	Scanner leer= new Scanner(System.in);
	
	protected void procesos()
	{
		
		long fin=0;
		long result=0;
		long inicio = System.currentTimeMillis();
		
		ProcessBuilder pb = new ProcessBuilder("java","ProcesoLento1");
		pb.directory(new File("/media/fran/FRAN/PSP/Procesos3"));

		ProcessBuilder pb2 = new ProcessBuilder("java","ProcesoLento2");
		pb.directory(new File("/media/fran/FRAN/PSP/Procesos3"));		
		//lanza el primer proceso y calcula el tiempo
		try
		{
			Process p1 = pb.start();
			Process p2 = pb2.start();
						
			try
			{
				p1.waitFor();
				p2.waitFor();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
		}
		
		catch (IOException e)
		{
			System.out.println("Error al lanzar el proceso");
		}
		
		fin = System.currentTimeMillis();
		result=fin-inicio;
		System.out.println(result);
	}
	
	protected void menu()
	{
		int opcion=0;
		
		do
		{

			try
			{
				System.out.println("-----MENU-----");
				System.out.println("|1) PROC 1    |");
				System.out.println("|2) PROC 2    |");
				System.out.println("|_____________|");
				System.out.println("Escoge una(1~2): ");
				opcion=leer.nextInt();
				leer.nextLine();
			}
			catch(Exception e)
			{
				leer.nextLine();
				System.out.println("");
				opcion=0;
			}
		}while(opcion<1||opcion>2);
		
		procesos();
	}

}