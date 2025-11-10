public class Pares50
{
    public static void main (String[] args)
    {
        int num1 = 8;

        System.out.println("Con el bucle FOR");

        for(;num1 <= 50; num1 += 2)
        {
            num1 = arreglarNumero(num1);
            System.out.print(num1+" ");
        }

        num1 = -47;

        System.out.println("\nCon el bucle DO WHILE");

        do
        {
            num1 = arreglarNumero(num1);
            System.out.print(num1+" ");
            num1 += 2;
        }while(num1 <= 50);

        num1 = 49;

        System.out.println("\nCon el bucle WHILE");
        // CON EL WHILE
        while(num1 <= 50)
        {
            num1 = arreglarNumero(num1);
            System.out.print(num1+" ");
            num1 += 2;
        }
    }

    private static int arreglarNumero(int numeroParam)
    {
        int arregladoNum = numeroParam;
        if(arregladoNum %2 == 1 || arregladoNum %2 == -1)
            arregladoNum++;
        return (arregladoNum);
    }
}