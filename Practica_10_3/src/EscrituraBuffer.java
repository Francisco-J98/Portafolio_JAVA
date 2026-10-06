import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EscrituraBuffer
{
    public void escribir()
    {
        int seguir = 0;
        String ruta, mensaje = "";
        Scanner leer = new Scanner (System.in);

        System.out.println("Introduzca ruta: ");
        ruta=leer.nextLine();

        try
        {
            FileWriter F1 = new FileWriter(ruta);
            BufferedWriter escritor = new BufferedWriter(F1);

            do
            {
                System.out.println("Introduzca mensaje: ");
                mensaje = leer.nextLine();
                escritor.write(mensaje);
                escritor.newLine();
                escritor.flush();

                try
                {
                    System.out.println("¿Seguir escribiendo más líneas? (1=S, Cualquier otra cosa=N): ");
                    seguir = leer.nextInt();
                    leer.nextLine();
                }

                catch(Exception e)
                {
                    System.out.println("");
                    leer.nextLine();
                    seguir = 0;
                }
            }while(seguir != 0);

            F1.close();
        }
        catch(IOException e)
        {
            System.out.println("Error al abrir el fichero.");
        }
    }
}