package Lab9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class Ejercicio3 {
	public static void main(String[] args) {
		try {
			// 1. crear conecion
			Connection miConexionMySQL=DriverManager.getConnection("jdbc:mysql://dif-mysql.ehu.es:3306/DBC05?&useSSL=false", "DBC05","DBC05");

			// 2. preparar consulta
			Statement instrSql=miConexionMySQL.createStatement();

			// 3. estableces parametros de consulta	
			PreparedStatement ps = miConexionMySQL.prepareStatement("select * from cliente where cliente.dni=? AND cliente.ntelefono=?");

			System.out.println("Introduzca el dni"); //10000023
			Scanner sc = new Scanner(System.in);
			String dniscanner = sc.nextLine();
			 
			System.out.println("Introduzca el tlf"); //943000023
			Scanner sc1 = new Scanner(System.in);
			String tlfscanner = sc1.nextLine();
			
			ps.setString(1, dniscanner);
			ps.setString(2,tlfscanner);
			ResultSet resulSQL = ps.executeQuery();
			while (resulSQL.next()) {
				System.out.println(resulSQL.getString("dni")+","
						+ resulSQL.getString("nombre") + ", "
						+ resulSQL.getString("direccion")+ ", "
						+ resulSQL.getInt("ntelefono"));
			}
			resulSQL.close();
			instrSql.close();
			miConexionMySQL.close();
			ps.close();

		}catch(SQLException ex) {

		}
	}
}
