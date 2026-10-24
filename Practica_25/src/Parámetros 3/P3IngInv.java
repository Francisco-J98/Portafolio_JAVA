//Division por param.
public class P3IngInv
{
	public static void main(String[] Args)
	{
		LaDivision D1=new LaDivision();
		int x=0, y=0;
		
		if(Args.length==2)
		{
			x=Integer.parseInt(Args[0]);
			y=Integer.parseInt(Args[1]);
			System.out.println(D1.Dividiendo(x, y));
		}
		else
			System.out.println("Param. incorrectos.");
	}
}