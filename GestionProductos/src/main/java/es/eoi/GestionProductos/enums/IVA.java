package es.eoi.GestionProductos.enums;

public enum IVA
{
	GENERAL(0.21),REDUCIDO(0.10),SUPERREDUCIDO(0.4);
	
	private Double cantidad;
	
	private IVA(Double cantidad)
	{
		this.cantidad = cantidad;
	}

	public Double getCantidad()
	{
		return cantidad;
	}
}
