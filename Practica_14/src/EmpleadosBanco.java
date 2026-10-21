/**
 * Datos de un empleado del banco.
 * El nombre completo se deriva de los campos, así que no puede quedar desincronizado.
 */

public record EmpleadoBanco(String nombre, String primerApellido, String segundoApellido, int sucursal)
{
    /** Formato "PrimerApellido SegundoApellido Nombre". */
    public String nombreCompleto()
    {
        return String.join(" ", primerApellido, segundoApellido, nombre);
    }

    @Override
    public String toString()
    {
        return nombreCompleto();
    }
}