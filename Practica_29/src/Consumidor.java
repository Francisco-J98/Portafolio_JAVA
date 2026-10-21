public class Consumidor implements Runnable
{
    private final Cola cola;

    public Consumidor(Cola cola)
    {
        this.cola = cola;
    }

    @Override
    public void run()
    {
        try
        {
            cola.consumir();
        }
        catch (InterruptedException excepcion)
        {
            Thread.currentThread().interrupt();
            System.err.println("Consumidor interrumpido.");
        }
    }
}