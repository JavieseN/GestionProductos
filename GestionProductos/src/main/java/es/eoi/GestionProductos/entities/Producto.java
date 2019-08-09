package es.eoi.GestionProductos.entities;

import es.eoi.GestionProductos.enums.Categorias;
import es.eoi.GestionProductos.enums.IVA;

public class Producto
{
	private String codigo;
	private String nombre;
	private String descripcion;
	private int precio;
	private int cantidadDisponible;
	private int cantidadVendida;
	private IVA IvaProducto;
	private Categorias categoriaProducto;
	
	public Producto(String codigo, String nombre, String descripcion, int precio, int cantidadDisponible,
			int cantidadVendida, IVA ivaProducto, Categorias categoriaProducto)
	{
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidadDisponible = cantidadDisponible;
		this.cantidadVendida = cantidadVendida;
		IvaProducto = ivaProducto;
		this.categoriaProducto = categoriaProducto;
	}

	public String getCodigo()
	{
		return codigo;
	}

	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getDescripcion()
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}

	public int getPrecio()
	{
		return precio;
	}

	public void setPrecio(int precio)
	{
		this.precio = precio;
	}

	public int getCantidadDisponible()
	{
		return cantidadDisponible;
	}

	public void setCantidadDisponible(int cantidadDisponible)
	{
		this.cantidadDisponible = cantidadDisponible;
	}

	public int getCantidadVendida()
	{
		return cantidadVendida;
	}

	public void setCantidadVendida(int cantidadVendida)
	{
		this.cantidadVendida = cantidadVendida;
	}

	public IVA getIvaProducto()
	{
		return IvaProducto;
	}

	public void setIvaProducto(IVA ivaProducto)
	{
		IvaProducto = ivaProducto;
	}

	public Categorias getCategoriaProducto()
	{
		return categoriaProducto;
	}

	public void setCategoriaProducto(Categorias categoriaProducto)
	{
		this.categoriaProducto = categoriaProducto;
	}

	@Override
	public String toString()
	{
		return String.format(
				"Producto [codigo=%s, nombre=%s, descripcion=%s, precio=%s, cantidadDisponible=%s, cantidadVendida=%s, IvaProducto=%s, categoriaProducto=%s]",
				codigo, nombre, descripcion, precio, cantidadDisponible, cantidadVendida, IvaProducto,
				categoriaProducto);
	}
	
	
}
