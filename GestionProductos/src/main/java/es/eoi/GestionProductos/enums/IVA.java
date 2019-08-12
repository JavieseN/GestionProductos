package es.eoi.GestionProductos.enums;

public enum IVA
{
	GENERAL(21),REDUCIDO(10),SUPERREDUCIDO(4);
	
	private int cantidad;
	
	private IVA(int cantidad)
	{
		this.cantidad = cantidad;
	}

	public int getCantidad()
	{
		return cantidad;
	}
}
