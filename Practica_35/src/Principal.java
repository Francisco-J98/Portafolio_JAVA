public class Principal
{
	public static void main(String[] Args)
	{
		Parseador P1=new Parseador();
		
		if(Args.length==1)
		{
			if(Args[0].contains("inventario.conf"))
				P1.Operador();
		}
		else
			System.out.println("Parámetros incorrectos.");
	}

}