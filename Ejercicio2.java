package Lab9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio2 {

	public static void main(String[] args) {
		
		try {
			// 1. crear conecion
			Connection miConexionMySQL=DriverManager.getConnection("jdbc:mysql://dif-mysql.ehu.es:3306/DBC05?&useSSL=false", "DBC05","DBC05");
			
			// 2. preparar consulta
			Statement instrSql=miConexionMySQL.createStatement();
			
			// 3. estableces parametros de consulta	
			PreparedStatement ps = miConexionMySQL.prepareStatement("select * from cliente where cliente.dni=?");

			ps.setString(1, "10000001");
			ResultSet resulSQL = ps.executeQuery();
			while (resulSQL.next()) {
				System.out.println(resulSQL.getString("dni")+","
				+ resulSQL.getString("nombre") + ", "
				+ resulSQL.getString("direccion")+ ", "
				+ resulSQL.getInt("ntelefono"));
				}
			resulSQL.close();
			
			ps.setString(1, "10000004");
			ResultSet resulSQL2 = ps.executeQuery();
			while (resulSQL2.next()) {
				System.out.println(resulSQL2.getString("dni")+","
				+ resulSQL2.getString("nombre") + ", "
				+ resulSQL2.getString("direccion")+ ", "
				+ resulSQL2.getInt("ntelefono"));
				}
			resulSQL2.close();
			instrSql.close();
			miConexionMySQL.close();
			ps.close();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}

}
