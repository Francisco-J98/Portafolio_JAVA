public class CircunferenciaCalcular
{
    public static void main()
    {
        double radio = 3, numpi = 3.14159, result; //valores arbitrarios por el ejercicio

        result = radio * radio * numpi;
        System.out.println("El área de la circunferencia es: "+ result);
        result = 2 * numpi * radio;
        System.out.println("La longitud de la circunferencia es: "+ result);
    }
}