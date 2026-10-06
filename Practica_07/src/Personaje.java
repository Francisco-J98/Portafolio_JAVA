public abstract class Personaje
{
    protected int vida;

    Personaje()
    {
        vida=100;
    }

    protected int getVida()
    {
        return vida;
    }

    protected void setVida(int vida)
    {
        this.vida = vida;
    }

    protected abstract int movimientoLucha();
}