package Lab9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;


public class Ejercicio8 {

	public static void main(String[] args) {
		int i=1;
		try {

			Connection miConexionMySQL=DriverManager.getConnection("jdbc:mysql://dif-mysql.ehu.es:3306/DBC05?&useSSL=false", "DBC05","DBC05");
			
			CallableStatement miSentenciaViajes = miConexionMySQL.prepareCall("{call MUESTRA_VIAJES()}");
					
			ResultSet miResultadoV=miSentenciaViajes.executeQuery();
			
			
			System.out.println("Acontinuación se imprimirán todos los viajes con destino a Donostia");
			System.out.println("Destino       FechaSalida     Dias  PrecioDia  CiudadSalida  DNI");
			
			while(miResultadoV.next()) {
				
				System.out.println(i+". "+miResultadoV.getString("Destino")+"      "+miResultadoV.getString("FechaSalida")+"      "+miResultadoV.getString("Dias")+"      "+miResultadoV.getString("PrecioDia")+"      "+miResultadoV.getString("CiudadSalida")+"      "+miResultadoV.getString("DNI"));
				i++;
			}
			
			
			
			CallableStatement miSentencialibros = miConexionMySQL.prepareCall("{call MUESTRA_LIBROS()}");
			
			ResultSet miResultadoL=miSentencialibros.executeQuery();
			
			
			System.out.println(" \n");
			System.out.println("Acontinuación se imprimirán todos los libros con la editorial Galera:");
			i=1;
			while(miResultadoL.next()) {
				
				System.out.println(i+". "+miResultadoL.getString("titulo")+","+miResultadoL.getString("editorial")+","+miResultadoL.getString("año")+","+miResultadoL.getString("peso")+","+miResultadoL.getString("paginas")+","+miResultadoL.getString("precio")+","+miResultadoL.getString("primer_solicitante"));
				i++;
			}
			
			
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}

	}



}
