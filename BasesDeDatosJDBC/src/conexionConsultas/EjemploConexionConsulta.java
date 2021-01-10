package conexionConsultas;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Scanner;

public class EjemploConexionConsulta {
	
	// JDBC
	// ---------------------------------------------------------------------------------------
	// * Java soporta la conexión con bases de datos, en especial, de tipo relacional.
	// * JDBC = Java DataBase Connectivity
	// * Java SE 8 nos da la version JDBC 4.2 (JSR 211), y ofrece una serie de interfaces.
	// * Conectividad con múltiples bases de datos de terceros a tráves de drivers.
	
	// TIPOS DE DRIVERS
	// ----------------------------------------------------------------------------------------
	// * Tipo 1 Bridge:		sirve de puente con otro API, como ODBC
	// * Tipo 2	Native:		traduce Java al API nativo de la base de datos
	// * Tipo 3	Middleware:	utiliza un servidor intermedio y un protoco,lo estándar.
	// * Tipo 4	Pure:		se conecta directamente a la base de datos a tráves de su protocolo de comunicaciones.
	// Lista drivers JDBC:	http://www.oracle.com/technetwork/java/index-136695.html
	// Driver mysql:		https://dev.mysql.com/downloads/connector/j/5.1.html
	
	// INTERFACES PRINCIPALES
	// ---------------------------------------------------------------------------------
	// * Connection: 					es el que permite mantener la conexión con la base de datos.
	// * Statement, PreparedStatement: 	nos permiten ejecutar consultas.
	// * ResultSet: 					juego de resultados de una consulta ejecutada.
	
	// CONEXION CON LA BASE DE DATOS
	// -----------------------------------------------------------------------------------
	// * Usar la clase DriverManager, que nos permite conectar a través de una url jdbc, y soporta 
	//	 varias operaciones.
	// * Usar el interfaz DataSource, que es más avanzado, y que permite ser transparente a nuestra aplicación.
	//	 Es más complejo que DriverManager.
	// DriverManager sera suficiente para proyectos pequeños. para proyectos grandes es recomendable usar un
	// sistema de persistencia como JPA.
	
	// URL JDBC
	// ------------------------------------------------------------------------------------
	// * Una cadena de texto con los datos de coxexión a la base de datos
	// * Depende del driver/base de datos.
	// Por ejemplo, para conectar con Mysql, una URL tipo sería:	jdbc:mysql://hostname/database
	
	// PASOS PARA CONECTAR, LANZAR CONSULTAS Y DESCONECTAR
	// ---------------------------------------------------------------------------------------
	// 1 Cargar el driver JDBC (< 4.0)
	// 2 Establecer datos de conexión
	// 3 Conectar obteniendo un objeto Connection.
	// 4 Crear un objeto Statement y ejecutar consultas SQL
	// 5 Los resultados se almacenan en un objeto ResultSet, donde se pueden consultar.
	// 6 Cerrar los objetos (ResultSet, Statement y Connection).
	
	// OBJETOS PARA LANZAR CONSULTAS Y PROCESAR LOS RESULTADOS
	// -----------------------------------------------------------------------
	// * Statement: 		nos permite lanzar consultas y recoger el resultado. Es la forma más básica 
	//						de realizarlo.
	// * PreparedStatement: permite lanzar consultas a las que podemos asignarle los valores de los 
	//   					parámetros mediante métodos convenientes.
	// * CallableStatement: nos permite lanzar la ejecución de procedimientos almacenados y recoger 
	//						sus resultados.
	// * ResultSet: 		es la clase que nos permite recoger el resultado de la ejecución de una consulta 
	//						realizada con alguno de los interfaces anteriores.
	
	// STATEMENT (Clase)
	// ---------------------------------------------------------------------------------------------------------
	// * Nos provee de métodos para ejecutar consultas en la base de datos.
	// * Recibe las consultas como un String.
	// * Genera, tras la ejecución de una consulta, un objeto de tipo ResultSet.
	//   Tiene, 3 métodos para ejecutar consultas:
	//	 	* execute:		para obtener más de un ResultSet
	//		* executeQuery:	devuelve un solo ResultSet
	//		* executeUpdate:devuelve un entero que representa el número de filas afectadas. Se usa con 
	//						consultas INSERT, UPDATE o DELETE
	
	// RESULTSET
	// -------------------------------------------------------------------------------------------------
	// * Recoge los resultados que devuelve una consulta.
	// * Tiene una estructura de cursor.
	// * Podemos navegar fila a fila, extrayendo los resultados con los métodos getXXX.
	// * Tiene métodos para todos los tipos de datos básicos que podemos utilizar en nuestras tablas.
	// * El método next() devuelve true mientras existan más resultados.
	// Tiene otros metodos para navegar por el cursor
	// 		* previus
	//		* first
	//		* last
	//		* beforeFirst
	//		* afterLast
	//		* relative(int)
	//		* absolute(int)
	// El uso de los anteriores métodos en ResultSet (en modo escritura) varia su correcto funcionamiento 
	// con respecto al driver y el sistema gestor de base de datos, para solucionarlo se recomienda 
	// el uso del patrol DAO o PreparedStatement.
	
	// PREPAREDSTATEMENT
	// -------------------------------------------------------------------------------------------------
	// * Una extension de Statement.
	// * Nos permite prever problemas de inyeccion de SQL.
	// * Nos permite indicar, donde vamos a insertar un valor, indicar con un interrogante, 
	//	 y darle valor después.
	// * JDBC se encarga de precompilar la consulta antes de enviarla, evitando código malicioso.
	
	// PATRON DAO
	// -----------------------------------------------------------------------------------------------------
	// * Patrón de diseño de software.
	// * Uso de clases modelo.
	// * Un solo objeto se encarga de realizar las operaciones con la base de datos.
	// * El restoo del sistema trabaja con ese objeto, que nos aísla del SGBD concreto.
	
	
	private static Scanner sc;
	
	public static void main(String[] args) {

		try {
			sc = new Scanner(System.in);
			int opcion;
			DaoEmpleado dao = DaoEmpleado.getDaoEmpleado();

			do {
				menu();
				opcion = Integer.parseInt(sc.nextLine());

				switch (opcion) {
				case 1:
					listarTodosEmpleados(dao);
					break;
				case 2:
					listarUnEmpleado(dao);
					break;
				case 3:
					nuevoEmpleado(dao);
					break;
				case 4:
					actualizarUnEmpleado(dao);
					break;
				case 5:
					eliminarUnEmpleado(dao);
					break;

				}

			} while (opcion != 0);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	

	



	public static void menu() {

		System.out.println("SISTEMA DE GESTIÓN DE EMPLEADOS");
		System.out.println("===============================");
		System.out.println("0. Salir");
		System.out.println("1. Listar todos los empleados");
		System.out.println("2. Listar un empleado por su ID");
		System.out.println("3. Insertar un nuevo empleado");
		System.out.println("4. Actualizar un empleado");
		System.out.println("5. Eliminar un empleado");

	}
	
	
	private static void listarTodosEmpleados(DaoEmpleado dao) {
		
		List<Empleado> lista = null;
		try {
			lista = dao.buscarTodos();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		if (lista != null)
			lista.forEach((e) -> {
				System.out.printf("%d %s %s\t\t (%s) - %.2f€ %n", e.getId(), e.getNombre(), e.getApellido(), 
						e.getFechaNacimiento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)),
						e.getSueldo());
			});
		else
			System.out.println("No hay empleados registrados en la base de datos");
		
		System.out.println("");
		
	}
	
	
	private static void listarUnEmpleado(DaoEmpleado dao) {
		System.out.println("Búsqueda de un empleado");
		System.out.print("Introduzca el ID del empleado: ");
		int id = Integer.parseInt(sc.nextLine());
		
		Empleado e = null;
		try {
			e = dao.buscarPorId(id);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		if (e != null) {
			System.out.printf("%s %s\t\t (%s) - %.2f€ %n", e.getNombre(), e.getApellido(), 
					e.getFechaNacimiento().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)),
					e.getSueldo());
		} else {
			System.out.println("No existe ningún registro con ese ID");
		}
		
		System.out.println("");
		
	}
	

	private static void nuevoEmpleado(DaoEmpleado dao) {
		System.out.println("Inserción de un nuevo empleado");
		System.out.print("Introduzca el nombre del empleado: ");
		String nombre = sc.nextLine();
		System.out.print("Introduzca los apellidos del empleado: ");
		String apellidos = sc.nextLine();
		System.out.print("Introduzca la fecha de nacimiento (dd/mm/aaaa) : ");
		String strFecha = sc.nextLine();
		LocalDate fecha = LocalDate.parse(strFecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		System.out.print("Introduzca el sueldo del empleado: ");
		float sueldo = Float.parseFloat(sc.nextLine());

		try {
			dao.insertar(new Empleado(nombre, apellidos, fecha, sueldo));
			System.out.println("Nuevo registro insertado");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("");

	}
	
	
	private static void actualizarUnEmpleado(DaoEmpleado dao) {
		System.out.println("Actualización de un empleado");
		System.out.print("Introduzca el ID del empleado: ");
		int id = Integer.parseInt(sc.nextLine());
		
		Empleado e = null;
		try {
			e = dao.buscarPorId(id);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		if (e == null) {
			System.out.println("El empleado a modificar no está registrado en la base de datos");			
		} else {
			System.out.print("Introduzca el nombre del empleado: ");
			String nombre = sc.nextLine();
			System.out.print("Introduzca los apellidos del empleado: ");
			String apellidos = sc.nextLine();
			System.out.print("Introduzca la fecha de nacimiento (dd/mm/aaaa) : ");
			String strFecha = sc.nextLine();
			LocalDate fecha = LocalDate.parse(strFecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			System.out.print("Introduzca el sueldo del empleado: ");
			float sueldo = Float.parseFloat(sc.nextLine());
			e.setNombre(nombre);
			e.setApellido(apellidos);
			e.setFechaNacimiento(fecha);
			e.setSueldo(sueldo);
			
			try {
				dao.actualizar(e);;
				System.out.println("Registro actualizado");
			} catch (SQLException ex) {				
				ex.printStackTrace();
			}
			
		}
		
		System.out.println("");
		
	}
	

	private static void eliminarUnEmpleado(DaoEmpleado dao) {
		System.out.println("Borrado de un empleado");
		System.out.print("Introduzca el ID del empleado: ");
		int id = Integer.parseInt(sc.nextLine());
		
		System.out.println("¿Está usted seguro de eliminar dicho registro? (S/N)");
		String opcion = sc.nextLine();
		
		if (opcion.equalsIgnoreCase("S")) {
			try {
				dao.borrar(id);;
				System.out.println("Registro eliminado");
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		
		System.out.println("");
		
	}
	

}
