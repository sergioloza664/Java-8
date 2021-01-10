package enumerados;

	// ENUMERADOS BASICOS
	// ------------------------------------------------------------------------
	// * Tipo de dato especial
	// * Indica que una variable tendrá como valor uno de entre un conjunto cerrado.
	// * Por ejemplo Direccion (Norte, Sur, Este, Oeste).

	// ENUMERADOS COMO CLASES
	// ---------------------------------------------------------------------------------
	// * Son más potentes que en otros lenguajes.
	// * Para Java son tipos de clases.
	// * Pueden incluir métodos yb otros atributos.
	// * El compilador añade métodos especiales (values).
	// * Podemos pensar en que tenemos un conjunto cerrado de instancias de una clase.


public class EjemploEnumerados {
	
	
	public static void main(String[] args) {
		enumeradoBasico();
		enumeradoComoClase();
		
	}
	
	
	
	
	// Enumerado básico
	public enum Direccion {
		NORTE, SUR, ESTE, OESTE
	}

	private static void enumeradoBasico() {
		Direccion d = Direccion.ESTE;
		if ( d == Direccion.ESTE) 
			System.out.println("este");
		
	}
	
	
	
	
	
	// Enumerado como una clase
	public enum Planeta {
		
		MERCURIO (3.303e+23, 2.4397e6),
	    VENUS    (4.869e+24, 6.0518e6),
	    TIERRA   (5.976e+24, 6.37814e6),
	    MARTE    (6.421e+23, 3.3972e6),
	    JUPITER  (1.9e+27,   7.1492e7),
	    SATURNO  (5.688e+26, 6.0268e7),
	    URANO    (8.686e+25, 2.5559e7),
	    NEPTUNO  (1.024e+26, 2.4746e7);
		
		
		private final double masa; //en kg
		private final double radio; //en metros
		
		Planeta(double masa, double radio) {
			this.masa = masa;
			this.radio = radio;
		}
		
		double masa() { return masa; }
		double radio() { return radio; }
		
		//Constante universal G (m3 kg-1 s-2)
		public static final double G = 6.67300E-11;
		
		public double gravedadSuperficial() {
			return G * masa / (radio * radio);
		}

		public double pesoEnLaSuperficie(double otraMasa) {
			return otraMasa * gravedadSuperficial();
		}
	}
	
	private static void enumeradoComoClase() {
		double pesoEnLaTierra = 75.0;
        double masaEnLaTierra = pesoEnLaTierra/ Planeta.TIERRA.gravedadSuperficial();
        for (Planeta p : Planeta.values())
           System.out.printf("Tu peso en %s es %f%n", p, p.pesoEnLaSuperficie(masaEnLaTierra));
	}
	
	
	
	
	
}
