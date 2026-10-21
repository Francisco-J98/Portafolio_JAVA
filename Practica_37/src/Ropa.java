public class Ropa
{
    private int id;
    private int talla;
    private String color;

    public Ropa(int talla, String color)
    {
        this.talla = talla;
        this.color = color;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getTalla()
    {
        return talla;
    }

    public void setTalla(int talla)
    {
        this.talla = talla;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    @Override
    public String toString()
    {
        return "ID: " + id + " Talla: " + talla + " Color: " + color;
    }
}