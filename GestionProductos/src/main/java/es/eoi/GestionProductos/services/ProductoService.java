package es.eoi.GestionProductos.services;

import java.util.List;

import es.eoi.GestionProductos.entities.Producto;

public interface ProductoService
{
	public boolean crear(Producto nuevoProducto);
	public boolean actualizar(Integer codigo, Producto nuevoProducto);
	public Producto buscar(Producto filtro);
	public List<Producto> listarTodos();
	public boolean borrar(Integer codigo);
	public boolean vender(Integer codigo);
}
