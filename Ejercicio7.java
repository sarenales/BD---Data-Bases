package Lab9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio7 {
	public static void main(String[] args) throws SQLException {
		Connection miConexionMyOracle = null;

		try {
			miConexionMyOracle=DriverManager.getConnection("jdbc:oracle:thin:@vsids11.si.ehu.es:1521:gipuzkoa", "BDC05","BDC05");


			// AutoCommit deber ser false para poder trabajar con transacciones
			
			miConexionMyOracle.setAutoCommit(false);


			Statement instrOracle=miConexionMyOracle.createStatement();

			String instruccionOracle_1="INSERT INTO viaje (Destino, FechaSalida, Dias, ciudadsalida, dni, preciodia)"
					+ "  SELECT Destino, add_months(FechaSalida,12), Dias, ciudadsalida, dni, preciodia FROM viaje where extract(Year from fechasalida)=2022";
			

//			miConexionMyOracle.prepareCall(instruccionOracle_1);
			
			instrOracle.executeUpdate(instruccionOracle_1);
			
			miConexionMyOracle.commit();
			
			System.out.println("La transacción termino con éxito.");

		}catch(SQLException error) {
			
			System.out.println(error.getMessage()+"Hubo un error en la transaccion...");
			try {
				miConexionMyOracle.rollback();
			}catch(Exception e) {
				System.out.println("Ha ocurrido un error...");
			}
			System.out.println("pero se ha realizado un rollback!");
			
			error.printStackTrace();
		}
	}
}
