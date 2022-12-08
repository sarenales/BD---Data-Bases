package Lab9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio6 {

	public static void main(String[] args) {
		try {
			// 1. crear las conexiones

			Connection miConexionMySQL=DriverManager.getConnection("jdbc:mysql://dif-mysql.ehu.es:3306/DBC05?&useSSL=false", "DBC05","DBC05");
			Connection miConexionMyOracle=DriverManager.getConnection("jdbc:oracle:thin:@vsids11.si.ehu.es:1521:gipuzkoa", "BDC05","BDC05");

			// 2. crear objetos 
			Statement Statement=miConexionMySQL.createStatement();
			Statement PreparedStatement=miConexionMyOracle.createStatement();

			//3. Ejecutar la consulta de MySQL
			Statement client = miConexionMySQL.createStatement();
			ResultSet resulSQL = client.executeQuery("select * from cliente where cliente.dni=10000001");

			//4. Recorrer el resultado de 3 y establecer parametros para la consutla de oracle
			resulSQL.next();
			String dniC = resulSQL.getString("dni");
			String nombreC = resulSQL.getString("nombre");
			int ntelC = resulSQL.getInt("ntelefono");

			resulSQL.close();

			//5. insertar en oracle
			PreparedStatement ins= miConexionMyOracle.prepareStatement("INSERT INTO guia VALUES (?,?,?)");
			ins.setString(1, dniC);
			ins.setString(2, nombreC);
			ins.setInt(3, ntelC);
			ins.executeUpdate();
			ins.close();
			
			System.out.println("Insertado el cliente"+nombreC +"en la tabla guia");

			//6. liberar recursos
			resulSQL.close();
			miConexionMySQL.close();
			miConexionMyOracle.close();
			Statement.close();
			client.close();
			PreparedStatement.close();

		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
//			ex.printStackTrace();
		}
	}
}
