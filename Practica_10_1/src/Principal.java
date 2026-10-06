public class Principal
{
    public static void main(String[] Args)
    {
        Escritura E1 = new Escritura ();
        E1.escribir();
        Lectura L1 = new Lectura ();
        L1.leer();
        System.out.println("\nEste documento tiene "+L1.conta+" vocal(es).");
    }
}