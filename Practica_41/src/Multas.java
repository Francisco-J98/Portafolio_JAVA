public class Multas
{
    public static final char PAGADA = 'S';
    public static final char NO_PAGADA = 'N';

    private int id;
    private char estadoPago;
    private double importe;
    private int placaAgente;
    private String localidad;

    public Multas(int placaAgente, String localidad, double importe, char estadoPago)
    {
        this.placaAgente = placaAgente;
        this.localidad = localidad;
        this.importe = importe;
        this.estadoPago = estadoPago;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public char getEstadoPago()
    {
        return estadoPago;
    }

    public void setEstadoPago(char estadoPago)
    {
        this.estadoPago = estadoPago;
    }

    public double getImporte()
    {
        return importe;
    }

    public void setImporte(double importe)
    {
        this.importe = importe;
    }

    public int getPlacaAgente()
    {
        return placaAgente;
    }

    public void setPlacaAgente(int placaAgente)
    {
        this.placaAgente = placaAgente;
    }

    public String getLocalidad()
    {
        return localidad;
    }

    public void setLocalidad(String localidad)
    {
        this.localidad = localidad;
    }

    @Override
    public String toString()
    {
        return "ID: " + id + " Lugar: " + localidad + " Coste: " + importe + "€ Agente: " + placaAgente + " Pagado(S/N): " + estadoPago;
    }
}