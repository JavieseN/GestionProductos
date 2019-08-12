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
					if(!listaProductos.get(i).getCodigo().equals(nuevoProducto.getCodigo()))
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

	public boolean actualizar(Producto nuevoProducto)
	{
		if(nuevoProducto != null)
		{
			String lista = volcarJSON();
			List<Producto> listaProductos = new ArrayList<Producto>(stringJSONtoLista(lista));
			boolean encontrado = false;
			for (int i = 0; i < listaProductos.size() && encontrado == false; i++)
			{
				if(!listaProductos.get(i).getCodigo().equals(nuevoProducto.getCodigo()))
				{
					encontrado = false;
				}
				else
				{
					encontrado = true;
				}
			}
			if(encontrado)
			{
				
			}
		}
		return false;
	}

	public Producto buscar(Producto filtro)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public List<Producto> listarTodos(Producto filtro)
	{
		String lista = volcarJSON();
		List<Producto> listaProductos = new ArrayList<Producto>(stringJSONtoLista(lista));
		return listaProductos;
	}

	public boolean borrar(Producto nuevoProducto)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public void vender(Producto nuevoProducto)
	{
		// TODO Auto-generated method stub
		
	}

}
