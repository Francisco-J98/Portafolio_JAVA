import java.util.Scanner;

public class Restaurante
{
    private double kgpapas;
    private double kgchocos;
    private final double KG_PAPAS_POR_CLIENTE  = 0.334;
    private final double KG_CHOCOS_POR_CLIENTE = 0.167;

    public void setKgpapas(double kgpapasParam)
    {
        this.kgpapas = kgpapasParam;
    }

    public void setKgchocos(double kgchocosParam)
    {
        this.kgchocos = kgchocosParam;
    }

    public double getKgpapas()
    {
        return kgpapas;
    }

    public double getKgchocos()
    {
        return kgchocos;
    }

    private void actualizarPapas(double kgPapasParam)
    {
        if (kgPapasParam < 0)
            kgpapas = 0;
    }

    private void actualizarChocos(double kgChocosParam)
    {
        if (kgChocosParam < 0)
            kgchocos = 0;
    }

    private int calcularClientes()
    {
        int clientesPapas, clientesChoco;
        if (kgpapas <= 0 || kgchocos <= 0)
            return 0;
        clientesPapas = (int) Math.ceil(kgpapas / KG_PAPAS_POR_CLIENTE);
        clientesChoco = (int) Math.ceil(kgchocos / KG_CHOCOS_POR_CLIENTE);
        return (Math.min(clientesPapas, clientesChoco));
    }

    public static void main(String[] args)
    {
        Scanner leer = new Scanner(System.in);
        Restaurante restAlmacen = new Restaurante();
        int opcion = 0, opcion2 = 0;
        double oppapas, opchocos;

        restAlmacen.setKgchocos(0);
        restAlmacen.setKgpapas(0);

        do
        {
            try
            {
                System.out.println("--------RESTAURANTE--------");
                System.out.println("|1) AÑADIR PAPAS.         |");
                System.out.println("|2) AÑADIR CHOCOS.        |");
                System.out.println("|3) QUITAR PAPAS.         |");
                System.out.println("|4) QUITAR CHOCOS.        |");
                System.out.println("|5) ALMACEN.              |");
                System.out.println("|6) CALCULAR CLIENTES.    |");
                System.out.println("|7) SALIR                 |");
                System.out.println("|_________________________|");
                System.out.println("Escoge una opción (1~7): ");
                opcion = leer.nextInt();
                System.out.println("Has seleccionado la opción: "+opcion);

                switch(opcion)
                {
                    case 1:
                    {
                        do
                        {
                            try
                            {
                                System.out.println("¿Cuántas papas (kgs) deseas añadir?");
                                oppapas = leer.nextDouble();
                                leer.nextLine();
                            }
                            catch (Exception e)
                            {
                                oppapas = -1;
                                leer.nextLine();
                            }
                        }while(oppapas < 0);

                        System.out.println("Has añadido: "+ oppapas+ "kg(s) de papas.");
                        restAlmacen.setKgpapas(restAlmacen.getKgpapas() + oppapas);
                    }break;
                    case 2:
                    {
                        do
                        {
                            try
                            {
                                System.out.println("¿Cuántas chocos (kgs) deseas añadir?");
                                opchocos = leer.nextDouble();
                                leer.nextLine();
                            }
                            catch (Exception e)
                            {
                                opchocos = -1;
                                leer.nextLine();
                            }
                        }while(opchocos < 0);

                        System.out.println("Has añadido: "+ opchocos+ "kg(s) de chocos.");
                        restAlmacen.setKgchocos(restAlmacen.getKgchocos() + opchocos);
                    }break;
                    case 3:
                    {
                        do
                        {
                            try
                            {
                                System.out.println("¿Cuántas papas (kgs) han mermado/ deseas quitar?");
                                oppapas = leer.nextInt();
                                leer.nextLine();
                            }
                            catch (Exception e)
                            {
                                oppapas = -1;
                                leer.nextLine();
                            }
                        }while(oppapas < 0);

                        if(oppapas == 0)
                            System.out.println("No has quitado provisiones de papas.");
                        else if(restAlmacen.getKgpapas() == 0)
                            System.out.println("No has podido quitar provisiones de papas, porque no hay provisiones de papas.");
                        else if(oppapas > restAlmacen.getKgpapas())
                        {
                            restAlmacen.setKgpapas(0);
                            System.out.println("La cantidad a quitar supera la de las provisiones actuales de papas.\nLa cantidad actual pasa a ser 0.");
                        }
                        else
                        {
                            System.out.println("Has quitado/ Han mermado: "+ oppapas+ "kg(s) de papas.");
                            restAlmacen.setKgpapas(restAlmacen.getKgpapas() - oppapas);
                            restAlmacen.actualizarPapas(restAlmacen.getKgpapas());
                        }
                    }break;
                    case 4:
                    {
                        do
                        {
                            try
                            {
                                System.out.println("¿Cuántos chocos (kgs) han mermado/ deseas quitar?");
                                opchocos = leer.nextInt();
                                leer.nextLine();
                            }
                            catch (Exception e)
                            {
                                opchocos = -1;
                                leer.nextLine();
                            }
                        }while(opchocos < 0);

                        if(opchocos == 0)
                            System.out.println("No has quitado provisiones de chocos.");
                        else if(restAlmacen.getKgchocos() == 0)
                            System.out.println("No has podido quitar provisiones de chocos, porque no hay provisiones de chocos.");
                        else if(opchocos > restAlmacen.getKgchocos())
                        {
                            restAlmacen.setKgchocos(0);
                            System.out.println("La cantidad a quitar supera la de las provisiones actuales de chocos.\nLa cantidad actual pasa a ser 0.");
                        }
                        else
                        {
                            System.out.println("Has quitado/ Han mermado: "+ opchocos+ "kg(s) de chocos.");
                            restAlmacen.setKgchocos(restAlmacen.getKgchocos() - opchocos);
                            restAlmacen.actualizarChocos(restAlmacen.getKgchocos());
                        }
                    }break;
                    case 5:
                    {
                        do
                        {
                            try
                            {
                                System.out.println("--------ALMACÉN--------");
                                System.out.println("|1) ALMACÉN DE PAPAS   |");
                                System.out.println("|2) ALMACÉN DE CHOCOS  |");
                                System.out.println("|______________________|");
                                System.out.println("Escoge una opción(1~2): ");
                                opcion2 = leer.nextInt();
                            }
                            catch (Exception e)
                            {
                                opcion2 = 0;
                                leer.nextLine();
                            }
                        }while(opcion2 < 1 || opcion2 > 2);

                        switch(opcion2)
                        {
                            case 1:
                            {
                                if(restAlmacen.getKgpapas() > 0)
                                {
                                    System.out.println("---ALMACÉN DE PAPAS---");
                                    System.out.println("PROVISIONES: "+ restAlmacen.getKgpapas()+ "kg de papas.");
                                }
                                else
                                {
                                    System.out.println("---ALMACÉN DE PAPAS---");
                                    System.out.println("PROVISIONES: NO HAY PAPAS.");
                                }
                            }break;
                            case 2:
                            {
                                if(restAlmacen.getKgchocos() > 0)
                                {
                                    System.out.println("---ALMACÉN DE CHOCOS---");
                                    System.out.println("PROVISIONES: "+ restAlmacen.getKgchocos()+ "kg de chocos.");
                                }
                                else
                                {
                                    System.out.println("---ALMACÉN DE CHOCOS---");
                                    System.out.println("PROVISIONES: NO HAY CHOCOS.");
                                }
                            }break;
                        }
                        System.out.println("\n");
                    }break;
                    case 6:System.out.println("Hay "+ restAlmacen.calcularClientes() +" cliente(s) en el restaurante.");break;
                    case 7:System.out.println("Has salido del programa.");break;
                    default:System.out.println("Opción no reconocida.");break;
                }
            }
            catch (Exception e)
            {
                opcion = 0;
                leer.nextLine();
            }
        }while(opcion != 7);
    }
}