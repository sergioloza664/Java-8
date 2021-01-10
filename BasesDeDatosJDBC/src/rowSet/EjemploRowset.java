package rowSet;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class EjemploRowset {
	
	// ROWSET
	// -----------------------------------------------------------------------------------------
	// * Se trata de un objeto que permite manejar informaci�n tabular de forma m�s flexible y f�cil 
	//   que un ResultSet.
	// * Existen 5 tipos distintos: JdbcRowset, CachedRowset, JoinWowset, FilteredRowset y WebRowset.
	// TIPOS DE ROWSET
	// 		Seg�n si est�n conectados o no:
	//	 	* Conectados: 	 el RowSet siempre tiene la conexi�n abierta con la base de datos. JdbcRowset.
	//		* Desconectados: la conexi�n no siempre est� abierta, as� que son m�s ligeros y serializables.
	//						 CachedRowSet, JoinRowSet, FilteredRowset, WebRowSet.
	// CREACI�N DE UN ROWSET
	// 		La factoria nos permite crear cualquier tipo de RowSet. RowSetFactory rsf = RowSetProvider.newFactory();
	
	// JDBCROWSET
	// ------------------------------------------------------------------------------------------------------
	// * Mantiene siempre la conexi�n abierta a la base de datos (r�pido e ineficiente).
	// * Nos permite recorrer los resultados, actulizarlos, insertar nuevos, borrar, etc...
	
	// CACHEDROWSET
	// ------------------------------------------------------------------------------------------------
	// * Solo abre la conexi�n en momentos puntuales.
	// * Tenemos que indicar que columnas son la clave (primaria).
	// * Los cambios tienen que ser aceptados (acceptChanges). Requiere autocommit = false;
	// * Nos permite realizar las mismas operaciones que con unJdbcRowSet. 
	

	public static void main(String[] args) {
		jdbcRowset();
		cachedRowset();

	}
	
	
	
	private static void jdbcRowset() {
		RowSetFactory myRowSetFactory = null;
		JdbcRowSet rowSet = null;
		
		try {
			myRowSetFactory = RowSetProvider.newFactory();
			rowSet = myRowSetFactory.createJdbcRowSet();
			
			rowSet.setUrl(DBConnection.JDBC_URL);
			rowSet.setUsername(DBConnection.USERNAME);
			rowSet.setPassword(DBConnection.PASSWORD);
			
			rowSet.setCommand("SELECT * FROM empleados");
			rowSet.execute();
			
			// Imprimimos todos los registros
			while (rowSet.next()) {
				System.out.printf("%d %s %s\t\t (%s) - %.2f� %n", rowSet.getInt("id"), 
						rowSet.getString("nombre"), rowSet.getString("apellidos"),
						rowSet.getDate("fecha_nacimiento").toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)),
						rowSet.getFloat("sueldo"));
			}
			System.out.println("");
			
			// A�adimos este "listener", que nos permite gestionar algunos
			// eventos sobre el RowSet.
//			rowSet.addRowSetListener(new RowSetListener() {
//				
//				@Override
//				public void rowSetChanged(RowSetEvent event) {
//					
//				}
//				
//				@Override
//				public void rowChanged(RowSetEvent event) {
//					System.err.println("Una fila ha sido modificada");
//				}
//				
//				@Override
//				public void cursorMoved(RowSetEvent event) {
//					System.err.println("Cursor movido");
//				}
//			});
			
			// Nos vamos al �ltimo registro de nuevo, y le subimos el sueldo un 10%
			rowSet.last();
			rowSet.updateFloat("sueldo", rowSet.getFloat("sueldo") * 1.1f);
			rowSet.updateRow();
			
			// Insertamos un nuevo registro
			rowSet.moveToInsertRow();
			rowSet.updateString("nombre", "Manuel");
			rowSet.updateString("apellidos", "Cuenca Lozano");
			rowSet.updateDate("fecha_nacimiento", Date.valueOf(LocalDate.of(1980, 11, 21)));
			rowSet.updateFloat("sueldo", 1600.0f);
			rowSet.insertRow();
			
			
			// Imprimimos todos los registros de nuevo
			rowSet.beforeFirst();
			while (rowSet.next()) {
				System.out.printf("%d %s %s\t\t (%s) - %.2f� %n", rowSet.getInt("id"), 
						rowSet.getString("nombre"), rowSet.getString("apellidos"),
						rowSet.getDate("fecha_nacimiento").toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)),
						rowSet.getFloat("sueldo"));
			}
			System.out.println("");
			
			
			// Cerramos el cursor
			rowSet.close();
			
			

		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	
	
	
	
	private static void cachedRowset() {
		RowSetFactory myRowSetFactory = null;
		CachedRowSet rowSet = null;

		try {
			myRowSetFactory = RowSetProvider.newFactory();
			rowSet = myRowSetFactory.createCachedRowSet();

			// Podemos crear el CachedRowSet de forma id�ntica
			rowSet.setUrl(DBConnection.JDBC_URL);
			rowSet.setUsername(DBConnection.USERNAME);
			rowSet.setPassword(DBConnection.PASSWORD);

			rowSet.setCommand("SELECT * FROM empleados");
			// Indicamos los n�meros de columnas que forman la PK
			rowSet.setKeyColumns(new int[] { 1 });

			// Se abre una conexi�n puntual para extraer los datos
			// y rellenar el CachedRowSet
			rowSet.execute();

			// o podr�amos usar las siguientes l�neas
//			ResultSet rs = DBConnection.getConnection().createStatement().executeQuery("SELECT * FROM empleados");
//			rowSet.populate(rs);

			// Imprimimos todos los registros
			while (rowSet.next()) {
				System.out.printf("%d %s %s\t\t (%s) - %.2f� %n", rowSet.getInt("id"), 
						rowSet.getString("nombre"), rowSet.getString("apellidos"),
						rowSet.getDate("fecha_nacimiento").toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)),
						rowSet.getFloat("sueldo"));
			}
			System.out.println("");

			// En este caso, vamos a subir el sueldo de todos los empleados
			rowSet.beforeFirst();
			while (rowSet.next()) {
				rowSet.updateFloat("sueldo", rowSet.getFloat("sueldo") * 1.1f);
				rowSet.updateRow();
			}

			// Imprimimos todos los registros de nuevo
			rowSet.beforeFirst();
			while (rowSet.next()) {
				System.out.printf("%d %s %s\t\t (%s) - %.2f� %n", rowSet.getInt("id"), 
						rowSet.getString("nombre"), rowSet.getString("apellidos"),
						rowSet.getDate("fecha_nacimiento").toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)),
						rowSet.getFloat("sueldo"));
			}
			System.out.println("");


			// �Estos cambios han sido consolidados en la base de datos?
			// NO, hasta que no ejecutamos acceptChanges.
			// �OJO! Necesitamos una conexi�n con auto-commit = false
			// para poder usar este m�todo.
			rowSet.acceptChanges(DBConnection.getConnection());

			// Cerramos el cursor
			rowSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
