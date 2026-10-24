import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Principal
{
    private static final int PUERTO = 5555;

    public static void main(String[] argumentos)
	{
        try (ServerSocket servidor = new ServerSocket(PUERTO))
		{
            System.out.println("Servidor escuchando en el puerto " + PUERTO);
            while (true)
			{
                Socket socketCliente = servidor.accept();
                new Hilo(socketCliente).start();
            }
        }
		catch (IOException e)
		{
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}