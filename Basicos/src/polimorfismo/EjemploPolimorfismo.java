package polimorfismo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// HERENCIA DE METODOS
	// --------------------------------------------------------------------------------
	// * Una subclase puede acceder a los metodos de su clase base (public y protected)
	// * Támbien puede sobrescribir el comportamiento del mismo

	// REFERENCIAS Y SUBCLASES
	// ---------------------------------------------------------------------------------------
	// * Una subclase puede ser accedida a traves de una referencia de una superclase.
	// * Esto es muy util, sobre todo para usar como atributos de metodos.
	// Ej: ClasePadre clase = new ClaseHija();

	// OCULTACION DE METODOS
	// --------------------------------------------------------------------------------------------------
	// * Si una subclase añade un metodo con mismo nombre y firma que otro de la clase base, oculta a este.
	// * Si se usa una referencia de la clase base, pero la instanciacion es de una clase que a 
	//   heredado de la clase base:
	//   	* Java escoge, en tiempo de ejecución, el tipo de objeto.
	//   	* Si la clase hija ha ocultado un metodo de la clase padre, se ejecuta el de la clase hija.
	//   	* Si la clase hija no ha ocultado un metodo de la clase padre, se ejecuta el de la clase padre.

	// POLIMORFISMO CON INTERFACES
	// ----------------------------------------------------------------------------------------------------
	// * Java tambien hace uso de polimorfismo con la herencia de interfaces y las clases que lo implementan.

public class EjemploPolimorfismo {
	
	public static void main(String[] args) {
		polimorfismoConClases();
		polimorfismoConInterfaces();
	}
	
	
	
	// Ejemplo polimorfismo de clases
	private static void polimorfismoConClases() {
		List<ClasePadreRectangulo> listaFiguras = new ArrayList<>();
		
		//Creamos una lista de 10 figuras.
		//Tanto la figura como el tamaño de la misma
		//son aleatorias :S
		for(int i = 0; i < 10; i++) {
			listaFiguras.add(tirarMoneda() ? 
					new ClasePadreRectangulo(aleatorio(50), aleatorio(50)) : 
						new ClaseHijaCuadrado(aleatorio(50)));
		}
		
		//Recorremos la lista, usando referencias de
		//tipo RECTANGULO
		for(ClasePadreRectangulo r : listaFiguras) {
			System.out.println(r);
			System.out.printf("Area de la figura: %f | Perímetro de la figura: %f %n", r.getArea(), r.getPerimetro());
			System.out.println("");
		}
			
	}
	
	public static float aleatorio(int tope) {
		Random r = new Random();
		return r.nextFloat() * r.nextInt(tope);
	}
	
	public static boolean tirarMoneda() {
		Random r = new Random();
		return (r.nextInt(2) % 2 == 0);
	}
	
	
	
	
	
	
	
	
	
	
	

	
	// Ejemplo de polimorfismo con interfaces
	private static void polimorfismoConInterfaces() {
		ClaseQueImplementaInterfazHija c1 = new ClaseQueImplementaInterfazHija();
		c1.saludar("Hola Mundo");
		
		InterfazHija c2 = new ClaseQueImplementaInterfazHija();
		c2.saludar("Hola Mundo, otra vez");
		
		InterfazPadre c3 = new ClaseQueImplementaInterfazHija();
		c3.saludar("Hola Mundo, por tercera vez");

	}
	
	
	
	
	
	

}








 interface InterfazPadre {
	
	default public void saludar(String mensaje) {
		System.out.println(mensaje + " desde Base");
	}

}
 interface InterfazHija extends InterfazPadre {
		
		default public void saludar(String s) {
			System.out.println(s + " desde Hija");
		}

}
class ClaseQueImplementaInterfazHija implements InterfazHija {

 }














 class ClasePadreRectangulo {
	
	public float base;
	public float altura;
	
	
	public ClasePadreRectangulo(float base, float altura) {
		this.base = base;
		this.altura = altura;
	}


	public float getBase() {
		return base;
	}


	public void setBase(float base) {
		this.base = base;
	}


	public float getAltura() {
		return altura;
	}


	public void setAltura(float altura) {
		this.altura = altura;
	}


	@Override
	public String toString() {
		return "Rectangulo [base=" + base + ", altura=" + altura + "]";
	}
	
	
	public float getArea() {
		System.out.println("Area de un rectángulo");
		return base*altura;
	}
	
	public float getPerimetro() {
		System.out.println("Perímetro de un rectángulo");
		return (base*2) + (altura*2);
	}
	
	

}
 
 class ClaseHijaCuadrado extends ClasePadreRectangulo{

		public ClaseHijaCuadrado(float lado) {
			super(lado, lado);
		}
		
		public float getLado() {
			return getBase();
		}
		
		public void setLado(float lado) {
			setBase(lado);
			setAltura(lado);
		}
		
		public float getArea() {
			System.out.println("Area de un cuadrado");
			return getBase()*getBase();
		}
		
		public float getPerimetro() {
			System.out.println("Perimetro de un cuadrado");
			return getBase()*4;
		}

		@Override
		public String toString() {
			return "Cuadrado [lado=" + getLado() + "]";
		}
		
		

	} 
 