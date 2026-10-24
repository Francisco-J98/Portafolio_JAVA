import java.util.Scanner;

public class Nombreapellido
{
	public static void main(String[] args)
	{
		String ncom,nombre="",nombre2="",apellido1="",apellido2="",aux="";
		Scanner leer=new Scanner(System.in);
		int act,i,opcion=0;
		
		do
		{
			try
			{
				System.out.println("-----MENÚ NOMBRES-----");
				System.out.println("|1)Nombre simple.    |");
				System.out.println("|2)Nombre compuesto. |");
				System.out.println("|____________________|");
				System.out.println("Escoje una opción(1~2): ");
				opcion=leer.nextInt();
				leer.nextLine();
			}
			catch(Exception e)
			{
				System.out.print("\n");
				leer.nextLine();
			}
		}while(opcion<1||opcion>2);
		
		switch(opcion)
			{
				case 1:
				{
					do
					{
						act=0;
						System.out.println("Introduzca 'nombre0apellido0apellido2 (0 como delimitador, sin espacios)', por favor: ");
						ncom=leer.next();
						leer.nextLine();
						
						for(i=0;i<ncom.length();i++)
							if(ncom.charAt(i)=='0')
								act++;
					}while(act!=2);
							
					aux=ncom.substring(0,1);
					aux=aux.toUpperCase();
					nombre=aux;
					aux=ncom.substring(0+1,ncom.indexOf('0'));	
					nombre+=aux;
								
					
					aux=ncom.substring(ncom.indexOf('0')+1,ncom.indexOf('0')+2);
					aux=aux.toUpperCase();
					apellido1=aux;
					aux=ncom.substring(ncom.indexOf('0')+2,ncom.length());
					ncom=aux;
					apellido1+=ncom.substring(0,ncom.indexOf('0'));
					
					aux=ncom.substring(ncom.indexOf('0')+1,ncom.indexOf('0')+2);
					aux=aux.toUpperCase();
					apellido2=aux;
					aux=ncom.substring(ncom.indexOf('0')+2,ncom.length());
					apellido2+=aux;
					
					System.out.println(apellido1+" "+apellido2+", "+nombre);					
				}break;
				case 2:
				{
					do
					{
						act=0;
						System.out.println("Introduzca 'primernombre0segundonombre0apellido0apellido2 (0 como delimitador, sin espacios)', por favor: ");
						ncom=leer.next();
						leer.nextLine();
						
						for(i=0;i<ncom.length();i++)
							if(ncom.charAt(i)=='0')
								act++;
					}while(act!=3);					
					
					aux=ncom.substring(0,1);
					aux=aux.toUpperCase(); //coge el primer carácter y lo convierte en mayúscula.
					nombre=aux;  //guarda en nombre dicha mayúscula
					aux=ncom.substring(0+1,ncom.indexOf('0')); //coge el resto.	
					nombre+=aux; //y lo añade a lo que había en nombre.

					ncom=ncom.substring(ncom.indexOf('0')+1,ncom.length()); //corta el substring ncom.
					
					aux=ncom.substring(0,1);
					aux=aux.toUpperCase();
					nombre2=aux;
					aux=ncom.substring(1,ncom.indexOf('0'));
					nombre2+=aux;
					
					ncom=ncom.substring(ncom.indexOf('0')+1,ncom.length());
					
					aux=ncom.substring(0,1);
					aux=aux.toUpperCase();
					apellido1=aux;
					aux=ncom.substring(1,ncom.indexOf('0'));
					apellido1+=aux;
					
					ncom=ncom.substring(ncom.indexOf('0')+1,ncom.length());
				
					aux=ncom.substring(0,1);
					aux=aux.toUpperCase();
					apellido2=aux;
					aux=ncom.substring(1,ncom.length());
					apellido2+=aux;
					
					
					
					System.out.println(apellido1+" "+apellido2+", "+nombre+" "+nombre2);					
				}
			}
	}

}
