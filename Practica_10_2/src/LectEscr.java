import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class LectEscr
{
    protected static ArrayList <Integer> datos = new ArrayList<>();
    protected static Scanner leer = new Scanner(System.in);

    public void Leer()
    {
        String ruta;
        int bytereader, conta = 0, i;
        boolean final_ar = false;

        try
        {
            System.out.println("Introduzca la ruta del fichero: ");
            ruta = leer.nextLine();
            FileInputStream Archivo = new FileInputStream(ruta);
            while(!final_ar)
            {
                bytereader = Archivo.read();
                if(bytereader != -1)
                    datos.add(bytereader);
                else
                    final_ar = true;
                conta++;
            }

            Archivo.close();
        }

        catch(IOException e)
        {
            System.out.println("Error al abrir el fichero.");
        }
    }

    public void Escribir()
    {
        String ruta;
        int i;
        Integer vector[];
        vector = new Integer [datos.size()];

        datos.toArray(vector);

        try
        {
            System.out.println("Introduzca la nueva ruta del fichero: ");
            ruta = leer.nextLine();
            FileOutputStream Archivo = new FileOutputStream(ruta);

            for(i = 0; i < datos.size(); i++)
                Archivo.write(vector[i]);

            Archivo.close();
        }
        catch(IOException e)
        {
            System.out.println("Error al abrir el fichero.");
        }
    }
}