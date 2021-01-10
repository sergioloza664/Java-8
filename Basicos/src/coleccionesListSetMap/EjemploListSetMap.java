package coleccionesListSetMap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EjemploListSetMap {
	
	// API DE COLECCIONES
	// -----------------------------------------------------------------------
	// Desde Java SE 2 se ofrece el tratamiento de colecciones. Actualmente tiene:
	// * Interfaces: 	   tipos de datos
	// * Implementaciones: concreciones de los diferentes interfaces.
	// * Algoritmos:	   para realizar operaciones como ordenación, búsqueda, …
	// Actualmente, todas las colecciones están definidas como genéricas.
	
	// TIPOS DE COLECCIONES
	// -----------------------------------------------------------------------------------
	// Java propone diferentes tipos de colecciones, a través de varias interfaces. Algunas de ellas: 
	// * List: Se trata de una estructura lineal, con posibilidad de orden y de repetidos.
	// * Set: es una colección que no soporta duplicados, y con posibilidad de orden.
	// * Map: es una estructura de tipo clave, valor, con posibilidad de orden de los elementos (por la clave)

	// INTERFAZ LIST
	// -----------------------------------------------------------------------------------------------------------
	// * Los elementos tienen posición.
	// * Permite duplicados.
	// * También permite búsqueda e iteraciones
	// * Las implementaciones más conocidas son ArrayList y LinkedList
	// * Si no sabemos cual escoger, utilizaremos siempre ArrayList.
	//
	// OPERACIONES CON LIST
	// 		* add: 		Añade un elemento al final de la lista
	// 		* addAll:   Añade todos los elementos de la colección pasada como argumento
	//		* clear: 	Elimina todos los elementos de la lista
	//		* contains: Comprueba si un elemento está o no en la lista
	//		* get: 		Devuelve el elemento de la posición especificada de la lista
	//		* isEmpty: 	Verifica si la lista está vaciá
	//		* remove: 	Elimina un elemento de la lista
	//		* size: 	Devuelve el número de elementos de la lista
	//		* toArray: 	Devuelve la lista como un array
	
	// INTERFAZ SET
	// ----------------------------------------------------------------------------------------------------
	// * No puede contener repetidos.
	// * Propone tres implementaciones: HashSet, TreeSet y LinkedHashSet
	// 	 	* HashSet: 		 es la más eficiente, pero no nos asegura nada sobre el orden.
	//		* TreeSet: 		 utiliza un árbol Red-Black, y ordena según el valor.
	//		* LinkedHashSet: es un HashSet ordenado por orden de inserción.
	//
	// OPERACIONES CON SET
	// 		* add: 		Añade un elemento al final de la lista
	// 		* addAll:   Añade todos los elementos de la colección pasada como argumento
	//		* clear: 	Elimina todos los elementos de la lista
	//		* contains: Comprueba si un elemento está o no en la lista
	//		* get: 		Devuelve el elemento de la posición especificada de la lista
	//		* isEmpty: 	Verifica si la lista está vaciá
	//		* remove: 	Elimina un elemento de la lista
	//		* size: 	Devuelve el número de elementos de la lista
	//		* toArray: 	Devuelve la lista como un array
	
	// INTERFAZ MAP
	// -----------------------------------------------------------------------------------------------------
	// * No es un subtipo de Collection (List y Set sí que lo son).
	// * Cada elemento tiene estructura clave, valor.
	// * La clave sirve para acceder directamente al valor.
	// * Las implementaciones son HashMap, TreeMap y LinkedHashMap. Las usos son análogas a Set.
	//
	// OPERACIONES CON MAP
	// 		* clear: 		 Elimina todos los elementos del diccionario
	// 		* containsKey: 	 Comprueba si una clave esta presente en el diccionario
	// 		* containsValue: Comprueba si un valor esta presente en el diccionario
	// 		* get: 			 Devuelve el valor asociado a una clave
	// 		* isEmpty: 		 Verifica si el conjunto esta vacio
	//		* keySet:		 Devuelve un Ser con todas las claves
	//		* put:			 Permite insertar un par clave valor
	// 		* remove: 		 Elimina un elemento del conjunto
	// 		* size: 		 Devuelve el número de elementos del conjunto
	// 		* values:
	
	public static void main(String[] args) {
		list();
		set();
		map();

	}
	
	
	
	
	
	
	private static void list() {
		List<Persona> listaPersonas = new ArrayList<>();
		//List<Persona> listaPersonas = new LinkedList<>();
		
		listaPersonas.add(new Persona("12345678A", "Pepe", "Perez", LocalDate.of(1990, 1, 2)));
		listaPersonas.add(new Persona("23456789B", "Juan", "Martínez", LocalDate.of(1991, 2, 3)));
		listaPersonas.add(new Persona("34567890C", "Ana", "Ramírez", LocalDate.of(1992, 3, 4)));
		listaPersonas.add(new Persona("45678901D", "María", "López", LocalDate.of(1993, 4, 5)));
		
		//Acceso posicional (3ª posición)
		Persona p = listaPersonas.get(2);
		System.out.println(p);
		System.out.println("\n\n");
		
		//Recorrer la lista completa
		for(Persona per : listaPersonas)
			System.out.println(per);
		
		
		//Añadir un nuevo elemento al final de la lista
		listaPersonas.add(new Persona("56789012E", "Julio", "Azcárate", LocalDate.of(1994, 5, 6)));
		
		//Añadir/modificar un elemento en medio de la lista
		listaPersonas.set(2, new Persona("67890123F", "Alfonso", "García", LocalDate.of(1995, 6, 7)));
		
		//Recorrer la lista completa
		System.out.println("\n\n\n");
		for(Persona per : listaPersonas)
			System.out.println(per);
		
		//Para ordenar, tenemos que aportar un orden
		//Será por fecha de nacimiento, a la inversa
		listaPersonas.sort(new Comparator<Persona>() {

			@Override
			public int compare(Persona p1, Persona p2) {
				return -p1.getFechaNacimiento().compareTo(p2.getFechaNacimiento());
			}
			
		});
		
		//Recorremos la lista completa, ya ordenada
		System.out.println("\n\n\n");
		for(Persona per : listaPersonas)
			System.out.println(per);
	}
	
	
	
	
	
	
	
	private static void set() {
		Set<Persona> juntaDirectiva = new HashSet<>();
		//Set<Persona> juntaDirectiva = new TreeSet<>(); //Para ordenar, Persona debe implementar Comparable
		//Set<Persona> juntaDirectiva = new LinkedHashSet<>();
		
		
		juntaDirectiva.add(new Persona("12345678A", "Pepe", "Perez", LocalDate.of(1990, 1, 2)));
		juntaDirectiva.add(new Persona("23456789B", "Juan", "Martínez", LocalDate.of(1991, 2, 3)));
		juntaDirectiva.add(new Persona("34567890C", "Ana", "Ramírez", LocalDate.of(1992, 3, 4)));
		juntaDirectiva.add(new Persona("45678901D", "María", "López", LocalDate.of(1993, 4, 5)));
		
		//Si tratamos de añadir un elemento repetido...
		juntaDirectiva.add(new Persona("45678901D", "María", "López", LocalDate.of(1993, 4, 5)));
		
		//Comprobamos que al listarlos todos, no aparece duplicado
		for(Persona p : juntaDirectiva)
			System.out.println(p);
	}
	
	
	
	
	
	
	
	
	
	
	private static void map() {
		Map<String, Persona> agenda = new HashMap<>();
		
		//Insertamos los pares clave, valor
		agenda.put("954000001", new Persona("12345678A", "Pepe", "Perez", LocalDate.of(1990, 1, 2)));
		agenda.put("954000002",new Persona("23456789B", "Juan", "Martínez", LocalDate.of(1991, 2, 3)));
		agenda.put("954000003",new Persona("34567890C", "Ana", "Ramírez", LocalDate.of(1992, 3, 4)));
		agenda.put("954000004",new Persona("45678901D", "María", "López", LocalDate.of(1993, 4, 5)));
		
		//Si insertamos un elemento con la misma clave, lo sustituimos
		agenda.put("954000004",new Persona("56789012E", "Martín", "García", LocalDate.of(1990, 12, 15)));
		
		//La forma más normal de recorrer un Map es tomar su conjunto de claves, iterar por ellas, y
		//para cada una de ellas, obtener el valor.
		for(String key : agenda.keySet())
			System.out.printf("%s %s %n", key, agenda.get(key));
	}
	
	
	
	
	

}


























class Persona {

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
	
	
	
	
	
	
}
