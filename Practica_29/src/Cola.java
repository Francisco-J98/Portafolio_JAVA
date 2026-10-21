import java.util.ArrayDeque;
import java.util.Deque;

public class Cola
{
    private final int capacidadMaxima;
    private final Deque<Integer> elementos = new ArrayDeque<>();

    public Cola(int capacidadMaxima)
    {
        this.capacidadMaxima = capacidadMaxima;
    }

    public synchronized void producir(int numero) throws InterruptedException
    {
        while (elementos.size() >= capacidadMaxima)
        {
            System.out.println("Cola llena. El productor espera.");
            wait();
        }

        elementos.addLast(numero);
        System.out.println("Productor ha producido el número " + numero);
        notifyAll();
    }

    public synchronized int consumir() throws InterruptedException
    {
        while (elementos.isEmpty())
        {
            System.out.println("Cola vacía. El consumidor espera.");
            wait();
        }

        int numero = elementos.removeFirst();
        System.out.println("Consumidor ha sacado el número " + numero);
        notifyAll();
        return numero;
    }
}