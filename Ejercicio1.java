package Lab9;

import java.sql.*;
import java.sql.DriverManager;
import java.io.*;
import java.util.*;

public class Ejercicio1 {

	public static void main(String[] args) {
		try {
			// 1. crear las conexiones
			Connection miConexionMySQL=DriverManager.getConnection("jdbc:mysql://dif-mysql.ehu.es:3306/DBC05?&useSSL=false", "DBC05","DBC05");
			Connection miConexionMyOracle=DriverManager.getConnection("jdbc:oracle:thin:@vsids11.si.ehu.es:1521:gipuzkoa", "BDC05","BDC05");

						
			// 2. crear las instrucciones
			Statement instrSql=miConexionMySQL.createStatement();
			Statement instrOracle=miConexionMyOracle.createStatement();
			
			// 3. ejecutar SQLs
			ResultSet resulSQL = instrSql.executeQuery("select * from cliente");
			ResultSet resulOracle = instrOracle.executeQuery("select * from cliente");
			
			// 4. conocer el resultado e imprimir
			System.out.println("------------------------");
			System.out.println("DATOS OBTENIDOS DE MySQL\n");
			while (resulSQL.next()) {
				System.out.println(resulSQL.getString("dni")+","
				+ resulSQL.getString("nombre") + ", "
				+ resulSQL.getString("direccion")+ ", "
				+ resulSQL.getInt("ntelefono"));
				}
			
			System.out.println("");
			System.out.println("------------------------");
			System.out.println("DATOS OBTENIDOS DE Oracle\n");
			while(resulOracle.next()) {
				System.out.println(resulOracle.getString(1)+","
						+ resulOracle.getString(2) + ", "
						+ resulOracle.getString(3)+ ", "
						+ resulOracle.getInt(4));
			}
			
			// 5. cerrar 
			
			miConexionMySQL.close();
			miConexionMyOracle.close();
			
			instrSql.close();
			instrOracle.close();
			
			resulSQL.close();
			resulOracle.close();
			
			
		}catch(SQLException ex) {
			// Tratamiento de errores
			ex.printStackTrace();
		}
	}
}
