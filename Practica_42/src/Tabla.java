public enum Tabla
{

    AGENTES("Agentes", "IDAge", "NombreA", "IDAgente"),
    LOCALIDADES("Localidades", "IDLoc", "NombreL", "IDLocalidad"),
    MULTAS("Multas", "ID", null, null);

    private final String nombre;
    private final String columnaId;
    private final String columnaNombre;
    private final String columnaEnMultas;

    Tabla(String nombre, String columnaId, String columnaNombre, String columnaEnMultas)
	{
        this.nombre = nombre;
        this.columnaId = columnaId;
        this.columnaNombre = columnaNombre;
        this.columnaEnMultas = columnaEnMultas;
    }

    public String getNombre()
	{
        return nombre;
    }

    public String getColumnaId()
	{
        return columnaId;
    }

    /** Columna con el nombre del registro (solo Agentes y Localidades). */
    public String getColumnaNombre()
	{
        return columnaNombre;
    }

    /** Columna de la tabla Multas que referencia a esta tabla (clave ajena); null en Multas. */
    public String getColumnaEnMultas()
	{
        return columnaEnMultas;
    }
}