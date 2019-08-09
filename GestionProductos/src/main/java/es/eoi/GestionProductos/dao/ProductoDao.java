package es.eoi.GestionProductos.dao;

import java.util.List;

import es.eoi.GestionProductos.entities.Producto;

public interface ProductoDao
{
	public boolean crear(Producto nuevoProducto);
	public boolean actualizar(Producto nuevoProducto);
	public Producto buscar(Producto filtro);
	public List<Producto> listarTodos(Producto filtro);
	public boolean borrar(Producto nuevoProducto);
	public void vender(Producto nuevoProducto);
}
