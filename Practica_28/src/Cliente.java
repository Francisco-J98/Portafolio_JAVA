import java.util.concurrent.ThreadLocalRandom;

public class Cliente implements Runnable
{
    private final Entrada[] entradas;
    private final int asientoDeseado;

    public Cliente(Entrada[] entradas)
    {
        this.entradas = entradas;
        this.asientoDeseado = ThreadLocalRandom.current().nextInt(entradas.length);
    }

    @Override
    public void run()
    {
        comprarEntrada();
    }

    private void comprarEntrada()
    {
        if (entradas[asientoDeseado].vender())
            System.out.println("Entrada " + asientoDeseado + " comprada.");
        else
            System.out.println("Entrada ya vendida.");
    }
}