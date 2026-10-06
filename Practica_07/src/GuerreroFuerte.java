import java.util.Calendar;

public class GuerreroFuerte extends Guerrero
{
    protected int ataque = 10;

    protected int movimientoLucha()
    {
        int atk = (int)(Math.random() * ataque) + 5;
        return(atk);
    }

    protected void fechacrea()
    {
        Calendar calendario = Calendar.getInstance();
        System.out.println("Fecha del Nuevo Guerrero Fuerte creado: " + calendario.getTime());
    }
}