package es.eoi.GestionProductos.app;

import java.util.List;
import java.util.Scanner;

import es.eoi.GestionProductos.entities.Producto;
import es.eoi.GestionProductos.enums.Categorias;
import es.eoi.GestionProductos.enums.IVA;
import es.eoi.GestionProductos.services.ProductoService;
import es.eoi.GestionProductos.services.ProductoServiceImpl;

public class Main
{
	static ProductoService servicio;
	//static List<Producto> listaProductos;
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args)
	{
		menu();
	}
	
	public static void menu()
	{
		servicio = new ProductoServiceImpl();
		System.out.println("1 Nuevo Producto\r\n" + 
				"2 Listado de Productos\r\n" + 
				"3 Buscar Producto\r\n" + 
				"4 Modificar Producto\r\n" + 
				"5 Eliminar Producto\r\n" + 
				"6 Vender Producto\r\n" + 
				"7 Super Informe Ejecutivo\n" + 
				"¿Que opción va a realizar?");
		int opcion = Integer.valueOf(scan.nextLine());
		switch (opcion) {
		case 1:
			crear();
			break;
		case 2:
			listarProductos();
			break;
		case 3:
			buscarProducto();
			break;
		case 4:
			modificarProducto();
			break;
		case 5:
			eliminarProducto();
			break;
		case 6:
			vender();
			break;
		case 7:
			informe();
			break;
		default:
			break;
		}
	}
	
	private static void vender() 
	{
		Producto productoVendido;
		System.out.println("Introduce el codigo");
		int codigo = Integer.valueOf(scan.nextLine());
		Producto filtro = new Producto("", null, 0.0, 0, 0, null, null);
		filtro.setCodigo(codigo);
		List<Producto> productosFiltrados = servicio.buscar(filtro);
		if(productosFiltrados.size() == 0)
		{
			System.out.println("Ese producto no existe");
		}
		else
		{
			productoVendido = productosFiltrados.get(0);
			System.out.println(productoVendido);
			System.out.println("¿Cuantos has vendido?");
			int vendidos = Integer.valueOf(scan.nextLine());
			do
			{
				System.out.println("No se pueden vender mas de los que tienes, como máximo puedes vender " + productoVendido.getCantidadDisponible());
				System.out.println("¿Cuantos has vendido?");
				vendidos = Integer.valueOf(scan.nextLine());
			}while(vendidos > productoVendido.getCantidadDisponible());
			if(servicio.vender(codigo, vendidos))
			{
				System.out.println("Se ha vendido correctamente");
			}
			else
			{
				System.out.println("No se ha podido vender el producto");
			}
		}
		menu();
	}

	private static void informe() 
	{
		String informe = servicio.informe();
		if(informe.equals(""))
		{
			System.out.println("No se han vendido productos");
		}
		else
		{
			System.out.println(servicio.informe());
		}	
		menu();
	}

	private static void eliminarProducto() 
	{
		System.out.println("Introduce el codigo");
		int codigo = Integer.valueOf(scan.nextLine());
		if(servicio.borrar(codigo))
		{
			System.out.println("Se ha borrado el producto");
		}
		else
		{
			System.out.println("No se ha borrado el producto");
		}
		menu();
	}

	private static void modificarProducto() 
	{
		Producto modificado;
		System.out.println("Introduce el codigo");
		int codigo = Integer.valueOf(scan.nextLine());
		Producto filtro = new Producto("", null, 0.0, 0, 0, null, null);
		filtro.setCodigo(codigo);
		List<Producto> productosFiltrados = servicio.buscar(filtro);
		if(productosFiltrados.size() == 0)
		{
			System.out.println("Ese producto no existe");
		}
		else
		{
			modificado = productosFiltrados.get(0);
			System.out.println(modificado);
			System.out.println("Introduce el nuevo nombre");
			String nombre = scan.nextLine();
			System.out.println("Introduce la nueva descripción");
			String descripcion = scan.nextLine();
			System.out.println("Introduce el nuevo precio");
			Double precio = Double.valueOf(scan.nextLine());
			System.out.println("Introduce la nueva cantidad disponible");
			int cantidadDisponible = Integer.valueOf(scan.nextLine());
			System.out.println("Introduce la nueva cantidad vendida");
			int cantidadVendida = Integer.valueOf(scan.nextLine());
			System.out.println("Que nuevo IVA va a tener el producto");
			System.out.println("1. GENERAL(21%)");
			System.out.println("2. REDUCIDO(10%)");
			System.out.println("3. SUPERREDUCIDO(4%)");
			int opcionIVA = Integer.valueOf(scan.nextLine());
			IVA iva = null;
			switch (opcionIVA) 
			{
			case 1:
				iva = IVA.GENERAL;
				break;
			case 2:
				iva = IVA.REDUCIDO;
				break;
			case 3:
				iva = IVA.SUPERREDUCIDO;
				break;
			default:
				break;
			}
			System.out.println("Que nueva Categoria va a tener el producto");
			System.out.println("1. ALIMENTACION");
			System.out.println("2. MATERIAL");
			System.out.println("3. MECANICO");
			System.out.println("4. LUJO");
			int opcionCategoria = Integer.valueOf(scan.nextLine());
			Categorias categoria = null;
			switch (opcionCategoria) 
			{
			case 1:
				categoria = Categorias.ALIMENTACION;
				break;
			case 2:
				categoria = Categorias.MATERIAL;
				break;
			case 3:
				categoria = Categorias.MECANICO;
				break;
			case 4:
				categoria = Categorias.LUJO;
				break;
			default:
				break;
			}
			if(servicio.actualizar(codigo, new Producto(nombre, descripcion, precio, cantidadDisponible, cantidadVendida, iva, categoria))) 
			{
				System.out.println("Se ha modificado el producto");
			}
			else
			{
				System.out.println("No se ha podido modificar el producto");
			}
		}
		menu();
	}

	private static void buscarProducto() 
	{
		System.out.println("Introduce el codigo");
		int codigo = Integer.valueOf(scan.nextLine());
		System.out.println("Introduce el nombre");
		String nombre = scan.nextLine();
		System.out.println("Selecciona la categoria del producto");
		System.out.println("1. ALIMENTACION");
		System.out.println("2. MATERIAL");
		System.out.println("3. MECANICO");
		System.out.println("4. LUJO");
		int opcionCategoria = Integer.valueOf(scan.nextLine());
		Categorias categoria = null;
		switch (opcionCategoria) 
		{
		case 1:
			categoria = Categorias.ALIMENTACION;
			break;
		case 2:
			categoria = Categorias.MATERIAL;
			break;
		case 3:
			categoria = Categorias.MECANICO;
			break;
		case 4:
			categoria = Categorias.LUJO;
			break;
		default:
			break;
		}
		Producto filtro = new Producto(nombre, null, 0.0, 0, 0, null, categoria);
		filtro.setCodigo(codigo);
		List<Producto> productosFiltrados = servicio.buscar(filtro);
		if(productosFiltrados.size() == 0)
		{
			System.out.println("No hay productos con esas caracteristicas");
		}
		else
		{
			for (Producto producto : productosFiltrados) 
			{
				System.out.println(producto);
			}
			
		}
		menu();
	}

	private static void listarProductos() 
	{
		List<Producto> listaProductos = servicio.listarTodos();
		if(listaProductos.size() == 0)
		{
			System.out.println("No hay productos");
		}
		else
		{
			for (Producto producto : listaProductos) 
			{
				System.out.println(producto);
			}
		}
		menu();
	}

	public static void crear()
	{
		System.out.println("Introduce el nombre");
		String nombre = scan.nextLine();
		System.out.println("Introduce la descripción");
		String descripcion = scan.nextLine();
		System.out.println("Introduce el precio");
		Double precio = Double.valueOf(scan.nextLine());
		System.out.println("Introduce la cantidad disponible");
		int cantidadDisponible = Integer.valueOf(scan.nextLine());
		System.out.println("Introduce la cantidad vendida");
		int cantidadVendida = Integer.valueOf(scan.nextLine());
		System.out.println("Que IVA va a tener el producto");
		System.out.println("1. GENERAL(21%)");
		System.out.println("2. REDUCIDO(10%)");
		System.out.println("3. SUPERREDUCIDO(4%)");
		int opcionIVA = Integer.valueOf(scan.nextLine());
		IVA iva = null;
		switch (opcionIVA) 
		{
		case 1:
			iva = IVA.GENERAL;
			break;
		case 2:
			iva = IVA.REDUCIDO;
			break;
		case 3:
			iva = IVA.SUPERREDUCIDO;
			break;
		default:
			break;
		}
		System.out.println("Que Categoria va a tener el producto");
		System.out.println("1. ALIMENTACION");
		System.out.println("2. MATERIAL");
		System.out.println("3. MECANICO");
		System.out.println("4. LUJO");
		int opcionCategoria = Integer.valueOf(scan.nextLine());
		Categorias categoria = null;
		switch (opcionCategoria) 
		{
		case 1:
			categoria = Categorias.ALIMENTACION;
			break;
		case 2:
			categoria = Categorias.MATERIAL;
			break;
		case 3:
			categoria = Categorias.MECANICO;
			break;
		case 4:
			categoria = Categorias.LUJO;
			break;
		default:
			break;
		}
		if(servicio.crear(new Producto(nombre, descripcion, precio, cantidadDisponible, cantidadVendida, iva, categoria)))
		{
			System.out.println("Se ha añadido el producto");
		}
		else
		{
			System.out.println("No se ha podido añadir el producto");
		}
		menu();
	}
}
