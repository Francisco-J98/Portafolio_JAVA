import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Hilo extends Thread
{

    private final Socket socketCliente;

    public Hilo(Socket socketCliente)
    {
        this.socketCliente = socketCliente;
    }

    @Override
    public void run()
    {
        multiplicar();
    }

    private void multiplicar()
    {
        System.out.println("Conexión establecida");

        try (Socket socket = socketCliente; DataInputStream entrada = new DataInputStream(socket.getInputStream()); DataOutputStream salida = new DataOutputStream(socket.getOutputStream()))
        {
            int primerNumero = entrada.readInt();
            int segundoNumero = entrada.readInt();
            salida.writeInt(primerNumero * segundoNumero);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}