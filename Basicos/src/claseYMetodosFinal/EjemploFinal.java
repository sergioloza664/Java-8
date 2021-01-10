package claseYMetodosFinal;

	// MOFDIFICADOR FINAL
	// ---------------------------------------------------------------------------------------------
	// Sepueden utilizar en diferentes contextos:
	// * Clases final
	// * M�todos final
	// * variables final
	// En todos los casos se indica que en el elemento donde se aplica no podr� ser modificado.

	// CLASES FINAL
	// ------------------------------------------------------------------------------------------
	// * Son clases que no se pueden extender.
	// * En una jerarqu�a de herencia, son el �ltimo nodo.
	// * Se pueden instanciar y tratar con normalidad.

	// M�TODOS FINAL
	// -------------------------------------------------------------------------------------------
	// * Se definen en clases susceptibles de ser extendidas.
	// * Nos permiten indicar que un m�todo no se va a poder sobrescribir.

	// VARIABLES FINAL
	// ----------------------------------------------------------------------------------------
	// Basicamente indican que aquella variable a la que afectan no se puede modificar. 
	// Podemos diferenciar entre:
	// * Tipos primitivos: 	Son valores constantes (usualmente final + static).
	// * Objetos: 		  	Si declaramos una referencia como final, estamos diciendo que esa referencia 
	//   					no podr� asignarse a otro objeto. Sin embargo s� que podemos modificar  
	//   					el estado del objeto con sus propios m�todos.
	// * Arrays:			Igual funcionamiento que los objetos.



// Esta clase no se podra extender
public final class EjemploFinal {
	
	// Variable final
	static final int CAPACIDAD=4;
	
	public static void main(String[] args) {
		
		// No podemos cambiar el valor
		//CAPACIDAD=5;
		
		// No podemos asignar otro objeto en la variable de referencia.
		final StringBuilder cadena = new StringBuilder("Hola");
		//cadena=new StringBuilder("Adios");
		
		
		// Se comporta igual que los objetos final
		final int array1[] = {1, 2, 3, 4, 5}; 
	       int array2[] = {10, 20, 30, 40, 50}; 
	       array2 = array1;       
	       //array1 = array2;
		
	}
	
	
	public final void metodoFinal() {
		System.out.println("Este m�todo no se podra sobrescribir");
	}

}
