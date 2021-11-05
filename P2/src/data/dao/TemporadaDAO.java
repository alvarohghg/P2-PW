package data.dao;

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

}
