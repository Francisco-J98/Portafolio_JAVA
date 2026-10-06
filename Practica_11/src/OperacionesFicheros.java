import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class OperacionesFicheros
{
    public String aux = "";

    public void escribirtexto(String Parrafo)
    {
        int i;
        String ruta = "Quijote.txt";
        try
        {
            FileWriter F2 = new FileWriter(ruta);

            System.out.println("Escribiendo datos en el fichero...");

            for(i = 0; i < Parrafo.length(); i++)
                F2.write(Parrafo.charAt(i));

            F2.close();
        }
        catch(IOException e)
        {
            System.out.println("No se ha podido crear el fichero");
        }

    }

    public void leertexto()
    {
        try
        {
            String ruta = "Quijote.txt";
            FileReader F1 = new FileReader(ruta);
            int num = 0;

            System.out.println("Leyendo el fichero...");
            while(num != -1)
            {
                num = F1.read();
                char car = (char)num;
                aux += car;
            }
            F1.close();
        }
        catch(IOException e)
        {
            System.out.println("Error al abrir el fichero.");
        }
    }

    public void grabarconbuffer()
    {
        String aux2 = "", aux3 = "";
        int i = 0;

        try
        {
            FileWriter F1 = new FileWriter("Quijote_lineas.txt");
            BufferedWriter escritor = new BufferedWriter(F1);
            System.out.println("Grabando en búffer...");

            while(i != aux.length())
            {
                if(aux.charAt(i) == '.' || aux.charAt(i) == ',' || aux.charAt(i) == ';') //puntos '.' tratados.
                {
                    aux2 = aux.substring(0, aux.indexOf(aux.charAt(i)) + 1);
                    aux = aux.substring(aux.indexOf(aux.charAt(i)) + 2, aux.length());
                    escritor.write(aux2);
                    escritor.newLine();
                    escritor.flush();
                    i = 0;
                }
                else
                    i++;
            }
            F1.close();
        }
        catch(IOException e)
        {
            System.out.println("Error al abrir el fichero.");
        }
    }


    public void leerconbuffer()
    {
        String linea = "",aux2 = "", aux3 = "";
        try
        {
            FileReader F1 = new FileReader("Quijote_lineas.txt");
            BufferedReader lector = new BufferedReader(F1);

            System.out.println("Leyendo en búffer...");
            while(linea != null)
            {
                linea = lector.readLine();

                if(linea != null)
                {
                    aux2 = linea;
                    aux3 += aux2 + "\n";
                }
            }
            F1.close();
            System.out.print(aux3);
        }
        catch(IOException e)
        {
            System.out.println("Error al abrir el fichero.");
        }
    }
}