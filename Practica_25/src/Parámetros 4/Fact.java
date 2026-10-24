public class Fact
{
	protected long factiterativo(int x)
	{
		long result=1,i,numero=x;
		
		if (x>1)
		{
			
			for (i=x;i>1;)
			{
				i--;
				result*=i;
			}
			numero*=result;
		}
		else
			numero=1;
		return numero;
	}
}