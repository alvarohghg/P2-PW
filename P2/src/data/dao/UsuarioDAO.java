package data.dao;
import java.sql.*;
import java.util.ArrayList;

//import com.mysql.jdbc.ResultSet;

import business.Usuario;
import data.common.DBConnection;



public class UsuarioDAO {
	public ArrayList<Usuario> obtenerUsuarios(){
		ArrayList<Usuario> listOfUsers = new ArrayList<Usuario>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			// Important: This query is hard-coded here for illustrative purposes only
			String query = "select * from usuario";
			
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
	
	public void escribirUsuarioBD(Usuario user) {
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			// Important: This query is hard-coded here for illustrative purposes only
			String nombre=user.getNombre();
			String apellido=user.getApellidos();
			String tipo=user.getTipo();
			String correo=user.getCorreo();
			String nick=user.getNick();
			String query = "INSERT INTO  `usuario` (`nombre`, `apellidos`, `tipo` , `correo`, `nick`) VALUES ( "
						+ nombre + apellido + tipo + correo + nick + " )";
					
			
			// Important: We can replace this direct invocation to CRUD operations in DBConnection
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);


			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
}
