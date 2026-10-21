import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Principal
{

    private static final int NUMERO_CLIENTES = 1000;
    private static final int NUMERO_ENTRADAS = 30;

    public static void main(String[] argumentos)
    {
        Entrada[] entradas = IntStream.range(0, NUMERO_ENTRADAS).mapToObj(Entrada::new).toArray(Entrada[]::new);

        List<Thread> hilosClientes = new ArrayList<>(NUMERO_CLIENTES);
        for (int contador = 0; contador < NUMERO_CLIENTES; contador++)
        {
            Thread hilo = new Thread(new Cliente(entradas));
            hilosClientes.add(hilo);
            hilo.start();
        }

        try
        {
            for (Thread hilo : hilosClientes)
                hilo.join();
        } catch (InterruptedException excepcion)
        {
            Thread.currentThread().interrupt();
            System.err.println("Espera de los clientes interrumpida.");
            return;
        }

        long entradasVendidas = Arrays.stream(entradas).filter(Entrada::isVendida).count();
        System.out.println("Entradas vendidas: " + entradasVendidas + " de " + NUMERO_ENTRADAS);
    }
}