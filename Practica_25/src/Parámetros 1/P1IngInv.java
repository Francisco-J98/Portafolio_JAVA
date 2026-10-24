//Suma por param.
public class P1IngInv
{
	public static void main(String[] Args)
	{
		int x=0, y=0;
		LaSuma S1=new LaSuma();
		if(Args.length==2)
		{
			x=Integer.parseInt(Args[0]);
			y=Integer.parseInt(Args[1]);
			System.out.println(S1.Sumando(x, y));
		}
		else
			System.out.println("Param. incorrectos.");
	}
}