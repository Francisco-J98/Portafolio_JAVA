//Resta por param.
public class P2IngInv
{
	public static void main(String[] Args)
	{
		LaResta R1= new LaResta();
		int x=0, y=0;
		
		if(Args.length==2)
		{
			x=Integer.parseInt(Args[0]);
			y=Integer.parseInt(Args[1]);
			System.out.println(R1.Restando(x, y));
		}
		else
			System.out.println("Parámetros incorrectos.");
	}
}