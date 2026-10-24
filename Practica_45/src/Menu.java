import net.xqj.exist.ExistXQDataSource;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;
import java.util.Scanner;

public class Menu
{
    private static final String SERVIDOR = "localhost";
    private static final int PUERTO_MINIMO = 1024;
    private static final int PUERTO_MAXIMO = 65535;
    private static final int OPCION_SALIR = 6;
    private static final int VALOR_INVALIDO = -1;

    private final Scanner teclado = new Scanner(System.in);
    private XQConnection conexion;

    public void iniciar()
	{
        try (teclado)
		{
            int puerto = pedirPuerto();
            conexion = crearConexion(puerto);
            ejecutarBucleMenu();
        }
		catch (XQException e)
		{
            System.err.println("Error al conectar con eXist-db: " + e.getMessage());
        }
		finally
		{
            cerrarConexion();
        }
    }

    private int pedirPuerto()
	{
        int puerto;
        do
		{
            System.out.println(Constantes.PEDIR_PUERTO);
            puerto = leerEntero();
        } while (puerto <= PUERTO_MINIMO || puerto > PUERTO_MAXIMO);
        return puerto;
    }

    private int leerEntero()
	{
        try
		{
            return Integer.parseInt(teclado.nextLine().trim());
        }
		catch (NumberFormatException e)
		{
            return VALOR_INVALIDO;
        }
    }

    private XQConnection crearConexion(int puerto) throws XQException
	{
        XQDataSource origenDatos = new ExistXQDataSource();
        origenDatos.setProperty("serverName", SERVIDOR);
        origenDatos.setProperty("port", String.valueOf(puerto));
        return origenDatos.getConnection();
    }

    private void cerrarConexion()
	{
        if (conexion == null)
            return;
        try
		{
            conexion.close();
        }
		catch (XQException e)
		{
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    private void ejecutarBucleMenu()
	{
        int opcion;
        do
		{
            System.out.println(Constantes.MENU_PRINCIPAL);
            System.out.println(Constantes.PEDIR_OPCION);
            opcion = leerEntero();
            procesarOpcion(opcion);
        } while (opcion != OPCION_SALIR);
    }

    private void procesarOpcion(int opcion)
	{
        switch (opcion)
		{
            case 1 -> ejecutarConsulta(Constantes.CONSULTA_NOMBRE_DIRECCION_SOCIOS);
            case 2 ->
			{
                System.out.println(Constantes.PEDIR_DIRECCION_SOCIO);
                ejecutarConsulta(construirConsultaPorDireccion(teclado.nextLine()));
            }
            case 3 -> ejecutarConsulta(Constantes.CONSULTA_SOCIOS_POR_ANTIGUEDAD);
            case 4 -> ejecutarConsulta(Constantes.CONSULTA_ACTIVIDADES);
            case 5 ->
			{
                System.out.println(Constantes.PEDIR_CONSULTA_PROPIA);
                ejecutarConsulta(teclado.nextLine());
            }
            case OPCION_SALIR -> System.out.println(Constantes.MENSAJE_SALIDA);
            default -> System.out.println(Constantes.MENSAJE_OPCION_INVALIDA);
        }
    }

    // ----- Consultas -----

    private String construirConsultaPorDireccion(String direccion)
	{
        // En XQuery una comilla simple dentro de un literal se escapa duplicándola
        String direccionEscapada = direccion.replace("'", "''");
        return Constantes.CONSULTA_CODIGO_SOCIO_POR_DIRECCION_INICIO + direccionEscapada + Constantes.CONSULTA_CODIGO_SOCIO_POR_DIRECCION_FIN;
    }

    /** Ejecuta una consulta XQuery e imprime cada elemento del resultado. */
    private void ejecutarConsulta(String consulta)
	{
        XQExpression expresion = null;
        try
		{
            expresion = conexion.createExpression();
            XQResultSequence resultado = expresion.executeQuery(consulta);
            
			while (resultado.next())
			{
                System.out.println(resultado.getItemAsString(null));
            }
        }
		catch (XQException e)
		{
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
		finally
		{
            cerrarExpresion(expresion);
        }
    }

    private void cerrarExpresion(XQExpression expresion)
	{
        if (expresion == null)
            return;
        try
		{
            expresion.close();
        }
		catch (XQException e)
		{
            System.err.println("Error al cerrar la expresión: " + e.getMessage());
        }
    }
}