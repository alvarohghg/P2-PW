package data.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import business.*;
import business.AbstractEspectaculo.categoria;
import data.common.DBConnection;

public class TemporadaDAO {
	public ArrayList<EspectaculoTemporada> obtenerTemporada(){
		ArrayList<EspectaculoTemporada> listaT = new ArrayList<EspectaculoTemporada>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			// Important: This query is hard-coded here for illustrative purposes only
			String query = "select * from espectaculotemporada";
			
			// Important: We can replace this direct invocation to CRUD operations in DBConnection
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			while (rs.next()) {
				String titulo = rs.getString("titulo_temp");
				String descripcion = rs.getString("descripcion_temp");
				categoria cate =business.AbstractEspectaculo.categoria.valueOf(rs.getString("categoria_temp"));
				int aforo = rs.getInt("aforolocalidades_temp");
				int localidades = rs.getInt("localidadesvendidas_temp");
				String dia= rs.getString("dia_temp");
				Date inicio = rs.getDate("inicio_temp");
				Date fin = rs.getDate("fin_temp");
				listaT.add(new EspectaculoTemporada(titulo, descripcion, cate,aforo,localidades,dia,inicio,fin));
			}

			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return listaT;
	}
public void escribirTemporadaBD(EspectaculoTemporada temporada) {
	try {
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		// Important: This query is hard-coded here for illustrative purposes only
		String titulo_temp=temporada.getTitulo();
		String descripcion_temp=temporada.getDescripcion();
		String categoria_temp=temporada.getCategoria().toString();
		String aforolocalidades_temp=String.valueOf(temporada.getAforolocalidades());
		String localidadesvendidas_temp=String.valueOf(temporada.getLocalidadesvendidas());
		String dia_temp=temporada.getDia();
		String inicio_temp=String.valueOf(temporada.getInicio());
		String fin_temp=String.valueOf(temporada.getFin());
		String query = "INSERT INTO `espectaculotemporada` ( `titulo_temp` , `descripcion_temp` , `categoria_temp` , `aforolocalidades_temp` , `localidadesvendidas_temp` , `dia_temp` , `inicio_temp` , `fin_temp` ) "
				+ "VALUES ( "
				+ titulo_temp +"," + descripcion_temp +"," + categoria_temp +"," +  aforolocalidades_temp + "," +localidadesvendidas_temp + "," + dia_temp+"," + inicio_temp + "," + fin_temp
				+ ");";
				
		
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

public void actualizarTemporadaBD(String titulo,String nuevotitulo,String nuevadescripcion,categoria nuevacategoria,int nuevoaforolocalidades,
		int localidadesvendidas,Date nuevafechafin,Date nuevafechainicio,String dia,int opcion) {
	try {
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		String query=null; 
		// Important: This query is hard-coded here for illustrative purposes only
		switch(opcion) {
		case 1:
			query = "UPDATE espectaculotemporada SET titulo_temp = "+ nuevotitulo + "WHERE [Last titulo_temp] = " + titulo;
		break;
		case 2:
			query = "UPDATE espectaculotemporada SET descripcion_temp =  "+ nuevadescripcion + "WHERE [Last titulo_temp] = " + titulo;
		break;
		case 3:
			query = "UPDATE espectaculotemporada SET categoria_temp = "+ nuevacategoria +"WHERE [Last titulo_temp] = " + titulo;
		break;
		case 4:
			query = "UPDATE espectaculotemporada SET aforolocalidades_temp =  "+ String.valueOf(nuevoaforolocalidades) +"WHERE [Last titulo_temp] = " + titulo;
		break;
		case 5:
			query = "UPDATE espectaculotemporada SET localidadesvendidas_temp = "+ String.valueOf(localidadesvendidas) +"WHERE [Last titulo_temp] = " + titulo;
		break;
		case 6:
			query = "UPDATE espectaculotemporada SET dia_temp =  "+ String.valueOf(dia) +"WHERE [Last titulo_temp] = " + titulo;
		break;
		case 7:
			query = "UPDATE espectaculotemporada SET inicio_temp =  "+ String.valueOf(nuevafechainicio) +"WHERE [Last titulo_temp] = " + titulo;
		break;
		case 8:
			query = "UPDATE espectaculotemporada SET fin_temp =  "+ String.valueOf(nuevafechafin) +"WHERE [Last titulo_temp] = " + titulo;
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
public void eliminarTemporadaTitulo(String titulo){
	try {
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		String query="DELETE FROM espectaculotemporada WHERE titulo_temp = "+ titulo;
		
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
//QUE HACEMOS PARA ELIMINAR ESPECTACULOS CUYA FECHA QUE LE PASES POR ESTA FUNCION ESTÉ EN MEDIO?¿?¿
public void eliminarTemporadaFecha(String titulo, Date fecha){
	try {
		DBConnection dbConnection = new DBConnection();
		Connection connection = dbConnection.getConnection();
		String query = "DELETE FROM espectaculotemporada WHERE titulo_temp = "+ titulo + "AND inicio_temp ='"+ fecha +"' OR fin_temp ='"+fecha
				+"' OR( '"+fecha+"' >=inicio_temp AND '"+fecha+"'<=fin_temp)";
		
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
