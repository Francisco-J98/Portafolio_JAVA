import objetos.Alumno;
import objetos.Asignatura;
import objetos.ConfiguracionOracle;
import objetos.NotaDeAlumno;
import objetos.NotaDetallada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControladorOracle
{
    private static final String SQL_ALUMNOS = "SELECT DNI, APENOM, DIREC, POBLE, TELEF FROM ALUMNOS";
    private static final String SQL_ASIGNATURAS = "SELECT COD, NOM_ASI FROM ASIGNATURAS";
    private static final String SQL_NOTAS = "SELECT NOTAS.ID_NOTA, ASIGNATURAS.NOM_ASI, ALUMNOS.APENOM, NOTAS.NOTA "+ "FROM NOTAS "+ "INNER JOIN ASIGNATURAS ON NOTAS.C_ASI = ASIGNATURAS.COD "+ "INNER JOIN ALUMNOS ON NOTAS.D_ALU = ALUMNOS.DNI "+ "ORDER BY NOTAS.ID_NOTA ASC";
    private static final String SQL_NOTAS_DE_ALUMNO ="SELECT ALUMNOS.DNI, ALUMNOS.APENOM, ASIGNATURAS.NOM_ASI, NOTAS.NOTA "+ "FROM ALUMNOS "+ "INNER JOIN NOTAS ON ALUMNOS.DNI = NOTAS.D_ALU "+ "INNER JOIN ASIGNATURAS ON NOTAS.C_ASI = ASIGNATURAS.COD " + "WHERE ALUMNOS.DNI = ?";

    @FunctionalInterface
    private interface Mapeador<T>
	{
        T mapear(ResultSet fila) throws SQLException;
    }

    private final String urlJdbc;
    private final String usuario;
    private final String contrasena;

    public ControladorOracle(ConfiguracionOracle configuracion)
	{
        this.urlJdbc = configuracion.construirUrlJdbc();
        this.usuario = configuracion.getUsuario();
        this.contrasena = configuracion.getContrasena();
    }

    public List<Alumno> consultarAlumnos() throws SQLException
	{
        return consultar(SQL_ALUMNOS, fila -> new Alumno(fila.getString("DNI"),fila.getString("APENOM"),fila.getString("DIREC"),fila.getString("POBLE"),fila.getString("TELEF")));
    }

    public List<Asignatura> consultarAsignaturas() throws SQLException
	{
        return consultar(SQL_ASIGNATURAS, fila -> new Asignatura(fila.getInt("COD"),fila.getString("NOM_ASI")));
    }

    public List<NotaDetallada> consultarNotas() throws SQLException
	{
        return consultar(SQL_NOTAS, fila -> new NotaDetallada(fila.getInt("ID_NOTA"),fila.getString("NOM_ASI"),fila.getString("APENOM"),fila.getInt("NOTA")));
    }

    public List<NotaDeAlumno> consultarNotasDeAlumno(String dni) throws SQLException
	{
        return consultar(SQL_NOTAS_DE_ALUMNO, fila -> new NotaDeAlumno(fila.getString("DNI"), fila.getString("APENOM"), fila.getString("NOM_ASI"), fila.getInt("NOTA")), dni);
    }

    private <T> List<T> consultar(String sql, Mapeador<T> mapeador, String... parametros) throws SQLException
	{
        try (Connection conexion = DriverManager.getConnection(urlJdbc, usuario, contrasena); PreparedStatement sentencia = conexion.prepareStatement(sql))
		{
            for (int i = 0; i < parametros.length; i++)
                sentencia.setString(i + 1, parametros[i]);
			
            try (ResultSet resultado = sentencia.executeQuery())
			{
                List<T> filas = new ArrayList<>();
                while (resultado.next())
				{
                    filas.add(mapeador.mapear(resultado));
                }
                return filas;
            }
        }
    }
}