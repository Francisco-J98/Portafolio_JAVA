public final class Constantes
{
    private Constantes()
	{
        // Clase de utilidad: no se instancia
    }

    // ----- Textos de la interfaz -----

    public static final String PEDIR_PUERTO = "Escoge el puerto al que quiere conectarse (>1024~8080)";

    public static final String MENU_PRINCIPAL = String.join(System.lineSeparator(),
            "--------------------MENU--------------------",
            "|1)Mostrar nombre y dirección de socios.   |",
            "|2)Mostrar cód. de socios según dirección. |",
            "|3)Ordenar socios por antigüedad.          |",
            "|4)Mostrar actividades disponibles.        |",
            "|5)Ejecutar consulta propia.               |",
            "|6)Salir.                                  |",
            "|__________________________________________|");

    public static final String PEDIR_OPCION = "Escoge una opción (1~6): ";
    public static final String MENSAJE_SALIDA = "Has salido del programa.";
    public static final String PEDIR_DIRECCION_SOCIO = "Escribe la dirección del socio a buscar: ";
    public static final String PEDIR_CONSULTA_PROPIA = "Escribe la consulta a realizar deseada: ";
    public static final String MENSAJE_OPCION_INVALIDA = "Opción no válida.";

    // ----- Consultas XQuery -----

    public static final String CONSULTA_NOMBRE_DIRECCION_SOCIOS = "for $socio in /SOCIOS_GIM/fila_socios " + "return <SOCIO>{<Nombre>{$socio/NOMBRE/text()}</Nombre>," + "<Direccion>{$socio/DIRECCION/text()}</Direccion>}</SOCIO>";
    public static final String CONSULTA_CODIGO_SOCIO_POR_DIRECCION_INICIO = "for $socio in /SOCIOS_GIM/fila_socios where $socio/DIRECCION ='";
    public static final String CONSULTA_CODIGO_SOCIO_POR_DIRECCION_FIN = "' return <COD_SOCIO>{$socio/COD/text()}</COD_SOCIO>";
    public static final String CONSULTA_SOCIOS_POR_ANTIGUEDAD = "for $socio in /SOCIOS_GIM/fila_socios " + "order by $socio/FECHA_ALT/@value/string() " + "return <SOCIO>{$socio/*}</SOCIO>";
    public static final String CONSULTA_ACTIVIDADES = "for $actividad in /ACTIVIDADES_GIM/fila_actividades " + "return <Actividad>{$actividad/NOMBRE/text()}</Actividad>";
}