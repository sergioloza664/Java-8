package clasesInternasLocalesAnonimas;

	// CLASES ANIDADAS (CLASES DENTRO DE OTRAS CLASES)
	// ------------------------------------------------------------------------------------- 
	// * Java permite definir clases dentro de otras clases
	// * A estas clases se les llaman anidadas.
	// * Pueden ser de dos tipos, est�ticas o no est�ticas
	// * No se trata de composici�n de clases, sino de anidamiento.
	// * Pueden acceder a los atributos de la clase que le envuelve.
	// RAZONES DEL USO DE CLASES ANIDADAS
	// 		* Agrupamiento l�gico de clases que se utilizan en un solo lugar. mayor cohesi�n.
	// 		* Aumento de la encapsulaci�n.
	//		* C�digo m�s legible y f�cil de mantener.

	// CLASES INTERNAS
	// ---------------------------------------------------------------------------------------------------
	// * Se llaman as� a las clases anidadas no est�ticas, que tienen que ser private.
	// * Para aceder a ellas hay que hacerlo desde una instancia de la clase externa. Esta instancia no debe estar en un m�todo estatico.
	// * Pueden acceder a los elementos de la clase externa.
	// * Si una variable de la clase interna se llama igual que una de la clase externa, la interna oculta
	//   a la variable de la externa (shadowing).

	// CLASES LOCALES
	// ----------------------------------------------------------------------------------------------------
	// * Clases que se definen dentro de un bloque, normalmente el cuerpo de un m�todo.
	// * Sirven para afinar aun m�s la cohesi�n del c�digo.

	// CLASE AN�NIMA
	// ---------------------------------------------------------------------------------------------------
	// * Permiten definir e instanciar una clase a la vez.
	// * Son como clases locales sin nombre.
	// * Sirven para ser usadas una vez.
	// * Las podemos definir a partir de otra clase o de una interfaz.
	// * Podemos crearlas en el cuerpo de un m�todo, de una clase, o como argumento de un m�todo.

public class EjemploInternaLocalAnonima {
	
	private int n;
	private static int m;
	
	public int getN() {
		return n;
	}
	private void metodoParaClaseInterna() {Interna interna = this.new Interna();}
	public static void main(String[] args) {}
	
	
	
	
	
	
	
	
	// Se pueden definir interfaces internas
	interface Hola {
        public void bien();
        public void muyBion(String bien);
    }
	// Clases anonimas
	private void metodoParaClaseAnonima() {
		// Clase local con interfaz interna
		class Hello implements Hola{
			@Override
			public void bien() {}				
			@Override
			public void muyBion(String bien) {}			
		}
		Hola hello = new Hello();
		
		//Clase an�nima: definici�n + instanciaci�n
		Hola bonjour = new Hola() {
			@Override
			public void bien() {}				
			@Override
			public void muyBion(String bien) {}			
		};
		
		//Clase an�nima que no se asigna a ninguna referencia.
		new Hola() {
			@Override
			public void bien() {}
				
			@Override
			public void muyBion(String bien) {}	
		}.bien();
		
				
	}
		
		
		
	
	
	
	
	
	private void metodoParaClaseLocal() {
		
		// Clase local
		class Local{
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	// Clase anidada
	public class Anidada {
		private String s;
		
		//Como clase anidada, puede acceder a los atributos de la 
		//clase externa.
		public int getN() {
			return n;
		}
			
	}
	
	
	
	
	
	
	
	/// Clase anidada estatica
	public static class AnidadaEstatica {
		private String s;
		
		//Como clase interna est�tica, no puede acceder 
		//a los atributos de la clase externa.
		//a no ser que sean est�ticos
//		public int getN() {
//			return n;
//		}
		static int g =m;
		
	}
	
	
	
	
	
	
	// Clase interna
	private class Interna {
		// podemos acceder a los elementos de la clase externa
		private int t=n;
		
	}
	
	
	

}
