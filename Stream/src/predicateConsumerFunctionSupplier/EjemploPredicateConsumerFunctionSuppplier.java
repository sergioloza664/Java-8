package predicateConsumerFunctionSupplier;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

	// PREDICATE<T>
	// -----------------------------------------------------------------------------------------------
	// * El m�todo abstracto es boolean test(T t).
	// * Comprueba si se cumple o no una determinada condici�n.
	// * Muy utilizado con expresiones lambda para filtrar.
	// * Se pueden componer predicados m�s complejos con sus m�todos and, or y negate.

	// CONSUMER<T>
	// ---------------------------------------------------------------------------------------------------
	// * El m�todo abstracto es void accept(T t).
	// * Sirve para consumir objetos.
	// * El ejemplo mas claro es imprimir
	// * Muy utilizado con expresiones lambda para imprimir
	// * Tiene el m�todo andThen, que permite componer consumidores, para encadenar una secuencia de 
	//   operaciones.

	// FUNCTION<T, R>
	// -------------------------------------------------------------------------------------------------------
	// * El m�todo abstracto es R apply(T t).
	// * Sirve para aplicar una transformaci�n a un objeto.
	// * El ejemplo m�s claro es el mapeo de un objeto en otro.
	// * Muy utilizado con expresiones lambda para mapear.
	// * Adicionalmente, tiene otros m�todos:
	//	 		* andThen: que permite componer funciones.
	//   		* compose: que compone dos funciones, a la inversa de la anterior.
	//   		* identity: una funci�n que siempre devuelve el argumento que recibe

	// SUPPLIER
	// --------------------------------------------------------------------------------------------------
	// * El m�todo abstracto es T get(T t).
	// * No recibe ningun parametro
	// * Sirve para obtener objetos.
	// * Su uso  est� menos extendido que las anteriores.
	// * Su sintaxis como expresi�n lambda ser�a.
	// * Tiene interfaces especializados para tipos b�sicos: 
	//   IntSupplier, LongSupplier, DoubleSupplier, BooleanSupplier.

public class EjemploPredicateConsumerFunctionSuppplier {
	
	public static void main(String[] args) {
		predicate();
		consumer();
		function();
		supplier();
	}
	
	
	
	// Predicate
	private static void predicate() {
		List<Persona> listaPersonas = new ArrayList<>();

		listaPersonas.add(new Persona("12345678A", "Pepe", "Perez", LocalDate.of(1990, 1, 2)));
		listaPersonas.add(new Persona("23456789B", "Juan", "Mart�nez", LocalDate.of(1981, 2, 3)));
		listaPersonas.add(new Persona("34567890C", "Ana", "Ram�rez", LocalDate.of(1972, 3, 4)));
		listaPersonas.add(new Persona("45678901D", "Mar�a", "L�pez", LocalDate.of(1993, 4, 5)));
		
		
		listaPersonas
			.stream()
			.filter((p) -> p.getEdad() >= 35l)
			.forEach(System.out::println);
		
		
		Predicate<Persona> edad = (p) -> p.getEdad() >= 35l;
		Predicate<Persona> nombre = (p) -> p.getApellidos().contains("e");
		Predicate<Persona> complejo = edad.or(nombre);
		
		System.out.println("");
		listaPersonas
			.stream()
			.filter(complejo)
			.forEach(System.out::println);		
	}
	
	
	
	
	
	
	
	// Consumer
	private static void consumer() {
		List<Integer> lista = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Consumer<Integer> consumer = i -> System.out.print(" "+i);
		Consumer<Integer> consumerWithAndThen = consumer.andThen(i -> System.out.println(" (hemos imprimido el " + i + ")"));
		
		//Usando expresiones lambda
		imprimirLista(lista, System.out::print);
		System.out.println("\n\n");
		//Solo utilizamos un "consumer"
		imprimirLista(lista, consumer);
		//Usamos dos consumers encadenados
		imprimirLista(lista, consumerWithAndThen);
	}
	public static void imprimirLista(List<Integer> l, Consumer<Integer> consumer) {
		for(Integer i : l)
			consumer.accept(i);
		System.out.println("");
	}
	
	
	
	
	
	
	
	// Function
	private static void function() {
		Function<Persona, String> functionPersonaANombre = (Persona p) -> { return p.getNombre(); };
		
		List<Persona> lista = Arrays.asList(
				new Persona("12345678A", "Pepe", "P�rez P�rez", LocalDate.of(1990, 1, 1)),
				new Persona("12345678A", "Mar�a", "L�pez Almagro", LocalDate.of(1996, 4, 21)),
				new Persona("12345678A", "Mart�n", "Casillas Cuenca", LocalDate.of(1991, 12, 12))				
				);
		
		List<String> listaNombres = transformarEmpleadosEnNombres(lista, functionPersonaANombre);
		
//		for(String s: listaNombres)
//			System.out.println(s);
//		System.out.println(" ");

		//Como expresi�n lambda
		lista
			.stream()
			.map((p) -> p.getNombre())
			.forEach(System.out::println);
	}
	private static List<String> transformarEmpleadosEnNombres(List<Persona> lista,
			Function<Persona, String> functionPersonaANombre) {
		List<String> result = new ArrayList<>();
		for(Persona p : lista)
			result.add(functionPersonaANombre.apply(p));
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	// Supplier
	private static void supplier() {
		Supplier<Item> supplier = Item::new;
		Item item = supplier.get();
		System.out.println(item.getMsg());

		Supplier<String> supplier2 = Item::getStaticVal;
		String valor = supplier2.get();
		System.out.println("Llamada a m�todo est�tico: " + valor);

		Supplier<String> supplier3 = () -> { return new String("Hola Mundo!"); };
		//Supplier<String> supplier3 = () -> "Hola Mundo!";
		valor = supplier3.get();
		System.out.println("Obtenci�n de un valor nuevo: " + valor);
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
	
	public long getEdad() {
		return Period.between(fechaNacimiento, LocalDate.now()).get(ChronoUnit.YEARS);
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


















class Item {
	private String name;

	public Item() {
	}

	public Item(String name) {
		this.name = name;
	}

	public static String getStaticVal() {
		return "Valor est�tico";
	}

	public String getMsg() {
		return "Mensaje!";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}