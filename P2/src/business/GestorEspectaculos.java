package business;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import business.AbstractEspectaculo.categoria;
import data.dao.PuntualDAO;
import data.dao.TemporadaDAO;
import data.dao.MultipleDAO;

/**
 * 
 * @author Alvaro Berjillos
 * @authos Alvaro Sanchez
 * @author Francisco Javier Diaz
 *
 */
public class GestorEspectaculos {
	/*Atributos*/
	PuntualDAO PDAO= new PuntualDAO();
	MultipleDAO MDAO= new MultipleDAO();
	TemporadaDAO TDAO= new TemporadaDAO();
	
	private ArrayList<EspectaculoMultiple> ListaEspectaculosM=new ArrayList<EspectaculoMultiple>();
	private ArrayList<EspectaculoPuntual> ListaEspectaculosP=new ArrayList<EspectaculoPuntual>();
	private ArrayList<EspectaculoTemporada> ListaEspectaculosT=new ArrayList<EspectaculoTemporada>();
	/*Constructor parametrizado*/
	public GestorEspectaculos(ArrayList<EspectaculoMultiple> listaEspectaculosM,
			ArrayList<EspectaculoPuntual> listaEspectaculosP,
			ArrayList<EspectaculoTemporada> listaEspectaculosT) {
		
		ListaEspectaculosM = listaEspectaculosM;
		ListaEspectaculosP = listaEspectaculosP;
		ListaEspectaculosT = listaEspectaculosT;
		
	}
	/* Constructor vacio y getters y setters */
	public GestorEspectaculos() {
		
	}
	public ArrayList<EspectaculoPuntual> getListaEspectaculosP() {
		return ListaEspectaculosP;
	}
	public void setListaEspectaculosP(ArrayList<EspectaculoPuntual> listaEspectaculosP) {
		ListaEspectaculosP = listaEspectaculosP;
	}
	public ArrayList<EspectaculoTemporada> getListaEspectaculosT() {
		return ListaEspectaculosT;
	}
	public void setListaEspectaculosT(ArrayList<EspectaculoTemporada> listaEspectaculosT) {
		ListaEspectaculosT = listaEspectaculosT;
	}
	public void setListaEspectaculosM(ArrayList<EspectaculoMultiple> listaEspectaculosM) {
		ListaEspectaculosM = listaEspectaculosM;
	}
	
	
	public void guardarBDlistas() {
		ListaEspectaculosP = PDAO.obtenerPuntual();
		ListaEspectaculosT = TDAO.obtenerTemporada();
		ListaEspectaculosM = MDAO.obtenerMultiple();
	}
	
	/**
	 * A�ade un espectaculo multiple a la lista de espectaculos multiples y lo escribe en su fichero
	 * @param newespectaculo El espectaculo a a�adir
	 */
	public void addEspectM(EspectaculoMultiple newespectaculo) {

		this.ListaEspectaculosM.add(newespectaculo);
		escribirFicheroMultiple(newespectaculo);
		
	}
	/**
	 * A�ade un espectaculo de temporada a la lista de espectaculos de temporada y lo escribe en su fichero
	 * @param newespectaculo El espectaculo a a�adir
	 */
	public void addEspectT(EspectaculoTemporada newespectaculo) {

		this.ListaEspectaculosT.add(newespectaculo);	
		escribirFicheroTemporal(newespectaculo);
		
	}
	/**
	 * A�ade un espectaculo puntual a la lista de espectaculos puntuales y lo escribe en su fichero
	 * @param newespectaculo El espectaculo a a�adir
	 */
	public void addEspectP(EspectaculoPuntual newespectaculo) {

		this.ListaEspectaculosP.add(newespectaculo);
		//escribirFicheroPuntual(newespectaculo);
		PDAO.escribirPuntualBD(newespectaculo);
		
		
	}
	/**
	 * Comprueba dado un titulo si existe un espectaculo recorriendo todas las listas de espectaculos 
	 * @param titulo El titulo del espectaculo a buscar
	 * @return true si existe el espectaculo
	 */
	public boolean existeEspectaculo(String titulo) {
		
		for(int i=0;i<ListaEspectaculosM.size();i++) {
			if(titulo.equals(ListaEspectaculosM.get(i).getTitulo())) {	
				return true;
			}
			
		}
		for(int i=0;i<ListaEspectaculosT.size();i++) {
			if(titulo.equals(ListaEspectaculosT.get(i).getTitulo())) {	
				return true;
			}
			
		}
		for(int i=0;i<ListaEspectaculosP.size();i++) {
			if(titulo.equals(ListaEspectaculosP.get(i).getTitulo())) {	
				return true;
			}
			
		}
		
		return false;
	}
	/**
	 * Devuelve una lista con los titulos de todos los espect�culos
	 * @return l Lista con todos los espectaculos
	 */
	public ArrayList<String> verEspectaculos(){
		ArrayList<String> l= new ArrayList<String>();
		for(int i=0;i<ListaEspectaculosM.size();i++) {
			l.add(ListaEspectaculosM.get(i).getTitulo());
		}
		for(int i=0;i<ListaEspectaculosT.size();i++) {
			l.add(ListaEspectaculosT.get(i).getTitulo());
		}
		for(int i=0;i<ListaEspectaculosP.size();i++) {
			l.add(ListaEspectaculosP.get(i).getTitulo());
		}
		return l;
	}
	/**
	 * Funcion para registrar un espectaculo m�ltiple en la lista de espectaculos m�ltiples y en el fichero
	 * @param titulo El titulo del espectaculo
	 * @param descripcion La descripcion del espectaculo 
	 * @param categoria La categoria del espectaculo 
	 * @param aforolocalidades El aforo del espectaculo 
	 * @param localidadesvendidas Las ventas del espectaculo 
	 * @param listafechas El listado de fechas del espectaculo 
	 */
	public void registerEspectaculoM(String titulo,String descripcion,categoria categoria ,
	 int aforolocalidades, int localidadesvendidas, 
	 ArrayList<Date> listafechas) {
		
			EspectaculoMultiple nuevoespect=new EspectaculoMultiple( titulo,  descripcion,  
					categoria, aforolocalidades, localidadesvendidas,listafechas);
			addEspectM(nuevoespect);
		
		}
	/**
	 * Funcion para registrar un espectaculo de temporada en la lista de espectaculos de temporada y en el fichero
	 * @param titulo El titulo del espectaculo 
	 * @param descripcion La descripcion del espectaculo 
	 * @param categoria La categoria del espectaculo 
	 * @param aforolocalidades El aforo del espectaculo 
	 * @param localidadesvendidas Las ventas del espectaculo 
	 * @param dia El dia de la semana en el que se realiza el espectaculo 
	 * @param inicio La fecha de inicio de temporada del espectaculo 
	 * @param fin La fecha de fin de temporada del espectaculo 
	 */
	public void registerEspectaculoT(String titulo,String descripcion,categoria categoria ,
			 int aforolocalidades, int localidadesvendidas, String dia,Date inicio,Date fin) {
				

				EspectaculoTemporada nuevoespect=new EspectaculoTemporada( titulo,  descripcion,  
							categoria, aforolocalidades, localidadesvendidas, dia, inicio, fin);
					addEspectT(nuevoespect);
				
				}
	/**
	* Funcion para registrar un espectaculo puntual en la lista de espectaculos puntuales y en el fichero
	 * @param titulo El titulo del espectaculo 
	 * @param descripcion La descripcion del espectaculo 
	 * @param categoria La categoria del espectaculo 
	 * @param aforolocalidades El aforo del espectaculo 
	 * @param localidadesvendidas Las ventas del espectaculo 
	 * @param fechaPuntual La fecha en la que se realiza el espectaculo puntual
	 */
	public void registerEspectaculoP(String titulo,String descripcion,categoria categoria ,
			 int aforolocalidades,  int localidadesvendidas, Date fechaPuntual) {
				
					EspectaculoPuntual nuevoespect=new EspectaculoPuntual( titulo,  descripcion,  
							categoria, aforolocalidades, localidadesvendidas, fechaPuntual);
					addEspectP(nuevoespect);
				
				}
		
	/**
	 * Retorna la informacion de un espectaculo dado el titulo
	 * @param titulo Titulo del espectaculo
	 * @return rt Una cadena con la informacion del espectaculo
	 */
	public String imprimirEspectaculoTitulo(String titulo) {
		String rt=null;
		for(int i=0; i< ListaEspectaculosM.size(); i++) {
			if(titulo.equals(ListaEspectaculosM.get(i).getTitulo())) {
				rt=(ListaEspectaculosM.get(i).toString());
			}
		}
		for(int i=0; i< ListaEspectaculosT.size(); i++) {
			if(titulo.equals(ListaEspectaculosT.get(i).getTitulo())) {
				rt=(ListaEspectaculosT.get(i).toString());
			}
		}
		
		for(int i=0; i< ListaEspectaculosP.size(); i++) {
			if(titulo.equals(ListaEspectaculosP.get(i).getTitulo())) {
				rt=(ListaEspectaculosP.get(i).toString());
			}
		}
		return rt;
			
	}
	/**
	 * Retorna la informacion de uno o varios espectaculos dada la categoria
	 * @param categoria La categoria de los espectaculos a buscar
	 * @return rt La cadena con la informacion de los espectaculos de la categoria
	 */
		public String imprimirEspectaculoCategoria(categoria categoria) {
			String rt="";
			for(int i=0; i< ListaEspectaculosM.size(); i++) {
				if(categoria.equals(ListaEspectaculosM.get(i).getCategoria())) {
					rt+=(ListaEspectaculosM.get(i).toString());
				}
				
			}
			for(int i=0; i< ListaEspectaculosT.size(); i++) {
				if(categoria.equals(ListaEspectaculosT.get(i).getCategoria())) {
					rt+=(ListaEspectaculosT.get(i).toString());
				}
				
			}
			for(int i=0; i< ListaEspectaculosP.size(); i++) {
				if(categoria.equals(ListaEspectaculosP.get(i).getCategoria())) {
					rt+=(ListaEspectaculosP.get(i).toString());
				}
				
			}
			return rt;
		}
		/**
		 * Elimina un espectaculo dado el titulo
		 * @param titulo Titulo del espectaculo a eliminar
		 * @return true si encuentra el espectaculo y lo elimina de la lista
		 * @throws IOException
		 */
		public boolean eliminarEspectaculoTodas(String titulo) throws IOException {
			boolean var=false;
				for(int i=0;i<ListaEspectaculosM.size();i++) {
					if(titulo.equals(ListaEspectaculosM.get(i).getTitulo())) {
						ListaEspectaculosM.remove(i);
						var=true;
					}
				}
				
				new FileWriter(propiedades(3), false).close();
				for(int i=0; i< ListaEspectaculosM.size(); i++) {
					escribirFicheroMultiple(ListaEspectaculosM.get(i));
				}
				
				for(int i=0;i<ListaEspectaculosT.size();i++) {
					if(titulo.equals(ListaEspectaculosT.get(i).getTitulo())) {
						ListaEspectaculosT.remove(i);
						var=true;
					}
				}
				
				new FileWriter(propiedades(2), false).close();
				for(int i=0; i< ListaEspectaculosT.size(); i++) {
					escribirFicheroTemporal(ListaEspectaculosT.get(i));
				}
				
				for(int i=0;i<ListaEspectaculosP.size();i++) {
					if(titulo.equals(ListaEspectaculosP.get(i).getTitulo())) {
						ListaEspectaculosP.remove(i);
						PDAO.eliminarPuntualTitulo(titulo);
						var=true;
					}
				}
				/*
				new FileWriter(propiedades(1), false).close();
				for(int i=0; i< ListaEspectaculosP.size(); i++) {
					escribirFicheroPuntual(ListaEspectaculosP.get(i));
				}*/
				
				return var;

		}
		/**
		 * Elimina un espectaculo multiple dado el titulo y la fecha
		 * @param titulo Titulo del espectaculo multiple a eliminar
		 * @param fecha Fecha de la sesion a eliminar
		 * @return true si se ha eliminado correctamente
		 * @throws IOException
		 */
		public boolean eliminarEspectaculoUnaM(String titulo,Date fecha) throws IOException {
			boolean var=false;
				for(int i=0;i<ListaEspectaculosM.size();i++) {
					for(int j=0;j<ListaEspectaculosM.get(i).getListaFechas().size();j++) {
						
						if(fecha.compareTo(ListaEspectaculosM.get(i).getListaFechas().get(j))==0 && titulo.equals(ListaEspectaculosM.get(i).getTitulo())) {
							
							if(ListaEspectaculosM.get(i).getListaFechas().size()==1) { //si solo queda una sesion al eliminar se elimina el espectaculo entero
								ListaEspectaculosM.remove(i);
								break;
									}else {
										ListaEspectaculosM.get(i).getListaFechas().remove(j);
									}

							var=true;
						}
					}
				}
				
				new FileWriter(propiedades(3), false).close();
				for(int i=0; i< ListaEspectaculosM.size(); i++) {
					escribirFicheroMultiple(ListaEspectaculosM.get(i));
				}
				return var;
		}
		/**
		 * Elimina un espectaculo puntual dado el titulo y la fecha
		 * @param titulo El titulo del espectaculo puntual a eliminar
		 * @param fecha La fecha del espectaculo
		 * @return true si se elimina correctamente
		 * @throws IOException
		 */
		public boolean eliminarEspectaculoUnaP(String titulo,Date fecha) throws IOException {
			boolean var=false;
				for(int i=0;i<ListaEspectaculosP.size();i++) {
					if(titulo.equals(ListaEspectaculosP.get(i).getTitulo()) && fecha.compareTo(ListaEspectaculosP.get(i).getFechaPuntual())==0) {
						ListaEspectaculosP.remove(i);
						PDAO.eliminarPuntualFecha(titulo, fecha);
						var=true;
					}
				}
				
				/*
				new FileWriter(propiedades(1), false).close();
				for(int i=0; i< ListaEspectaculosP.size(); i++) {
					escribirFicheroPuntual(ListaEspectaculosP.get(i));
				}*/
				return var;
		}
		/**
		 * Elimina un espectaculo de temporada dado el titulo y la fecha
		 * @param titulo El titulo del espectaculo puntual a eliminar
		 * @param fecha La fecha del espectaculo
		 * @return true si se elimina correctamente
		 * @throws IOException
		 */
		public boolean eliminarEspectaculoUnaT(String titulo,Date fecha) throws IOException {
			boolean var=false;
			for(int i=0;i<ListaEspectaculosT.size();i++) {
				if(titulo.equals(ListaEspectaculosT.get(i).getTitulo()) && ListaEspectaculosT.get(i).getInicio().compareTo(fecha)<0
						&&ListaEspectaculosT.get(i).getFin().compareTo(fecha)>0 || ListaEspectaculosT.get(i).getFin().compareTo(fecha)==0
						||ListaEspectaculosT.get(i).getInicio().compareTo(fecha)==0) {
					ListaEspectaculosT.remove(i);
					var=true;
				}
			}
			new FileWriter(propiedades(2), false).close();
			for(int i=0; i< ListaEspectaculosT.size(); i++) {
				escribirFicheroTemporal(ListaEspectaculosT.get(i));
			}
				return var;
		}
		
		/**
		 * Actualiza un espectaculo en la lista de espectaculos multiples
		 * @param titulo El titulo del espectaculo a cambiar
		 * @param nuevotitulo El nuevo titulo del espectaculo
		 * @param nuevadescripcion La nueva descripcion del espectaculo
		 * @param nuevacategoria La nueva categoria del espectaculo
		 * @param nuevoaforolocalidades El nuevo aforo del espectaculo
		 * @param localidadesvendidas Las ventas del espectaculo
		 * @param fecha1 La fecha de la sesion antigua 
		 * @param fecha2 La nueva fecha de la sesion que va a cambiar
		 * @param opcion La opcion que elige el usuario en el main que determina el cambio que se realizara sobre el espectaculo
		 * @throws IOException
		 */
		public void updateEspectaculoM(String titulo,String nuevotitulo,String nuevadescripcion,categoria nuevacategoria,int nuevoaforolocalidades,
				int localidadesvendidas,Date fecha1,Date fecha2,int opcion) throws IOException {
			for(int i=0;i<ListaEspectaculosM.size();i++) {
				if(titulo.equals(ListaEspectaculosM.get(i).getTitulo())) {
							switch(opcion) {
								case 1:
									this.ListaEspectaculosM.get(i).setTitulo(nuevotitulo);
								break;
								case 2:
									this.ListaEspectaculosM.get(i).setDescripcion(nuevadescripcion);
								break;
								case 3:
									this.ListaEspectaculosM.get(i).setCategoria(nuevacategoria);
								break;
								case 4:
									this.ListaEspectaculosM.get(i).setAforolocalidades(nuevoaforolocalidades);
								break;
								case 5:
									this.ListaEspectaculosM.get(i).setLocalidadesvendidas(localidadesvendidas);
								break;
								case 6:
									this.ListaEspectaculosM.get(i).actualizarFecha(fecha1,fecha2);
										
								break;
							}
				}
			}
			new FileWriter(propiedades(3), false).close();
			for(int i=0; i< ListaEspectaculosM.size(); i++) {
				escribirFicheroMultiple(ListaEspectaculosM.get(i));
			}
			
	}
		/**
		 * Actualiza un espectaculo en la lista de espectaculos puntuales
		 * @param titulo El titulo del espectaculo a cambiar
		 * @param nuevotitulo El nuevo titulo del espectaculo
		 * @param nuevadescripcion La nueva descripcion del espectaculo
		 * @param nuevacategoria La nueva categoria del espectaculo
		 * @param nuevoaforolocalidades El nuevo aforo del espectaculo
		 * @param localidadesvendidas Las ventas del espectaculo
		 * @param fecha2 La nueva fecha de la sesion
		 * @param opcion La opcion que elige el usuario en el main que determina el cambio que se realizara sobre el espectaculo
		 * @throws IOException
		 */
		public void updateEspectaculoP(String titulo,String nuevotitulo,String nuevadescripcion,categoria nuevacategoria,int nuevoaforolocalidades,
				int localidadesvendidas,Date fecha2,int opcion) throws IOException {
			for(int i=0;i<ListaEspectaculosP.size();i++) {
				if(titulo.equals(ListaEspectaculosP.get(i).getTitulo())) {
							switch(opcion) {
								case 1:
									this.ListaEspectaculosP.get(i).setTitulo(nuevotitulo);
								break;
								case 2:
									this.ListaEspectaculosP.get(i).setDescripcion(nuevadescripcion);
								break;
								case 3:
									this.ListaEspectaculosP.get(i).setCategoria(nuevacategoria);
								break;
								case 4:
									this.ListaEspectaculosP.get(i).setAforolocalidades(nuevoaforolocalidades);
								break;
								case 5:
									this.ListaEspectaculosP.get(i).setLocalidadesvendidas(localidadesvendidas);
								break;
								case 6:
									this.ListaEspectaculosP.get(i).setFechaPuntual(fecha2);
										
								break;
							}
				}
			}
			PDAO.actualizarPuntualBD(titulo, nuevotitulo, nuevadescripcion, nuevacategoria, nuevoaforolocalidades, localidadesvendidas, fecha2, opcion);
			
			
	}
		/**
		* Actualiza un espectaculo en la lista de espectaculos de temporada
		 * @param titulo El titulo del espectaculo a cambiar
		 * @param nuevotitulo El nuevo titulo del espectaculo
		 * @param nuevadescripcion La nueva descripcion del espectaculo
		 * @param nuevacategoria La nueva categoria del espectaculo
		 * @param nuevoaforolocalidades El nuevo aforo del espectaculo
		 * @param localidadesvendidas Las ventas del espectaculo
		 * @param nuevafechafin La nueva fecha de finalizacion de temporada
		 * @param nuevafechainicio La nueva fecha de inicio de temporada
		 * @param dia El nuevo dia de la semana
		 * @param opcion La opcion que elige el usuario en el main que determina el cambio que se realizara sobre el espectaculo
		 * @throws IOException
		 */
		public void updateEspectaculoT(String titulo,String nuevotitulo,String nuevadescripcion,categoria nuevacategoria,int nuevoaforolocalidades,
				int localidadesvendidas,Date nuevafechafin,Date nuevafechainicio,String dia,int opcion) throws IOException {
			for(int i=0;i<ListaEspectaculosT.size();i++) {
				if(titulo.equals(ListaEspectaculosT.get(i).getTitulo())) {
							switch(opcion) {
								case 1:
									this.ListaEspectaculosT.get(i).setTitulo(nuevotitulo);
								break;
								case 2:
									this.ListaEspectaculosT.get(i).setDescripcion(nuevadescripcion);
								break;
								case 3:
									this.ListaEspectaculosT.get(i).setCategoria(nuevacategoria);
								break;
								case 4:
									this.ListaEspectaculosT.get(i).setAforolocalidades(nuevoaforolocalidades);
								break;
								case 5:
									this.ListaEspectaculosT.get(i).setLocalidadesvendidas(localidadesvendidas);
								break;
								case 6:
									this.ListaEspectaculosT.get(i).setDia(dia);
								break;
								case 7:
									this.ListaEspectaculosT.get(i).setInicio(nuevafechainicio);
								break;
								case 8:
									this.ListaEspectaculosT.get(i).setFin(nuevafechafin);
								break;
							}
				}
			}
			new FileWriter(propiedades(2), false).close();
			for(int i=0; i< ListaEspectaculosT.size(); i++) {
				escribirFicheroTemporal(ListaEspectaculosT.get(i));
			}
			
			
	}
		/**
		 * Devuelve una lista con los espectaculos con entradas disponibles
		 * @return lista La lista con los titulos de los espectaculos con entradas disponibles
		 */
		public ArrayList<String> entradasDisponibles() {
			ArrayList<String> lista=new ArrayList<String>();
			int entradas=0;
			for(int i=0; i< ListaEspectaculosT.size(); i++) {
			
					entradas=ListaEspectaculosT.get(i).entradasDisponibles();
					if(entradas>0) {
						lista.add(ListaEspectaculosT.get(i).getTitulo());
					
					
					
				}
			}
			entradas=0;
			for(int i=0; i< ListaEspectaculosP.size(); i++) {
				
					entradas=ListaEspectaculosP.get(i).entradasDisponibles();
					if(entradas>0) {
						lista.add(ListaEspectaculosP.get(i).getTitulo());
					}
					
				
			}
			entradas=0;
			for(int i=0; i< ListaEspectaculosM.size(); i++) {
					
						entradas=ListaEspectaculosM.get(i).entradasDisponibles();
						if(entradas>0) {
							lista.add(ListaEspectaculosM.get(i).getTitulo());
						}
					
			}
			return lista;
			
		}
		/**
		 * Devuelve una lista de los titulos de los espectaculos con entradas y sesiones posteriores a uan fecha
		 * @param fecha Fecha a partir de la cual se calculan los pr�ximos espectaculos (en el main se introduce la actual)
		 * @return lista Lista de cadenas con los titulos de los espectaculos proximos
		 */
		public ArrayList<String> proximosEspectaculos(Date fecha) {
			ArrayList<String> lista=new ArrayList<String>();
			int entradas=0;
			for(int i=0; i< ListaEspectaculosT.size(); i++) {
				if(ListaEspectaculosT.get(i).getInicio().compareTo(fecha)<0
				&& ListaEspectaculosT.get(i).getFin().compareTo(fecha)>0 ) {
					entradas=ListaEspectaculosT.get(i).entradasDisponibles();
					if(entradas>0) {
						lista.add(ListaEspectaculosT.get(i).getTitulo());
					}
					
					
				}
			}
			entradas=0;
			for(int i=0; i< ListaEspectaculosP.size(); i++) {
				if(ListaEspectaculosP.get(i).getFechaPuntual().compareTo(fecha)<0) {
					entradas=ListaEspectaculosP.get(i).entradasDisponibles();
					if(entradas>0) {
						lista.add(ListaEspectaculosP.get(i).getTitulo());
					}
					
				}
			}
			entradas=0;
			for(int i=0; i< ListaEspectaculosM.size(); i++) {
				for(int j=0;j<ListaEspectaculosM.get(i).getListaFechas().size();j++) {
					if(ListaEspectaculosM.get(i).getListaFechas().get(j).compareTo(fecha)<0) {
						entradas=ListaEspectaculosM.get(i).entradasDisponibles();
						if(entradas>0) {
							lista.add(ListaEspectaculosM.get(i).getTitulo());
						}
					}
				}
			}
			return lista;
		}
		/**
		 * Devuelve una cadena de los titulos de los espectaculos con entradas pertenecientes a una categoria dada
		 * @param fecha Sesion de los espectaculos con entradas a devolver 
		 * @param categoria Categoria que deben cumplir los espectaculos devueltos
		 * @return lista Lista con los titulos de los espectaculos
		 */
		public ArrayList<String> entradasDisponiblesCategoria(Date fecha,categoria categoria) {
			ArrayList<String> lista=new ArrayList<String>();
			int entradas=0;
			for(int i=0; i< ListaEspectaculosT.size(); i++) {
				if(ListaEspectaculosT.get(i).getInicio().compareTo(fecha)<0
				&& ListaEspectaculosT.get(i).getFin().compareTo(fecha)>0 && ListaEspectaculosT.get(i).getCategoria()==categoria) {
					entradas=ListaEspectaculosT.get(i).entradasDisponibles();
					if(entradas>0) {
						lista.add(ListaEspectaculosT.get(i).getTitulo());
					}
					
					
				}
			}
			entradas=0;
			for(int i=0; i< ListaEspectaculosP.size(); i++) {
				if(ListaEspectaculosP.get(i).getFechaPuntual().compareTo(fecha)<0&& ListaEspectaculosT.get(i).getCategoria()==categoria) {
					entradas=ListaEspectaculosP.get(i).entradasDisponibles();
					if(entradas>0) {
						lista.add(ListaEspectaculosP.get(i).getTitulo());
					}
					
				}
			}
			entradas=0;
			for(int i=0; i< ListaEspectaculosM.size(); i++) {
				for(int j=0;j<ListaEspectaculosM.get(i).getListaFechas().size();j++) {
					if(ListaEspectaculosM.get(i).getListaFechas().get(j).compareTo(fecha)<0&& ListaEspectaculosT.get(i).getCategoria()==categoria) {
						entradas=ListaEspectaculosM.get(i).entradasDisponibles();
						if(entradas>0) {
							lista.add(ListaEspectaculosM.get(i).getTitulo());
						}
					}
				}
			}
			return lista;
			
		}
		
		/**
		 * Devuelve las entradas disponibles dado un titulo y una fecha
		 * @param titulo Titulo del espectaculo a buscar
		 * @param fecha Fecha de la sesion 
		 * @return
		 */
		public int localidadesDisponibles(String titulo,Date fecha) {
			int entradas=0;
			for(int i=0; i< ListaEspectaculosP.size(); i++) {
				if(titulo.equals(ListaEspectaculosP.get(i).getTitulo()) && ListaEspectaculosP.get(i).getFechaPuntual().compareTo(fecha)==0) {
					entradas=ListaEspectaculosP.get(i).entradasDisponibles();
					if(entradas>0) {
						
						return entradas;
					}
				}
				
			}
			
			for(int i=0; i< ListaEspectaculosT.size(); i++) {
				if(titulo.equals(ListaEspectaculosT.get(i).getTitulo()) && ListaEspectaculosT.get(i).getInicio().compareTo(fecha)<0
						&&ListaEspectaculosT.get(i).getFin().compareTo(fecha)>0 || ListaEspectaculosT.get(i).getFin().compareTo(fecha)==0
						||ListaEspectaculosT.get(i).getInicio().compareTo(fecha)==0) {
					entradas=ListaEspectaculosT.get(i).entradasDisponibles();
					if(entradas>0) {
						return entradas;
					}
				}
			}
			for(int i=0; i< ListaEspectaculosM.size(); i++) {
				if(titulo.equals(ListaEspectaculosM.get(i).getTitulo())){
					for(int j=0;j<ListaEspectaculosM.get(i).getListaFechas().size();j++) {
						if(fecha.compareTo(ListaEspectaculosM.get(i).getListaFechas().get(j))==0) {
							entradas=ListaEspectaculosM.get(i).entradasDisponibles();
							if(entradas>0) {
								return entradas;
							}
							
							
						}
					
					}
				}
				
			}
			
			
			return entradas;
		}
		/**
		 * Indica de que tipo es el espectaculo dado el titulo
		 * @param titulo El titulo del espectaculo
		 * @return tipo Retorna un entero distinto asociado a cada tipo
		 */
		public int tipoEvento(String titulo) {
			int tipo=0;
			for(int i=0;i<ListaEspectaculosM.size();i++) {
				if(titulo.equals(ListaEspectaculosM.get(i).getTitulo())) {
					tipo=1; //Espectaculo Multiple
				}
			}
			for(int i=0;i<ListaEspectaculosT.size();i++) {
				if(titulo.equals(ListaEspectaculosT.get(i).getTitulo())) {
					tipo=2; //Espectaculo de temporada
				}
			}
			for(int i=0;i<ListaEspectaculosP.size();i++) {
				if(titulo.equals(ListaEspectaculosP.get(i).getTitulo())) {
					tipo=3; //Espectaculo puntual
				}
			}
			return tipo;
		}
		
		public String propiedades(int elec) {
			
			Properties prop = new Properties();
			String filename = "conf.propierties";
			String f=null;
			try {
				BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
				prop.load(reader);
				if(elec==1) {
					f = prop.getProperty("puntual");
				}
				else if(elec==2) {
					f = prop.getProperty("temporal");
				}else {
					f = prop.getProperty("multiple");
				}
						
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			return f;
		}
		
		
		
		/**
		 * Escribe un espectaculo puntual en el fichero de espectaculos puntuales
		 * @param p Espectaculo puntual a a�adir al fichero
		 */
		public void escribirFicheroPuntual(EspectaculoPuntual p){
			FileWriter fichero = null;
			String f=null;
			f=propiedades(1);	
			try {
				fichero = new FileWriter(f, true);
				fichero.write(p.getTitulo()+"\n");
				fichero.write(p.getDescripcion()+"\n");
				String auxCat=p.getCategoria().toString();
                fichero.write(auxCat+"\n");
				String aforo=String.valueOf(p.getAforolocalidades());
				fichero.write(aforo+"\n");
				String localidades=String.valueOf(p.getLocalidadesvendidas());
				fichero.write(localidades+"\n");
				String fecha=String.valueOf(p.getFechaPuntual());
				fichero.write(fecha+"\n");
				fichero.close();

			} catch (Exception ex) {
				System.out.println("Mensaje de la excepci�n: " + ex.getMessage());
			}
		}
		/**
		 * Vuelca los espectaculos puntuales a la lista de espectaculos puntuales del gestor de espectaculos
		 * @throws IOException
		 */
		public void leerFicheroPuntual() throws IOException {
			BufferedReader in = null;
			ArrayList<EspectaculoPuntual> lista = new ArrayList<EspectaculoPuntual>();
			ArrayList<String>  vec = new ArrayList<String>();
			File file = new File("ficheroP.txt");
	        file.createNewFile();
	       
			String f=null;
			f=propiedades(1);
			
	          

			try {   
			    in = new BufferedReader(new FileReader(f));
			    String str;
			    while ((str = in.readLine()) != null) {
			        vec.add(str);
			        
			    }
			} catch (FileNotFoundException e) {
			    e.printStackTrace();
			} catch (IOException e) {
			    e.printStackTrace();
			} finally {
			    if (in != null) {
			       
			    }
			}
			int aforo,localidades;
			Date fecha;
			categoria cat;
			for(int ind=0;ind<vec.size();ind+=6) {
				aforo=Integer.parseInt(vec.get(ind+3));
				localidades=Integer.parseInt(vec.get(ind+4));
				String date1=vec.get(ind+5);
				fecha=Date.valueOf(date1);
				cat=categoria.valueOf(vec.get(ind+2));

					EspectaculoPuntual aux=new EspectaculoPuntual(vec.get(ind),vec.get(ind+1),cat,aforo,localidades,fecha);
					lista.add(aux);
			}
				
			
			ListaEspectaculosP=lista;
			
		}
		/**
		 * Escribe un espectaculo de temporada en el fichero de espectaculos de temporada
		 * @param e Espectaculo de temporada a a�adir al fichero
		 */
		public void escribirFicheroTemporal(EspectaculoTemporada e){
            FileWriter fichero = null;
            String f=null;
            f=propiedades(2);
            
            
            try {

                fichero = new FileWriter(f, true);
                fichero.write(e.getTitulo()+"\n");
                fichero.write(e.getDescripcion()+"\n");
                String auxCat=e.getCategoria().toString();
                fichero.write(auxCat+"\n");
                String auxAforo=String.valueOf(e.getAforolocalidades());
                fichero.write(auxAforo+"\n");
                String auxVendidas=String.valueOf(e.getLocalidadesvendidas());
                fichero.write(auxVendidas+"\n");
                fichero.write(e.getDia()+"\n");
                String auxInicio=String.valueOf(e.getInicio());
                fichero.write(auxInicio+"\n");    
                String auxFin=String.valueOf(e.getFin());
                fichero.write(auxFin+"\n");
                fichero.close();

            } catch (Exception ex) {
                System.out.println("Mensaje de la excepci�n: " + ex.getMessage());
            }
        }
		/**
		 * Vuelca los espectaculos de temporada a la lista de espectaculos de temporada del gestor de espectaculos
		 * @throws IOException
		 */
		public void leerFicheroTemporada() throws IOException {
            BufferedReader in = null;
            ArrayList<EspectaculoTemporada> lista = new ArrayList<EspectaculoTemporada>();
            ArrayList<String>  vec = new ArrayList<String>();
            File file = new File("ficheroT.txt");
            file.createNewFile();
           
            String f=null;
            f=propiedades(2);
            try {   
                in = new BufferedReader(new FileReader(f));
                String str;
                while ((str = in.readLine()) != null) {
                    vec.add(str);
                    
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                  
                }
            }
            
            int aforoAux, vendidasAux;
            Date fechaAux, fechaAux1;
            categoria catAux;
            
            for(int ind=0;ind<vec.size();ind+=8) {
                aforoAux=Integer.parseInt(vec.get(ind+3));
                vendidasAux=Integer.parseInt(vec.get(ind+4));
                fechaAux=Date.valueOf(vec.get(ind+6));
                fechaAux1=Date.valueOf(vec.get(ind+7));
                catAux=categoria.valueOf(vec.get(ind+2));
                EspectaculoTemporada aux=new EspectaculoTemporada(vec.get(ind),vec.get(ind+1),catAux,aforoAux,vendidasAux,vec.get(ind+5),fechaAux,fechaAux1);
                lista.add(aux);
            }
                
            ListaEspectaculosT=lista;
            
        }
		/**
		 * Escribe un espectaculo multiple en el fichero de espectaculos multiples
		 * @param p Espectaculo multiple a a�adir al fichero
		 */
		public void escribirFicheroMultiple(EspectaculoMultiple p){
            FileWriter fichero = null;
            String f=null;
            f=propiedades(3);
          
            try {

                fichero = new FileWriter(f, true);  
                fichero.write(p.getTitulo()+"\n");
                fichero.write(p.getDescripcion()+"\n");
                fichero.write(p.getCategoria().toString()+"\n");
                fichero.write(String.valueOf(p.getAforolocalidades())+"\n");
                fichero.write(String.valueOf(p.getLocalidadesvendidas())+"\n");
                String enter=null;
                for(int i=0;i<p.getListaFechas().size();i++) {
                	if(i==0) {
                		enter=p.getListaFechas().get(i).toString()+",";
                	}else {
                        enter+= p.getListaFechas().get(i).toString()+",";

                	}
                }
                fichero.write(enter+"\n");

                fichero.close();

            } catch (Exception ex) {
                System.out.println("Mensaje de la excepción: " + ex.getMessage());
            }
        }
		/**
		 * Vuelca los espectaculos multiples a la lista de espectaculos multiples del gestor de espectaculos
		 * @throws IOException
		 */
         public void leerFicheroMultiple() throws IOException {
            BufferedReader in = null;
            ArrayList<EspectaculoMultiple> lista = new ArrayList<EspectaculoMultiple>();
            ArrayList<String>  vec = new ArrayList<String>();
            File file = new File("ficheroM.txt");
            file.createNewFile();
           String f=null;
            f=propiedades(3);
            
            try {   
                in = new BufferedReader(new FileReader(f));
                String str;
                while ((str = in.readLine()) != null) {
                    vec.add(str);
                    
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                   
                }
            }
            int aforo,localidades;
            for(int ind=0;ind<vec.size();ind+=6) {
                aforo=Integer.parseInt(vec.get(ind+3));
                localidades=Integer.parseInt(vec.get(ind+4));
                String[] parts=vec.get(ind+5).split(",");
                ArrayList<Date> laux = new ArrayList<Date>();
                for(int i=0;i<parts.length;i++) {
                    laux.add(Date.valueOf(parts[i]));
                }
                    EspectaculoMultiple aux=new EspectaculoMultiple(vec.get(ind),vec.get(ind+1),categoria.valueOf(vec.get(ind+2)),aforo,localidades,laux);
                    lista.add(aux);
            }
                
            
            ListaEspectaculosM=lista;

        }
		 
	
}
