import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class OpBBDD
{

	String url="";
	
	public OpBBDD(String db)
	{
		url="jdbc:sqlite:"+db;
	}
	
	protected void crearBBDD()
	{
		Connection conexion=null;
		String sentencia = "CREATE TABLE IF NOT EXISTS Ropa (Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Talla INTEGER, Color VARCHAR2(20));";
		
		try
		{
			conexion = DriverManager.getConnection(url,"","");
			Statement os = conexion.createStatement();
			os.executeUpdate(sentencia);
			os.close();
			conexion.close();
		}
		catch (SQLException e)
		{
			System.out.println("Error al crear la tabla en la BBDD.");
		}
	}
	
	protected void agregar(Ropa R1)
	{
		Connection conexion = null;
		String consulta="INSERT INTO Ropa(Talla,Color) VALUES(?,?)";
		
		try
		{
			conexion = DriverManager.getConnection(url,"","");
			PreparedStatement ps = conexion.prepareStatement(consulta);
			ps.setInt(1, R1.getTalla());
			ps.setString(2, R1.getColor());
			ps.executeUpdate();
			ps.close();
			conexion.close();
		}
		catch (SQLException e)
		{
			System.out.println("Error al agregar datos en la BBDD.");
		}
	}
	
	protected void modificarT(int n, int m) //SIN HACER
	{
		
	}
	
	protected void modificarC(int n,String s) //SIN HACER
	{
		
	}
	
	protected void eliminar(int n) //SIN HACER
	{
		
	}
	
	protected void consultarID(int n) //SIN HACER
	{
		
	}
	
	protected void consultarT() //SIN HACER
	{
		
	}
	
	private void actualizableBBDD() //SIN HACER
	{
		
	}
	
	protected boolean comprobarBBDD() //SIN HACER
	{
		boolean bandera=false;
		
		return(bandera);
	}
	
	protected int contRegBBDD() //SIN HACER
	{
		int id=0;
		
		return(id);
	}
}