import java.io.Serializable;

public class Ropa implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int id = 1;
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
}