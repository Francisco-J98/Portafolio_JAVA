import com.google.gson.annotations.SerializedName;

public class NotaDetallada
{
    @SerializedName("ID")
    private final int idNota;
    @SerializedName("Asignatura")
    private final String asignatura;
    @SerializedName("Apenom")
    private final String alumno;
    @SerializedName("Nota")
    private final int nota;

    public NotaDetallada(int idNota, String asignatura, String alumno, int nota)
	{
        this.idNota = idNota;
        this.asignatura = asignatura;
        this.alumno = alumno;
        this.nota = nota;
    }

    public int getIdNota()
	{
        return idNota;
    }

    public String getAsignatura()
	{
        return asignatura;
    }

    public String getAlumno()
	{
        return alumno;
    }

    public int getNota()
	{
        return nota;
    }
}