package claseFiles;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class EjemploMetodosClaseFiles {

	public static void main(String[] args) {
		comprobacionesFiles();
		System.out.println("");
		try {copiarBorrarMoverFiles();} catch (IOException e) {e.printStackTrace();}
		System.out.println("");
		try {crearLeerFiles();} catch (IOException e) {e.printStackTrace();}
		System.out.println("");
		try {crearListarDirectoriosFiles();} catch (IOException e) {e.printStackTrace();}

	}
	
	
	
	
	private static void comprobacionesFiles() {
		Path p = Paths.get("file.txt");

		if (Files.notExists(p)) {
			System.out.println("La ruta no existe");
			try {
				Files.createFile(p);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (Files.exists(p)) //Indica si existe
			System.out.println("La ruta sí existe");

		if (Files.notExists(p)) //Indica si no existe
			System.out.println("La ruta no existe");

		if (Files.isRegularFile(p))	//Indica si es un fichero
			System.out.println("El fichero " + p.toString() + " es regular");

		Path p2 = Paths.get("file.txt");

		try {
			if (Files.isSameFile(p, p2)) //Indica si son el mismo fichero
				System.out.println("Son el mismo fichero");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private static void copiarBorrarMoverFiles() throws IOException{
		//Creamos una ruta para crear un fichero
		Path p = Paths.get("src", "claseFiles", "files", "copiarBorrarMover.txt");
				
		//Creamos un fichero, y abrimos el flujo de texto para escribir
		BufferedWriter bw = Files.newBufferedWriter(p);
		bw.write("esto es un mensaje de prueba");
		bw.close();
				
		//Copiamos el fichero
		Path copia = Paths.get("src", "claseFiles", "files", "copiarBorrarMover_copiado.txt");
		Files.copy(p, copia, StandardCopyOption.REPLACE_EXISTING);
				
		//Lo movemos fuera del directorio
		Files.move(copia, Paths.get("src", "claseFiles", "files", "copiarBorrarMover_copiado.txt"), StandardCopyOption.REPLACE_EXISTING);
				
		//Lo eliminamos
		Files.deleteIfExists(Paths.get("src", "claseFiles", "files", "copiarBorrarMover_copiado.txt"));
				
	}
	
	
	
	
	private static void crearLeerFiles() throws IOException{
		Path p = Paths.get("src", "claseFiles", "files", "crearLeer.txt");
		Path p2 = Paths.get("src", "claseFiles", "files", "crearLeer2.txt");
		if (Files.exists(p)) {
			
			BufferedWriter bw = Files.newBufferedWriter(p2, Charset.forName("UTF-8"));
			
			//El Charset del fichero debe ser UTF-8
			List<String> lineas = Files.readAllLines(p); //read AllLines lee todas las lineas a la vez, mientras que lines lee las lineas con forme las necesitemos.
			lineas.forEach((s) ->{
				try {
					bw.write(s);
					bw.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println(s);
			});
			
			bw.close();
		}
	}
	
	
	
	
	private static void crearListarDirectoriosFiles() throws IOException{
		Files.createDirectory(Paths.get("src", "claseFiles", "files", "subdir")); //crea el directorio subdir
		
		DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get("src", "claseFiles", "files")); //mustra los ficheros y directorios del directorio files
		
		for(Path p : directoryStream)
			System.out.println(p.getFileName());
	}

}
