package data.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
			String query = "select * from espectaculopuntual";
			
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

}

