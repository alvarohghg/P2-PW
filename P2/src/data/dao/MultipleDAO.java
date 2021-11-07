package data.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import business.EspectaculoPuntual;
import business.Usuario;
import business.AbstractEspectaculo.categoria;
import business.EspectaculoMultiple;
import data.common.DBConnection;

public class MultipleDAO {
	public String propiedades(int r) {
        Properties prop = new Properties();
        String filename = "sqlM.propierties";
        String f=null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
            prop.load(reader);
            if(r==1) {
                f = prop.getProperty("obtenerMultiple1");
            }
            else if(r==2) {
                f = prop.getProperty("obtenerMultiple2");
            }
            else if(r==3) {
                f = prop.getProperty("escribirMultipleBD1");
            }
            else if(r==4) {
                f = prop.getProperty("escribirMultipleBD2");
            }
            else if(r==5) {
                f = prop.getProperty("actualizarMultipleBD1");
            }
            else if(r==6) {
                f = prop.getProperty("actualizarMultipleBD2");
            }
            else if(r==7) {
                f = prop.getProperty("actualizarMultipleBD3");
            }
            else if(r==8) {
                f = prop.getProperty("actualizarMultipleBD4");
            }
            else if(r==9) {
            	 f = prop.getProperty("actualizarMultipleBD5");
            }
            else if(r==10) {
           	 f = prop.getProperty("actualizarMultipleBD6");
            }
            else if(r==11) {
           	 f = prop.getProperty("eliminarTemporadaTitulo1");
           }
            else if(r==12) {
              	 f = prop.getProperty("eliminarTemporadaTitulo2");
              }
            else {
                f = prop.getProperty("eliminarMultipleFecha");
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
	public ArrayList<EspectaculoMultiple> obtenerMultiple(){
		ArrayList<EspectaculoMultiple> listaM = new ArrayList<EspectaculoMultiple>();
		ArrayList<Date> listaFechas=new ArrayList<Date>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			// Important: This query is hard-coded here for illustrative purposes only
			String query = propiedades(1);
			
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
				String query2=propiedades(2);
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
            String query = propiedades(3);
            String query2=null;
            int i=0;
            while(i<listaFechas.size()) {
                query2 = propiedades(4);
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
					query = propiedades(5);
				break;
				case 2:
					query = propiedades(6);
					break;
				case 3:
					query = propiedades(7);
					break;
				case 4:
					query = propiedades(8);
					break;
				case 5:
					query = propiedades(9);
					break;
				case 6:
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
	public void eliminarMultipleTitulo(String titulo){
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query=propiedades(11);
			String query2=propiedades(12);
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
			String query = propiedades(13);
			
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
