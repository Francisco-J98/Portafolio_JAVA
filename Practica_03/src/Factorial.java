public class Factorial
{
    protected long numero;

    public long getNumero()
    {
        return numero;
    }

    public void setNumero(long numeroParam)
    {
        this.numero = numeroParam;
    }

    protected long factorialIterativo()
    {
        long i, numfact, resultado = 1;

        numfact = numero;

        if(numfact == 0 || numfact == 1)
            numfact = 1;
        else
        {
            for(i = numfact; i > 1;)
            {
                i--;
                resultado *= i;
            }
            numfact *= resultado;
        }
        return (numfact);
    }

    protected long factorialRecursivo(long numParam)
    {
        long resultado = 0;

        if(numParam == 1)
        {
            resultado = numParam;
            return (resultado);
        }
        return(numParam * factorialRecursivo(numParam - 1));
    }
}