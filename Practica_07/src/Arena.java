import java.util.Scanner;

public class Arena
{
    public static int Lucha(Guerrero G1, Mago M1, int danovalor)
    {
        int danobatalla;

        danobatalla = G1.movimientoLucha();
        M1.vida -= danobatalla;
        danovalor += danobatalla;

        System.out.println("¡El Guerrero atacó!");
        System.out.println("El Guerrero ha hecho "+danobatalla+" de daño al Mago!");

        danobatalla = M1.movimientoLucha();
        G1.vida -= danobatalla;
        danovalor += danobatalla;

        System.out.println("¡El Mago invocó un hechizo!");
        System.out.println("¡El Mago ha hecho " + danobatalla + " de daño al Guerrero!");
        return(danovalor);
    }

    public static int Lucha(Mago M1, int danovalor, GuerreroFuerte G2)
    {
        int danobatalla;

        danobatalla = G2.movimientoLucha();
        M1.vida -= danobatalla;
        danovalor += danobatalla;

        System.out.println("¡El Guerrero ataca!");
        System.out.println("El Guerrero ha hecho " + danobatalla + " de daño al Mago!");

        danobatalla = M1.movimientoLucha();
        G2.vida -= danobatalla;
        danovalor += danobatalla;

        System.out.println("¡El Mago invocó un hechizo!");
        System.out.println("¡El Mago ha hecho " + danobatalla + " de daño al Guerrero!");

        return(danovalor);
    }

    public static void main(String[] Args)
    {
        Scanner leer = new Scanner(System.in);

        final Mago M1 = new Mago();
        final GuerreroFuerte G2 = new GuerreroFuerte();

        int op = 0, danovalor = 0;
        Integer danoreferencia = new Integer(danovalor);

        M1.fechacrea();
        G2.fechacrea();

        do
        {
            try
            {
                System.out.println("----MENÚ----");
                System.out.println("0) Salir.");
                System.out.println("----MAGO----");
                System.out.println("1)Mostrar HP.");
                System.out.println("----GUERRERO FUERTE----");
                System.out.println("2)Mostrar HP.");
                System.out.println("----COMANDOS---- ");
                System.out.println("3)Atacar.");
                System.out.println("4)Daño acumulado total.");

                System.out.println("Opción: ");
                op = leer.nextInt();
                leer.nextLine();
            }

            catch(Exception e)
            {
                op = -1;
                leer.nextLine();
                System.out.println();
            }

            switch(op)
            {
                case 0:System.out.println("Has salido del juego.");break;
                case 1:System.out.println("El HP actual del Mago es de: " + M1.getVida());break;
                case 2:System.out.println("El HP actual del Guerrero es de: " + G2.getVida());break;

                case 3:
                {
                    danovalor = Lucha(M1, danovalor, G2);
                    danoreferencia = danovalor;

                    Guerrero G1 = (Guerrero)new GuerreroFuerte();

                    danovalor = Lucha(G1, M1, danovalor);
                    danoreferencia = danovalor;
                }break;

                case 4:
                {
                    System.out.println("**** El daño acumulado por valor es " + danovalor + "****");
                    System.out.println("**** El daño acumulado por referencia es " + danoreferencia + "****");
                }break;
            }

            if(M1.getVida() <= 0)
            {
                System.out.println("El Mago ha sido derrotado.");
                op = 0;
            }
            else if(G2.getVida() <= 0)
            {
                System.out.println("El Guerrero Fuerte ha sido derrotado.");
                op = 0;
            }

        }while(op != 0);
    }
}