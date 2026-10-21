import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente
{

    public static void main(String[] args)
    {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Introduzca la IP: ");
        String direccionIp = teclado.nextLine();
        System.out.println("A continuación, introduzca el puerto: ");
        int puerto = teclado.nextInt();

        try (Socket socket = new Socket(direccionIp, puerto); DataOutputStream salida = new DataOutputStream(socket.getOutputStream()); DataInputStream entrada = new DataInputStream(socket.getInputStream()))
        {
            System.out.println("Primer número: ");
            salida.writeInt(teclado.nextInt());

            System.out.println("Segundo número: ");
            salida.writeInt(teclado.nextInt());

            System.out.println(entrada.readInt());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}