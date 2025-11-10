import java.util.Arrays;
import java.util.Scanner;

public class MatematicasUno
{
    static Scanner leer = new Scanner(System.in);
    private static final double NUMPI = 3.14159;


    private static void ordenacionNumeros()
    {
        int[] vector;
        int num1, num2, num3;

        System.out.println("Introduce el valor del primer número: ");
        num1 = leer.nextInt();
        System.out.println("Introduce el valor del segundo número: ");
        num2 = leer.nextInt();
        System.out.println("Introduce el valor del tercer número: ");
        num3 = leer.nextInt();

        vector = new int[]{num1, num2, num3};
        Arrays.sort(vector);
        System.out.println(vector[2] + " > " + vector[1] + " > " + vector[0]);
    }

    private static void potencia()
    {
        int base, exponente;
        long resultado = 1, aux;

        System.out.println("Introduce el valor de la base: ");
        base = leer.nextInt();

        //aquí aplicamos la restricción de aceptar únicamente exponentes positivos
        do
        {
            try
            {
                System.out.println("Introduce ahora el exponente(>=0): ");
                exponente = leer.nextInt();
            }
            catch (Exception e)
            {
                exponente = -1;
                leer.nextLine();
            }
        }while(exponente < 0);

        if(base == 0 && exponente == 0)
            System.out.println(base + "^" + exponente + ": Indeterminación matemática.");
        else
        {
            aux = exponente;

            while(aux > 0)
            {
                resultado *= base;
                aux--;
            }

            System.out.println(base + "^" + exponente + " = " + resultado);
        }
    }

    private static double poligonoPerimetro(int numeroLadosParam, double ladoTamanioParam)
    {
        return(numeroLadosParam * ladoTamanioParam);
    }

    private static double cuadradoArea(double ladoTamanioParam)
    {
        return(ladoTamanioParam * ladoTamanioParam);
    }

    private static double poligonoArea(double x, double y)
    {
        return((x * y) / 2);
    }

    private static double circuloPerimetro(double radioParam)
    {
        return(2 * NUMPI * radioParam);
    }

    private static double circuloArea(double radioParam)
    {
        return(NUMPI * radioParam * radioParam);
    }


    public static void main(String[] args)
    {
        int opcion1, opcion2, opcion3, opcion4, numLados = 0;
        double lado_radio_base = 0, altura_apotema = 0;

        do
        {
            //MENÚ INICIAL
            try
            {
                System.out.println("-------MENÚ PRINCIPAL-------");
                System.out.println("|1)Figuras geométricas.    |");
                System.out.println("|2)Operaciones matemáticas.|");
                System.out.println("|3)Salir.                  |");
                System.out.println("|__________________________|");
                System.out.println("Selecciona una opción(1~3): ");
                opcion1 = leer.nextInt();
                System.out.println("Has escogido la opción: "+opcion1);

                switch(opcion1)
                {
                    case 1:
                    {
                        do
                        {
                            //MENÚ 1
                            try
                            {
                                System.out.println("-----MENÚ Figuras Geometricas-----");
                                System.out.println("|1)Triángulo                      |");
                                System.out.println("|2)Cuadrado                       |");
                                System.out.println("|3)Hexágono                       |");
                                System.out.println("|4)Círculo                        |");
                                System.out.println("|_________________________________|");
                                System.out.println("Escoge una opción(1~4): ");
                                opcion2 = leer.nextInt();
                                System.out.println("Has escogido la opción: "+opcion2);

                                switch(opcion2)
                                {
                                    case 1:
                                    {
                                        do
                                        {
                                            try
                                            {
                                                System.out.println("-----MENÚ Figuras Geométricas/Triángulo-----");
                                                System.out.println("|1)Perímetro                                |");
                                                System.out.println("|2)Área                                     |");
                                                System.out.println("|___________________________________________|");
                                                System.out.println("Escoge una opción(1~2): ");
                                                opcion3 = leer.nextInt();
                                                System.out.println("Has escogido la opción: "+opcion3);

                                                switch(opcion3)
                                                {
                                                    case 1:
                                                    {
                                                        numLados = 3;

                                                        do
                                                        {
                                                            try
                                                            {
                                                                System.out.println("Introduce el valor del lado: ");
                                                                lado_radio_base = leer.nextDouble();
                                                            }
                                                            catch (Exception e)
                                                            {
                                                                lado_radio_base = 0;
                                                                leer.nextLine();
                                                            }
                                                        }while(lado_radio_base <= 0);

                                                        System.out.println("El perímetro del TRIÁNGULO es de: " + poligonoPerimetro(numLados, lado_radio_base) + " unidad(es).");
                                                    }break;

                                                    case 2:
                                                    {
                                                        numLados = 3;

                                                        do
                                                        {
                                                            try
                                                            {
                                                                System.out.println("Introduce el valor de la base: ");
                                                                lado_radio_base = leer.nextDouble();
                                                            }
                                                            catch (Exception e)
                                                            {
                                                                lado_radio_base = 0;
                                                                leer.nextLine();
                                                            }
                                                        }while(lado_radio_base <= 0);

                                                        do
                                                        {
                                                            try
                                                            {
                                                                System.out.println("A continuación, introduce el valor de la altura: ");
                                                                altura_apotema = leer.nextDouble();
                                                            }
                                                            catch (Exception e)
                                                            {
                                                                altura_apotema = 0;
                                                                leer.nextLine();
                                                            }
                                                        }while(altura_apotema <= 0);
                                                        System.out.println("El área del TRIÁNGULO es de: " + poligonoArea(lado_radio_base, altura_apotema) + " unidad(es)^2.");
                                                    }break;
                                                }
                                            }
                                            catch (Exception e)
                                            {
                                                opcion3 = 0;
                                                leer.nextLine();
                                            }
                                        }while(opcion3 < 1 || opcion3 > 2);
                                    }break;
                                    case 2:
                                    {
                                        do
                                        {
                                            try
                                            {
                                                System.out.println("-----MENÚ Figuras Geométricas/Cuadrado-----");
                                                System.out.println("|1)Perímetro                                |");
                                                System.out.println("|2)Área                                     |");
                                                System.out.println("|___________________________________________|");
                                                System.out.println("Escoge una opción(1~2): ");
                                                opcion3 = leer.nextInt();
                                                System.out.println("Has escogido la opción: "+opcion3);

                                                switch(opcion3)
                                                {
                                                    case 1:
                                                    {
                                                        numLados = 4;

                                                        do
                                                        {
                                                            try
                                                            {
                                                                System.out.println("Introduce el valor del lado: ");
                                                                lado_radio_base = leer.nextDouble();
                                                            }
                                                            catch (Exception e)
                                                            {
                                                                lado_radio_base = 0;
                                                                leer.nextLine();
                                                            }
                                                        }while(lado_radio_base <= 0);

                                                        System.out.println("El perímetro del CUADRADO es de: " + poligonoPerimetro(numLados, lado_radio_base) + " unidad(es).");
                                                    }break;

                                                    case 2:
                                                    {
                                                        do
                                                        {
                                                            try
                                                            {
                                                                System.out.println("Introduce el valor del lado: ");
                                                                lado_radio_base = leer.nextDouble();
                                                            }
                                                            catch (Exception e)
                                                            {
                                                                lado_radio_base = 0;
                                                                leer.nextLine();
                                                            }
                                                        }while(lado_radio_base <= 0);

                                                        System.out.println("El área del CUADRADO es de: " + cuadradoArea(lado_radio_base) + " unidad(es)^2.");
                                                    }break;
                                                }
                                            }
                                            catch (Exception e)
                                            {
                                                opcion3 = 0;
                                                leer.nextLine();
                                            }
                                        }while(opcion3 < 1 || opcion3 > 2);
                                    }break;
                                    case 3:
                                    {
                                        do
                                        {
                                            try
                                            {
                                                System.out.println("-----MENÚ Figuras Geométricas/Hexágono-----");
                                                System.out.println("|1)Perímetro                                |");
                                                System.out.println("|2)Área                                     |");
                                                System.out.println("|___________________________________________|");
                                                System.out.println("Escoge una opción(1~2): ");
                                                opcion3 = leer.nextInt();
                                                System.out.println("Has escogido la opción: "+opcion3);

                                                switch(opcion3)
                                                {
                                                    case 1:
                                                    {
                                                        numLados = 6;

                                                        do
                                                        {
                                                            try
                                                            {
                                                                System.out.println("Introduce el valor del lado: ");
                                                                lado_radio_base = leer.nextDouble();
                                                            }
                                                            catch (Exception e)
                                                            {
                                                                lado_radio_base = 0;
                                                                leer.nextLine();
                                                            }
                                                        }while(lado_radio_base <= 0);

                                                        System.out.println("El perímetro del HEXÁGONO es de: " + poligonoPerimetro(numLados, lado_radio_base) + " unidad(es).");
                                                    }break;

                                                    case 2:
                                                    {
                                                        numLados = 6;

                                                        do
                                                        {
                                                            try
                                                            {
                                                                System.out.println("Introduce el valor del lado: ");
                                                                lado_radio_base = leer.nextDouble();
                                                            }
                                                            catch (Exception e)
                                                            {
                                                                lado_radio_base = 0;
                                                                leer.nextLine();
                                                            }
                                                        }while(lado_radio_base <= 0);

                                                        do
                                                        {
                                                            try
                                                            {
                                                                System.out.println("A continuación, introduce el valor del apotema: ");
                                                                altura_apotema = leer.nextDouble();
                                                            }
                                                            catch (Exception e)
                                                            {
                                                                altura_apotema = 0;
                                                                leer.nextLine();
                                                            }
                                                        }while(altura_apotema <= 0);

                                                        System.out.println("El área del HEXÁGONO es de: " + poligonoArea(poligonoPerimetro(numLados, lado_radio_base), altura_apotema) + " unidad(es)^2.");
                                                    }break;
                                                }
                                            }
                                            catch (Exception e)
                                            {
                                                opcion3 = 0;
                                                leer.nextLine();
                                            }
                                        }while(opcion3 < 1 || opcion3 > 2);
                                    }break;
                                    case 4:
                                    {
                                        do
                                        {
                                            try
                                            {
                                                System.out.println("------MENÚ Figuras Geométricas/Círculo------");
                                                System.out.println("|1)Perímetro                                |");
                                                System.out.println("|2)Área                                     |");
                                                System.out.println("|___________________________________________|");
                                                System.out.println("Escoge una opción(1~2): ");
                                                opcion3 = leer.nextInt();
                                                System.out.println("Has escogido la opción: "+opcion3);

                                                do
                                                {
                                                    try
                                                    {
                                                        System.out.println("Introduce el valor del radio: ");
                                                        lado_radio_base = leer.nextDouble();
                                                    }
                                                    catch (Exception e)
                                                    {
                                                        lado_radio_base = 0;
                                                        leer.nextLine();
                                                    }
                                                }while(lado_radio_base <= 0);

                                                switch(opcion3)
                                                {
                                                    case 1:System.out.println("El perímetro del CÍRCULO es de: " + circuloPerimetro(lado_radio_base) + " unidad(es).");break;
                                                    case 2:System.out.println("El área del CÍRCULO es de: " + circuloArea(lado_radio_base) + " unidad(es)^2.");break;
                                                }
                                            }
                                            catch (Exception e)
                                            {
                                                opcion3 = 0;
                                                leer.nextLine();
                                            }
                                        }while(opcion3 < 1 || opcion3 > 2);
                                    }break;
                                }
                            }
                            catch (Exception e)
                            {
                                opcion2 = 0;
                                leer.nextLine();
                            }
                        }while(opcion2 < 1 || opcion2 > 4);
                    }break;
                    case 2:
                    {
                        do
                        {
                            //MENÚ 2
                            try
                            {
                                System.out.println("-----MENÚ Operaciones Matemáticas-----");
                                System.out.println("|1)Ordenar de mayor a menor.          |");
                                System.out.println("|2)Potencia.                          |");
                                System.out.println("|_____________________________________|");
                                System.out.println("Escoge una opción(1~2): ");
                                opcion4 = leer.nextInt();
                                System.out.println("Has escogido la opción: "+opcion4);

                                switch(opcion4)
                                {
                                    case 1:ordenacionNumeros();break;
                                    case 2:potencia();break;
                                }
                            }
                            catch (Exception e)
                            {
                                opcion4 = 0;
                                leer.nextLine();
                            }
                        }while(opcion4 < 1 || opcion4 > 2);
                    }break;
                    case 3:System.out.println("Has salido del programa.");break;
                    default:System.out.println("Opción no existente.");break;
                }
            }
            catch (Exception e)
            {
                opcion1 = 0;
                leer.nextLine();
                System.out.println("Dato no reconocido por teclado.");
            }
        }while (opcion1 != 3);
    }
}