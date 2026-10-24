import java.util.Scanner;

public class Cuentavocales
{
	public static void main(String[] args)
	{
		int i,result,conta_a,conta_e,conta_i,conta_o,conta_u;
		String texto;
		Scanner leer=new Scanner(System.in);
		
		result=0;
		conta_a=0;
		conta_e=0;
		conta_i=0;
		conta_o=0;
		conta_u=0;
		
		System.out.println("Introduzca texto/palabra(s): ");
		texto=leer.nextLine();
		
		for(i=0;i<texto.length();i++)
		{
			if(texto.charAt(i)=='a'||texto.charAt(i)=='A'||texto.charAt(i)=='á'||texto.charAt(i)=='Á')
				conta_a++;
			else if(texto.charAt(i)=='e'||texto.charAt(i)=='E'||texto.charAt(i)=='é'||texto.charAt(i)=='É')
				conta_e++;
			else if(texto.charAt(i)=='i'||texto.charAt(i)=='I'||texto.charAt(i)=='í'||texto.charAt(i)=='Í')
				conta_i++;
			else if(texto.charAt(i)=='o'||texto.charAt(i)=='O'||texto.charAt(i)=='ó'||texto.charAt(i)=='Ó')
				conta_o++;
			else if(texto.charAt(i)=='u'||texto.charAt(i)=='U'||texto.charAt(i)=='ú'||texto.charAt(i)=='Ú')
				conta_u++;
		}
		
		result=(conta_a+conta_e+conta_i+conta_o+conta_u);
		
		System.out.println("Hay en total: "+result+" vocal(es):");
		System.out.println(conta_a+" A(es)");
		System.out.println(conta_e+" E(s)");
		System.out.println(conta_i+" I(es)");
		System.out.println(conta_o+" O(es)");
		System.out.println(conta_u+" U(es)");
	}
}