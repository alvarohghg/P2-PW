package data.dao;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileWriter;
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

public class TemporadaDAO {
	public String propiedades(int r) {
        Properties prop = new Properties();
        String filename = "sqlT.propierties";
        String f=null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
            prop.load(reader);
            if(r==1) {
                f = prop.getProperty("obtenerTemporada");
            }
            else if(r==2) {
                f = prop.getProperty("escribirTemporadaBD");
            }
            else if(r==3) {
                f = prop.getProperty("actualizarTemporadaBD1");
            }
            else if(r==4) {
                f = prop.getProperty("actualizarTemporadaBD2");
            }
            else if(r==5) {
                f = prop.getProperty("actualizarTemporadaBD3");
            }
            else if(r==6) {
                f = prop.getProperty("actualizarTemporadaBD4");
            }
            else if(r==7) {
                f = prop.getProperty("actualizarTemporadaBD5");
            }
            else if(r==8) {
                f = prop.getProperty("actualizarTemporadaBD6");
            }
            else if(r==9) {
            	 f = prop.getProperty("actualizarTemporadaBD7");
            }
            else if(r==10) {
           	 f = prop.getProperty("actualizarTemporadaBD8");
            }
            else if(r==11) {
           	 f = prop.getProperty("eliminarTemporadaTitulo");
           }
            else {
                f = prop.getProperty("eliminarTemporadaFecha");
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
	public ArrayList<EspectaculoTemporada> obtenerTemporada(){
		ArrayList<EspectaculoTemporada> listaT = new ArrayList<EspectaculoTemporada>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			// Important: This query is hard-coded here for illustrative purposes only
			String query = propiedades(1);
			
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
		String query =propiedades(2);
				
		
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
		case 7:
			query = propiedades(9);		
			break;
		case 8:
			query = propiedades(10);		
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
		String query= propiedades(11);		
		
		
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
		String query = propiedades(12);
		
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
