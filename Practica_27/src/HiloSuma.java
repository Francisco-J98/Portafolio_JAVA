public class HiloSuma extends Thread
{
    private final Resultado contador;

    public HiloSuma(Resultado contador)
	{
        this.contador = contador;
    }

    @Override
    public void run()
	{
        contador.aumentar();
    }
}