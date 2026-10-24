public class Principal
{
    private static final String NOMBRE_PERSONA = "Pepe";
    private static final String NOMBRE_HILO = "Julián";

    public static void main(String[] argumentos)
	{
        Persona persona = new Persona(NOMBRE_PERSONA);
        System.out.println(persona.getNombre());

        new MiHilo(NOMBRE_HILO).start();
    }
}