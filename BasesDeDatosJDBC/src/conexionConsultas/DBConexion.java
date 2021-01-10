package conexionConsultas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// Clase que implementa el patr�n SINGLETON para obtener la consulta a la base de datos.
public class DBConexion {
	
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/jdbc";
	
	private static Connection conexion = null;
	
	private DBConexion() { }
	
	public static Connection getConexion() throws SQLException {
		if (conexion == null) {
			// Usamos un objeto de properties para pasar
			// de una manera m�s c�moda el user, password
			Properties props = new Properties();
			props.put("user", "sergio");
			props.put("password", "12345678");
			
			// Si trabajaramos con JDBC < 4.0 tendr�amos que indicar esta l�nea
			// para indicar el tipo de driver que tiene que cargar DriverManager.
			// Class.forName("com.mysql.jdbc.Driver");
						
			// Obtenemos la conexi�n a partir de la URL jdbc correspondiente
			conexion = DriverManager.getConnection(JDBC_URL, props);
		}
		
		return conexion;
	}

}
