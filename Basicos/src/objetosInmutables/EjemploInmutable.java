package objetosInmutables;

import java.time.LocalDate;

public class EjemploInmutable {
	
	// OBJETOS INMUTABLES
	// -------------------------------------------------------------------------------------------
	// Son objetos cuyo estado no puede ser modificado una vez se haya inicializado. 
	// Para crear objetos inmutables:
	// 1 Definir todas las propiedades como final private
	// 2 No a�adir m�todos setter
	// 3 Evitar que existiendan la clase, a�adiendole el modificador final a la definici�n.
	
	public static void main(String[] args) {
		
		PersonaInmutable yo = new PersonaInmutable("Sergio", "Lozano P�rez", LocalDate.of(1992, 9, 18));
		
		//No tenemos forma de modificar las propiedades
		//Solo de acceder a ellas en modo lectura
		System.out.printf("%s %s (%s)", yo.getNombre(), yo.getApellidos(), yo.getFechaNacimiento());

	}


}
