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
	
	public boolean crear(Producto nuevoProducto)
	{
		if(nuevoProducto != null)
		{
			try
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
					FileWriter fw = new FileWriter("Productos.json", false);
					fw.write(jsonModificado);
					fw.close();
					return true;
				}
				else if(repetido)
				{
					return false;
				}
				
			}
			catch(IOException e)
			{
				System.err.println("No se ha podido escribir en el fichero");
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
				FileWriter fw;
				try
				{
					fw = new FileWriter("Productos.json", false);
					fw.write(jsonModificado);
					fw.close();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	public Producto buscar(Producto filtro)
	{
		// TODO Auto-generated method stub
		return null;
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
				listaProductos.remove(producto);
			}
		}
		return false;
	}

	public void vender(Producto nuevoProducto)
	{
		// TODO Auto-generated method stub
		
	}
}
