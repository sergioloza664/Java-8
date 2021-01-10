package stream;


import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EjemploStream {
	
	// STREAM
	// ---------------------------------------------------------------------------------------------------
	// * Usando expresiones lambda
	// * Permitiendo el encadenamiento de operaciones (permitiendo codigo mas legible y conciso)
	// * De forma secuencial o paralela

	
	// CARACTERISTICAS DE UN STREAM
	// -------------------------------------------------------------------------------------------------
	// * Las operaciones intermedias retornan un Stream (permitiendo así el encadenamiento de llamadas a métodos).
	// * Las operaciones intermedias se encolan, y son invocadas al invocar una operación terminal.
	// * Las operaciones intermedias se encolan, y son invocadas al invocar una operación terminal.
	// * Utiliza iteración interna en lugar de iteración externa; así nos centramos en qué hacer con los datos, no en como recorrerlos.
	
	
	//  SUBTIPOS DE STREAM
	// ------------------------------------------------------------------------------------------------------
	// En el caso de que vayamos a utilizar un stream de tipos básicos (int, long y double), 
	// Java nos proporciona las interfaces IntStream, LongStream y DoubleStream.
	
	
	// FORMAS DE OBTENER UN STREAM
	// ---------------------------------------------------------------------------------------------------------
	// * Stream.of(...): 		retorna un stream secuencial y ordenado de los parámetros que se le pasan.
	// * Arrays.streams(T[] t): retorna un stream secuencial a partir del array proporcionado. 
	//							Si el array es de tipo básico, se retorna un subtipo de Stream.
	// * Stream.empty(): 		retorna un stream vacío.
	// * Stream.iterate(T, UnaryOperator<T>): devuelve un stream infinito, ordenado y secuencial.
	//										  Lo hace a partir de un valor y de aplicar una función a ese 
	//										  valor. Se puede limitar el tamaño con limit(long).
	// * Collection.stream() y Collection.parallelStream(): devuelve un stream (secuencial o paralelo) a 
	//														partir de una colección.
	// * Collection.generate: 	retorna un stream infinito, secuencial y no ordenado a partir de una 
	//							instancia de Supplier (o su correspondiente expresión lambda).
	
	
	// OPERACIONES INTERMEDIAS SOBRE UN STREAM
	// -----------------------------------------------------------------------------------------------------
	// * Son operaciones que devuelven un Stream
	// * Nos permiten realizar diversas funciones (filtrado, transformación, ...)
	// OPERACIONES DE FILTRADO
	// 		* filter(Predicate<T>): nos permite filtrar usando una condición.
	//   	* limit(n): nos permite obtener los n primeros elementos.
	//   	* skip(m): nos permite obviar los m primeros elementos.
	// OPERACIONES DE MAPEO
	//   	* map(Function<T,R>): 	nos permite transformar los valores de un stream a través de una 
	//								expresión lambda o una instancia de Function.
	//   	* mapToInt(...), mapToDouble(...) y mapToLong(...): nos permite transformar a tipos básicos, 
	//															obteniendo IntStream, DoubleStream o 
	//															LongStream, respectivamente.
	// OPERACIONES TERMINALES
	//   	Provocan que se ejecuten todas las operaciones intermedias. Las hay de varios tipos:
	//   	*Para consumir los elementos (por ejemplo, forEach)
	//   	* Para obtener datos de un stram (agregación)
	//   	* Para recolectar los elementos y transformarlos en otro objeto, como una colección.
	
	
	// METODOS DE BUSQUEDA
	// ------------------------------------------------------------------------------------------------------
	// Son un tipo de operaciones terminales sobre un stream, que nos permiten:
	// * Identificar si hay elementos que cumplen una determinada condición
	// * Obtener (si el stream contiene alguno) determinados elementos en particular.
	// Algunos de los métodos de búsqueda son:
	//   	* allMatch(Predicate<T>): verifica si todos los elementos de un stream satisfacen un predicado.
	//   	* anyMatch(Predicate<T>): verifica si algún elemento de un stream satisface un predicado.
	//   	* noneMatch(Predicate<T>): opuesto de allMatch(…)
	//   	* findAny(): devuelve en un Optional<T> un elemento (cualquiera) del stream. Recomendado en streams paralelos.
	//   	* findFirst() devuelve en un Optional<T> el primer elemento del stream. NO RECOMENDADO en streams paralelos.

	
	// METODOS DE REDUCCIION
	// ------------------------------------------------------------------------------------------------------
	// Son métodos que reducen el stream hasta dejarlo en un solo valor.
	// * reduce(BinaryOperator<T>):Optional<T> realiza la reducción del Stream usando una función asociativa. 
	//   Devuevle un Optional
	// * reduce(T, BinaryOperator<T>):T realiza la reducción usando un valor inicial y una función asociativa.
	//   Se devuelve un valor como resultado.
	
	
	// METODOS DE RESUMEN
	// ----------------------------------------------------------------------------------------------------------
	// Son métodos que resumen todos los elementos de un stream en uno solo:
	// * count: devuelve el número de elementos del stream.
	// * min(...), max(...): devuelven el máximo o mínimo (se puede utilizar un Comparator para modificar 
	//   el orden natural).
	
	
	// METODOS DE ORDENACION
	// --------------------------------------------------------------------------------------------------------
	// Son operaciones intermedias, que devuelven un stream con sus elementos ordenados.
	// * sorted() el stream se ordena según el orden natural.
	// * sorted(Comparator<T>) el stream se ordena según el orden indicado por la instancia de Com
	
	
	// MAP
	// ------------------------------------------------------------------------------------------------------
	// * una de las operaciones intermedias más usadas.
	// * Permite la transformación de un objeto en otro, a través de un Function<T, R>.
	// * Se invoca sobre un Stream<T> y retorna un Stream<R>.
	// * Además, es muy habitual realizar transformaciones sucesivas.
	
	
	// FLATMAP
	// -----------------------------------------------------------------------------------------------------
	// * Los streams sobre colecciones de un nivel permiten transformaciones a través de map. Usaremos 
	//   flatmap cuando tengamos una colección dentro de otra.
	// * Para poder trabajar con la colección interna, necesitamos un método que nos unifique 
	//   un Stream<Stream<T>> en un solo Stream<T>.
	// * También tenemos disponibles las versiones primitivas flatMapToInt, flatMapToLong y flatMapToDouble	
	
	
	// COLLECTORS
	// -----------------------------------------------------------------------------------------------------
	// Los collectors nos van a permitir, en una operación terminal, construir una collección mutable, 
	// el resultado de las operaciones sobre un stream.
	//
	// COLECTORES BASICOS
	//   	Nos permiten operaciones que recolectan todos los valores en uno solo. 
	//   	Se solapan con alguans operacinoes finales ya estudiadas, pero están presentes porque se 
	//		pueden combinar con otros colectores más potentes.
	//   	* counting: cuenta el número de elementos.
	//   	* minBy(…), maxBy(…): obtiene el mínimo o máximo según un comparador.
	//   	*summingInt, summingLong, summingDouble: la suma de los elementos (según el tipo).
	//   	* averagingInt, averagingLong, averagingDouble: la media (según el tipo).
	//   	* summarizingInt, summarizingLong, summarizingDouble: los valores anteriores, 
	//															  agrupados en un objeto (según el tipo).
	//   	* joinning: unión de los elementos en una cadena.
	//
	// COLECTORES GROUPING BY
	// ------------------------------------------------------------------------------------------------------
	//		* Hacen una función similar a la cláusula GROUP BY de SQL.
	//		* Permiten agrupar los elementos de un stream por uno o varios valores.
	//		* retornan un Map con los diferentes grupos, y los elementos de cada grupo.
	//		* Se pueden usar en conjunción con los colectores básicos, o con otro colector grouping by.
	//
	// COLECTORES PARTITIONING
	//		* También tenemos los colectores partitioning, que nos agrupan los resultados dos conjuntos, 
	//		  según si cumplen una condición.
	//
	// COLECTORES COLLECTION
	//   	* Producen como resultado una colección: List, Set y Map.
	
	
	// FILTER
	// -------------------------------------------------------------------------------------------------------
	// * Operación intermedia
	// * Nos permite eliminar del stream aquellos elementos que no cumplen con una determinada condición
	// * Condición como Predicate<T>.
	// * Es muy combinable con findAny o findFirst
	// * Combinable con el resto de métodos intermedios y terminales, como map.
	
	
	// REFERENCIAS A METODOS
	// ------------------------------------------------------------------------------------------------------
	// Las referencias a métodos son una forma de hacer nuestro código aun más conciso.
	//
	// TIPOS DE REFERENCIAS A METODOS
	//   	* Clase::metodoEstatico:   referencia a un método estático.
	//   	* objeto::metodoInstancia: referencia a un método de instancia de un objeto concreto.
	//   	* Tipo::nombreMetodo: 	   referencia a un método de instancia de un objeto arbitrario de un tipo en particular.
	// 		* Clase::new:			   referencia a un constructor.
	
	
	public static void main(String[] args) {
		
		Producto pr1 = new Producto(1, "Coco", 15.0);
        Producto pr2 = new Producto(2, "Manzana", 25.50);
        Producto pr3 = new Producto(3, "Banana", 35.50);
        Producto pr4 = new Producto(4, "Coco", 15.0);
        
        List<Producto> productos = Arrays.asList(pr1, pr2, pr3, pr4);
		
		Persona p1 = new Persona(1, "Manolo", LocalDate.of(1991, 1, 21), productos);
        Persona p2 = new Persona(2, "Celia", LocalDate.of(1990, 2, 21), productos);
        Persona p3 = new Persona(3, "Juan", LocalDate.of(1980, 6, 23), productos);
        Persona p4 = new Persona(4, "David", LocalDate.of(2019, 5, 15), productos);
        Persona p5 = new Persona(5, "Julia", LocalDate.of(2010, 1, 4), productos);

        

        List<Persona> personas = Arrays.asList(p1, p2, p3, p4, p5);
        
        
        
        /*
        for (int i = 0; i < personas.size(); i++) {s
			System.out.println(personas.get(i));
		}
        */
        /*
        for (Persona p : personas) {
			System.out.println(p);
		}
        */
        //personas.forEach(x -> System.out.println(x)); //lambda
        
        //personas.forEach(System.out::println); //referencia a metodo
        
        
        
        
        
        
        // 1-Filter (parametro: Predicate)
        List<Persona> listaFiltrada1 = personas.stream()
        		.filter(p -> EjemploStream.getEdad(p.getFechaNacimiento()) >= 18 )
        		.collect(Collectors.toList());
        System.out.println("-----Lista Filtrada-----");	EjemploStream.imprimirLista(listaFiltrada1);
        
        
        
        // 2-Map (parametro: Function)
        List<Integer>listaMapeada1 = personas.stream()
        								.filter(p -> EjemploStream.getEdad(p.getFechaNacimiento()) >= 18 )
        								.map(p -> EjemploStream.getEdad(p.getFechaNacimiento())) 
        								.collect(Collectors.toList());
        System.out.println("-----Lista Mapeada 1-----"); 
        EjemploStream.imprimirLista(listaMapeada1);
        
        //-------------------------------------------------------------------------------       
        List<String>listaMapeada2 = personas.stream()
        								.map(p -> "Hola " + p.getNombre())
        								.collect(Collectors.toList());
        System.out.println("-----Lista Mapeada 2 añadiendo un String-----"); 
        EjemploStream.imprimirLista(listaMapeada2);
        
        //------------------------------------------------------------------------------
        Function<String, String> holaFunction = nombre -> "Hola " + nombre;
        List<String>listaMapeada3 = personas.stream()
        		.map(p -> p.getNombre())
				.map(holaFunction)
				.collect(Collectors.toList());
        System.out.println("-----Lista Mapeada 3 con function-----"); 
        EjemploStream.imprimirLista(listaMapeada3);
        
        //-----------------------------------------------------------------------------
        Function<String, String> holaFunction1 = nombre -> "Hola " + nombre;
        List<String>listaMapeada4 = personas.stream()
        		.map(Persona::getNombre)
				.map(holaFunction1)
				.collect(Collectors.toList());
        System.out.println("-----Lista Mapeada 4 con function y referencia a metodo-----"); 
        EjemploStream.imprimirLista(listaMapeada4);
        
        
        
        // 3-Sorted (parametro: Comparator)
        Comparator<Persona> porNombreAsc = (Persona o1, Persona o2) -> o1.getNombre().compareTo(o2.getNombre());
        List<Persona> listaOrdenada1 = personas.stream()
        		.sorted(porNombreAsc)
        		.collect(Collectors.toList());
        System.out.println("-----Lista Ordenada Ascendente Por Nombre-----"); 
        EjemploStream.imprimirLista(listaOrdenada1);
        
        //----------------------------------------------------------------------------
        Comparator<Persona> porNombreDesc = (Persona o1, Persona o2) -> o2.getNombre().compareTo(o1.getNombre());
        List<Persona> listaOrdenada2 = personas.stream()
        		.sorted(porNombreDesc)
        		.collect(Collectors.toList());
        System.out.println("-----Lista Ordenada Descendente Por Nombre-----"); 
        EjemploStream.imprimirLista(listaOrdenada2);
        
        //------------------------------------------------------------------------------
        Comparator<Persona> porFechaNacimiento = (Persona o1, Persona o2) -> o1.getFechaNacimiento().compareTo(o2.getFechaNacimiento());
        List<Persona> listaOrdenada3 = personas.stream()
        		.sorted(porFechaNacimiento)
        		.collect(Collectors.toList());
        System.out.println("-----Lista Ordenada Por Fecha de Nacimiento Ascendente-----"); 
        EjemploStream.imprimirLista(listaOrdenada3);
        
        
        
        // 4-Match (parametro: Predicate)
        // anyMatch : No evalua todo el stream bajo la condicion
	        boolean anyMatch = personas.stream()
	        		.anyMatch(p -> p.getNombre().startsWith("J"));
	        System.out.println("-----¿Algún Nombre De La Lista Comienza Por 'J'?-----"); 
	        System.out.println(anyMatch);
	        System.out.println("");
        
        // allMatch : Evalua todo el stream bajo la condicion
	        boolean allMatch = personas.stream()
	        		.allMatch(p -> p.getNombre().startsWith("J"));
	        System.out.println("-----¿Todos Los Nombres De La Lista Comienzan Por 'J'?-----"); 
	        System.out.println(allMatch); 
	        System.out.println("");
        
        // noneMatch : Evalua todo el stream bajo la condicion
	        boolean noneMatch = personas.stream()
	        		.noneMatch(p -> p.getNombre().startsWith("J"));
	        System.out.println("-----¿Ninguno De Los Nombre De La Lista Comienzan Por 'J'?-----"); 
	        System.out.println(noneMatch); 
	        System.out.println("");
        
        // Using Predicate
	        Predicate<Persona> empiezaPorPredicate = p -> p.getNombre().startsWith("J");
	        boolean anyMatchPredicate = personas.stream()
	        		.anyMatch(empiezaPorPredicate);
	        System.out.println("-----¿Algún Nombre De La Lista Comienza Por 'J'? Pasandole Predicate-----"); 
	        System.out.println(anyMatchPredicate);
	        System.out.println("");
        
        //finfAny() : devuelve Optional<T>.Recomendado en streams paralelos
	    //finfFirst() : devuelve Optional<T>.No recomendado en streams paralelos
        
        
        // 5-Limit/Skip
        List<Persona> listaSkip = personas.stream()
        									.skip(2)
        									.collect(Collectors.toList());
        System.out.println("-----Lista Skip Los 2 Primeros Elementos (omitir)-----"); 
        EjemploStream.imprimirLista(listaSkip);
        
        //-----------------------------------------------------------------------------
        List<Persona> listaLimit = personas.stream()
				.limit(2)
				.collect(Collectors.toList());
        System.out.println("-----Lista limitada A Los 2 Primeros Elementos-----"); 
        EjemploStream.imprimirLista(listaLimit);
        
        //-----------------------------------------------------------------------------
        int pageNumber = 0;
        int pageSize = 2;
        List<Persona> listaLimitSkip = personas.stream()
        		.skip(pageNumber * pageSize)
				.limit(pageSize)
				.collect(Collectors.toList());
        System.out.println("-----Lista con Skip Y Limit (Número De Elementos Por Página) -----"); 
        EjemploStream.imprimirLista(listaLimitSkip);
        
        
        
        // 6-Collectors
        // GroupBy
	        Map<Double, List<Producto>> groupByPrecio = productos.stream()
	        										.filter(p -> p.getPrecio() > 20)
	        										.collect(Collectors.groupingBy(Producto::getPrecio));
	        System.out.println("-----Map Con groupBy (agrupado por precio)-----"); 
	        System.out.println(groupByPrecio);
	        System.out.println("");
	        
	        //-------------------------------------------------------------------------------------------
	        Map<String, List<Producto>> groupByNombre = productos.stream()
														.filter(p -> p.getPrecio() > 20)
														.collect(Collectors.groupingBy(Producto::getNombre));
	        System.out.println("-----Map Con groupBy (agrupado por nombre)-----"); 
	        System.out.println(groupByNombre);
	        System.out.println("");
	        
	        System.out.println("-----Map Con groupBy (agrupado por nombre) Imprime Solo Los Valores-----"); 
	        System.out.println(groupByNombre.values());
	        System.out.println("");
        
        // Counting
	        Map<String, Long> groupByNombreCounting = productos.stream()
	        							.collect(Collectors.groupingBy(Producto::getNombre 
	        															,Collectors.counting()));
	        System.out.println("-----Map Con groupBy (agrupado por nombre) Contando la cantidad de cada producto-----"); 
	        System.out.println(groupByNombreCounting);
	        System.out.println("");
        
        // Agrupando por nombre producto y sumando
	        Map<String, Double> groupByNombreSumming = productos.stream()
					.collect(Collectors.groupingBy(Producto::getNombre 
													,Collectors.summingDouble(Producto::getPrecio)));
	        System.out.println("-----Map Con groupBy (agrupado por nombre) Sumando el precio de cada producto-----"); 
	        System.out.println(groupByNombreSumming);
	        System.out.println("");
        
        // Obteniendo suma y resumen
	        DoubleSummaryStatistics estadisticas = productos.stream()
	        		.collect(Collectors.summarizingDouble(Producto::getPrecio));
	        System.out.println("-----Estadisticas 'summarizing' (Count, Sum, Min, Average, Max)-----"); 
	        System.out.println(estadisticas);
	        System.out.println("");
	        
	    // Agrupando por nombre producto y Precio
	        Map<String, Map<Double, List<Producto>>> groupByNombrePrecio = productos.stream()
					.collect(Collectors.groupingBy(Producto::getNombre 
													,Collectors.groupingBy(Producto::getPrecio)   )    );
	        System.out.println("-----Map Con groupBy (agrupado por nombre y precio)-----"); 
	        System.out.println(groupByNombrePrecio);
	        System.out.println("");
	        
	    // partitioningBy
	        Map<Boolean, List<Producto>>precioMayor15 =productos.stream()
	        											.collect(Collectors.partitioningBy(p -> p.getPrecio() >15));
	        System.out.println("-----Map Con partitioning (comprobamos si el precio es mayor a 15)-----"); 
	        System.out.println(precioMayor15);
	        System.out.println("");
	        
	    // joining
	        String personasJoining = personas.stream().map(Persona::getNombre).collect(Collectors.joining(", "));
	        System.out.println("-----Join de cadenas de caracteres-----"); 
			System.out.println("Las personas de la colección son " + personasJoining);
			System.out.println("");
			
		//minBy(Comparator) : Devuelve el minimo segun el Comparator
	    //maxBt(Comparator) : Devuelve el maximo segun el Comparator
        
        
        // 7-reduce
        Optional<Double> suma =productos.stream()
        		.map(Producto::getPrecio)
        		.reduce(Double::sum);	//Seria igual a esto .reduce((a, b) -> Double.sum(a, b));
        System.out.println("-----Reduce, reduce una lista a un solo valor-----"); 
        System.out.println(suma);
        System.out.println("");
        
        
        
        //8-FlatMap (parametro: Function)
        List<String> listaFlatMap = personas.stream()
        									.map((Persona p) -> p.getListaProductos())
        									.flatMap(viajes -> viajes.stream())
        									.map((Producto p) -> p.getNombre())
        									.collect(Collectors.toList());
        System.out.println("-----Lista FlatMap (obtenemos la lista de productos que esta dentro de cada persona)-----"); 
        EjemploStream.imprimirLista(listaMapeada1);
        
        
        // distinct() : Elimina los elementos repetidos de una lista.
        //orElse(new Persona()) : Si no se cumple lo anterior a este, en este caso nos devolvera una persona vacia
        
        // peek : util para entender el flujo de funcionamiento de un stream
        //			Ej:
        /*
         * lista.stream()
			    .peek((cadena)-> {
			      System.out.println("***inicio****");
			      System.out.println(cadena);
			      System.out.println("****fin inicio****");
			    })
			    .map((cadena)->cadena.toUpperCase())
			    .peek((cadena)-> {
			      System.out.println(">>>>>>mayusculas>>>>>>");
			      System.out.println(cadena);
			      System.out.println(">>>>>>>fin mayusculas>>>>>>");
			    })
			    .forEach(System.out::println);
         */
        
        
        
        
        
        
	}
	
	
	public static int getEdad(LocalDate cumpleanyos) {
		return Period.between(cumpleanyos, LocalDate.now()).getYears();
	}
	
	public static void imprimirLista(List<?> lista) {
		lista.forEach(System.out::println);
		System.out.println("");
	}

}
