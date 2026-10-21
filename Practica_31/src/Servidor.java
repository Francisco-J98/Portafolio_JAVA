import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor
{
    private static final int PUERTO = 5555;

    public static void main(String[] args)
    {
        try (ServerSocket socketServidor = new ServerSocket(PUERTO))
        {
            System.out.println("¡Arrancando servidor!");

            while (true)
            {
                Socket socketCliente = socketServidor.accept();
                new Hilo(socketCliente).start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}