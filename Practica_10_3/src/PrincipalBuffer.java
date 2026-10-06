public class PrincipalBuffer
{
    public static void main(String[] Args)
    {
        EscrituraBuffer E1 = new EscrituraBuffer ();
        LecturaBuffer L1 = new LecturaBuffer ();
        E1.escribir();
        L1.leer();
        System.out.println("\nEste documento tiene "+L1.conta+" vocal(es).");
    }

}