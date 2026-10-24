import java.util.concurrent.atomic.AtomicInteger;

public class Resultado
{
    private final AtomicInteger valor = new AtomicInteger();

    public int getValor()
	{
        return valor.get();
    }

    public void aumentar()
	{
        valor.incrementAndGet();
    }
}