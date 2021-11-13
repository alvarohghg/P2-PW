package es.uco.pw.data.dao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

//import com.mysql.jdbc.ResultSet;

import es.uco.pw.business.user.Usuario;
import es.uco.pw.data.common.DBConnection;



public class UsuarioDAO {
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
			else if (r==5) {
				f = prop.getProperty("actualizarUsuarioBD3");
			}
			else {
				f = prop.getProperty("actualizarUltima");
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
	
	public ArrayList<Usuario> obtenerUsuarios(){
		ArrayList<Usuario> listOfUsers = new ArrayList<Usuario>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			// Important: This query is hard-coded here for illustrative purposes only
			//String query = "select * from usuarioEJ2";
			String query = propiedades(1);

			// Important: We can replace this direct invocation to CRUD operations in DBConnection
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			while (rs.next()) {
				String nombre = rs.getString("nombreEJ2");
				String apellidos = rs.getString("apellidosEJ2");
				String tipo = rs.getString("tipoEJ2");
				String correo = rs.getString("correoEJ2");
				String nick = rs.getString("nickEJ2");
				Date registro = rs.getDate("fechaRegistroEJ2");
				Date ultima = rs.getDate("ultimaConexion");

				listOfUsers.add(new Usuario(nombre, apellidos, nick,correo,tipo,registro,ultima));
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
			//String query = propiedades(2);
			String query ="INSERT INTO usuarioEJ2 (nombreEJ2, apellidosEJ2 , tipoEJ2 , correoEJ2, nickEJ2, fechaRegistroEJ2, ultimaConexion ) VALUES ( ?,?,?,?,?,?,? )";
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, user.getNombre());
			ps.setString(2, user.getApellidos());
			ps.setString(3, user.getTipo());
			ps.setString(4, user.getCorreo());
			ps.setString(5, user.getNick());
			ps.setDate(6, user.getFechaRegistro());
			ps.setDate(7, user.getUltimaConexion());
			ps.executeUpdate();
			
			// Important: We can replace this direct invocation to CRUD operations in DBConnection
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public void actualizarUsuarioBD(String correo,String nuevonombre,String nuevoapellidos,String nuevonick,String nuevocorreo,int opcion){
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query=null; 
			// Important: This query is hard-coded here for illustrative purposes only
			switch(opcion) {
				case 1:
					//query = propiedades(3);
					query = "UPDATE usuarioEJ2 SET nickEJ2 = ? WHERE correoEJ2 = ?";
					PreparedStatement ps=connection.prepareStatement(query);
					ps.setString(1, nuevonick);
					ps.setString(2, correo);
					ps.executeUpdate();
				break;
				case 2:
					//query = propiedades(4);
					query = "UPDATE usuarioEJ2 SET nombreEJ2 =  ? WHERE correoEJ2 = ?";
					PreparedStatement ps1=connection.prepareStatement(query);
					ps1.setString(1, nuevonombre);
					ps1.setString(2, correo);
					ps1.executeUpdate();
				break;
				case 3:
					//query = propiedades(5);
					query = "UPDATE usuarioEJ2 SET apellidosEJ2 = ? WHERE correoEJ2 = ?";
					PreparedStatement ps2=connection.prepareStatement(query);
					ps2.setString(1, nuevoapellidos);
					ps2.setString(2, correo);
					ps2.executeUpdate();
					break;
			}


			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/*
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
	}*/
	
	public void actualizarUltima(String correo, Date fecha){
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			//String query = propiedades(6);
			String query="UPDATE usuarioEJ2 SET ultimaConexion = ? WHERE correoEJ2 = ? ";
			
			// Important: We can replace this direct invocation to CRUD operations in DBConnection
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setDate(1, fecha);
			ps.setString(2, correo);
			ps.executeUpdate();

			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	
}