package rowSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	
	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/jdbc";
	public static final String USERNAME = "openwebinars";
	public static final String PASSWORD = "12345678";
	
	private static Connection conexion = null;
	
	private DBConnection() { }
	
	public static Connection getConnection() throws SQLException {
		if (conexion == null) {
			Properties props = new Properties();
			props.put("user", "sergio");
			props.put("password", "12345678");
			conexion = DriverManager.getConnection(JDBC_URL, props);
			conexion.setAutoCommit(false);
		}
		
		return conexion;
	}
	

}
