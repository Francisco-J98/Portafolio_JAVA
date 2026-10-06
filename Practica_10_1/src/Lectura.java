import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Lectura
{
    public int conta = 0;

    public void leer()
    {
        Scanner leer = new Scanner (System.in);

        try
        {
            String ruta;
            System.out.println("Introduzca ruta: ");
            ruta = leer.nextLine();
            FileReader F1 = new FileReader(ruta);
            int num = 0;

            while(num != -1)
            {
                num = F1.read();
                char car = (char)num;
                System.out.print(car);
                if (car == 'A' || car == 'a' || car == 'E' || car == 'e' || car == 'I' || car == 'i' || car == 'O' || car == 'o' || car == 'U' || car == 'u')
                    conta++;
            }
            F1.close();
        }
        catch(IOException e)
        {
            System.out.println("Error al abrir el fichero.");
        }
    }
}