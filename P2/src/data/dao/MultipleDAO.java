package data.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import business.EspectaculoPuntual;
import business.AbstractEspectaculo.categoria;
import business.EspectaculoMultiple;
import data.common.DBConnection;

public class MultipleDAO {
	public ArrayList<EspectaculoMultiple> obtenerMultiple(){
		ArrayList<EspectaculoMultiple> listaM = new ArrayList<EspectaculoMultiple>();
		ArrayList<Date> listaFechas=new ArrayList<Date>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			// Important: This query is hard-coded here for illustrative purposes only
			String query = "select * from espectaculomultiple";
			
			// Important: We can replace this direct invocation to CRUD operations in DBConnection
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			String titulo="";
			String descripcion="";
			categoria cate = null;
			int aforo = 0;
			int localidades = 0;
			
			while (rs.next()) {
				titulo = rs.getString("titulo_mult");
				descripcion = rs.getString("descripcion_mult");
				cate =business.AbstractEspectaculo.categoria.valueOf(rs.getString("categoria_mult"));
				aforo = rs.getInt("aforolocalidades_mult");
				localidades = rs.getInt("localidadesvendidas_mult");
				String query2="select fecha_mult from multiplefechas when titulo_mult="+titulo;
				Statement stmt2 = connection.createStatement();
				ResultSet rs2 = (ResultSet) stmt.executeQuery(query2);
				while (rs2.next()) {
					Date fecha=rs.getDate("fecha");
					listaFechas.add(fecha);
				}
				listaM.add(new EspectaculoMultiple(titulo, descripcion, cate,aforo,localidades,listaFechas));
				if (stmt2 != null){ 
					stmt2.close(); 
				}
			}
			
			if (stmt != null){ 
				stmt.close(); 
			}
			
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return listaM;
	}
}
