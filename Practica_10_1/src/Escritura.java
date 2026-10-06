import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Escritura
{
    public void escribir()
    {
        int i;
        String ruta = "", mensaje = "";
        Scanner leer = new Scanner(System.in);

        try
        {
            System.out.println("Introduzca la ruta en donde quiere guardar el fichero: ");
            ruta = leer.nextLine();
            FileWriter F2 = new FileWriter(ruta);

            System.out.println("Introduzca mensaje: ");
            mensaje = leer.nextLine();

            for(i = 0; i < mensaje.length(); i++)
                F2.write(mensaje.charAt(i));

            F2.close();
        }
        catch(IOException e)
        {
            System.out.println("No se ha podido crear el fichero");
        }
    }
}