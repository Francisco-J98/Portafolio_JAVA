import java.util.Calendar;

public class Guerrero extends Personaje
{
    protected int ataque = 10;

    protected int movimientoLucha()
    {
        int atk = (int)(Math.random() * ataque);
        return(atk);
    }

    protected void fechacrea()
    {
        Calendar calendario = Calendar.getInstance();
        System.out.println("Fecha del Nuevo Guerrero creado: " + calendario.getTime());
    }
}