package flujosDeSalida;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class EjemploFlujoHaciaOtroFlujo {
	
	// Solo FileOutputStream tiene un constructor que acepta una ruta (entre otras opciones).
	// El resto reciben en sus constructores un tipo de OutputStream.
	// Podemos construir flujos que escriben en flujos (encadenados).

	public static void main(String[] args) {
		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("flujoAOtroFlujo.dat"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
