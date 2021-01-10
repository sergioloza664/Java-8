package path;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EjemploPath {
	// RUTA
	//---------------------------------------------------------------------------------------
	// Un path o ruta es una forma de identificar (y acceder) un fichero o un directorio 
	// dentro del sistema de ficheros.
	// Linux:	/home/sergio/estado
	// Windows:	C:\home\sergio\estado 
	
	
	// TIPO DE RUTA
	// ---------------------------------------------------------------------------------------------
	// Ruta absoluta:	se indica toda la ruta del archivo incluyendo el directorio raiz.
	//					Ej:	/home/sergio/estado
	// Ruta relativa:	se indica la ruta desde donde se esta situado. No incluye el directorio raiz.
	//					Ej: sergio/estado		Ej: ./sergio/estado
	
	
	// INTERFAZ PATH
	// --------------------------------------------------------------------------
	// * Introducida en Java SE 7.
	// * Representa una ruta en el sistema de ficheros.
	// * Contiene el nombre de fichero y la lista de directorios usada para construir la ruta.
	// * Permite manejar diferentes sistemas de ficheros (Windows, Linux, Mac, ...).
	
	// CLASE PATHS
	// --------------------------------------------------
	// La creación de un Path hara casi siempre uso de los diferentes métodos estáticos de Paths.
	
	public static void main(String[] args) {
		crearPath();
		System.out.println("");
		informacionPath();
		System.out.println("");
		eliminarRedundancias();
		System.out.println("");
		unirPath();
		System.out.println("");
		compararPath();

	}
	
	
	
	
	
	private static void crearPath() {
		//Obtenemos la ruta del fichero desde la posicion actual
		Path p1 = Paths.get("java", "temario.txt"); 
		//Obtiene la ruta con el sistema de ficheros por defecto del sistema (menos comun que el anterior)
		Path p2 = FileSystems.getDefault().getPath("java", "temario.txt"); 
		// obtiene la ruta del fichero del usuario actual
		Path p3 = Paths.get(System.getProperty("user.home"),"documents", "java", "temario.txt");
		
		System.out.println(p1.toAbsolutePath().toString());
		System.out.println(p2.toAbsolutePath().toString());
		System.out.println(p3.toAbsolutePath().toString());
		
	}
	
	
	
	
	private static void informacionPath() {
		Path path = Paths.get(System.getProperty("user.home"),"documents", "java", "temario.txt");
		
		System.out.format("toString: %s%n", path.toString());			// devuelve la ruta en una cadena de caracteres
		System.out.format("getFileName: %s%n", path.getFileName());		// devuelve la ultima parte de la ruta (directorio o fichero)
		System.out.format("getName(0): %s%n", path.getName(0));			// devuelve el elemento de la ruta del indice especificado
		System.out.format("getNameCount: %d%n", path.getNameCount());	// devuelve el numero de elemento de la ruta
		System.out.format("subpath(0,2): %s%n", path.subpath(0,2));		// devuelve las partes especificadas
		System.out.format("getParent: %s%n", path.getParent());			// devuelve la ruta del directorio padre de nuestro fichero o directorio
		System.out.format("getRoot: %s%n", path.getRoot());				// devuelve la raiz del sistema
	}
	
	
	
	private static void eliminarRedundancias() {
		Path path = Paths.get(System.getProperty("user.home"),"documents", "java", "..", "..", "temario.txt");		
		System.out.println(path.toString());
		
		Path normalized = path.normalize(); // Obtiene la ruta del fichero sin los retrocesos (..)
		System.out.println(normalized.toString());
	}
	
	
	
	
	private static void unirPath() {
		Path basePath = Paths.get(System.getProperty("user.home"),"documents", "java");
		Path file = Paths.get("temario.txt");
		
		Path complete = basePath.resolve(file); //Une dos Path en uno
		
		System.out.println(complete.toString());
	}
	
	
	
	
	private static void compararPath() {
		Path p1 = Paths.get(System.getProperty("user.home"), "documents", "java", "..", "..", "documents", "java",
				"temario.txt");
		
		Path p2 = Paths.get(System.getProperty("user.home"), "documents", "java", "temario.txt");
		
		if (p1.equals(p2)) // Compara los Path
			System.out.println("Son iguales");
		else
			System.out.println("No son iguales");
		
		if (p1.normalize().equals(p2))
			System.out.println("Ahora sí son iguales");
	}

}
