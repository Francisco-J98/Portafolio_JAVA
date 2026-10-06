public class Alumno
{
    protected String nombreAlumno;
    protected String apellidoAlumno;
    private static int numAlumnos = 0;
    private static double notaMedia = 0;

    public void setNombreAlumno(String nombreAlumno)
    {
        this.nombreAlumno = nombreAlumno;
    }

    public void setApellidoAlumno(String apellidoAlumno)
    {
        this.apellidoAlumno = apellidoAlumno;
    }

    public static void setNumAlumnos(int numAlumnos)
    {
        Alumno.numAlumnos = numAlumnos;
    }

    public static void setNotaMedia(double notaMedia)
    {
        Alumno.notaMedia = notaMedia;
    }

    public String getNombreAlumno()
    {
        return nombreAlumno;
    }

    public String getApellidoAlumno()
    {
        return apellidoAlumno;
    }

    public static int getNumAlumnos()
    {
        return numAlumnos;
    }

    public static double getNotaMedia()
    {
        return notaMedia;
    }
    protected void incrementar()
    {
        notaMedia++;
    }

    protected double resultado()
    {
        incrementar();
        return(notaMedia);
    }

    Alumno() //constructor por defecto
    {
        String nomb = "", apell = "";

        nomb = getNombreAlumno();
        apell = getApellidoAlumno();

        nombreAlumno = nomb;
        apellidoAlumno = apell;
        numAlumnos++;
        resultado();
    }

    Alumno(String nombreParam, String apellidoParam) //constructor con parámetros
    {
        this.nombreAlumno = nombreParam;
        this.apellidoAlumno = apellidoParam;

        numAlumnos++;
        resultado();
    }

    Alumno(Alumno alumnoParam) //constructor copia
    {
        this.nombreAlumno = alumnoParam.getNombreAlumno();
        this.apellidoAlumno = alumnoParam.getApellidoAlumno();
        numAlumnos++;
    }
}