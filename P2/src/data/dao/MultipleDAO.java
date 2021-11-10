package data.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import business.AbstractEspectaculo.categoria;
import business.EspectaculoMultiple;
import data.common.DBConnection;

public class MultipleDAO {
	public String propiedades(int r) {
        Properties prop = new Properties();
        String filename = "C:\\Users\\xboxn\\AppData\\Roaming\\SPB_16.6\\git\\P2-PW\\P2\\sqlM.properties";
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
           	 f = prop.getProperty("eliminarMultipleTitulo1");
           }
            else if(r==12) {
              	 f = prop.getProperty("eliminarMultipleTitulo2");
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
				cate = business.AbstractEspectaculo.categoria.valueOf(rs.getString("categoria_mult"));
				aforo = rs.getInt("aforolocalidades_mult");
				localidades = rs.getInt("localidadesvendidas_mult");
				String query2=propiedades(2);
				PreparedStatement ps=connection.prepareStatement(query2);
				ps.setString(1, titulo);
				ResultSet rs2 =  ps.executeQuery();
				while(rs2.next()) {
					Date fecha=rs2.getDate("fecha_mult");
					listaFechas.add(fecha);
				}
				listaM.add(new EspectaculoMultiple(titulo, descripcion, cate,aforo,localidades,listaFechas));
				
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
            ArrayList<Date> listaFechas=multiple.getListaFechas();
            String query =propiedades(3);
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1,titulo);
			ps.setString(2,descripcion);
			ps.setString(3,cate);
			ps.setInt(4, multiple.getAforolocalidades());
			ps.setInt(5, multiple.getLocalidadesvendidas());
			ps.executeUpdate();
            String query2=null;
            int i=0;
            while(i<listaFechas.size()) {
                query2 = propiedades(4);
    			PreparedStatement ps1=connection.prepareStatement(query2);
    			ps1.setString(1,titulo);
    			ps1.setDate(2, listaFechas.get(i));
    			ps1.executeUpdate();
                i++;
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
			// Important: This query is hard-coded here for illustrative purposes only
			switch(opcion) {
				case 1:
					query = propiedades(5);
					PreparedStatement ps=connection.prepareStatement(query);
					ps.setString(1, nuevotitulo);
					ps.setString(2, titulo);
					ps.executeUpdate();
				break;
				case 2:
					query = propiedades(6);
					PreparedStatement ps1=connection.prepareStatement(query);
					ps1.setString(1, nuevadescripcion);
					ps1.setString(2, titulo);
					ps1.executeUpdate();
					break;
				case 3:
					query = propiedades(7);
					PreparedStatement ps2=connection.prepareStatement(query);
					ps2.setString(1, nuevacategoria.toString());
					ps2.setString(2, titulo);
					ps2.executeUpdate();
					break;
				case 4:
					query = propiedades(8);
					PreparedStatement ps3=connection.prepareStatement(query);
					ps3.setInt(1, nuevoaforolocalidades);
					ps3.setString(2, titulo);
					ps3.executeUpdate();
					break;
				case 5:
					query = propiedades(9);
					PreparedStatement ps4=connection.prepareStatement(query);
					ps4.setInt(1, localidadesvendidas);
					ps4.setString(2, titulo);
					ps4.executeUpdate();
					break;
				case 6:
					query = propiedades(10);
					PreparedStatement ps5=connection.prepareStatement(query);
					ps5.setDate(1, fecha2);
					ps5.setString(2, titulo);
					ps5.setDate(3,fecha1);
					ps5.executeUpdate();
					break;
			}
		
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	public void eliminarMultipleTitulo(String titulo){
		try {
			System.out.println("NOmbre en dao "+ titulo);
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query=propiedades(11);
			String query2=propiedades(12);
			PreparedStatement ps=connection.prepareStatement(query);
			PreparedStatement ps1=connection.prepareStatement(query2);
			ps.setString(1,titulo);
			ps1.setString(1,titulo);
			ps1.executeUpdate();
			ps.executeUpdate();
			

			
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
			
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, titulo);
			ps.setDate(2, fecha);
			ps.executeUpdate();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
}
