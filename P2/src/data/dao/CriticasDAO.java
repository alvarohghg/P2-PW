package data.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import business.Criticas;
import data.common.DBConnection;

public class CriticasDAO {
	public ArrayList<Criticas> obtenerCriticas(){
		ArrayList<Criticas> listOfCriticas = new ArrayList<Criticas>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			// Important: This query is hard-coded here for illustrative purposes only
			String query = propiedades(1);
			
			// Important: We can replace this direct invocation to CRUD operations in DBConnection
			Statement stmt = connection.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(query);

			while (rs.next()) {
				String titulo = rs.getString("titulo");
				String puntuacion = rs.getString("puntuacion");
				String espectaculo = rs.getString("espectaculo");
				String review = rs.getString("review");
				String valoraciones = rs.getString("valoraciones");
				String controlarPrimeraVez=rs.getString("controlarPrimeraVez");
				String autor = rs.getString("autor");
				String votantes = rs.getString("votantes");
				listOfCriticas.add(new Criticas(titulo, espectaculo, puntuacion,review,autor,valoraciones,controlarPrimeraVez,votantes));
			}

			if (stmt != null){ 
				stmt.close(); 
			}
			dbConnection.closeConnection();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return listOfCriticas;
	}
	
	public void escribirCriticasBD(Criticas critica) {
		
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			// Important: This query is hard-coded here for illustrative purposes only
			String query = propiedades(2);
			PreparedStatement ps=connection.prepareStatement(query);
			
			String titulo=critica.getTitulo();
			String puntuacion=critica.getPuntuacion();
			String espectaculo=critica.getEspectaculo();
			String review=critica.getReview();
			String valoraciones=critica.getValoraciones();
			String controlarPrimeraVez=critica.getcontrolarPrimeraVez();
			String autor=critica.getAutor();
			String votantes=critica.getVotantes();
			
			ps.setString(1,titulo);
			ps.setString(2,puntuacion);
			ps.setString(3,espectaculo);
			ps.setString(4,review);
			ps.setString(5,valoraciones);
			ps.setString(6,controlarPrimeraVez);
			ps.setString(7,autor);
			ps.setString(8,votantes);
			ps.executeUpdate();
			
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public void actualizarAutorBD(String correonuevo,String correoviejo){
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query=propiedades(3); 
			
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1,correonuevo);
			ps.setString(2,correoviejo);
			ps.executeUpdate();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public void borraCriticaBD(String titulo, String correo){
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query=propiedades(4);
			
			// Important: We can replace this direct invocation to CRUD operations in DBConnection
			PreparedStatement ps=connection.prepareStatement(query);

			ps.setString(1,titulo);
			ps.setString(2,correo);
			ps.executeUpdate();

			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	public void actualizarCriticaBDvotantes(String titulo,String votantes) {
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = propiedades(5);
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1,titulo);
			ps.setString(2,votantes);
			ps.executeUpdate();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	public void actualizarCriticaBDpuntuacion(String titulo,String puntuacion) {
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			String query = propiedades(6);
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1,titulo);
			ps.setString(2,puntuacion);
			ps.executeUpdate();
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	public String propiedades(int r) {
        Properties prop = new Properties();
        String filename = "sqlC.properties";
        String f=null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
            prop.load(reader);
            if(r==1) {
                f = prop.getProperty("obtenerCriticas");
            }
            else if(r==2) {
                f = prop.getProperty("escribirCriticasBD");
            }
            else if(r==3) {
                f = prop.getProperty("actualizarAutor");
            }
            else if(r==4) {
                f = prop.getProperty("borrarCriticaBD");
            }
            else if(r==5) {
                f = prop.getProperty("actualizarCriticaBDvotantes");
            }
            else {
                f = prop.getProperty("actualizarCriticaBDpuntuacion");
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
