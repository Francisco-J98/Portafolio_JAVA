public class RelojInicial
{
    public static void main(String[] args)
    {
        int segundos = 0, minutos = 0, horas = 0, tiempo = 18908; //tiempo t ejercicio 5 horas 15 minutos 8 segundos
        minutos = tiempo / 60;
        segundos = tiempo % 60;
        horas = minutos / 60;
        minutos %= 60;
        System.out.println("Son las "+horas+" horas, "+minutos+" minutos, "+segundos+" segundos.");
    }
}