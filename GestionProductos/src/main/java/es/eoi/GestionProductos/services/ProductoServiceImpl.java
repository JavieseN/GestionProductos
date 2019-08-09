package es.eoi.GestionProductos.services;

import java.util.List;

import es.eoi.GestionProductos.dao.ProductoDao;
import es.eoi.GestionProductos.dao.ProductoDaoImpl;
import es.eoi.GestionProductos.entities.Producto;

public class ProductoServiceImpl implements ProductoService
{
	private ProductoDao myDao;
	
	public ProductoServiceImpl()
	{
		this.myDao = new ProductoDaoImpl();
	}
	
	public boolean crear(Producto nuevoProducto)
	{
		return this.myDao.crear(nuevoProducto);
	}

	public boolean actualizar(Producto nuevoProducto)
	{
		return this.myDao.actualizar(nuevoProducto);
	}

	public Producto buscar(Producto filtro)
	{
		return this.myDao.buscar(filtro);
	}

	public List<Producto> listarTodos(Producto filtro)
	{
		return this.myDao.listarTodos(filtro);
	}

	public boolean borrar(Producto nuevoProducto)
	{
		return this.myDao.borrar(nuevoProducto);
	}

	public void vender(Producto nuevoProducto)
	{
		// TODO Auto-generated method stub
		
	}
}
