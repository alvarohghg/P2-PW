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
			String query = "INSERT INTO  `espectaculopuntual` (`titulo_puntual`, `descripcion_puntual`, `categoria_puntual` , `aforolocalidades_puntual`, `localidadesvendidas_puntual`, `fecha_puntual`) VALUES ( "
						+ titulo +"," + descripcion +"," + cate +"," + aforo +"," + vendidas +","+ fecha + " )";
					
			
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
					query = "UPDATE espectaculopuntual SET titulo_puntual = "+ nuevotitulo + "WHERE [Last titulo_puntual] = " + titulo;
				break;
				case 2:
					query = "UPDATE espectaculopuntual SET descripcion_puntual =  "+ nuevadescripcion + "WHERE [Last titulo_puntual] = " + titulo;
				break;
				case 3:
					query = "UPDATE espectaculopuntual SET categoria_puntual = "+ nuevacategoria +"WHERE [Last titulo_puntual] = " + titulo;
				break;
				case 4:
					query = "UPDATE espectaculopuntual SET aforolocalidades_puntual =  "+ String.valueOf(nuevoaforolocalidades) +"WHERE [Last titulo_puntual] = " + titulo;
				break;
				case 5:
					query = "UPDATE espectaculopuntual SET localidadesvendidas_puntual = "+ String.valueOf(localidadesvendidas) +"WHERE [Last titulo_puntual] = " + titulo;
				break;
				case 6:
					query = "UPDATE espectaculopuntual SET fecha_puntual =  "+ String.valueOf(fecha) +"WHERE [Last titulo_puntual] = " + titulo;
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
	
	
}

