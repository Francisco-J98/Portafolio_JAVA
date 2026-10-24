public class Ropa
{
    //falta controlar el ID
	int id=1;
	int talla=0;
	String color="";
	
	Ropa(int X, String C)
	{
		talla = X;
		color = C;
	}
	
	protected int getId()
	{
		return id;
	}
	
	protected void setId(int id)
	{
		this.id = id;
	}
	
	protected int getTalla()
	{
		return talla;
	}
	
	protected void setTalla(int talla)
	{
		this.talla = talla;
	}
	
	protected String getColor()
	{
		return color;
	}
	
	protected void setColor(String color)
	{
		this.color = color;
	}

}
