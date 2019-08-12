package es.eoi.GestionProductos.app;

import java.util.List;

import es.eoi.GestionProductos.entities.Producto;
import es.eoi.GestionProductos.enums.Categorias;
import es.eoi.GestionProductos.enums.IVA;
import es.eoi.GestionProductos.services.ProductoService;
import es.eoi.GestionProductos.services.ProductoServiceImpl;

public class Main
{
	public static void main(String[] args)
	{
		ProductoService servicio = new ProductoServiceImpl();
		
		if(servicio.crear(new Producto("0001", "Prueba", "Descripción Producto", 10, 20, 1, IVA.GENERAL, Categorias.ALIMENTACION)))
		{
			System.out.println("Nice");
		}
		else
		{
			System.err.println("F");
		}
		List<Producto> listaProductos = servicio.listarTodos(new Producto("0001", "Prueba", "Descripción Producto", 10, 20, 1, IVA.GENERAL, Categorias.ALIMENTACION));
		for (Producto producto : listaProductos) 
		{
			System.out.println(producto);
		}
	}
}
