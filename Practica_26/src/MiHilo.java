public class MiHilo extends Thread
{
    private final String nombre;

    public MiHilo(String nombre)
	{
        this.nombre = nombre;
        System.out.println(this.nombre);
    }

    @Override
    public void run()
	{
        System.out.println("Hola a todos");
    }
}