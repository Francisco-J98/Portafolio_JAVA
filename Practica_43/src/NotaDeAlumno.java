import com.google.gson.annotations.SerializedName;

public class NotaDeAlumno
{
    @SerializedName("Nombre")
    private final String nombreAlumno;
    @SerializedName("DNI")
    private final String dni;
    @SerializedName("Asignatura")
    private final String asignatura;
    @SerializedName("Nota")
    private final int nota;

    public NotaDeAlumno(String dni, String nombreAlumno, String asignatura, int nota)
	{
        this.dni = dni;
        this.nombreAlumno = nombreAlumno;
        this.asignatura = asignatura;
        this.nota = nota;
    }

    public String getNombreAlumno()
	{
        return nombreAlumno;
    }

    public String getDni()
	{
        return dni;
    }

    public String getAsignatura()
	{
        return asignatura;
    }

    public int getNota()
	{
        return nota;
    }
}