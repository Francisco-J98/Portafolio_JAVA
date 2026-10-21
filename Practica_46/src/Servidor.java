import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Servidor
{
    private static final int PUERTO = 5555;
    public static void main(String[] argumentos)
    {
        int clave = ThreadLocalRandom.current().nextInt(1, 100);
        System.out.println(clave);

        List<Hilo> clientesConectados = new CopyOnWriteArrayList<>();

        try (ServerSocket servidor = new ServerSocket(PUERTO))
        {
            while (true)
            {
                atenderCliente(servidor.accept(), clientesConectados, clave);
            }
        }
        catch (IOException e)
        {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    private static void atenderCliente(Socket socket, List<Hilo> clientesConectados, int clave)
    {
        try
        {
            Hilo hilo = new Hilo(socket, clientesConectados, clave);
            clientesConectados.add(hilo);
            new Thread(hilo).start();
        }
        catch (IOException e)
        {
            System.out.println("No se pudo atender al cliente: " + e.getMessage());
            try
            {
                socket.close();
            }
            catch (IOException ignorada)
            {
                // Ya se ha informado del fallo principal
            }
        }
    }
}