import java.util.concurrent.ThreadLocalRandom;

public class Productor implements Runnable
{

    private static final int LIMITE_NUMERO = 10;
    private final Cola cola;

    public Productor(Cola cola)
    {
        this.cola = cola;
    }

    @Override
    public void run()
    {
        int numeroProducido = ThreadLocalRandom.current().nextInt(LIMITE_NUMERO);

        try
        {
            cola.producir(numeroProducido);
        }
        catch (InterruptedException excepcion)
        {
            Thread.currentThread().interrupt();
            System.err.println("Productor interrumpido.");
        }
    }
}