package es.eoi.GestionProductos.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.eoi.GestionProductos.entities.Producto;
import es.eoi.GestionProductos.enums.Categorias;

public class ProductoDaoImpl implements ProductoDao
{
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	
	public String volcarJSON()
	{
		String jsonEntero = "";
		FileReader fr;
		try
		{
			fr = new FileReader("Productos.json");
			BufferedReader br = new BufferedReader(fr);
			String linea = "";
			while((linea = br.readLine()) != null)
			{
				//System.out.println(linea);
				jsonEntero = jsonEntero.concat(linea);
			}
			br.close();
		}
		catch (FileNotFoundException e)
		{
			System.err.println("No se ha podido encontrar el fichero");
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonEntero;
	}
	
	public List<Producto> stringJSONtoLista(String json)
	{
		Producto[] productos = gson.fromJson(json, Producto[].class);
		List<Producto> listaProductos = new ArrayList<Producto>();
		if(productos != null)
		{
			listaProductos = Arrays.asList(productos);
		}
		return listaProductos;
	}
	
	public String ListaToStringJSON(List<Producto> listaProductos)
	{
		String json = new GsonBuilder().setPrettyPrinting().create().toJson(listaProductos);
		return json;
	}
	
	public boolean escribirJSON(String json)
	{
		FileWriter fw;
		try {
			fw = new FileWriter("Productos.json", false);
			fw.write(json);
			fw.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean crear(Producto nuevoProducto)
	{
		if(nuevoProducto != null)
		{
			String json = volcarJSON();
			List<Producto> listaProductos = new ArrayList<Producto>(stringJSONtoLista(json));
			boolean repetido = false;
			for (int i = 0; i < listaProductos.size() && repetido == false; i++)
			{
				if(!listaProductos.get(i).getNombre().equals(nuevoProducto.getNombre()))
				{
					repetido = false;
				}
				else
				{
					repetido = true;
				}
			}
			if(repetido == false)
			{
				Producto ultimoProducto = null;
				Integer ultimoCodigo;
				if(listaProductos.size() == 0)
				{
					ultimoCodigo = 1;
				}
				else
				{
					ultimoProducto = listaProductos.get(listaProductos.size()-1);
					ultimoCodigo = ultimoProducto.getCodigo()+1;
				}
				nuevoProducto.setCodigo(ultimoCodigo);
				listaProductos.add(nuevoProducto);
				String jsonModificado = ListaToStringJSON(listaProductos);
				if(escribirJSON(jsonModificado))
				{
					return true;
				}
			}
			else if(repetido)
			{
				return false;
			}
		}
		return false;
	}
	
	public boolean actualizar(Integer codigo ,Producto nuevoProducto)
	{
		if(nuevoProducto != null || codigo != null)
		{
			String lista = volcarJSON();
			List<Producto> listaProductos = new ArrayList<Producto>(stringJSONtoLista(lista));
			Producto productoNoModificado = null;
			boolean encontrado = false;
			for (int i = 0; i < listaProductos.size() && encontrado == false; i++)
			{
				if(!listaProductos.get(i).getCodigo().equals(codigo))
				{
					encontrado = false;
				}
				else
				{
					productoNoModificado = listaProductos.get(i);
					listaProductos.remove(productoNoModificado);
					encontrado = true;
				}
			}
			if(encontrado)
			{
				productoNoModificado.setCodigo(codigo);
				if(nuevoProducto.getNombre() != null)
				{
					productoNoModificado.setNombre(nuevoProducto.getNombre());
				}
				if(nuevoProducto.getDescripcion() != null)
				{
					productoNoModificado.setDescripcion(nuevoProducto.getDescripcion());
				}
				if(!nuevoProducto.getPrecio().equals(null))
				{
					productoNoModificado.setPrecio(nuevoProducto.getPrecio());				
				}
				if(!nuevoProducto.getCantidadDisponible().equals(null))
				{
					productoNoModificado.setCantidadDisponible(nuevoProducto.getCantidadDisponible());					
				}
				if(!nuevoProducto.getCantidadVendida().equals(null))
				{
					productoNoModificado.setCantidadVendida(nuevoProducto.getCantidadVendida());
				}
				if(nuevoProducto.getIvaProducto() != null)
				{
					productoNoModificado.setIvaProducto(nuevoProducto.getIvaProducto());
				}
				if(nuevoProducto.getCategoriaProducto() != null)
				{
					productoNoModificado.setCategoriaProducto(nuevoProducto.getCategoriaProducto());
				}
				listaProductos.add(productoNoModificado);
				
				String jsonModificado = ListaToStringJSON(listaProductos);
				
				if(escribirJSON(jsonModificado))
				{
					return true;
				}
			}
			else
			{
				return false;
			}
		}
		return false;
		
	}

	public List<Producto> buscar(Producto filtro)
	{
		/*Integer codidoProducto;
		String nombreProducto;
		Categorias categoriaProducto;*/
		List<Producto> productosFiltrados = new ArrayList<Producto>();
		
		String lista = volcarJSON();
		List<Producto> listaProductos = new ArrayList<Producto>(stringJSONtoLista(lista));
		
		for (Producto producto : listaProductos) 
		{
			if(producto.getCodigo().equals(filtro.getCodigo()) || producto.getNombre().equals(filtro.getNombre()) || producto.getCategoriaProducto().equals(filtro.getCategoriaProducto()))
			{
				productosFiltrados.add(producto);
			}
			/*if(producto.getCodigo().equals(filtro.getCodigo()) && producto.getNombre().equals(filtro.getNombre()) && producto.getCategoriaProducto().equals(filtro.getCategoriaProducto()))
			{
				productosFiltrados.add(producto);
			}
			else if(producto.getCodigo().equals(filtro.getCodigo()) && producto.getNombre().equals(filtro.getNombre()))
			{
				productosFiltrados.add(producto);
			}
			else if(producto.getCodigo().equals(filtro.getCodigo()) && producto.getCategoriaProducto().equals(filtro.getCategoriaProducto()))
			{
				productosFiltrados.add(producto);
			}
			else if(producto.getNombre().equals(filtro.getNombre()) && producto.getCategoriaProducto().equals(filtro.getCategoriaProducto()))
			{
				productosFiltrados.add(producto);
			}
			else if(producto.getCodigo().equals(filtro.getCodigo()))
			{
				productosFiltrados.add(producto);
			}
			else if(producto.getNombre().equals(filtro.getNombre()))
			{
				productosFiltrados.add(producto);
			}
			else if(producto.getCategoriaProducto().equals(filtro.getCategoriaProducto()))
			{
				productosFiltrados.add(producto);
			}*/
		}
		return productosFiltrados;
	}

	public List<Producto> listarTodos()
	{
		String lista = volcarJSON();
		List<Producto> listaProductos = new ArrayList<Producto>(stringJSONtoLista(lista));
		return listaProductos;
	}

	public boolean borrar(Integer codigo)
	{
		String lista = volcarJSON();
		List<Producto> listaProductos = new ArrayList<Producto>(stringJSONtoLista(lista));
		for (Producto producto : listaProductos)
		{
			if(producto.getCodigo().equals(codigo))
			{
				if(listaProductos.remove(producto))
				{
					String jsonModificado = ListaToStringJSON(listaProductos);
					if(escribirJSON(jsonModificado))
					{
						return true;
					}
				}
				else
				{
					return false;
				}
			}
		}
		return false;
	}

	public boolean vender(Integer codigo, int cantidad)
	{
		String lista = volcarJSON();
		List<Producto> listaProductos = new ArrayList<Producto>(stringJSONtoLista(lista));
		for (Producto producto : listaProductos)
		{
			if(producto.getCodigo().equals(codigo) && producto.getCantidadDisponible() > 0)
			{
				producto.setCantidadVendida(producto.getCantidadVendida()+cantidad);
				producto.setCantidadDisponible(producto.getCantidadDisponible()-cantidad);
				String jsonModificado = ListaToStringJSON(listaProductos);
				if(escribirJSON(jsonModificado))
				{
					return true;
				}
			}
		}
		return false;
	}

	public String informe() 
	{
		String lista = volcarJSON();
		List<Producto> listaProductos = new ArrayList<Producto>(stringJSONtoLista(lista));
		String informeAlimentacion = "ALIMENTACION\n";
		String informeLujo = "LUJO\n";
		String informeMaterial = "MATERIAL\n";
		String informeMecanico = "MECANICO\n";
		Double sumaSinIVA = 0.0;
		Double sumaConIVA = 0.0;
		
		List<Producto> listaProductosAlimentacion = new ArrayList<Producto>();
		List<Producto> listaProductosLujo = new ArrayList<Producto>();
		List<Producto> listaProductosMaterial = new ArrayList<Producto>();
		List<Producto> listaProductosMecanico = new ArrayList<Producto>();
		for (Producto producto : listaProductos) 
		{
			if(producto.getCategoriaProducto().equals(Categorias.ALIMENTACION) && producto.getCantidadVendida() > 0) 
			{
				listaProductosAlimentacion.add(producto);
			}
			else if(producto.getCategoriaProducto().equals(Categorias.LUJO) && producto.getCantidadVendida() > 0) 
			{
				listaProductosLujo.add(producto);
			}
			else if(producto.getCategoriaProducto().equals(Categorias.MATERIAL) && producto.getCantidadVendida() > 0) 
			{
				listaProductosMaterial.add(producto);
			}
			else if(producto.getCategoriaProducto().equals(Categorias.MECANICO) && producto.getCantidadVendida() > 0) 
			{
				listaProductosMecanico.add(producto);
			}
		}
		if(listaProductosAlimentacion.size() > 0)
		{
			for (Producto producto : listaProductosAlimentacion) 
			{
				informeAlimentacion = informeAlimentacion.concat(String.format("%04d", producto.getCodigo()) + " - " + producto.getNombre() + "(x"+ producto.getCantidadVendida() + ")............. Total sin IVA: " + (producto.getCantidadVendida() * producto.getPrecio()) + "€ / Total con IVA: " + (producto.getCantidadVendida() *(producto.getPrecio() + (producto.getPrecio() * producto.getIvaProducto().getCantidad()))) + "\n");
				sumaSinIVA += (producto.getCantidadVendida() * producto.getPrecio());
				sumaConIVA += (producto.getCantidadVendida() *(producto.getPrecio() + (producto.getPrecio() * producto.getIvaProducto().getCantidad())));
			}
			informeAlimentacion = informeAlimentacion.concat("TOTAL ............. Total sin IVA: " + sumaSinIVA + "€ / Total con IVA: " + sumaConIVA + "€\n");
			
		}
		else
		{
			informeAlimentacion = "";
		}
		sumaSinIVA = 0.0;
		sumaConIVA = 0.0;
		if(listaProductosLujo.size() > 0)
		{
			for (Producto producto : listaProductosLujo) 
			{
				informeLujo = informeLujo.concat(String.format("%04d", producto.getCodigo()) + " - " + producto.getNombre() + "(x"+ producto.getCantidadVendida() + ")............. Total sin IVA: " + (producto.getCantidadVendida() * producto.getPrecio()) + "€ / Total con IVA: " + (producto.getCantidadVendida() *(producto.getPrecio() + (producto.getPrecio() * producto.getIvaProducto().getCantidad()))) + "\n");
				sumaSinIVA += (producto.getCantidadVendida() * producto.getPrecio());
				sumaConIVA += (producto.getCantidadVendida() *(producto.getPrecio() + (producto.getPrecio() * producto.getIvaProducto().getCantidad())));
			}
			informeLujo = informeLujo.concat("TOTAL ............. Total sin IVA: " + sumaSinIVA + "€ / Total con IVA: " + sumaConIVA + "€\n");
		}
		else
		{
			informeLujo = "";
		}
		sumaSinIVA = 0.0;
		sumaConIVA = 0.0;
		if(listaProductosMaterial.size() > 0)
		{
			for (Producto producto : listaProductosMaterial) 
			{
				informeMaterial = informeMaterial.concat(String.format("%04d", producto.getCodigo()) + " - " + producto.getNombre() + "(x"+ producto.getCantidadVendida() + ")............. Total sin IVA: " + (producto.getCantidadVendida() * producto.getPrecio()) + "€ / Total con IVA: " + (producto.getCantidadVendida() *(producto.getPrecio() + (producto.getPrecio() * producto.getIvaProducto().getCantidad()))) + "\n");
				sumaSinIVA += (producto.getCantidadVendida() * producto.getPrecio());
				sumaConIVA += (producto.getCantidadVendida() *(producto.getPrecio() + (producto.getPrecio() * producto.getIvaProducto().getCantidad())));
			}
			informeMaterial = informeMaterial.concat("TOTAL ............. Total sin IVA: " + sumaSinIVA + "€ / Total con IVA: " + sumaConIVA + "€\n");
		}
		else
		{
			informeMaterial = "";
		}
		sumaSinIVA = 0.0;
		sumaConIVA = 0.0;
		if(listaProductosMecanico.size() > 0)
		{
			for (Producto producto : listaProductosMecanico) 
			{
				informeMecanico = informeMecanico.concat(String.format("%04d", producto.getCodigo()) + " - " + producto.getNombre() + "(x"+ producto.getCantidadVendida() + ")............. Total sin IVA: " + (producto.getCantidadVendida() * producto.getPrecio()) + "€ / Total con IVA: " + (producto.getCantidadVendida() *(producto.getPrecio() + (producto.getPrecio() * producto.getIvaProducto().getCantidad()))) + "\n");
				sumaSinIVA += (producto.getCantidadVendida() * producto.getPrecio());
				sumaConIVA += (producto.getCantidadVendida() *(producto.getPrecio() + (producto.getPrecio() * producto.getIvaProducto().getCantidad())));
			}
			informeMecanico = informeMecanico.concat("TOTAL ............. Total sin IVA: " + sumaSinIVA + "€ / Total con IVA: " + sumaConIVA + "€\n");
		}
		else
		{
			informeMecanico = "";
		}
		return informeAlimentacion.concat(informeLujo).concat(informeMaterial).concat(informeMecanico);
	}
}
