import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente
{
    private static final String DIRECCION_SERVIDOR = "localhost";
    private static final int PUERTO = 5555;

    public static void main(String[] argumentos)
    {
        try (Socket socket = new Socket(DIRECCION_SERVIDOR, PUERTO); DataInputStream entrada = new DataInputStream(socket.getInputStream()); DataOutputStream salida = new DataOutputStream(socket.getOutputStream()); Scanner teclado = new Scanner(System.in))
        {

            int clave = entrada.readInt();
            iniciarReceptor(entrada, clave);

            while (true)
            {
                System.out.println("Introduzca el mensaje a cifrar: ");
                if (!teclado.hasNextLine())
                    break;

                salida.writeUTF(Cesar.cifrar(teclado.nextLine(), clave));
            }
        }
        catch (IOException e)
        {
            System.out.println("Error de comunicación con el servidor: " + e.getMessage());
        }
    }

    /** Hilo que muestra los mensajes recibidos sin bloquear la escritura por teclado. */
    private static void iniciarReceptor(DataInputStream entrada, int clave)
    {
        Thread receptor = new Thread(() ->
        {
            try
            {
                while (true)
                {
                    System.out.println(Cesar.descifrar(entrada.readUTF(), clave));
                }
            }
            catch (IOException e)
            {
                System.out.println("Conexión cerrada.");
            }
        });
        receptor.setDaemon(true);
        receptor.start();
    }
}