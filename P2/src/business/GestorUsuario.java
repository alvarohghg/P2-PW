package business;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import data.dao.UsuarioDAO;

/**
 * Una clase que representa al gestor de usuarios
 * @author Alvaro Berjillos
 * @author Alvaro Sanchez
 * @author Francisco Javier D?az
 * @version 1.0
 * */
public class GestorUsuario {
	/* Atributos */
	private ArrayList<Usuario> ListaUsuarios=new ArrayList<Usuario>();
	Scanner entrada = new Scanner(System.in);
	Scanner entrada2 = new Scanner(System.in);
	UsuarioDAO UDAO=new UsuarioDAO();
	/**
	 * Constructor parametrizado
	 * @param listausers Lista de los usuarios
	 * */
	public GestorUsuario(ArrayList<Usuario> listausers) {
		this.ListaUsuarios=listausers;
		
	}
	/*setter de la lista de usuarios*/
	public ArrayList<Usuario> devolverLista(){
		return ListaUsuarios;
	}
	/**
	 * Añade un usuario a la lista interna del gestor 
	 * y lo escribe en el fichero "ficheroU.txt"
	 * @param newuser El usuario a a?adir
	 * */
	public void addUser(Usuario newuser) {
				
		this.ListaUsuarios.add(newuser);	
		UDAO.escribirUsuarioBD(newuser);
		
	}
	
	public void guardarBDU() {
		ListaUsuarios=UDAO.obtenerUsuarios();
	}
	
	/**
	 * Comprueba si existe un usuario dado el correo
	 * @param correo El correo a buscar
	 * @return existe Una variable inicializada a false
	 * que es toma el valor a true si existe el usuario
	 * */
	public boolean existeUsuario(String correo) {
		boolean existe=false;
		for(int i=0;i<ListaUsuarios.size();i++) {
			if(correo.equals(ListaUsuarios.get(i).getCorreo())) {	
				return true;
			}
			
		} 
		
		return existe;
	}
	
	public ArrayList<String> verAutores(){
		ArrayList<String> l= new ArrayList<String>();
		for(int i=0;i<ListaUsuarios.size();i++) {
			l.add(ListaUsuarios.get(i).getCorreo());
		} 
		return l;
	}
	
	/**
	 * Registra un usuario en la lista de usuarios 
	 * @param nombre El nombre del nuevo usuario
	 * @param apellidos Los apellidos del nuevo usuario
	 * @param nick El nick del nuevo usuario
	 * @param correo El correo del nuevo usuario
	 * */
	public void register(String nombre,String apellidos,String nick,String correo,String tipo) {
		Usuario nuevouser=new Usuario(nombre,apellidos,nick,correo,tipo);
		addUser(nuevouser);
	}
	
	public String tipoUsuario(String correo) {
		for(int i=0; i<ListaUsuarios.size();i++) {
			if(correo.equals(ListaUsuarios.get(i).getCorreo())) {
				return ListaUsuarios.get(i).getTipo();
			}
		}
		return null;
	}
	
	
	
	/**
	 * Elimina a un usuario de la lista de usuarios 
	 * y del fichero "ficheroU.txt"
	 * @param correo El correo del usaurio a eliminar
	 * @return var Una variable inicializada a false
	 * que es toma el valor a true si se ha
	 * eliminado de la lista correctamente
	 * */
	public boolean unregister(String correo) throws IOException {
		boolean var=false;
			for(int i=0;i<ListaUsuarios.size();i++) {
				if(correo.equals(ListaUsuarios.get(i).getCorreo())) {
					ListaUsuarios.remove(i);
					var=true;
				}
			}
			
			UDAO.eliminarUsuarioBD(correo);
			return var;

	}
	
	
	/**
	 * Imprime todos los correos de los usuarios registrados
	 */
	public void imprimirCorreo() {
		for(int i=0; i< ListaUsuarios.size(); i++) {
			System.out.println(ListaUsuarios.get(i).getCorreo()+"\n");
		}
	}
	/**
	 * Imprime todos los datos de los usuarios registrados
	 */
	public void imprimirUsuario(String correo) {
		for(int i=0; i< ListaUsuarios.size(); i++) {
			if(correo.equals(ListaUsuarios.get(i).getCorreo())) {
				System.out.println(ListaUsuarios.get(i).toString());
			}
			
			
		}
	}
	
	/**
	 * Actualiza la lista de usuarios y el fichero "ficheroU.txt"
	 * @param correo Correo con el que se ha iniciado sesion
	 * @param nuevonombre Nuevo nombre del usuario
	 * @param nuevoapellidos Nuevos apellidos del usuario
	 * @param nuevonick Nuevo nick del usuario
	 * @param nuevocorreo Nuevo correo del usuario
	 * @param opcion La opcion (inicializada en el main) que determina los cambios
	 * @throws IOException
	 */
	public void updateUser(String correo,String nuevonombre,String nuevoapellidos,String nuevonick,String nuevocorreo,int opcion) throws IOException {
		for(int i=0;i<ListaUsuarios.size();i++) {
			if(correo.equals(ListaUsuarios.get(i).getCorreo())) {
						switch(opcion) {
							case 1:
								this.ListaUsuarios.get(i).setNick(nuevonick);
							break;
							case 2:
								this.ListaUsuarios.get(i).setNombre(nuevonombre);
							break;
							case 3:
								this.ListaUsuarios.get(i).setApellidos(nuevoapellidos);
							break;
							case 4:
								this.ListaUsuarios.get(i).setCorreo(nuevocorreo);
							break;
						}
			}
		}
		UDAO.actualizarUsuarioBD(correo, nuevonombre, nuevoapellidos, nuevonick, nuevocorreo, opcion);
		
}
	public String propiedades() {
		Properties prop = new Properties();
		String filename = "conf.propierties";
		String f=null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			prop.load(reader);
			f = prop.getProperty("usuario");			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}
		
	}
