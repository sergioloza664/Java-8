package herencia;

	// HERENCIA
	// --------------------------------------------------------------------------------------------------
	// * Una clase que extiende a otra hereda sus atributos y sus métodos (no constructores).
	// * Puede añadir atributos y métodos nuevos.
	// * Si usamos protected en la clase base, tendremos acceso directo a los atributos.
	// * En otro caso, tendremos que acceder vía getters/setters (la más recomendada en lugar de usar protected).
	// * Los constructores no se heredan aunque sean públicos.

	// HERENCIA DE INTERFACES
	// ----------------------------------------------------------------------------------------------------
	// * También podemos establecer relaciones jerárquicas entre interfaces.
	// * Nos regimos por las mismas reglas que en el caso de las clases.

	// COMPOSICION DE CLASES (UML)
	// ------------------------------------------------------------------------------------------------
	// PROVINCIA --------> tiene muchos --------> MUNICIPIOS
	// MUNICIPIO -------->  tiene una   --------> PROVINCIA 
	// http://www.vc.ehu.es/jiwotvim/ISOFT2007-2008/Teoria/BloqueII/6.%20UML%20a%20Codigo%20Java.pdf
	// En una relacion muchos a muchos:
	// ESTUDIANTE <--------> MATRICULA <--------> CLASE
	//
	// Estudiante: tendra una lista de MATRICULA 
	// Matricula:  tendra una lista de ESTUDIANTE y otra lista de CLASE
	// Clase:	   tendra una lista de MATRICULA

public class EjemploHerencia {}




// Ejemplo de herencia
class Base {
	
	private String nombre;
	protected String apellidos;
	
	public Base() { }

	public Base(String nombre, String apellidos) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
}

 class Hija extends Base {
	
	public Hija(String n, String a) {
		super(n, a);
	}
	
	public void metodo() {
		//this.nombre = Pepe;   // Imposible acceder. Nos da error
		this.setNombre("Pepe"); // Funciona perfectamente
		this.apellidos = "Perez";
	}
	
	public void print() {
		System.out.println(this.getNombre() + " " + this.apellidos);
	}

}















// Ejemplo de una interfaz que hereda de otra
interface Imprimible {
	
	public void print(String s);

}
interface ImprimiblePorConsola extends Imprimible {
	
	default public void printConsola(String s) {
		System.out.println(s);
	}

}