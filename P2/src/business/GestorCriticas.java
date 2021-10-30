package business;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.io.*;
import data.dao.CriticasDAO;
import data.dao.UsuarioDAO;

/**
 * Una clase que representa al gestor de criticas
 * @author Alvaro Berjillos
 * @author Alvaro Sanchez
 * @author Francisco Javier Díaz
 * @version 1.0
 * */

public class GestorCriticas {
	/* Atributos */
	CriticasDAO CDAO=new CriticasDAO();
	private ArrayList<Criticas> ListaCriticas = new ArrayList<Criticas>();
	Scanner entrada = new Scanner(System.in);
	private ArrayList<Usuario> ListaUsuario  = new ArrayList<Usuario>();
	GestorEspectaculos GE=new GestorEspectaculos();
	//GestorUsuario GU=new GestorUsuario();

	/* Patron de diseno singlenton */
	private static GestorCriticas instancia;
	
	private GestorCriticas(){}
	
	public static GestorCriticas getInstancia(){
		if(instancia==null) {
			instancia=new GestorCriticas();
		}
		return instancia;
	}
	
	/**
	 * Comprueba si existe el titulo dado 
	 * @param titulo El titulo a buscar
	 * @return true o false segun si exite o no existe el titulo
	 * */
	
	public boolean existeTitulo(String titulo) {
		boolean existe=false;
		for(int i=0;i<ListaCriticas.size();i++) {
			if(titulo.equals(ListaCriticas.get(i).getTitulo())) {	
				return true;
			}
		} 
		return existe;
	}
	
	/**
	 * 
	 * @param titulo El titulo de la critica
	 * @param espectaculo El espectaculo al que hace la critica
	 * @param puntuacion La puntuacion que le da al espectaculo
	 * @param review Su opinion sobre el espectaculo
	 * @param autor El autor de la critica
	 * @param valoraciones La puntuacion que le dan los usuario a esa critica
	 * @throws IOException
	 */
	public boolean crearCritica(String titulo,String espectaculo,String puntuacion,String review, String correo, String valoraciones)throws IOException{
		leerFicheroUsuarios();
		boolean correcto=false;
		for(int i=0; i< ListaUsuario.size(); i++){
			if(correo.equals(ListaUsuario.get(i).getCorreo())){
					Criticas C1 = new Criticas(titulo,espectaculo,puntuacion,review,correo,valoraciones,"0","");
					subirCritica(C1);
					correcto=true;
				
			}
		}
		
		return correcto;
	}	
	
	
	/**
	 * Guarda la critica que le pasamos en la lista de critica y en el fichero
	 * @param c Critca que queremos guardar
	 * @throws IOException
	 */
	public void subirCritica(Criticas c)throws IOException {
		this.ListaCriticas.add(c);
		CDAO.escribirCriticasBD(c);
		
	}
	/**
	 * Devolvemos la lista de criticas para poder interactuar con ella en el main
	 * @return Devuelve la lista de criticas entera
	 * @throws IOException
	 */
	public ArrayList<Criticas> verCriticas() throws IOException {
		leerFicheroUsuarios();
		return ListaCriticas;
	}
	
	/**
	 * Borramos la critica que nos dan, comprobando que sea el autor
	 * @param titulo Titulo de la critica a borrar
	 * @param correo Correo del usuario que ha iniciado sesion, que tiene que coincidir con el del autor de la critica a borrar
	 * @throws IOException
	 */
	public void borraCritica(String titulo, String correo) throws IOException {
		
		//String titulo;
		for(int i=0; i< ListaCriticas.size(); i++) {
			if(correo.equals(ListaCriticas.get(i).getAutor()) && titulo.equals(ListaCriticas.get(i).getTitulo())){
				ListaCriticas.remove(i);
				CDAO.borraCriticaBD(titulo,correo);
			}
		}
		
		
	}
	/**
	 * Actualizamos el valor de la critica haciendo la media entre el valor que ya teniamos y el nuevo que nos dan
	 * @param correo Correo del que ha iniciado sesion, que tiene que ser diferente al del autor de la critica
	 * @param puntuacion Puntuacion que le da a la critica
	 * @param titulo titulo de la critica a votar
	 * @throws IOException
	 */
	public void votarCritica(String correo, float puntuacion, String titulo) throws IOException {
		if(titulo.equals(null)) {
			System.out.println("ha ocurrido un fallo");
			
		}else {
			ArrayList<String> listaVotantes= new ArrayList<String>();
			String vot=null;
			for(int i=0; i< ListaCriticas.size(); i++) {
				if(titulo.equals(ListaCriticas.get(i).getTitulo())){
					 vot=ListaCriticas.get(i).getVotantes();
				}
			}
			
			String[] parts = vot.split(",");
			for(int i=0;i<parts.length;i++) {
				listaVotantes.add(parts[i]);
			}
			
			boolean yaVoto=false;
			for(int j=0; j< listaVotantes.size(); j++) {	
				if(listaVotantes.get(j).equals(correo)) {
					yaVoto=true;
				}
			}

			if(yaVoto==false) {
				for(int i=0; i< ListaCriticas.size(); i++) {			
					if(titulo.equals(ListaCriticas.get(i).getTitulo())) {
						float estrellas= Float.parseFloat(ListaCriticas.get(i).getValoraciones());
						if(ListaCriticas.get(i).getcontrolarPrimeraVez().equals("0")) {
							puntuacion=puntuacion*2;
							ListaCriticas.get(i).setcontrolarPrimeraVez("1");
						}
						String op=Float.toString((puntuacion+estrellas)/2);
						ListaCriticas.get(i).setValoraciones(op);
						CDAO.actualizarCriticaBDpuntuacion(titulo, op);
						ListaCriticas.get(i).setVotantes(ListaCriticas.get(i).getVotantes()+correo+",");
						CDAO.actualizarCriticaBDvotantes(titulo,ListaCriticas.get(i).getVotantes()+correo+"," );
					}
				}
			}
			else {
				System.out.println("No puede votar 2 veces la misma critica\n");
			}
			
			
		}
		
		
	}
	
	
		
	public String propiedades(int r) {
		
		Properties prop = new Properties();
		String filename = "conf.propierties";
		String f=null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			prop.load(reader);
			if(r==0) {
				f = prop.getProperty("criticas");
			}
			else {
				f = prop.getProperty("usuario");
			}
			//System.out.println(f);			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
	}
	
	/**
	 * Nos dan una critica y la escribimos en el fichero correspondiente 
	 * @param C Critica a escribir en el fichero
	 */
	/*
	public void escribirFicheroCritica(Criticas C){
		FileWriter fichero = null;
		String f=null;
		f=propiedades(0);
		//System.out.println(f);
		try {

			fichero = new FileWriter(f, true);
			//fichero = new FileWriter(f);
			
			fichero.write(C.getTitulo()+"\n");
			fichero.write(C.getEspectaculo()+"\n");
			fichero.write(C.getPuntuacion()+"\n");
			fichero.write(C.getReview()+"\n");
			fichero.write(C.getAutor()+"\n");
			fichero.write(C.getValoraciones()+"\n");
			fichero.write(C.getcontrolarPrimeraVez()+"\n");			
			fichero.write(C.getVotantes()+"\n");
			
			//fichero.write("\n");
			fichero.close();

		} catch (Exception ex) {
			System.out.println("Mensaje de la excepción: " + ex.getMessage());
		}
	}
	*/
	/**
	 * Leemos el fichero de criticas y guardamos su contenido en nuestra lista de criticas para poder trabajar con ella
	 * @throws IOException
	 */
	/*
	public void leerFicheroCriticas() throws IOException {
		BufferedReader in = null;
		ArrayList<Criticas> lista = new ArrayList<Criticas>();
		ArrayList<String>  vec = new ArrayList<String>();
		File file = new File("ficheroC.txt");
        file.createNewFile();
       
		String f=null;
		f=propiedades(0);
		//System.out.println(f);
		
          

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
		        //in.close();
		    }
		}
		
		for(int ind=0;ind<vec.size();ind+=8) {
				Criticas aux=new Criticas(vec.get(ind),vec.get(ind+1),vec.get(ind+2),vec.get(ind+3),vec.get(ind+4),vec.get(ind+5),vec.get(ind+6),vec.get(ind+7));
				lista.add(aux);
		}
			
		
		ListaCriticas=lista;
		
	}
		
	*/
	/**
	 * Leemos el fichero de usuarios y lo guardamos en nuestra lista de usuarios para poder trabajar en ella
	 * @throws IOException
	 */
	
	public void leerFicheroUsuarios()throws IOException  {
		BufferedReader in = null;
		ArrayList<String>  vec = new ArrayList<String>();
		File file = new File("ficheroU.txt");
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
		}
		
		for(int ind=0;ind<vec.size();ind+=5) {
				Usuario aux=new Usuario(vec.get(ind+1),vec.get(ind+2),vec.get(ind+3),vec.get(ind),vec.get(ind+4));
				ListaUsuario.add(aux);
		}
		
	}
		
	/**
	 * Actualizamos las criticas de autor cuando este se cambia el correo
	 * @param correoNuevo Correo nuevo
	 * @param correoViejo Correo viejo
	 * @throws IOException
	 */
	
	public void actualizarAutor(String correoNuevo, String correoViejo) throws IOException {
		for(int i=0;i<ListaCriticas.size();i++) {
			if(correoViejo.equals(ListaCriticas.get(i).getAutor())) {
				ListaCriticas.get(i).setAutor(correoNuevo);
			}
		}

		
				
			CDAO.actualizarAutorBD(correoNuevo, correoViejo);
		
	}
	
}
