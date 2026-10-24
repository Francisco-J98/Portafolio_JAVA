import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Controlador
{

    public static final String PAGADA = "S";
    public static final String NO_PAGADA = "N";

    private static final String URL_BBDD = "jdbc:sqlite:DGT.db";
    private static final int ERROR_SQL = -1;

    private static final String SQL_CREAR_LOCALIDADES = "CREATE TABLE IF NOT EXISTS Localidades (" + "IDLoc INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT DEFAULT 1, " + "NombreL VARCHAR(20))";

    private static final String SQL_CREAR_AGENTES = "CREATE TABLE IF NOT EXISTS Agentes (" + "IDAge INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT DEFAULT 1, " + "NombreA VARCHAR(50))";

    private static final String SQL_CREAR_MULTAS = """
            CREATE TABLE IF NOT EXISTS Multas (
                ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT DEFAULT 1,
                Coste REAL DEFAULT 0,
                Pagada VARCHAR(1) NOT NULL DEFAULT 'N',
                IDAgente INTEGER NOT NULL,
                IDLocalidad INTEGER NOT NULL,
                CONSTRAINT fk_Loc FOREIGN KEY(IDLocalidad) REFERENCES Localidades(IDLoc) ON DELETE CASCADE,
                CONSTRAINT fk_Age FOREIGN KEY(IDAgente) REFERENCES Agentes(IDAge) ON DELETE CASCADE)
            """;

    private static final String SQL_INSERTAR_MULTA = "INSERT INTO Multas (Coste, Pagada, IDAgente, IDLocalidad) VALUES (?, ?, ?, ?)";

    private static final String MENSAJE_ALTA_CORRECTA = "Datos añadidos satisfactoriamente.";
    private static final String MENSAJE_ERROR_ALTA = "Error al agregar datos de la BBDD.\nEl registro podría ya estar agregado.";
    private static final String MENSAJE_ERROR_CONSULTA = "Error al consultar datos de la BBDD.";
    private static final String MENSAJE_ERROR_MODIFICACION = "Error al modificar los datos.";
    private static final String MENSAJE_SIN_COINCIDENCIAS = "No existe ningún registro que coincida con los datos indicados.";

    @FunctionalInterface
    private interface AsignadorParametros
	{
        void asignar(PreparedStatement sentencia) throws SQLException;
    }

    public Controlador()
	{
        crearTablas();
    }

    // ----- Creación de la base de datos -----

    private void crearTablas()
	{
        try (Connection conexion = abrirConexion(); Statement sentencia = conexion.createStatement())
		{
            sentencia.executeUpdate(SQL_CREAR_LOCALIDADES);
            sentencia.executeUpdate(SQL_CREAR_AGENTES);
            sentencia.executeUpdate(SQL_CREAR_MULTAS);
        }
		catch (SQLException e)
		{
            System.out.println("Error al crear las tablas de la BBDD: " + e.getMessage());
        }
    }

    // ----- Altas -----

    public void agregarAgente(String nombre)
	{
        insertarNombre(Tabla.AGENTES, nombre);
    }

    public void agregarLocalidad(String nombre)
	{
        insertarNombre(Tabla.LOCALIDADES, nombre);
    }

    public void agregarMulta(double coste, String pagada, int idAgente, int idLocalidad)
	{
        int filas = ejecutarEscritura(SQL_INSERTAR_MULTA, MENSAJE_ERROR_ALTA, sentencia -> {
            sentencia.setDouble(1, coste);
            sentencia.setString(2, pagada);
            sentencia.setInt(3, idAgente);
            sentencia.setInt(4, idLocalidad);
        });
        informarResultado(filas, MENSAJE_ALTA_CORRECTA);
    }

    private void insertarNombre(Tabla tabla, String nombre)
	{
        String sql = "INSERT INTO " + tabla.getNombre() + " (" + tabla.getColumnaNombre() + ") VALUES (?)";
        int filas = ejecutarEscritura(sql, MENSAJE_ERROR_ALTA, sentencia -> sentencia.setString(1, nombre));
        informarResultado(filas, MENSAJE_ALTA_CORRECTA);
    }

    // ----- Bajas -----

    /** Borra un registro y, si es un agente o una localidad, también sus multas (en una transacción). */
    public void eliminar(Tabla tabla, int id)
	{
        try
		{
            if (eliminarConDependencias(tabla, id))
                System.out.println("Datos borrados satisfactoriamente.");
			else
                System.out.println(MENSAJE_SIN_COINCIDENCIAS);
			
        }
		
		catch (SQLException e)
		{
            System.out.println("Error al eliminar datos de la BBDD.");
            System.out.println("El registro podría ya estar borrado.");
        }
    }

    private boolean eliminarConDependencias(Tabla tabla, int id) throws SQLException {
        try (Connection conexion = abrirConexion()) {
            conexion.setAutoCommit(false);
            try {
                int filasBorradas = borrarPorColumna(conexion, tabla.getNombre(), tabla.getColumnaId(), id);
                if (filasBorradas == 0) {
                    conexion.rollback();
                    return false;
                }
                if (tabla.getColumnaEnMultas() != null) {
                    borrarPorColumna(conexion, Tabla.MULTAS.getNombre(), tabla.getColumnaEnMultas(), id);
                }
                conexion.commit();
                return true;
            } catch (SQLException e) {
                conexion.rollback();
                throw e;
            }
        }
    }

    private int borrarPorColumna(Connection conexion, String tabla, String columna, int id) throws SQLException
	{
        String sql = "DELETE FROM " + tabla + " WHERE " + columna + " = ?";
        try (PreparedStatement sentencia = conexion.prepareStatement(sql))
		{
            sentencia.setInt(1, id);
            return sentencia.executeUpdate();
        }
    }

    // ----- Consultas -----

    public void consultarMultaPorId(int idMulta)
	{
        consultarMultas("SELECT * FROM Multas WHERE ID = ?", sentencia -> sentencia.setInt(1, idMulta));
    }

    /** Muestra las multas pagadas ({@link #PAGADA}) o no pagadas ({@link #NO_PAGADA}). */
    public void consultarMultasPorPago(String estadoPago)
	{
        consultarMultas("SELECT * FROM Multas WHERE Pagada = ?", sentencia -> sentencia.setString(1, estadoPago));
    }

    /** Muestra las multas ordenadas por coste, de mayor a menor. */
    public void consultarMultasOrdenadasPorCoste()
	{
        consultarMultas("SELECT * FROM Multas ORDER BY Coste DESC", sentencia -> { });
    }

    public void consultarMultasPorAgente(String nombreAgente)
	{
        consultarMultasPorNombre(Tabla.AGENTES, nombreAgente, "Éstas son las sanciones llevadas a cabo por el Agente " + nombreAgente + ":");
    }

    public void consultarMultasPorLocalidad(String nombreLocalidad)
	{
        consultarMultasPorNombre(Tabla.LOCALIDADES, nombreLocalidad, "Éstas son las sanciones llevadas a cabo en la Localidad de " + nombreLocalidad + ":");
    }

    private void consultarMultasPorNombre(Tabla tabla, String nombre, String encabezado)
	{
        String sql = "SELECT Multas.* FROM Multas INNER JOIN " + tabla.getNombre() + " ON " + tabla.getNombre() + "." + tabla.getColumnaId() + " = Multas." + tabla.getColumnaEnMultas() + " WHERE " + tabla.getNombre() + "." + tabla.getColumnaNombre() + " = ?";
        System.out.println(encabezado);
        consultarMultas(sql, sentencia -> sentencia.setString(1, nombre));
    }

    private void consultarMultas(String sql, AsignadorParametros asignador)
	{
        try (Connection conexion = abrirConexion(); PreparedStatement sentencia = conexion.prepareStatement(sql))
		{
            asignador.asignar(sentencia);
            try (ResultSet resultado = sentencia.executeQuery())
			{
                boolean hayResultados = false;
                while (resultado.next())
				{
                    hayResultados = true;
                    System.out.printf("ID: %d Precio: %.2f€ Pagada: %s%n", resultado.getInt("ID"), resultado.getDouble("Coste"), resultado.getString("Pagada"));
                }
				
                if (!hayResultados)
                    System.out.println("No se han encontrado multas.");
            }
        }
		catch (SQLException e)
		{
            System.out.println(MENSAJE_ERROR_CONSULTA);
        }
    }

    // ----- Modificaciones -----

    public void modificarCosteMulta(int idMulta, double nuevoCoste)
	{
        int filas = ejecutarEscritura("UPDATE Multas SET Coste = ? WHERE ID = ?", "Error al modificar el coste de la sanción.", sentencia -> { sentencia.setDouble(1, nuevoCoste); sentencia.setInt(2, idMulta); });
        informarResultado(filas, "Coste de la multa cambiado satisfactoriamente.");
    }

    public void renombrarAgente(int idAgente, String nuevoNombre)
	{
        renombrar(Tabla.AGENTES, idAgente, nuevoNombre, "Nombre del Agente cambiado satisfactoriamente.");
    }

    public void renombrarLocalidad(int idLocalidad, String nuevoNombre)
	{
        renombrar(Tabla.LOCALIDADES, idLocalidad, nuevoNombre, "Nombre de la Localidad cambiada satisfactoriamente.");
    }

    private void renombrar(Tabla tabla, int id, String nuevoNombre, String mensajeExito)
	{
        String sql = "UPDATE " + tabla.getNombre() + " SET " + tabla.getColumnaNombre() + " = ? WHERE " + tabla.getColumnaId() + " = ?";
        int filas = ejecutarEscritura(sql, MENSAJE_ERROR_MODIFICACION, sentencia -> {
            sentencia.setString(1, nuevoNombre);
            sentencia.setInt(2, id);
        });
        informarResultado(filas, mensajeExito);
    }

    public void cambiarAgenteDeMulta(int idMulta, int idAgenteAntiguo, int idAgenteNuevo)
	{
        cambiarReferenciaDeMulta(Tabla.AGENTES, idMulta, idAgenteAntiguo, idAgenteNuevo, "Agente a cargo cambiado satisfactoriamente.");
    }

    public void cambiarLocalidadDeMulta(int idMulta, int idLocalidadAntigua, int idLocalidadNueva)
	{
        cambiarReferenciaDeMulta(Tabla.LOCALIDADES, idMulta, idLocalidadAntigua, idLocalidadNueva, "Ubicación cambiada satisfactoriamente.");
    }

    private void cambiarReferenciaDeMulta(Tabla tablaReferenciada, int idMulta, int idAntiguo, int idNuevo, String mensajeExito)
	{
        String columna = tablaReferenciada.getColumnaEnMultas();
        String sql = "UPDATE Multas SET " + columna + " = ? WHERE " + columna + " = ? AND ID = ?";
        int filas = ejecutarEscritura(sql, MENSAJE_ERROR_MODIFICACION, sentencia -> {
            sentencia.setInt(1, idNuevo);
            sentencia.setInt(2, idAntiguo);
            sentencia.setInt(3, idMulta);
        });
        informarResultado(filas, mensajeExito);
    }

    /** Cambia el estado de pago de una multa ({@link #PAGADA} o {@link #NO_PAGADA}). */
    public void modificarPagoMulta(int idMulta, String nuevoEstado)
	{
        try (Connection conexion = abrirConexion())
		{
            String estadoActual = obtenerEstadoPago(conexion, idMulta);
            
			if (estadoActual == null)
                System.out.println(MENSAJE_SIN_COINCIDENCIAS);
			
			else if (estadoActual.equals(nuevoEstado))
                System.out.println("La multa ya estaba marcada como " + describirPago(nuevoEstado) + ".");
			
			else
			{
                actualizarEstadoPago(conexion, idMulta, nuevoEstado);
                System.out.println("Multa marcada como " + describirPago(nuevoEstado) + ".");
            }
        }
		catch (SQLException e)
		{
            System.out.println(MENSAJE_ERROR_MODIFICACION);
        }
    }

    private String obtenerEstadoPago(Connection conexion, int idMulta) throws SQLException
	{
        try (PreparedStatement sentencia = conexion.prepareStatement("SELECT Pagada FROM Multas WHERE ID = ?"))
		{
            sentencia.setInt(1, idMulta);
            try (ResultSet resultado = sentencia.executeQuery())
			{
                return resultado.next() ? resultado.getString("Pagada") : null;
            }
        }
    }

    private void actualizarEstadoPago(Connection conexion, int idMulta, String nuevoEstado) throws SQLException
	{
        try (PreparedStatement sentencia = conexion.prepareStatement("UPDATE Multas SET Pagada = ? WHERE ID = ?"))
		{
            sentencia.setString(1, nuevoEstado);
            sentencia.setInt(2, idMulta);
            sentencia.executeUpdate();
        }
    }

    private String describirPago(String estado)
	{
        return PAGADA.equals(estado) ? "pagada" : "no pagada";
    }

    // ----- Reajuste de identificadores -----

    /**
     * Renumera los ID de una tabla de forma consecutiva (1, 2, 3...). Si es Agentes o Localidades,
     * actualiza también las multas que los referencian para no dejar datos huérfanos.
     */
    public void reajustarIds(Tabla tabla)
	{
        try (Connection conexion = abrirConexion())
		{
            conexion.setAutoCommit(false);
            try
			{
                renumerar(conexion, tabla);
                conexion.commit();
                System.out.println("IDs de la tabla " + tabla.getNombre() + " reajustados satisfactoriamente.");
            }
			catch (SQLException e)
			{
                conexion.rollback();
                throw e;
            }
        }
		catch (SQLException e)
		{
            System.out.println("Error al manipular datos de la BBDD.");
        }
    }

    private void renumerar(Connection conexion, Tabla tabla) throws SQLException
	{
        String columnaId = tabla.getColumnaId();
        List<Integer> idsActuales = new ArrayList<>();

        String sqlIds = "SELECT " + columnaId + " FROM " + tabla.getNombre() + " ORDER BY " + columnaId;
        try (Statement sentencia = conexion.createStatement(); ResultSet resultado = sentencia.executeQuery(sqlIds))
		{
            while (resultado.next())
			{
                idsActuales.add(resultado.getInt(1));
            }
        }

        String sqlActualizarId = "UPDATE " + tabla.getNombre() + " SET " + columnaId + " = ? WHERE " + columnaId + " = ?";
        String columnaEnMultas = tabla.getColumnaEnMultas();
        String sqlActualizarReferencias = columnaEnMultas == null ? null : "UPDATE Multas SET " + columnaEnMultas + " = ? WHERE " + columnaEnMultas + " = ?";

        try (PreparedStatement actualizarId = conexion.prepareStatement(sqlActualizarId); PreparedStatement actualizarReferencias = sqlActualizarReferencias == null ? null : conexion.prepareStatement(sqlActualizarReferencias))
		{
            for (int posicion = 0; posicion < idsActuales.size(); posicion++)
			{
                int idNuevo = posicion + 1;
                int idAntiguo = idsActuales.get(posicion);
                
				if (idNuevo == idAntiguo)
                    continue;
                
				actualizarId.setInt(1, idNuevo);
                actualizarId.setInt(2, idAntiguo);
                actualizarId.executeUpdate();

                if (actualizarReferencias != null
				{
                    actualizarReferencias.setInt(1, idNuevo);
                    actualizarReferencias.setInt(2, idAntiguo);
                    actualizarReferencias.executeUpdate();
                }
            }
        }

        // Para que el siguiente AUTOINCREMENT continúe desde el último ID real
        try (PreparedStatement secuencia = conexion.prepareStatement("UPDATE sqlite_sequence SET seq = ? WHERE name = ?"))
		{
            secuencia.setInt(1, idsActuales.size());
            secuencia.setString(2, tabla.getNombre());
            secuencia.executeUpdate();
        }
    }

    // ----- Utilidades -----

    private Connection abrirConexion() throws SQLException
	{
        return DriverManager.getConnection(URL_BBDD);
    }

    /** Ejecuta un INSERT/UPDATE. Devuelve las filas afectadas o {@link #ERROR_SQL} si falla. */
    private int ejecutarEscritura(String sql, String mensajeError, AsignadorParametros asignador)
	{
        try (Connection conexion = abrirConexion(); PreparedStatement sentencia = conexion.prepareStatement(sql))
		{
            asignador.asignar(sentencia);
            return sentencia.executeUpdate();
        }
		catch (SQLException e)
		{
            System.out.println(mensajeError);
            return ERROR_SQL;
        }
    }

    private void informarResultado(int filasAfectadas, String mensajeExito)
	{
        if (filasAfectadas == ERROR_SQL)
            return; // el error ya se ha comunicado al usuario
        System.out.println(filasAfectadas > 0 ? mensajeExito : MENSAJE_SIN_COINCIDENCIAS);
    }
}