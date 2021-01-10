package interfacesFuncionalesLambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// INTERFACES
	// ------------------------------------------------------------------------------
	// * Una interfaz es un contrato que compromete a la clase que lo implementa a dar cuerpo a una serie 
	//   de m�todos abstractos.
	// * se pueden utilizar como referencias a la hora de crear objetos (que implementen esa interfaz, 
	//   claro est�): Ej: List<String> lista = new ArrayList<>();
	// * Desde Java SE 8, las interfaces pueden incluir m�todos  anotados con default y static.

	// INTERFAZ FUNCIONAL
	// ----------------------------------------------------------------------------------------------------
	// * Interfaz que solo tiene un m�todo abstracto.
	// * Puede tener uno o varios m�todos por defecto y/o est�ticos.
	// * Puede tener varios m�todos abstractos, siempre que todos menos uno sobrescriban un m�todo de la 
	//   clase Object.
	// * Usualmene, los implementamos con una clase an�nima.
	// * Muchos interfaces conocidos son funcionales
	// * Java SE 8 tambi�n incorpora la anotaci�n @FunctionalInterface que comprueba en tiempo de compilaci�n 
	//   si se cumplen con las condiciones anteriores.

	// INTERFACES FUNCIONALES Y EXPRESIONES LAMBDA
	// --------------------------------------------------------------------------------------------------
	// * Altamente relacionados
	// * All� donde se espere una instancia de una clase que implemente una interfaz funcional, podremos 
	//   utilizar una expresi�n lambda.
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
		System.out.println("M�todo con implementaci�n por defecto");
	}
	
	static void estatico() {
		System.out.println("M�todo est�tico");
	}

}
