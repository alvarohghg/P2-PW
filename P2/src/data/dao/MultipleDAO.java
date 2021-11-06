package data.dao;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import business.EspectaculoPuntual;
import business.Usuario;
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
				ResultSet rs2 = (ResultSet) stmt2.executeQuery(query2);
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
	public void escribirMultipleBD(EspectaculoMultiple multiple ) {
        try {
        	
            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.getConnection();
            // Important: This query is hard-coded here for illustrative purposes only
            String titulo=multiple.getTitulo();
            String descripcion=multiple.getDescripcion();
            String cate=multiple.getCategoria().toString();
            String aforo=String.valueOf(multiple.getAforolocalidades());
            String vendidas=String.valueOf(multiple.getLocalidadesvendidas());
            ArrayList<Date> listaFechas=multiple.getListaFechas();
            String query = "INSERT INTO  `espectaculomultiple` (`titulo_mult`, `descripcion_mult`, `cate_mult` , `aforo_mult`, `vendidas_mult`) VALUES ( "
                        + titulo+", " + descripcion +", "+ cate +", "+ aforo +", "+ vendidas + " )";
            String query2=null;
            int i=0;
            while(i<listaFechas.size()) {
                query2 = "INSERT INTO  `multiplefechas` (`titulo_mult`, `fecha_mult`) "
                		+ "VALUES ( "+titulo+", " + String.valueOf(listaFechas.get(i))+ " )";
                i++;
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
	
	public void actualizarMultipleBD(String titulo,String nuevotitulo,String nuevadescripcion,categoria nuevacategoria,int nuevoaforolocalidades,
			int localidadesvendidas,Date fecha1,Date fecha2,int opcion){
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query=null; 
			String query2=null;
			// Important: This query is hard-coded here for illustrative purposes only
			switch(opcion) {
				case 1:
					query = "UPDATE espectaculomultiple SET titulo_mult = "+ nuevotitulo +"WHERE [Last titulo_mult] = " + titulo;
				break;
				case 2:
					query = "UPDATE espectaculomultiple SET descripcion_mult =  "+ nuevadescripcion +"WHERE [Last titulo_mult] = " + titulo;
				break;
				case 3:
					query = "UPDATE espectaculomultiple SET categoria = "+ nuevacategoria +"WHERE [Last titulo_mult] = " + titulo;
				break;
				case 4:
					query = "UPDATE espectaculomultiple SET aforolocalidades =  "+ String.valueOf(nuevoaforolocalidades) +"WHERE [Last titulo_mult] = " + titulo;
				break;
				case 5:
					query = "UPDATE espectaculomultiple SET localidadesvendidas =  "+ String.valueOf(nuevoaforolocalidades) +"WHERE [Last titulo_mult] = " + titulo;
				break;
				case 6:
					query2 = "UPDATE multiplefechas SET fecha_mult =  "+ String.valueOf(fecha2) +"WHERE [Last titulo_mult] = " + titulo+ "AND [Last fecha_mult] ="+String.valueOf(fecha1);
						
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
	public void eliminarMultipleTitulo(String titulo){
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query="DELETE FROM espectaculomultiple WHERE titulo_mult = "+ titulo;
			String query2="DELETE FROM multiplefechas WHERE titulo_mult = "+ titulo;
			// Important: We can replace this direct invocation to CRUD operations in DBConnection
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);
			Statement stmt2 = connection.createStatement();
			ResultSet rs2 = (ResultSet) stmt2.executeQuery(query2);

			if (stmt != null){ 
				stmt.close(); 
			}
			if (stmt2 != null){ 
				stmt2.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	public void eliminarMultipleFecha(String titulo, Date fecha){
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = "DELETE FROM multiplefechas WHERE titulo_mult = "+ titulo + "AND fecha_mult ="+ fecha;
			
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
