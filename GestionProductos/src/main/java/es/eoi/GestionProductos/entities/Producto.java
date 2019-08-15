package es.eoi.GestionProductos.entities;

import es.eoi.GestionProductos.enums.Categorias;
import es.eoi.GestionProductos.enums.IVA;

public class Producto
{
	private Integer codigo;
	private String nombre;
	private String descripcion;
	private Double precio;
	private Integer cantidadDisponible;
	private Integer cantidadVendida;
	private IVA IvaProducto;
	private Categorias categoriaProducto;
	
	public Producto(String nombre, String descripcion, Double precio, Integer cantidadDisponible,
			Integer cantidadVendida, IVA ivaProducto, Categorias categoriaProducto)
	{
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidadDisponible = cantidadDisponible;
		this.cantidadVendida = cantidadVendida;
		IvaProducto = ivaProducto;
		this.categoriaProducto = categoriaProducto;
	}

	public Integer getCodigo()
	{
		return codigo;
	}

	public void setCodigo(Integer codigo)
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

	public Double getPrecio()
	{
		return precio;
	}

	public void setPrecio(Double precio)
	{
		this.precio = precio;
	}

	public Integer getCantidadDisponible()
	{
		return cantidadDisponible;
	}

	public void setCantidadDisponible(Integer cantidadDisponible)
	{
		this.cantidadDisponible = cantidadDisponible;
	}

	public Integer getCantidadVendida()
	{
		return cantidadVendida;
	}

	public void setCantidadVendida(Integer cantidadVendida)
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
