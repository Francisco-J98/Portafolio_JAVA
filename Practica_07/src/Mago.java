import java.util.Calendar;

public class Mago extends Personaje
{
    protected int magia = 10;

    Mago()
    {
        vida = (int)(getVida() * 0.1) + getVida();
    }
    protected int movimientoLucha()
    {
        int mag = (int)(Math.random() * magia);
        return(mag);
    }

    protected void fechacrea()
    {
        Calendar calendario = Calendar.getInstance();
        System.out.println("Fecha del Nuevo Mago creado: " + calendario.getTime());
    }
}