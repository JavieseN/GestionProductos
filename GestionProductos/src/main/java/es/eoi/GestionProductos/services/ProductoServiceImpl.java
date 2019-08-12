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

	public boolean actualizar(Integer codigo, Producto nuevoProducto)
	{
		return this.myDao.actualizar(codigo,nuevoProducto);
	}

	public Producto buscar(Producto filtro)
	{
		return this.myDao.buscar(filtro);
	}

	public List<Producto> listarTodos()
	{
		return this.myDao.listarTodos();
	}

	public boolean borrar(Integer codigo)
	{
		return this.myDao.borrar(codigo);
	}

	public void vender(Producto nuevoProducto)
	{
		// TODO Auto-generated method stub
		
	}
}
