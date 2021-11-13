package data.dao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import business.Usuario;
import data.common.DBConnection;

/**
 * Clase correspondiente a las funciones de creación/eliminación/modificación de
 * usuarios de temporada de la base de datos
 * @author Alvaro Berjillos
 * @author Francisco Javier Diaz
 * @author Alvaro Sanchez
 *
 */

public class UsuarioDAO {
	/**
	 * Funcion para obtener las secuencias de ordenes en sql del fichero
	 * sqlU.properties 
	 * @param r Opcion correspondiente a la línea 
	 * que determinará los datos que se obtendrán
	 * @return f
	 */
	public String propiedades(int r) {
		Properties prop = new Properties();
		String filename = "sqlU.properties";
		String f=null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			prop.load(reader);
			if(r==1) {
				f = prop.getProperty("obtenerUsuarios");
			}
			else if(r==2) {
				f = prop.getProperty("escribirUsuarioBD");
			}
			else if(r==3) {
				f = prop.getProperty("actualizarUsuarioBD1");
			}
			else if(r==4) {
				f = prop.getProperty("actualizarUsuarioBD2");
			}
			else if(r==5) {
				f = prop.getProperty("actualizarUsuarioBD3");
			}
			else if(r==6) {
				f = prop.getProperty("actualizarUsuarioBD4");
			}
			else {
				f = prop.getProperty("eliminarUsuarioBD");
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
	 * Funcion para extraer los usuarios de la BBDD a la lista
	 * @return listOfUsers Una lista de usuarios
	 * de la base de datos
	 */
	public ArrayList<Usuario> obtenerUsuarios(){
		ArrayList<Usuario> listOfUsers = new ArrayList<Usuario>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			// Important: This query is hard-coded here for illustrative purposes only
			String query = propiedades(1);
			
			// Important: We can replace this direct invocation to CRUD operations in DBConnection
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			while (rs.next()) {
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String tipo = rs.getString("tipo");
				String correo = rs.getString("correo");
				String nick = rs.getString("nick");
				listOfUsers.add(new Usuario(nombre, apellidos, nick,correo,tipo));
			}

			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return listOfUsers;
	}
	/**
	 * Funcion para escribir usuario en la base de datos
	 * pasado por argumento
	 * @param user Usuario que sera añadido a la base de datos
	 */
	public void escribirUsuarioBD(Usuario user) {
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			// Important: This query is hard-coded here for illustrative purposes only
			String query =propiedades(2);
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, user.getNombre());
			ps.setString(2, user.getApellidos());
			ps.setString(3, user.getTipo());
			ps.setString(4, user.getCorreo());
			ps.setString(5, user.getNick());
			ps.executeUpdate();
			
			// Important: We can replace this direct invocation to CRUD operations in DBConnection
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	/**
	 * Funcion para modificar los datos de un usuario existente en la base de datos
	 * @param correo Correo del usuario a modificar
	 * @param nuevonombre Nuevo nombre del usuario
	 * @param nuevoapellidos Nuevos apellidos
	 * @param nuevonick Nuevo nick del usuario
	 * @param nuevocorreo Nuevo correo del usuario
	 * @param opcion Opcion que determina la operación que se realizará
	 */
	public void actualizarUsuarioBD(String correo,String nuevonombre,String nuevoapellidos,String nuevonick,String nuevocorreo,int opcion){
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query=null; 
			// Important: This query is hard-coded here for illustrative purposes only
			switch(opcion) {
				case 1:
					query = propiedades(3);
					PreparedStatement ps=connection.prepareStatement(query);
					ps.setString(1, nuevonick);
					ps.setString(2, correo);
					ps.executeUpdate();
				break;
				case 2:
					query = propiedades(4);
					PreparedStatement ps1=connection.prepareStatement(query);
					ps1.setString(1, nuevonick);
					ps1.setString(2, correo);
					ps1.executeUpdate();
				break;
				case 3:
					query = propiedades(5);
					PreparedStatement ps2=connection.prepareStatement(query);
					ps2.setString(1, nuevonick);
					ps2.setString(2, correo);
					ps2.executeUpdate();
					break;
				case 4:
					query = propiedades(6);
					PreparedStatement ps3=connection.prepareStatement(query);
					ps3.setString(1, nuevonick);
					ps3.setString(2, correo);
					ps3.executeUpdate();
					break;
			}

			


			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Borra un usuario de la base de datos dado el titulo
	 * @param correo Correo del espectaculo
	 */
	public void eliminarUsuarioBD(String correo){
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query=propiedades(7);
			
			// Important: We can replace this direct invocation to CRUD operations in DBConnection
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, correo);
			ps.executeUpdate();

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	
}
