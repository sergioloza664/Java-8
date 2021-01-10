package conexionConsultas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoEmpleado {

	/*
	 * PROPIEDADES Y MÉTODOS SINGLETON
	 */

	private Connection con = null;

	private static DaoEmpleado daoEmpleado = null;

	private DaoEmpleado() throws SQLException {
		con = DBConexion.getConexion();
	}

	public static DaoEmpleado getDaoEmpleado() throws SQLException {
		if (daoEmpleado == null)
			daoEmpleado = new DaoEmpleado();

		return daoEmpleado;
	}

	/*
	 * MÉTODOS PROPIOS DE LA CLASE DAO
	 */

	public void insertar(Empleado e) throws SQLException {

		PreparedStatement ps = con.prepareStatement(
				"INSERT INTO empleados (nombre, apellidos, fecha_nacimiento, sueldo) VALUES (?, ?, ?, ?)");
		ps.setString(1, e.getNombre());
		ps.setString(2, e.getApellido());
		ps.setDate(3, Date.valueOf(e.getFechaNacimiento()));
		ps.setFloat(4, e.getSueldo());

		ps.executeUpdate();

		ps.close();

	}

	public List<Empleado> buscarTodos() throws SQLException {

		PreparedStatement ps = con.prepareStatement("SELECT * FROM empleados");
		ResultSet rs = ps.executeQuery();

		List<Empleado> result = null;

		while (rs.next()) {
			if (result == null)
				result = new ArrayList<>();

			result.add(new Empleado(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"),
					rs.getDate("fecha_nacimiento").toLocalDate(), rs.getFloat("sueldo")));
		}

		rs.close();
		ps.close();

		return result;
	}

	public Empleado buscarPorId(int id) throws SQLException {

		PreparedStatement ps = con.prepareStatement("SELECT * FROM empleados WHERE id = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		Empleado empleado = null;

		if (rs.next()) {
			empleado = new Empleado(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"),
					rs.getDate("fecha_nacimiento").toLocalDate(), rs.getFloat("sueldo"));
		}

		rs.close();
		ps.close();

		return empleado;

	}

	public void borrar(Empleado e) throws SQLException {
		borrar(e.getId());
	}
	
	public void borrar(int id) throws SQLException {
		
		if (id <= 0)
			return;
		
		PreparedStatement ps = con.prepareStatement("DELETE FROM empleados WHERE id = ?");
		ps.setInt(1, id);

		ps.executeUpdate();

		ps.close();
	}

	public void actualizar(Empleado e) throws SQLException {

		if (e.getId() == 0)
			return;

		PreparedStatement ps = con.prepareStatement(
				"UPDATE empleados SET nombre = ?, apellidos = ?, fecha_nacimiento = ?, sueldo = ? WHERE id = ?");
		
		
		ps.setString(1, e.getNombre());
		ps.setString(2, e.getApellido());
		ps.setDate(3, Date.valueOf(e.getFechaNacimiento()));
		ps.setFloat(4, e.getSueldo());
		ps.setInt(5, e.getId());
		
		ps.executeUpdate();
		
		ps.close();

	}

}
