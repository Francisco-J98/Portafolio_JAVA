import java.util.concurrent.atomic.AtomicBoolean;

public class Entrada
{
    private final int asiento;
    private final AtomicBoolean vendida = new AtomicBoolean(false);

    public Entrada(int asiento)
    {
        this.asiento = asiento;
    }

    public int getAsiento()
    {
        return asiento;
    }

    public boolean isVendida()
    {
        return vendida.get();
    }

    /**
     * Intenta vender la entrada de forma atómica.
     *
     * @return true si esta llamada la ha vendido, false si ya estaba vendida
     */
    public boolean vender()
    {
        return vendida.compareAndSet(false, true);
    }
}