import java.io.Serializable;

public class Alumno implements Serializable
{
    protected String nombre;
    protected String apellido;
    private static int numalumnos=0;
    private static double notamedia=0;

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    public static void setNumalumnos(int numalumnos)
    {
        Alumno.numalumnos = numalumnos;
    }

    public static void setNotamedia(double notamedia)
    {
        Alumno.notamedia = notamedia;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getApellido()
    {
        return apellido;
    }

    public static int getNumalumnos()
    {
        return numalumnos;
    }

    public static double getNotamedia()
    {
        return notamedia;
    }

    Alumno() //constructor por defecto
    {
        String n="",apelli="";

        n=getNombre();
        apelli=getApellido();

        nombre=n;
        apellido=apelli;
        numalumnos++;
        resultado();
    }

    Alumno(String N,String A) //constructor pasado con parámetros
    {
        this.nombre=N;
        this.apellido=A;
        numalumnos++;
        resultado();
    }

    Alumno(Alumno persona)//constructor copia
    {
        this.nombre=persona.getNombre();
        this.apellido=persona.getApellido();
        numalumnos++;
    }

    public void incrementar()
    {
        notamedia++;
    }

    public double resultado()
    {
        incrementar();
        return(this.notamedia);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\nNombre: ");
        sb.append(nombre);
        sb.append("\nApellido: ");
        sb.append(apellido);
        return sb.toString();
    }

}