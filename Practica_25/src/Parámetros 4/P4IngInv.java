//Factorial por param.
public class P4IngInv
{
	public static void main(String[] Args)
	{
		int x=0;
		Fact F1 = new Fact();
		if(Args.length==1)
		{
			x=Integer.parseInt(Args[0]);
			System.out.println(F1.factiterativo(x));
		}
		else
			System.out.println("Param. incorrectos.");
	}
}