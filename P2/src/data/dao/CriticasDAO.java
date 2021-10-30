package data.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import business.Criticas;
import data.common.DBConnection;

public class CriticasDAO {
	public ArrayList<Criticas> obtenerUsuarios(){
		ArrayList<Criticas> listOfCriticas = new ArrayList<Criticas>();
		try {
			DBConnection dbConnection = new DBConnection();
			Connection connection = dbConnection.getConnection();
			// Important: This query is hard-coded here for illustrative purposes only
			String query = "select * from usuario";
			
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
			
			String titulo=critica.getTitulo();
			String puntuacion=critica.getPuntuacion();
			String espectaculo=critica.getEspectaculo();
			String review=critica.getReview();
			String valoraciones=critica.getValoraciones();
			String controlarPrimeraVez=critica.getcontrolarPrimeraVez();
			String autor=critica.getAutor();
			String votantes=critica.getVotantes();
			String query = "INSERT INTO  `criticas` (`titulo`, `puntuacion`, `espectaculo` , `review`, `valoraciones`,`controlarPrimeraVez`,`autor`,`votantes`) VALUES ( "
			+ titulo + puntuacion + espectaculo + review + valoraciones +controlarPrimeraVez+autor+votantes+ " )";
					
			
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
