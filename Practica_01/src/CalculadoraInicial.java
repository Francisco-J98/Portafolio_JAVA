public class CalculadoraInicial
{
    public static void main(String[] args)
    {
        int num1 = 7, num2 = 7, result;
        float result2;
        boolean var1;

        result = num1 + num2;
        System.out.println(num1 + "+" + num2 + "=" + result);
        result = num1 - num2;
        System.out.println(num1 + "-" + num2 + "=" + result);
        result = num1 * num2;
        System.out.println(num1 + "*" + num2 + "=" + result);
        result2 = (float)num1 / (float)num2;
        System.out.println(num1 + "/" + num2 + "=" + result2);

        var1 = (num1 > num2);
        System.out.println("¿"+num1 + ">" + num2 + "? " + var1);
        var1 = (num1 < num2);
        System.out.println("¿"+num1 + "<" + num2 + "? " + var1);
        var1 = (num1 == num2);
        System.out.println("¿"+num1 + "=" + num2 + "? " + var1);
    }
}