import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Hilo extends Thread
{
    private static final String MENSAJE = "HOLA";
    private final Socket socketCliente;

    public Hilo(Socket socketCliente)
	{
        this.socketCliente = socketCliente;
    }

    @Override
    public void run()
	{
        try (socketCliente; DataOutputStream salida = new DataOutputStream(socketCliente.getOutputStream()))
		{
            while (!socketCliente.isClosed())
			{
                salida.writeUTF(MENSAJE);
            }
        }
		catch (IOException e)
		{
            System.out.println("Cliente desconectado: " + e.getMessage());
        }
    }
}