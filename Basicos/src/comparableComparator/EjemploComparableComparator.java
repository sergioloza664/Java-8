package comparableComparator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

	// OPERACIONES CON OBJETOS
	// ------------------------------------------------------------------------------------
	// * Muchas operaciones entre objetos nos obligan a compararlos: buscar, ordenar, …
	// * los tipos primitivos y algunas clases ya implementan su orden (natural, lexicográfico).
	// * para nuestras clases (modelo) tenemos que especificar el orden con el que las vamos a tratar.

	// COMPARABLE
	// ----------------------------------------------------------------------------------------------------
	// * Se trata de un interfaz sencillo:	public interface Compararable<T> { public int compareTo(T o); }
	// * Recibe un objeto del mismo tipo que la clase que lo implementa. 
	// * Devuelve 0 si son iguales, un valor negativo si esmenor, y un valor positivo si es mayor.
	// * Nos sirve para indicar el orden principal de una clase.

	// COMPARATOR
	// ----------------------------------------------------------------------------------------------------
	// * Se ttrata de un interfaz sencillo:	public interface Comparator<T> { public int compare(T o1, T o2); }
	// * Recibe dos argumentos. 
	// * Devuelve 0 si son iguales, un valor negativo si esmenor, y un valor positivo si es mayor.
	// * Nos sirve para indicar un orden puntual, diferente al orden principal de una clase.


public class EjemploComparableComparator {

	public static void main(String[] args) {
		comparable();
		comparator();

	}
	
	
	
	
	
	
	private static void comparable() {
		List<Persona> listaPersonas = new ArrayList<>();
		
		listaPersonas.add(new Persona("12345678A", "Pepe", "Perez", LocalDate.of(1990, 1, 2)));
		listaPersonas.add(new Persona("23456789B", "Juan", "Martínez", LocalDate.of(1981, 2, 3)));
		listaPersonas.add(new Persona("34567890C", "Ana", "Ramírez", LocalDate.of(1972, 3, 4)));
		listaPersonas.add(new Persona("45678901D", "María", "López", LocalDate.of(1993, 4, 5)));
		
		//Este método ordena una colección según el orden
		//definido por Comparable
		Collections.sort(listaPersonas);
		
		for(Persona p : listaPersonas)
			System.out.println(p);


		int position = Collections.binarySearch(listaPersonas, new Persona("3242434G", "Pepe", "Perez", LocalDate.of(1990, 1, 2)));
		System.out.println("\n\n");
		System.out.println(listaPersonas.get(position));
	}
	
	
	
	
	
	
	
	
	
	private static void comparator() {
		List<Persona> listaPersonas = new ArrayList<>();

		listaPersonas.add(new Persona("12345678A", "Pepe", "Perez", LocalDate.of(1990, 1, 2)));
		listaPersonas.add(new Persona("23456789B", "Juan", "Martínez", LocalDate.of(1981, 2, 3)));
		listaPersonas.add(new Persona("34567890C", "Ana", "Ramírez", LocalDate.of(1972, 3, 4)));
		listaPersonas.add(new Persona("45678901D", "María", "López", LocalDate.of(1993, 4, 5)));

		//Ordenamos de más jóven a más viejo
		listaPersonas.sort(new Comparator<Persona>() {

			@Override
			public int compare(Persona o1, Persona o2) {
				return -o1.compareTo(o2);
			}
			
		});
		
		System.out.println("Lista de personas, ordenadas de más joven a mayor edad");
		for(Persona p : listaPersonas)
			System.out.println(p);
		
		//Y ahora, ordenamos alfabéticamente por apellido 
		listaPersonas.sort(new Comparator<Persona>() {

			@Override
			public int compare(Persona o1, Persona o2) {
				return o1.getApellidos().compareTo(o2.getApellidos());
			}
			
		});
		
		System.out.println("Lista de personas, ordenadas alfabéticamente por apellido");
		for(Persona p : listaPersonas)
			System.out.println(p);
	}
	

}



















class Persona implements Comparable<Persona>{

	private String dni;
	private String nombre;
	private String apellidos;
	private LocalDate fechaNacimiento;
	
	public Persona() { }

	public Persona(String dni, String nombre, String apellidos, LocalDate fechaNacimiento) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaNacimiento="
				+ fechaNacimiento + "]";
	}

	/*
	 * Nos permite comparar dos instancias de Persona
	 * Una persona será menor que otra cuando su fecha de nacimiento lo sea
	 * Aprovechamos el orden definido por LocalDate
	 */
	@Override
	public int compareTo(Persona p) {
		return fechaNacimiento.compareTo(p.getFechaNacimiento());
	}
	
	
	
	
	
	
}
