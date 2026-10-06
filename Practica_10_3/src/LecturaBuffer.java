import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LecturaBuffer
{
    protected int conta=0;

    public void leer()
    {
        int i;
        String ruta, linea = "", aux = "";
        Scanner leer = new Scanner (System.in);

        System.out.println("Introduzca ruta: ");
        ruta = leer.nextLine();

        try
        {
            FileReader F1 = new FileReader(ruta);
            BufferedReader lector = new BufferedReader(F1);

            while(linea != null)
            {
                linea = lector.readLine();

                if(linea != null)
                    aux += linea;
            }
            F1.close();

            for(i = 0; i < aux.length(); i++)
                if(aux.charAt(i) == 'A' || aux.charAt(i) == 'E' || aux.charAt(i) == 'I' || aux.charAt(i) == 'O' || aux.charAt(i) == 'U' || aux.charAt(i) == 'a' || aux.charAt(i) == 'e' || aux.charAt(i) == 'i' || aux.charAt(i) == 'o' || aux.charAt(i) == 'u')
                    conta++;
        }
        catch(IOException e)
        {
            System.out.println("Error al abrir el fichero.");
        }
    }
}