import java.util.Scanner;

public class MiNumero
{
    private int numero1;
    private int numero2;

    public int getNumero1()
    {
        return numero1;
    }

    public int getNumero2()
    {
        return numero2;
    }

    public void setNumero1(int numero1Param)
    {
        this.numero1 = numero1Param;
    }

    public void setNumero2(int numero2Param)
    {
        this.numero2 = numero2Param;
    }

    private int elDoble(int numeroParam)
    {
        return(numeroParam * 2);
    }

    private int elTriple(int numeroParam)
    {
        return(numeroParam * 3);
    }

    private int elCuadruple(int numeroParam)
    {
        return(numeroParam * 4);
    }

    private int adicion(int num1, int num2)
    {
        return(num1 + num2);
    }

    public static void main(String[] args)
    {
        int numerito1, numerito2;
        MiNumero numero = new MiNumero();
        Scanner leer = new Scanner(System.in);

        System.out.println("Introduce un número: ");
        numerito1 = leer.nextInt();
        numero.setNumero1(numerito1);

        System.out.println("El doble de "+ numero.getNumero1() +" es: "+ numero.elDoble(numero.getNumero1()));
        System.out.println("El triple de "+ numero.getNumero1() +" es: "+ numero.elTriple(numero.getNumero1()));
        System.out.println("El cuádruple de "+ numero.getNumero1() +" es: "+ numero.elCuadruple(numero.getNumero1()));

        System.out.println("Introduce otro número: ");
        numerito2 = leer.nextInt();
        numero.setNumero2(numerito2);

        System.out.println(numero.getNumero1() + " + " + numero.getNumero2() + " = "+ numero.adicion(numero.getNumero1(), numero.getNumero2()));
        numero.setNumero1(numero.adicion(numero.getNumero1(), numero.getNumero2()));
        System.out.println("El nuevo primer número es: " + numero.getNumero1());
    }
}