public class Principal
{
	public static void main(String[] args)
	{
		int i=0;
		long fin=0;
		long inicio = System.currentTimeMillis();
		for(i=100;i>0;i--)
		{
			System.out.println(i);
		}
		fin=System.currentTimeMillis();
		System.out.println("El programa ha tardado: "+(fin-inicio)+" milisegundo(s).");
	}

}