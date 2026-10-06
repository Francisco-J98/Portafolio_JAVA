import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Principal
{
    public static void main(String[] Args)
    {
        int i;
        Scanner leer = new Scanner(System.in);
        Alumno A1 = new Alumno("Alma","González");
        String NAME = "", SURNAME, ruta = "", ruta2 = "";

        System.out.println("Datos ya creados en A1...");
        System.out.println("Introduce tu nombre de usuario (del PC): ");
        NAME = leer.nextLine();

        ruta = ruta.concat("C:/Users/"+NAME+"/Desktop/Fichero.bin");
        // guardando en un fichero el alumno X
        try
        {
            ObjectOutputStream escritura = new ObjectOutputStream(new FileOutputStream(ruta));
            escritura.writeObject(A1);
            escritura.close();
            System.out.println("Guardado en: "+ ruta);
        }
        catch(Exception e)
        {
            System.out.println("Error al guardar en el fichero.");
        }
        // abriendo el nuevo fichero
        try
        {
            ObjectInputStream lectura = new ObjectInputStream(new FileInputStream(ruta));
            Alumno auxiliar = (Alumno) lectura.readObject();
            lectura.close();

            System.out.println("Mostrando contenido:");
            System.out.println(auxiliar);
        }
        catch(Exception e)
        {
            System.out.println("Error al leer el fichero.");
        }

        NAME = "";
        ruta = "";

        //creando array de 5 objetos
        Alumno [] clase = new Alumno[5];
        for (i = 0; i < 5; i++)
        {
            System.out.println("Introduce nombre alumno: ");
            NAME = leer.nextLine();
            System.out.println("Introduce apellido alumno: ");
            SURNAME = leer.nextLine();
            clase[i] = new Alumno(NAME,SURNAME);
        }

        for (i = 0; i < clase.length; i++)
        {
            System.out.println("Alumno "+ (i + 1));
            System.out.println(clase[i].getNombre());
            System.out.println(clase[i].getApellido()+"\n");
        }

        System.out.println("Introduce tu nombre de usuario (del PC): ");
        NAME = leer.nextLine();

        ruta = ruta.concat("C:/Users/"+NAME+"/Desktop/Fichero2.bin");
        //grabando en el fichero
        try
        {
            ObjectOutputStream escritura = new ObjectOutputStream(new FileOutputStream(ruta));
            escritura.writeObject(clase);
            escritura.close();
            System.out.println("Guardado en: "+ ruta);
        }

        catch(Exception e)
        {
            System.out.println("Error al grabar en el fichero.");
        }

        //recuperando el fichero y mostrándolo
        try
        {
            ObjectInputStream lectura = new ObjectInputStream(new FileInputStream(ruta));
            Alumno[] auxiliar = (Alumno[]) lectura.readObject();
            lectura.close();

            System.out.println("Mostrando contenido:");
            for(Alumno a1 : auxiliar)
                System.out.println(a1);
        }
        catch(Exception e)
        {
            System.out.println("Error al leer el fichero.");
        }
    }
}