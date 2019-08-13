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
		
		if(servicio.crear(new Producto("Pruebr", "Descripci�n Producto", 10, 20, 1, IVA.GENERAL, Categorias.ALIMENTACION)))
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
		
		if(servicio.borrar(3))
		{
			System.out.println("Se ha borrado");
		}
		else
		{
			System.out.println("TOnto");
		}
		
		listaProductos = servicio.listarTodos();
		for (Producto producto : listaProductos) 
		{
			System.out.println(producto);
		}
		
		if(servicio.vender(2))
		{
			System.out.println("Se ha vendido");
		}
		else
		{
			System.out.println("No se ha vendido");
		}
		
		listaProductos = servicio.listarTodos();
		for (Producto producto : listaProductos) 
		{
			System.out.println(producto);
		}
	}
}
