import java.util.Scanner;

public class Almacen
{
	Ropa R1;
	Scanner leer = new Scanner(System.in);
	OpBBDD O1= new OpBBDD("Ejercicio.db");

	protected void menu()
	{
		int opcion=0;
		
		O1.crearBBDD();
		
		do
		{
			try
			{
				System.out.println("-----MENÚ PRINCIPAL-----");
				System.out.println("|1) Agregar.           |");
				System.out.println("|2) Modificar.         |");
				System.out.println("|3) Eliminar.          |");
				System.out.println("|4) Consultar.         |");
				System.out.println("|5) Salir.             |");
				System.out.println("------------------------");
				System.out.println("Escoge una opción(1~5): ");
				opcion=leer.nextInt();
				leer.nextLine();
				switch(opcion)
				{
					case 1:
					{
						System.out.println("Ha escogido: Agregar.");
						agregar();
					}break;
					
					case 2:
					{
						System.out.println("Ha escogido: Modificar.");
						menumodificar();
					}break;
					
					case 3:
					{
						System.out.println("Ha escogido: Eliminar.");
						eliminar();
					}break;
					
					case 4:
					{
						System.out.println("Ha escogido: Consultar.");
						menuconsultar();
					}break;
					
					case 5:
					{
						System.out.println("Ha salido del programa.");
					}break;
				}
			}
			catch(Exception e)
			{
				opcion=0;
				leer.nextLine();
				System.out.println();
			}
		}while(opcion!=5);
	}
	
	private void agregar()
	{
		int talla=0,i;
		Boolean bandera;
		String Color="",aux="";
		
		do
		{
			try
			{
				System.out.println("Introduzca talla: ");
				talla=leer.nextInt();
				leer.nextLine();				
			}
			catch(Exception e)
			{
				talla=0;
				leer.nextLine();
				System.out.println();
			}
		}while(talla<=0);
		
		do
		{
			bandera=false;
			System.out.println("Introduzca color: ");
			Color=leer.nextLine();
			aux=Color.substring(0,1);
			aux=aux.toUpperCase();
			aux+=Color.substring(1,Color.length());
			Color=aux;
			System.out.println(Color);
			for(i=0;i<Color.length();i++)
			{
				if(Color.charAt(i)>='0'&&Color.charAt(i)<='9')
				{
					bandera=true;
				}
			}
		}while(bandera==true||Color.length()>20);
		
		R1 = new Ropa(talla,Color);
		O1.agregar(R1);
	}
	
	private void menumodificar()
	{
		int op1=0;
		boolean b=O1.comprobarBBDD();
		
		if(b==false)
		{
			System.out.println("No se ha encontrado nada para modificar en la BBDD.");
		}
		else
		{
			do
			{
				try
				{
					System.out.println("-------SUBMENÚ-------");
					System.out.println("|1) Modificar Talla.");
					System.out.println("|2) Modificar Color.");
					System.out.println("----------------------------");
					System.out.println("Escoge una opción(1~2): ");
					op1=leer.nextInt();
					leer.nextLine();
				}
				catch(Exception e)
				{
					op1=0;
					leer.nextLine();
					System.out.println();
				}
			}while(op1<1||op1>2);
			
			modificar(op1);
		}
	}
	
	private void modificar(int x)
	{
		boolean nbandera=false;
		String ncolor="",naux="";
		int identificador=0,ntalla=0,i;
		
		do
		{
			try
			{
				System.out.println("Introduzca el ID del registro que desee modificar: ");
				identificador=leer.nextInt();
				leer.nextLine();
			}
			catch(Exception e)
			{
				identificador=0;
				leer.nextInt();
				System.out.println();
			}
		}while(identificador<=0);
		
		switch(x)
		{
			case 1:
			{
				do
				{
					try
					{
						System.out.println("Introduzca el nuevo valor de la talla: ");
						ntalla=leer.nextInt();
						leer.nextLine();
					}
					catch(Exception e)
					{
						ntalla=0;
						leer.nextInt();
						System.out.println();
					}
				}while(ntalla<=0);
				O1.modificarT(identificador, ntalla);
			}break;
			
			case 2:
			{
				do
				{
					nbandera=false;
					System.out.println("Introduzca color: ");
					ncolor=leer.nextLine();
					naux=ncolor.substring(0,1);
					naux=naux.toUpperCase();
					naux+=ncolor.substring(1,ncolor.length());
					ncolor=naux;
					System.out.println(ncolor);
					for(i=0;i<ncolor.length();i++)
					{
						if(ncolor.charAt(i)>='0'&&ncolor.charAt(i)<='9')
						{
							nbandera=true;
						}
					}
				}while(nbandera==true||ncolor.length()>20);
				O1.modificarC(identificador, ncolor);
			}break;
		}
	}
	
	private void eliminar()
	{
		int identificador=0;
		boolean b=O1.comprobarBBDD();
		
		if(b==false)
		{
			System.out.println("No se ha encontrado nada para eliminar en la BBDD.");
		}
		else
		{
			do
			{
				try
				{
					System.out.println("Introduzca el ID del registro que desee eliminar: ");
					identificador=leer.nextInt();
					leer.nextLine();
				}
				catch(Exception e)
				{
					identificador=0;
					leer.nextInt();
					System.out.println();
				}
			}while(identificador<=0);
			O1.eliminar(identificador);
		}
	}
	
	private void menuconsultar()
	{		
		int op2=0;
		boolean b=O1.comprobarBBDD();
		
		if(b==false)
		{
			System.out.println("No se ha encontrado nada para consultar en la BBDD.");
		}
		else
		{
			do
			{
				try
				{
					System.out.println("-------SUBMENÚ-------");
					System.out.println("1) Primer Registro.");
					System.out.println("2) Último Registro.");
					System.out.println("3) Registro específico.");
					System.out.println("4) Todos los Registros.");
					System.out.println("----------------------------");
					System.out.println("Escoge una opción(1~4): ");
					op2=leer.nextInt();
					leer.nextLine();

				}
				catch(Exception e)
				{
					op2=0;
					leer.nextLine();
					System.out.println();
				}
			}while(op2<1||op2>4);

			consultar(op2);
		}
	}
	
	private void consultar(int y)
	{
		int identificador=0;
		
		switch(y)
		{
			case 1:
			{
				identificador=1;
				O1.consultarID(identificador);
			}break;
			
			case 2:
			{
				identificador=O1.contRegBBDD();
				O1.consultarID(identificador);
			}break;
			
			case 3:
			{
				do
				{
					try
					{
						System.out.println("Introduzca el ID del registro que desee consultar: ");
						identificador=leer.nextInt();
						leer.nextLine();
					}
					catch(Exception e)
					{
						identificador=0;
						leer.nextLine();
						System.out.println();
					}
				}while(identificador<=0);
				O1.consultarID(identificador);
			}break;
			
			case 4:
			{
				O1.consultarT();
			}break;
		}
	}
}