import java.util.Scanner;
import java.io.File;

public class Principal
{
    public static void main(String[] Args)
    {
        int i, j, opcion = 0;
        String ruta = "";
        boolean verificador = false;
        Scanner leer = new Scanner (System.in);

        do
        {
            try
            {
                System.out.println("---------------MENÚ---------------");
                System.out.println("|1) COMPROBAR FICHERO/DIRECTORIO |");
                System.out.println("|2) LISTAR ./BIN                 |");
                System.out.println("|3) CREAR DIRECTORIO             |");
                System.out.println("|4) SALIR                        |");
                System.out.println("|________________________________|");
                System.out.println("Introduzca una opcion (1~4): ");
                opcion = leer.nextInt();
                leer.nextLine();

                switch(opcion)
                {
                    case 1:
                    {
                        System.out.println("Introduce la ruta del fichero/directorio: ");
                        ruta = leer.nextLine();

                        File general = new File(ruta);

                        if(general.exists())
                        {
                            if(general.isDirectory())
                                System.out.println(ruta+" existe y es un directorio");
                            else if(general.isFile())
                                System.out.println(ruta+" existe y es un fichero");
                            System.out.println("La ruta absoluta es: "+ general.getAbsolutePath());
                        }
                        else
                            System.out.println("El fichero/directorio NO existe o ha sido mal introducido.");
                    }break;

                    case 2:
                    {
                        File F1 = new File("bin");
                        String[] lista1 = F1.list();
                        for (i = 0; i < lista1.length; i++)
                        {
                            System.out.println(lista1[i]);
                            File F2 = new File(F1.getAbsolutePath(),lista1[i]);
                            if(F2.isDirectory())
                            {
                                String[] sublista = F2.list();
                                for (j = 0; j < sublista.length;j++)
                                    System.out.println(sublista[j]);
                            }
                        }
                    }break;

                    case 3:
                    {

                        File F1 = new File("PruebaDirectorio");
                        if(F1.exists())
                            System.out.println("El directorio ya existe/ha sido creado.");
                        else
                        {
                            verificador = F1.mkdir();
                            if(verificador)
                                System.out.println("Directorio creado.");
                        }
                    }break;

                    case 4:System.out.println("Ha salido del programa.");break;

                }
            }
            catch(Exception e)
            {
                leer.nextLine();
                System.out.print("\n");
                opcion = 0;
            }
        }while(opcion != 4);
    }
}