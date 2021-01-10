package interfacesFuncionalesLambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// INTERFACES
	// ------------------------------------------------------------------------------
	// * Una interfaz es un contrato que compromete a la clase que lo implementa a dar cuerpo a una serie 
	//   de métodos abstractos.
	// * se pueden utilizar como referencias a la hora de crear objetos (que implementen esa interfaz, 
	//   claro está): Ej: List<String> lista = new ArrayList<>();
	// * Desde Java SE 8, las interfaces pueden incluir métodos  anotados con default y static.

	// INTERFAZ FUNCIONAL
	// ----------------------------------------------------------------------------------------------------
	// * Interfaz que solo tiene un método abstracto.
	// * Puede tener uno o varios métodos por defecto y/o estáticos.
	// * Puede tener varios métodos abstractos, siempre que todos menos uno sobrescriban un método de la 
	//   clase Object.
	// * Usualmene, los implementamos con una clase anónima.
	// * Muchos interfaces conocidos son funcionales
	// * Java SE 8 también incorpora la anotación @FunctionalInterface que comprueba en tiempo de compilación 
	//   si se cumplen con las condiciones anteriores.

	// INTERFACES FUNCIONALES Y EXPRESIONES LAMBDA
	// --------------------------------------------------------------------------------------------------
	// * Altamente relacionados
	// * Allí donde se espere una instancia de una clase que implemente una interfaz funcional, podremos 
	//   utilizar una expresión lambda.
	//   Ej: Collections.sort(lista, (str1, str2)-> str1.length()-str2.length());




public class EjemploInterfazFuncional {

	public static void main(String[] args) {
		interfazFuncional();

	}
	
	
	
	// Interfaz funcional con y sin lambda
	private static void interfazFuncional() {
		List<String> lista = Arrays.asList("uno", "dos", "tres", "cuatro", "cinco", "seis");
		
		Collections.sort(lista, new Comparator<String>() {

			//Ordenamos la cadena por su longitud
			@Override
			public int compare(String str1, String str2) {
				return str1.length()-str2.length();
			}
			
		});
		
		//Con lambda
		Collections.sort(lista, (str1, str2)-> str1.length()-str2.length());
		
		lista.forEach(System.out::println);
	}
	

}


// Interfaz con metodos static y default introducidos en Java 8
@FunctionalInterface
interface NuevaInterfaz {
	
	void print(String str);
	
	boolean equals(Object o);
	
	default void defecto() {
		System.out.println("Método con implementación por defecto");
	}
	
	static void estatico() {
		System.out.println("Método estático");
	}

}
