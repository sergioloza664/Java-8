package manejoDeExcepciones;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// EXCEPCION
	// --------------------------------------------------------------------------------------------
	// * Situacion excedpcional
	// * Altera la ejecución normal del programa.
	// * El método donde sucede crea un objeto, llamado objeto de excepción, y se lo pasa a alguien que 
	//   pueda tratarlo.
	// * Si existe quien pueda manejarlo, larecoge.
	// * Si no existe, se encarga la JVM.


	// USO DE EXCEPCIONES
	// ------------------------------------------------------------------------------------------------
	// * Permite separar el código de tratamiento de errores del código normal.
	// * Evitan que haya errores inadvertidos.
	// * Permiten la propagación de los errores.
	// * Permiten agrupar en un lugar común el tratamiento de errores.


	// TIPOS DE EXCEPCIONES
	// -----------------------------------------------------------------------------------------------------
	// * Excepciones comprobadas (checked exceptions): son aquellas excepciones que pueden surgir internamente
	//												   en un programa, pero que al estar bien escrito, podemos
	//												   tratar y de las que nos podremos recuperar.
	// * Errores (errors): situaciones externas que no son anticipables, y de las que puede que no podamos 
	//					   recuperarnos (por ejemplo, un error hardware). Son un tipo no comprobado (unchecked)
	// * Errores de ejecución (Runtime errors): son situaciones interas de la aplicación, de las que 
	//											probablemente no podamos recuperarnos. Son un tipo no 
	//											comprobado (unchecked).


	// TRATAMIENTO DE EXCEPCIONES
	// --------------------------------------------------------------------------------------------------------
	// * Se realiza utilizando la siguiente sintaxis: 
	//	 try {instrucciones;} catch (Exception e) {instruccinoes;} finally {instrucciones}
	// * finally no es obligatorio
	// * Puede haber más de un catch
	// * Los tipos de excepción deben ir de más concretos a más genericos.
	// El operador | nos permite tratar más de un tipo de excepción en un catch


	// TRY
	// -------------------------------------------------------------------------------------------------
	// * Debe envolver las sentencias que son susceptibles de provocar uno o varios tipos de excepción.
	// * Debemos agrupar las sentencias que vayan a tener un tratamiento idéntico de la situación excepcional.


	// CATCH
	// ---------------------------------------------------------------------------------------------------
	// * Puede haber uno, o más de uno.
	// * Nos permiten definir los manejadores de las excepciones.
	// * Cada bloque maneja uno o varios tipos de excepciones.


	// FINALLY
	// ------------------------------------------------------------------------------------------------------
	// * Se ejecuta siempre (si venimos de try o de catch)
	// * Se suele utilizar como código que asegura el cierre de recursos abiertsos (ficheros, 
	//   bases de datos, ...)


	// EXCEPCIONES MAS COMUNES
	// -------------------------------------------------------------------------------------------------------
	// * ArithmeticException:	Errores en operaciones aritméticas
	// * ArraryIndexOutOfBoundsException:	Índice de array fuera de los límites
	// * ClassCastException:	Intento de convertir a una clase incorrecta
	// * IllegalArgumentException:	Argumento ilegal en la llamada a un método
	// * IndexOutOfBoundsException:	Índice fuera de colección
	// * NegativeArraySizeException:	Tamaño de array negativo
	// * NullPointerException:	Uso de referencia nula
	// * NumberFormatException:	Formato de número incorrecto
	// * StringIndexOutOfBounds:	Índice usado en String fuera de los límites


	// LANZAMIENTO DE EXCEPCIONES
	// -----------------------------------------------------------------------------------------------------
	// * Cualquier código puede lanzar excepciones (hecho por java, por nosotros o de terceros).
	// * Si no vamos a tratar las excepciones en un método, tenemos que indicar que se relanzarán hacia 
	//   arriba (throws).
	//
	// THROWS
	//    	* Un método cuyo código puede producir excepciones puede capturarlas y tratarlas, o relanzarlas 
	//		  para que sea otro quien las trate.
	//		* throws se escribe a continuación de la firma del método y antes de la apertura del cuerpo, y 
	//		  espera la excepción o excepciones (lista separada por comas) que se pueden lanzar.
	//
	// THROW
	//  	* Nos permite lanzar una excepción en un momento determinado.
	// 		* También se puede usar en el bloque catch, para tratar una excepción, pero aun así relanzarla.


	// EXCEPCIONES PROPIAS
	// --------------------------------------------------------------------------------------------------
	// * Podemos crear nuestros propios tipos, extendiendo a Exception.
	// * Nos permiten manejar nuestras propias situaciones.
	// * Es buena práctica añadir el sufijo …Exception.








// Ejemplo de uso de trows
class EjemploConThrows {


	public static void main(String[] args) {
		
			try {
				writeList();
				System.out.println("Fichero escrito correctamente");
			} catch (IOException e) {
				System.out.println("Error al intentar abrir un fichero de texto");
			}
	}
	
	public static void writeList() throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("OutFile.txt"));
		
		for(int i = 0; i < 10; i++) {
			out.println("Mensaje nº " + i);
		}
		
		out.close();
	}

}










	// Ejemplo de throw
 class EjemploThrow{
	
	private static int saldo =50;
	public static void main(String[] args) {
		try {sacarDinero(100);} catch (SaldoNegativoException e) {e.printStackTrace();}
	}
	public static void sacarDinero(double cantidad) throws SaldoNegativoException {
	     saldo -= cantidad;
	     if (saldo < 0) {
	          throw new SaldoNegativoException(saldo);
	     }
	}
}















// Ejemplo de excepcion propia
 class EjemploExcepcionPropia {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CuentaCorriente cc = new CuentaCorriente("Sergio Lozano", 100.0);
		
		try {
			cc.sacarDinero(160.0);
			System.out.println("Saldo actual " + cc.getSaldo());
		} catch (SaldoNegativoException e) {
			System.err.println(e.getMessage());
			System.err.println("Póngase en contacto con su banco");
		}
		

	}

}
 class SaldoNegativoException extends Exception {

	public SaldoNegativoException(double saldo) {
		super("La cuenta ha quedado en descubierto (" + Double.toString(saldo) + ")");
	}

}
 class CuentaCorriente {
	
	private String propietario;
	private double saldo;
	
	
	public CuentaCorriente(String propietario, double saldo) {
		this.propietario = propietario;
		this.saldo = saldo;
	}


	public String getPropietario() {
		return propietario;
	}


	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}


	public double getSaldo() {
		return saldo;
	}
	
	public void ingresarDinero(double cantidad) {
		saldo += cantidad;
	}
	
	public void sacarDinero(double cantidad) throws SaldoNegativoException {
		saldo -= cantidad;
		if (saldo < 0) {
			throw new SaldoNegativoException(saldo);
		}
	}
	
	
	
	

}












































