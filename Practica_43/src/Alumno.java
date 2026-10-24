import com.google.gson.annotations.SerializedName;

public class Alumno
{
    private final String dni;
    @SerializedName("apenom")
    private final String apellidosNombre;
    @SerializedName("direct")
    private final String direccion;
    @SerializedName("poble")
    private final String poblacion;
    @SerializedName("telef")
    private final String telefono;

    public Alumno(String dni, String apellidosNombre, String direccion, String poblacion, String telefono)
	{
        this.dni = dni;
        this.apellidosNombre = apellidosNombre;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.telefono = telefono;
    }

    public String getDni()
	{
        return dni;
    }

    public String getApellidosNombre()
	{
        return apellidosNombre;
    }

    public String getDireccion()
	{
        return direccion;
    }

    public String getPoblacion()
	{
        return poblacion;
    }

    public String getTelefono()
	{
        return telefono;
    }
}