public class Edad
{
    public static void main(String[] args)
    {
        boolean jasp;
        int edad = 28, nivel_de_estudio = 4, ingresos = 7865; //valores dados para comprobar jasp
        jasp = ((edad <= 28 && nivel_de_estudio >= 3)||(edad < 30 && ingresos > 28000));
        System.out.println("La booleana Jasp es " + jasp);
    }
}