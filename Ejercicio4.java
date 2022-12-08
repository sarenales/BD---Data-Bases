package Lab9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Ejercicio4 {
	public static void main(String[] args) throws Exception {
		try {
			// 1. crear conexion
			Connection miConexionMyOracle=DriverManager.getConnection("jdbc:oracle:thin:@vsids11.si.ehu.es:1521:gipuzkoa", "BDC05","BDC05");

			
			// 2. preparar consulta
			Statement instrOracle=miConexionMyOracle.createStatement();
			


			// INSERTAR
			PreparedStatement ins=miConexionMyOracle.prepareCall("INSERT INTO cliente VALUES (?,?,?,?) ");
			ins.setString(1, "10000067");
			ins.setString(2, "Silvia");
			ins.setString(3, "A, Donostia");
			ins.setString(4, "943000000");
			ins.executeUpdate();
			ins.close();


			// UPDATE
			PreparedStatement up = miConexionMyOracle.prepareCall( "UPDATE cliente SET dni = ?, nombre = ? WHERE nombre = ? ");
			up.setString(1, "10000068");
			up.setString(2, "aaaaaaaa");
			up.setString(3, "Silvia");
			up.executeUpdate();
			up.close();

			// DELETE
			PreparedStatement de = miConexionMyOracle.prepareCall( "DELETE FROM cliente WHERE nombre = ? ");
			de.setString(1, "aaaaaaaa");
			de.executeUpdate();
			de.close();


			ResultSet todosSQL = instrOracle.executeQuery("select * from cliente");

			System.out.println("DATOS OBTENIDOS");
			while(todosSQL.next()) {
				System.out.println(todosSQL.getString(1)+","
						+ todosSQL.getString(2) + ", "
						+ todosSQL.getString(3)+ ", "
						+ todosSQL.getInt(4));
			}

			instrOracle.close();
			miConexionMyOracle.close();
			todosSQL.close();

		}catch(SQLException ex) {
			System.out.println("Algo va mal\n");
			ex.printStackTrace();

			
			System.out.println(ex.getMessage());
		}
	}
}
