package data.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import business.*;
import business.AbstractEspectaculo.categoria;
import data.common.DBConnection;

public class PuntualDAO {
	public ArrayList<EspectaculoPuntual> obtenerPuntual(){
		ArrayList<EspectaculoPuntual> listaP = new ArrayList<EspectaculoPuntual>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			// Important: This query is hard-coded here for illustrative purposes only
			String query = propiedades(1);
			
			// Important: We can replace this direct invocation to CRUD operations in DBConnection
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			while (rs.next()) {
				String titulo = rs.getString("titulo_puntual");
				String descripcion = rs.getString("descripcion_puntual");
				categoria cate =business.AbstractEspectaculo.categoria.valueOf(rs.getString("categoria_puntual"));
				int aforo = rs.getInt("aforolocalidades_puntual");
				int localidades = rs.getInt("localidadesvendidas_puntual");
				Date fecha = rs.getDate("fecha_puntual");
				listaP.add(new EspectaculoPuntual(titulo, descripcion, cate,aforo,localidades,fecha));
			}

			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return listaP;
	}
	
	public void escribirPuntualBD(EspectaculoPuntual puntual ) {
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			// Important: This query is hard-coded here for illustrative purposes only
			String titulo=puntual.getTitulo();
			String descripcion=puntual.getDescripcion();
			String cate=puntual.getCategoria().toString();
			String aforo=String.valueOf(puntual.getAforolocalidades());
			String vendidas=String.valueOf(puntual.getLocalidadesvendidas());
			String fecha= String.valueOf(puntual.getFechaPuntual());
			String query = propiedades(2);
					
			
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
	
	public void actualizarPuntualBD(String titulo,String nuevotitulo,String nuevadescripcion,categoria nuevacategoria,int nuevoaforolocalidades,
			int localidadesvendidas,Date fecha,int opcion) {
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query=null; 
			// Important: This query is hard-coded here for illustrative purposes only
			switch(opcion) {
				case 1:
					query = propiedades(3);
				break;
				case 2:
					query = propiedades(4);
				break;
				case 3:
					query = propiedades(5);
				break;
				case 4:
					query = propiedades(6);
				break;
				case 5:
					query = propiedades(7);
				break;
				case 6:
					query = propiedades(8);
				break;
			}
			
					
			
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
	
	public void eliminarPuntualTitulo(String titulo){
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query=propiedades(9);
			
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
	
	public void eliminarPuntualFecha(String titulo, Date fecha){
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = propiedades(10);
			
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

	public String propiedades(int r) {
		Properties prop = new Properties();
		String filename = "sqlP.properties";
		String f=null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
			prop.load(reader);
			if(r==1) {
				f = prop.getProperty("obtenerPuntual");
			}
			else if(r==2) {
				f = prop.getProperty("escribirPuntualBD");
			}
			else if(r==3) {
				f = prop.getProperty("actualizarPuntualBD1");
			}
			else if(r==4) {
				f = prop.getProperty("actualizarPuntualBD2");
			}
			else if(r==5) {
				f = prop.getProperty("actualizarPuntualBD3");
			}
			else if(r==6) {
				f = prop.getProperty("actualizarPuntualBD4");
			}
			else if(r==7) {
				f = prop.getProperty("actualizarPuntualBD5");
			}
			else if(r==8) {
				f = prop.getProperty("actualizarPuntualBD6");
			}
			else if(r==9) {
				f = prop.getProperty("eliminarPuntualTitulo");
			}
			else {
				f = prop.getProperty("eliminarPuntualFecha");
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
}

