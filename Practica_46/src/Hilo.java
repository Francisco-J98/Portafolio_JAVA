import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/** Atiende a un cliente: le envía la clave y reenvía a todos los clientes cada mensaje que recibe. */
public class Hilo implements Runnable
{
    private final Socket socket;
    private final List<Hilo> clientesConectados;
    private final DataOutputStream salida;

    public Hilo(Socket socket, List<Hilo> clientesConectados, int clave) throws IOException
    {
        this.socket = socket;
        this.clientesConectados = clientesConectados;
        this.salida = new DataOutputStream(socket.getOutputStream());

        // Se envía en el constructor, antes de que el hilo esté en la lista y pueda recibir difusiones
        this.salida.writeInt(clave);
    }

    @Override
    public void run()
    {
        try (DataInputStream entrada = new DataInputStream(socket.getInputStream()))
        {
            while (true)
            {
                String mensajeCifrado = entrada.readUTF();
                System.out.println("Mensaje cifrado: " + mensajeCifrado);
                difundir(mensajeCifrado);
            }
        }
        catch (IOException e)
        {
            System.out.println("Cliente desconectado: " + socket.getRemoteSocketAddress());
        }
        finally
        {
            clientesConectados.remove(this);
        }
    }

    private void difundir(String mensajeCifrado)
    {
        for (Hilo cliente : clientesConectados)
            cliente.enviar(mensajeCifrado);
    }

    /** Sincronizado: varios hilos pueden escribir en el mismo cliente a la vez. */
    private synchronized void enviar(String mensajeCifrado)
    {
        try
        {
            salida.writeUTF(mensajeCifrado);
        }
        catch (IOException e)
        {
            System.out.println("No se pudo enviar el mensaje a " + socket.getRemoteSocketAddress());
        }
    }
}