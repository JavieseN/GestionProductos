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
		List<Producto> listaProductos;
		
		if(servicio.crear(new Producto("Pruebo", "Descripción Producto", 10, 20, 1, IVA.GENERAL, Categorias.ALIMENTACION)))
		{
			System.out.println("Nice");
		}
		else
		{
			System.err.println("F");
		}
		listaProductos = servicio.listarTodos();
		
		if(servicio.actualizar(2,new Producto("Lolo", "asdsadcto", 15, 50, 5, IVA.GENERAL, Categorias.LUJO)))
		{
			System.out.println("Nice");
		}
		else
		{
			System.err.println("F");
		}
		listaProductos = servicio.listarTodos();
		for (Producto producto : listaProductos) 
		{
			System.out.println(producto);
		}
	}
}
