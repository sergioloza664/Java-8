package streamConNIO2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EjemploStreamConNIO2 {

	public static void main(String[] args) {
		try {
			list();
			System.out.println("");
			find();
			System.out.println("");
			walk();
			System.out.println("");
			lines();
		} catch (IOException e) {e.printStackTrace();}

	}
	
	
	// Lista los ficheros o directorios que no empiezan por un punto
	private static void list() throws IOException{
		Stream<Path> stream = Files.list(Paths.get(System.getProperty("user.home"), "Desktop") ); 
		    stream
		        .map(String::valueOf)
		        .filter(path -> !path.startsWith("."))
		        .sorted()
		        .forEach(System.out::println);
		    stream.close();
		   
	}
	
	
	
	
	// Busca con una profundidad de 5 directorios
	private static void find() throws IOException{
		Path empieza = Paths.get(System.getProperty("user.home"),"Desktop");
		int maxProfundidad = 5;
		Stream<Path> stream = Files.find(empieza
									, maxProfundidad
									, (path, attrib) ->  String.valueOf(path).endsWith(".txt") );
			stream
		        .sorted()
		        .map(String::valueOf)
		        .forEach(System.out::println);
			stream.close();
		
	}
	
	
	
	
	// Muestra el numero de ficheros por extension en una profundidad de 5 directorios
	private static void walk() throws IOException{
		Path empieza = Paths.get(System.getProperty("user.home"), "Desktop");
		int maxProfundidad = 5;
		Stream<Path> stream = Files.walk(empieza, maxProfundidad); //Devuelve todas las rutas a partir de un directorio y una profundidad dados.
		TreeMap<String, Long> groupByExtension =
				stream
					.filter(Files::isRegularFile)				
					.sorted()
					.collect(Collectors.groupingBy(EjemploStreamConNIO2::getExtension, TreeMap::new, Collectors.counting()));
			
		groupByExtension.forEach((k, v) -> System.out.printf("%s -> %d ficheros%n", k, v));
		stream.close();	
				
		
	}
	
	private static String getExtension(Path p) {
		String extension = "";
		String fileName = p.getFileName().toString();

		int i = fileName.lastIndexOf('.');
		if (i >= 0) {
		    extension = fileName.substring(i);
		}
		return extension;
	}
	
	
	
	
	// Lee las lineas de un fichero con forme lo necesitemos, con palabras separadas por (;)
	private static void lines() throws IOException{
		Path path = Paths.get("src", "streamConNio2","ejemplo", "lines.txt");
		Stream<String> stream = Files.lines(path, Charset.forName("Cp1252"));
		List<String[]>listaArray =	stream
				.map(s -> s.split(";")) // split devuelve un array de String
				.collect(Collectors.toList());
		listaArray.forEach(
					array -> {
						System.out.print(Arrays.toString(array)+" ");
						System.out.println("");						
					}
				);
		stream.close();
	}
	
	
	

}

