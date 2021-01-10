package file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EjemploFile {
	
	// la clase File esta obsoleta con respecto a las clases de java NIO2
	
	// isDirectory:			Devuelve true si el File es un directorio.	
	// isFile:				Devuelve true si el File es un fichero.
	// createNewFile:		Crea un nuevo fichero, si aun no existe.
	// createtempFile:		Crea un nuevo fichero temporal.
	// delete:				Elimina el fichero o directorio.
	// getName:				Devuelve el nombre del fichero o directorio.
	// getAbsolutePath:		Devuelve la ruta absoluta del File.
	// getCanonicalPath:	Devuelve la ruta canónoca del File.
	//list, listFiles:		Devuelve el contenido de un directorio.

	public static void main(String[] args) {
		creacionFicheros();
		tiposFicheros();
		listarDirectorios();

	}
	
	
	
	
	private static void creacionFicheros() {
		try {
			File f = new File("./src/file/", "nuevoFile.txt");
			f.createNewFile();
			
			File temp = File.createTempFile("./src/file/temporalFile", ".tmp");
			System.out.println(temp.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private static void tiposFicheros() {
		File f = new File("./src/file/", "nuevoFile.txt");
		//File f = new File("./src/file/");
		
		if (f.isFile()) 
			System.out.println("Es un fichero");
		else if (f.isDirectory())
			System.out.println("Es un directorio");
		else
			System.out.println("No es ni un fichero ni un directorio");

	}
	
	
	
	
	private static void listarDirectorios() {
		File f = new File("./src/file/", "nuevo.txt");
		
		if (f.isDirectory()) {
			//Manejando la lista como String[]
			System.out.println("Como lista de String");
			List<String> lista = Arrays.asList(f.list());
			lista.forEach(System.out::println);
			
			//Manejando la lista como File[]
			System.out.println("\nComo lista de File");
			List<File> listaFiles = Arrays.asList(f.listFiles());
			//listaFiles.forEach((file) -> { 
			//System.out.println(file.getName());
			//});
			listaFiles
				.stream()
				.map(File::getName)
				.forEach(System.out::println);
			
		} else {
			System.out.println("No es un directorio!!!");
		}
		
	}
	

}
