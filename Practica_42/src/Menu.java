import java.util.Scanner;

public class Menu
{

    private static final int OPCION_SALIR = 6;
    private static final int OPCION_CANCELAR_ELIMINAR = 4;
    private static final int OPCION_CANCELAR_MODIFICAR = 7;
    private static final int VALOR_INVALIDO = -1;

    private static final String MENSAJE_OPCION_INVALIDA = "Opción no válida.";
    private static final String MENSAJE_VALOR_INVALIDO = "Valor no válido, inténtalo de nuevo.";
    private static final String MENSAJE_NOMBRE_INVALIDO = "El nombre no puede estar vacío ni contener números.";
    private static final String PREGUNTA_PAGADA = "¿La multa ha sido pagada (S/N)? ";

    private static final String MENU_PRINCIPAL = """
            -------MENÚ PRINCIPAL-------
            | 1) AGREGAR REG.          |
            | 2) ELIMINAR REG.         |
            | 3) CONSULTAR REG.        |
            | 4) MODIFICAR REG.        |
            | 5) ACT. ID TABLAS.       |
            | 6) SALIR.                |
            |__________________________|""";

    private static final String SUBMENU_AGREGAR = """
            --------------SUBMENÚ--------------
            | 1) Agregar Agentes.             |
            | 2) Agregar Localidades.         |
            | 3) Agregar Multas.              |
            |_________________________________|""";

    private static final String AVISO_ELIMINAR = """
            Recuerde: Si borra un agente o una localidad de la base de datos,
            las multas que compartan dicho dato se borrarán. Tenga cuidado.""";

    private static final String SUBMENU_ELIMINAR = """
            --------------SUBMENÚ--------------
            | 1) Eliminar Agentes.            |
            | 2) Eliminar Localidades.        |
            | 3) Eliminar Multas.             |
            | 4) Cancelar.                    |
            |_________________________________|""";

    private static final String SUBMENU_CONSULTAR = """
            --------------SUBMENÚ--------------
            | 1) Multas por Agente.           |
            | 2) Multas por Localidad.        |
            | 3) Multas por ID.               |
            | 4) Multas ordenadas por Coste.  |
            | 5) Multas Pagadas.              |
            | 6) Multas No Pagadas            |
            |_________________________________|""";

    private static final String SUBMENU_MODIFICAR = """
            --------------SUBMENÚ--------------
            | 1) ¿Multa Pagada?               |
            | 2) Coste Multa.                 |
            | 3) Agente a cargo en multa.     |
            | 4) Localidad en multa.          |
            | 5) Nombre del Agente.           |
            | 6) Nombre de la Localidad.      |
            | 7) Cancelar.                    |
            |_________________________________|""";

    private static final String SUBMENU_REAJUSTAR_IDS = """
            -------------SUBMENÚ-------------
            | 1) Tabla Agentes.             |
            | 2) Tabla Localidades.         |
            | 3) Tabla Multas.              |
            |_______________________________|""";

    private final Controlador controlador = new Controlador();
    private final Scanner teclado = new Scanner(System.in);

    public void iniciar()
	{
        try (teclado)
		{
            int opcion;
            do
			{
                System.out.println(MENU_PRINCIPAL);
                opcion = pedirOpcion("Escoge una opción (1~6): ", 1, OPCION_SALIR);
                switch (opcion)
				{
                    case 1 ->
					{
                        System.out.println("Ha escogido: agregar registros.");
                        menuAgregar();
                    }
                    case 2 ->
					{
                        System.out.println("Ha escogido: eliminar registros.");
                        menuEliminar();
                    }
                    case 3 ->
					{
                        System.out.println("Ha escogido: realizar consultas.");
                        menuConsultar();
                    }
                    case 4 ->
					{
                        System.out.println("Ha escogido: modificar registros.");
                        menuModificar();
                    }
                    case 5 ->
					{
                        System.out.println("Ha escogido: actualizar tablas.");
                        menuReajustarIds();
                    }
                    case OPCION_SALIR -> System.out.println("Ha salido del programa.");
                }
            } while (opcion != OPCION_SALIR);
        }
    }

    // ----- Submenús -----

    private void menuAgregar()
	{
        System.out.println(SUBMENU_AGREGAR);
        int opcion = pedirOpcion("Escoge una opción (1~3): ", 1, 3);
        switch (opcion)
		{
            case 1 -> controlador.agregarAgente(pedirNombre("Introduzca nombre del agente: "));
            case 2 -> controlador.agregarLocalidad(pedirNombre("Introduzca nombre de la localidad: "));
            case 3 -> agregarMulta();
        }
    }

    private void agregarMulta()
	{
        int idAgente = pedirEnteroPositivo("Introduce ID del agente que va a sancionar: ");
        int idLocalidad = pedirEnteroPositivo("Introduce ID de la localidad en donde se ha producido la sanción: ");
        double coste = pedirImporte("Introduce el coste de la sanción: ");
        String pagada = pedirSiNo("¿La multa ha sido pagada en el acto (S/N)? ");
        controlador.agregarMulta(coste, pagada, idAgente, idLocalidad);
    }

    private void menuEliminar()
	{
        System.out.println(AVISO_ELIMINAR);
        System.out.println(SUBMENU_ELIMINAR);
        int opcion = pedirOpcion("Escoge una opción (1~4): ", 1, OPCION_CANCELAR_ELIMINAR);
        
		if (opcion == OPCION_CANCELAR_ELIMINAR)
            return;
        
		int id = pedirEnteroPositivo("Introduce ID que desee borrar: ");
        controlador.eliminar(tablaDeOpcion(opcion), id);
    }

    private void menuConsultar()
	{
        System.out.println(SUBMENU_CONSULTAR);
        int opcion = pedirOpcion("Escoge una opción (1~6): ", 1, 6);
        switch (opcion)
		{
            case 1 -> controlador.consultarMultasPorAgente(pedirNombre("Nombre del Agente: "));
            case 2 -> controlador.consultarMultasPorLocalidad(pedirNombre("Nombre de la Localidad: "));
            case 3 -> controlador.consultarMultaPorId(pedirEnteroPositivo("Introduce ID de Multa: "));
            case 4 -> controlador.consultarMultasOrdenadasPorCoste();
            case 5 -> controlador.consultarMultasPorPago(Controlador.PAGADA);
            case 6 -> controlador.consultarMultasPorPago(Controlador.NO_PAGADA);
        }
    }

    private void menuModificar()
	{
        System.out.println(SUBMENU_MODIFICAR);
        int opcion = pedirOpcion("Escoge una opción (1~7): ", 1, OPCION_CANCELAR_MODIFICAR);
        
		if (opcion == OPCION_CANCELAR_MODIFICAR)
            return;
		
        int id = pedirEnteroPositivo("Introduce ID que desee modificar: ");
        switch (opcion)
		{
            case 1 -> controlador.modificarPagoMulta(id, pedirSiNo(PREGUNTA_PAGADA));
            case 2 -> controlador.modificarCosteMulta(id, pedirImporte("Introduce el nuevo coste de la sanción: "));
            case 3 -> cambiarAgenteDeMulta(id);
            case 4 -> cambiarLocalidadDeMulta(id);
            case 5 -> controlador.renombrarAgente(id, pedirNombre("Introduzca nombre del agente: "));
            case 6 -> controlador.renombrarLocalidad(id, pedirNombre("Introduzca nombre de la localidad: "));
        }
    }

    private void cambiarAgenteDeMulta(int idMulta)
	{
        int idAntiguo = pedirEnteroPositivo("Introduce ID del antiguo agente a cargo: ");
        int idNuevo = pedirEnteroPositivo("Introduce ID del nuevo agente a cargo: ");
        controlador.cambiarAgenteDeMulta(idMulta, idAntiguo, idNuevo);
    }

    private void cambiarLocalidadDeMulta(int idMulta)
	{
        int idAntigua = pedirEnteroPositivo("Introduce ID de la antigua localidad: ");
        int idNueva = pedirEnteroPositivo("Introduce ID de la nueva localidad: ");
        controlador.cambiarLocalidadDeMulta(idMulta, idAntigua, idNueva);
    }

    private void menuReajustarIds()
	{
        System.out.println(SUBMENU_REAJUSTAR_IDS);
        int opcion = pedirOpcion("Escoge una opción (1~3): ", 1, 3);
        controlador.reajustarIds(tablaDeOpcion(opcion));
    }

    /** Las opciones 1, 2 y 3 de los submenús corresponden a Agentes, Localidades y Multas. */
    private Tabla tablaDeOpcion(int opcion)
	{
        return Tabla.values()[opcion - 1];
    }

    // ----- Lectura y validación de datos -----

    /** Lee una línea y la convierte a entero; devuelve -1 si no es un número válido. */
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

    private int pedirOpcion(String mensaje, int minimo, int maximo)
	{
        while (true)
		{
            System.out.println(mensaje);
            int opcion = leerEntero();
            
			if (opcion >= minimo && opcion <= maximo)
                return opcion;
            
			System.out.println(MENSAJE_OPCION_INVALIDA);
        }
    }

    private int pedirEnteroPositivo(String mensaje)
	{
        while (true)
		{
            System.out.println(mensaje);
            int valor = leerEntero();
            
			if (valor > 0)
                return valor;
				
            System.out.println(MENSAJE_VALOR_INVALIDO);
        }
    }

    /** Pide un importe positivo (acepta coma o punto decimal) redondeado a 2 decimales. */
    private double pedirImporte(String mensaje)
	{
        while (true)
		{
            System.out.println(mensaje);
            double leido = parsearDecimal(teclado.nextLine());
            if (Double.isFinite(leido))
			{
                double redondeado = Math.round(leido * 100) / 100.0;
                if (redondeado > 0)
                    return redondeado;
            }
            System.out.println(MENSAJE_VALOR_INVALIDO);
        }
    }

    private static double parsearDecimal(String texto)
	{
        try
		{
            return Double.parseDouble(texto.trim().replace(',', '.'));
        }
		catch (NumberFormatException e)
		{
            return Double.NaN;
        }
    }

    /** Pide S o N (en mayúscula o minúscula) y devuelve la constante correspondiente. */
    private String pedirSiNo(String mensaje)
	{
        while (true)
		{
            System.out.println(mensaje);
            String respuesta = teclado.nextLine().trim().toUpperCase();
			
            if (respuesta.startsWith(Controlador.PAGADA))
                return Controlador.PAGADA;
			
            if (respuesta.startsWith(Controlador.NO_PAGADA))
                return Controlador.NO_PAGADA;
			
            System.out.println(MENSAJE_VALOR_INVALIDO);
        }
    }

    /** Pide un nombre sin dígitos y con la primera letra en mayúscula. */
    private String pedirNombre(String mensaje)
	{
        while (true)
		{
            System.out.println(mensaje);
            String nombre = teclado.nextLine().trim();
			
            if (!nombre.isEmpty() && nombre.chars().noneMatch(Character::isDigit))
                return Character.toUpperCase(nombre.charAt(0)) + nombre.substring(1);
			
            System.out.println(MENSAJE_NOMBRE_INVALIDO);
        }
    }
}