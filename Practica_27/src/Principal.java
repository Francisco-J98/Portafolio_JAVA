import java.util.ArrayList;
import java.util.List;

public class Principal
{
    private static final int NUMERO_HILOS = 100;

    public static void main(String[] argumentos)
	{
        Resultado contador = new Resultado();
        List<HiloSuma> hilos = new ArrayList<>(NUMERO_HILOS);

        for (int i = 1; i <= NUMERO_HILOS; i++)
		{
            HiloSuma hilo = new HiloSuma(contador);
            hilos.add(hilo);
            hilo.start();
            System.out.println("Hilos lanzados: " + i + " -> valor actual: " + contador.getValor());
        }

        try
		{
            for (HiloSuma hilo : hilos)
			{
                hilo.join();
            }
        } catch (InterruptedException e)
		{
            Thread.currentThread().interrupt();
            System.out.println("Ejecución interrumpida antes de que terminaran todos los hilos.");
            return;
        }

        System.out.println("El resultado es: " + contador.getValor());
    }
}