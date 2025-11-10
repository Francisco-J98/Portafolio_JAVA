public class CelsiusFahrenheit
{
    public static void main (String[] args)
    {
        double celsius, fahrenheit;

        System.out.println("CELSIUS_FAHRENHEIT");
        for(celsius = 10; celsius <= 100; celsius += 10)
        {
            fahrenheit = (celsius * 1.8) + 32;
            System.out.println(" "+ celsius + "_____" + fahrenheit);
        }

        System.out.println("FAHRENHEIT_CELSIUS");

        for(fahrenheit = 10; fahrenheit <= 100; fahrenheit += 10)
        {
            celsius = (fahrenheit - 32) / (1.8);
            System.out.println(" "+ fahrenheit + "_____" + celsius);
        }
    }
}