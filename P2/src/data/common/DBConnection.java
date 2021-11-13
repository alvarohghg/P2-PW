package data.common;
import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Una clase para manejar la conexión MySQL (métodos generales y configuración).
 * @author Alvaro Berjillos
 * @author Francisco Javier Diaz
 * @author Alvaro Sanchez
 * */
public class DBConnection {
	protected Connection connection = null;
	
	protected String url = propiedades(1);

	protected String user = propiedades(2);
 
	protected String password = propiedades(3);
	
	/*Funciones para notificar apertura y cierre de conexión*/
	public Connection getConnection(){

		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = (Connection) DriverManager.getConnection(url, user, password);
			System.out.println("Database connection successfully opened!");
		} 
		catch (SQLException e) {
			System.err.println("Connection to MySQL has failed!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found.");
			e.printStackTrace();
		}
		return this.connection;
	}
	public void closeConnection() {
		try {
			if(this.connection != null && !this.connection.isClosed()) {
				this.connection.close();
				System.out.println("Database connection successfully closed!");
			}
		} catch (SQLException e) {
			System.err.println("Error while trying to close the connection.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Funcion propiedades para conectar a la base de datos
	 * @param r Opcion correspondiente a la linea del fichero
	 * @return f Cadena que contiene la información que se corresponda
	 * con la linea deseada
	 */
	public String propiedades(int r) {
		
		Properties prop = new Properties();
		String filename = "config.properties";
		String f=null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			prop.load(reader);
			if(r==1) {
				f = prop.getProperty("url");
			}
			else if(r==2) {
				f = prop.getProperty("user");
			}
			else {
				f = prop.getProperty("password");
			}
					
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return f;
	}
	
}
