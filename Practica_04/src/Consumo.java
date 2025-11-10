import java.util.Scanner;

public class Consumo
{
    private double kms;
    private double litros;
    private double pgas;
    private double vmedia;

    public double getKms()
    {
        return kms;
    }

    public double getLitros()
    {
        return litros;
    }

    public double getPgas()
    {
        return pgas;
    }

    public double getVmedia()
    {
        return vmedia;
    }

    public void setKms(double kms)
    {
        this.kms = kms;
    }

    public void setLitros(double litros)
    {
        this.litros = litros;
    }

    public void setPgas(double pgas)
    {
        this.pgas = pgas;
    }

    public void setVmedia(double vmedia)
    {
        this.vmedia = vmedia;
    }

    private double tiempo(double kmParam, double vMediaParam)
    {
        return(kmParam / vMediaParam);
    }

    private double consumoMedio(double kmParam,double litrosParam)
    {
        return((litrosParam * 100)/ kmParam);
    }

    private double consumoEuros(double litrosParam, double precioGasParam)
    {
        return(litrosParam * precioGasParam);
    }

    public static void main(String[] args)
    {
        Consumo coche1 = new Consumo();
        Scanner leer = new Scanner(System.in);

        System.out.println("Introduce los datos del coche: ");

        do
        {
            try
            {
                System.out.println("Kilometraje: ");
                coche1.setKms(leer.nextDouble());
                leer.nextLine();
            } catch (Exception e)
            {
                coche1.setKms(-1);
                leer.nextLine();
            }
        }while(coche1.getKms() <= 0);

        do
        {
            try
            {
                System.out.println("Capacidad: ");
                coche1.setLitros(leer.nextDouble());
                leer.nextLine();
            } catch (Exception e)
            {
                coche1.setLitros(-1);
                leer.nextLine();
            }
        }while(coche1.getLitros() <= 0);

        do
        {
            try
            {
                System.out.println("Precio de la gasolina(€): ");
                coche1.setPgas(leer.nextDouble());
                leer.nextLine();
            } catch (Exception e)
            {
                coche1.setPgas(-1);
                leer.nextLine();
            }
        }while(coche1.getPgas() < 0);

        do
        {
            try
            {
                System.out.println("Velocidad media: ");
                coche1.setVmedia(leer.nextDouble());
                leer.nextLine();
            } catch (Exception e)
            {
                coche1.setVmedia(-1);
                leer.nextLine();
            }
        }while(coche1.getVmedia() <= 0);

        for(int i = 0; i < 5; i++)
            System.out.println("Cargando...");

        System.out.println("-------Datos del coche-------");
        System.out.println("Kilometros recorridos: "+ coche1.getKms()+ " km");
        System.out.println("Capacidad: "+ coche1.getLitros()+" L");
        System.out.println("Velocidad media: "+coche1.getVmedia()+" km/h");
        System.out.println("Precio de la gasolina: "+coche1.getPgas()+ " euro(s)");
        System.out.println("Tiempo de viaje: "+coche1.tiempo(coche1.getKms(), coche1.getVmedia())+" horas");
        System.out.println("Consumo medio: "+coche1.consumoMedio(coche1.getKms(), coche1.getLitros())+" L/km");
        System.out.println("Consumo económico: "+coche1.consumoEuros(coche1.getLitros(), coche1.getPgas())+" EURO(S)");
    }
}