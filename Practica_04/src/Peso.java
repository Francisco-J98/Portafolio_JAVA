import java.util.Scanner;

public class Peso
{
    private double kilos;
    private static final double LIBRAS = 0.453;
    private static final double ONZAS = 0.02835;
    private static final double LINGOTES = 14.59;
    private static final double QUINTALES = 43.3;
    private static final double PENIQUES = 0.00155;

    public double getKilos()
    {
        return kilos;
    }

    public void setKilos(double kilosParam)
    {
        this.kilos = kilosParam;
    }

    private double aLibras(double kilosParam)
    {
        return (kilosParam / LIBRAS);
    }

    private double aOnzas (double kilosParam)
    {
        return (kilosParam / ONZAS);
    }

    private double aLingotes(double kilosParam)
    {
        return (kilosParam / LINGOTES);
    }

    private double aQuintales (double kilosParam)
    {
        return (kilosParam / QUINTALES);
    }

    private double aPeniques (double kilosParam)
    {
        return (kilosParam / PENIQUES);
    }

    public static void main(String[] args)
    {
        int opcion = 0;
        double kilogramos = 0;
        Peso medir = new Peso();
        Scanner leer = new Scanner(System.in);

        do
        {
            try
            {
                System.out.println("----MENÚ CONVERSOR de Kilogramos----");
                System.out.println("|1) Libras                          |");
                System.out.println("|2) Onzas                           |");
                System.out.println("|3) Lingotes                        |");
                System.out.println("|4) Quintales                       |");
                System.out.println("|5) Peniques                        |");
                System.out.println("|___________________________________|");
                System.out.println("Escoge una opcion(1~5): ");
                opcion= leer.nextInt();
            }
            catch (Exception e)
            {
                opcion = 0;
                leer.nextLine();
            }
        }while(opcion < 1 || opcion > 5);

        do
        {
            try
            {
                System.out.println("A continuación, introduce la masa (en kg): ");
                kilogramos = leer.nextDouble();
            }
            catch (Exception e)
            {
                kilogramos = -1;
                leer.nextLine();
            }
        }while(kilogramos < 0);

        medir.setKilos(kilogramos);

        switch(opcion)
        {
            case 1:System.out.println(medir.getKilos()+ "kg en libras: "+ medir.aLibras(medir.getKilos())+ " libra(s).");break;
            case 2:System.out.println(medir.getKilos()+ "kg en onzas: "+ medir.aOnzas(medir.getKilos())+ " onzas(s).");break;
            case 3:System.out.println(medir.getKilos()+ "kg en lingotes: "+ medir.aLingotes(medir.getKilos())+ " lingote(s).");break;
            case 4:System.out.println(medir.getKilos()+ "kg en quintales: "+ medir.aQuintales(medir.getKilos())+ " quintal(es).");break;
            case 5:System.out.println(medir.getKilos()+ "kg en peniques: "+ medir.aPeniques(medir.getKilos())+ " penique(s).");break;
        }
    }
}