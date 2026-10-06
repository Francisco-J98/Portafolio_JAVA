import java.util.Scanner;

public class Principal
{
    public static void main()
    {
        String nombreAux, apellidoAux;
        Scanner leer = new Scanner(System.in);
        Alumno alumno1, alumno2, alumno3, alumno4;

        alumno1 = new Alumno("Alma", "González");
        alumno2 =new Alumno(alumno1);

        System.out.println("Nombre del alumno1: " + alumno1.getNombreAlumno());
        System.out.println("Apellido del alumno1: " + alumno1.getApellidoAlumno());

        System.out.println("Nombre (copia) del alumno1: " + alumno2.getNombreAlumno());
        System.out.println("Apellido (copia) del alumno1: " + alumno2.getApellidoAlumno());

        alumno1 = alumno2;

        System.out.println("Nuevo nombre para el Alumno1: ");
        alumno1.setNombreAlumno(leer.nextLine());
        System.out.println("\nPROCESANDO NUEVOS DATOS...\n");
        System.out.println("Nuevo nombre del alumno1: " + alumno1.getNombreAlumno());
        System.out.println("Nombre(¿copia?) del alumno1: " + alumno2.getNombreAlumno());

        alumno3 = new Alumno();

        System.out.println("\nNuevo nombre de alumno3: ");
        alumno3.nombreAlumno = leer.nextLine();
        System.out.println("\nNuevo apellido de alumno3: ");
        alumno3.apellidoAlumno = leer.nextLine();
        System.out.println("PROCESANDO NUEVOS DATOS");
        System.out.println("Nombre del alumno3: " + alumno3.getNombreAlumno());

        //un alumno más
        System.out.println("\nNuevo nombre de alumno4: ");
        nombreAux = leer.nextLine();
        System.out.println("Nuevo apellido de alumno4: ");
        apellidoAux = leer.nextLine();
        alumno4 = new Alumno(nombreAux, apellidoAux);

        System.out.println("PROCESANDO NUEVOS DATOS");
        System.out.println("Nombre del alumno4: " + alumno4.getNombreAlumno());
        System.out.println("Número de alumnos actuales: " + Alumno.getNumAlumnos());

        System.out.println(alumno4.resultado());
    }
}