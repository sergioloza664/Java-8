package claasesGenericas;

import java.util.Arrays;
import java.util.List;

	// CLASE GENERICA
	// --------------------------------------------------------------------------------------
	// * Java permite desde sus orígenes usar clases genéricas, utilizando referencias de tipo Object.
	// * Sin embargo, estas pueden producir problemas en tiempo de ejecución.
	// * Desde Java SE 5, podemos crear clases cuyo tipo se indica en tiempo de compilación.
	// * Podemos utilizar más de un tipo diferente a la vez.
	// NOMENCLATURA CON LOS TIPOS
	// 		* E (element, elemento)				Ej: usado con colecciones (List, Set, ...)
	// 		* K (key, clave)					Ej: llave, usado en mapas (map)
	//		* N (number, número)				Ej: para números (int, double, long, ...)
	//		* T (type, tipo)					Ej: para representar una clase (Thread, StringBuilder, ...)
	//		* V (value, valor)					Ej: representa el valor, también se usa en mapas (map)
	//		* S, U, V,... (2º, 3º, 4º ... tipo)	Ej: para representar otras clases (Thread, StringBuilder, ...)

	// INSTANCIACIÓN Y OPERADOR DIAMOND
	// -----------------------------------------------------------------------------------------------
	// * Hasta Java SE 6, para instanciar un objeto genérico, tenemos que indicar los tipos dos veces.
	//   Ej: Par<String, String> saludo = new Par<String, String>("Hola", "Mundo");
	// * Desde Java SE 7, tenemos el operador <> diamond:
	//   Ej: Par<String, String> saludo = new Par<>("Hola", "Mundo");

	// CLASES GENÉRICAS CON TIPOS CERRADOS
	// ---------------------------------------------------------------------------------------------------------
	// * Podemos acotar el tipo parametrizado, para que sea uno en particular o sus derivados.
	// * Se puede indicar más de un tipo. Uno de ellos (y solo uno) se corresponderá con una clase; 
	//   el resto deben ser interfaces. La clase a extender debe ser la primera de la lista:

	// GENÉRICOS CON TIPOS COMODÍN
	// ----------------------------------------------------------------------------------------------------------
	// * Los tipos comodín nos permiten relajar el tipo concreto de una clase genérica a un subtipo. 
	//   Son muy útiles en el caso de trabajar con colecciones


public class EjemploClaseGenerica {

	public static void main(String[] args) {
		claseGenerica();

	}
	
	
	
	private static void claseGenerica() {
		Par<Integer, String> pareja1 = new Par<>(1, "UNO");
		
		System.out.println(pareja1);
		
		//pareja1.setObj1("UNO"); //Error de compilación
		
		Par<String, String> pareja2 = new Par<>("Hola", "Mundo");
		
		System.out.println(pareja2);

	}
	

}




 // Ejemplo clase generica
class Par<T, S> {
	
	private T obj1;
	private S obj2;
	
	public Par(T obj1, S obj2) {
		this.obj1 = obj1;
		this.obj2 = obj2;
	}

	public T getObj1() {
		return obj1;
	}

	public void setObj1(T obj1) {
		this.obj1 = obj1;
	}

	public S getObj2() {
		return obj2;
	}

	public void setObj2(S obj2) {
		this.obj2 = obj2;
	}

	@Override
	public String toString() {
		return "Par [" + (obj1 != null ? "obj1=" + obj1 + ", " : "") + (obj2 != null ? "obj2=" + obj2 : "") + "]";
	}
	
	
}













 // Ejemplo de  Clase generica con tipo cerrado
 class A {
	 
 }
 interface B {
	 
 }
 interface C {
	 
 }
  class GenericoCerrado<T extends A & B & C> {

 }
  
  
  
  
  // Ejemplo de generico con tipo comodín
   class GenericoTipoComodin {

	   public static void main(String[] args) {
			List<Integer> listaEntera = Arrays.asList(1, 2, 3, 4, 5);
			System.out.printf("La suma de la lista entera es %.0f %n%n", sumaDeLista(listaEntera));
			
			List<Float> listaReal = Arrays.asList(0.5f, 1.5f, 2.5f, 3.4f, 4.3f);
			System.out.printf("La suma de la lista real es %.2f %n%n", sumaDeLista(listaReal));
			
		}
		
		public static double sumaDeLista(List<? extends Number> list) {
		    double s = 0.0;
		    for (Number n : list)
		        s += n.doubleValue();
		    return s;
		}
		
  }		
  
  






