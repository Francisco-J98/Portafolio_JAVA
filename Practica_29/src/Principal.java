import java.util.ArrayList;
import java.util.List;

public class Principal
{
    private static final int NUMERO_PRODUCTORES = 1000;
    private static final int NUMERO_CONSUMIDORES = 1000;
    private static final int CAPACIDAD_COLA = 30;

    public static void main(String[] argumentos)
    {
        Cola cola = new Cola(CAPACIDAD_COLA);
        List<Thread> hilos = new ArrayList<>(NUMERO_PRODUCTORES + NUMERO_CONSUMIDORES);

        for (int contador = 0; contador < NUMERO_PRODUCTORES; contador++)
            hilos.add(new Thread(new Productor(cola)));

        for (int contador = 0; contador < NUMERO_CONSUMIDORES; contador++)
            hilos.add(new Thread(new Consumidor(cola)));

        hilos.forEach(Thread::start);

        try
        {
            for (Thread hilo : hilos)
                hilo.join();
        }
        catch (InterruptedException excepcion)
        {
            Thread.currentThread().interrupt();
            System.err.println("Espera de los hilos interrumpida.");
        }
    }
}