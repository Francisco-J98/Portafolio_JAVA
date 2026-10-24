import com.google.gson.annotations.SerializedName;

public class Asignatura
{
    @SerializedName("Codigo")
    private final int codigo;
    @SerializedName("NombAsi")
    private final String nombre;

    public Asignatura(int codigo, String nombre)
	{
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo()
	{
        return codigo;
    }

    public String getNombre()
	{
        return nombre;
    }
}