package flujoDeEntrada;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EjemploEntradaDeBytes {
	
	// PATRON BASICO DE LOS FLUJOS DE ENTRADA
		/* -----------------------------------------
		 * Abrir el flujo
		 * Mientras hay datos que leer
		 * 	Leer datos del flujo
		 * 	Procesar los datos
		 * Cerrar el flujo		
		*/
		
		//FLUJO DE ENTRADA DE BYTES
		//----------------------------------------------------------------------------------------------------------
		// InputStream:				clase abstracta, padre de la mayoría de los flujos de bytes.
		// FileInputStream:			flujo que permite leer de un fichero, byte a byte.
		// BufferedInputStream:		flujo que permite leer grupos (buffers) de bytes.	
		// ByteArrayInputStream:	flujo que permite leer de memoria (de un array de bytes).
		
	public static void main(String[] args) {
		fileInputStream();
		bufferedInputStream();
		bufferedInputStreamV2(args);

	}
	
	
	
	private static void fileInputStream() {
		FileInputStream fIn = null;
		
		try {
			fIn = new FileInputStream("fileOutputStream.dat");
			int c;
			while ((c = fIn.read()) != -1) 
				System.out.println(c);
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fIn != null)
				try {fIn.close();} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	
	
	// Para copiar ficheros
	private static void bufferedInputStream() {
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;

		final int TAM = 1024 * 16;

		try {
			bin = new BufferedInputStream(new FileInputStream("src\\flujoDeEntrada\\prohibido.jpg"));
			bout = new BufferedOutputStream(new FileOutputStream("src\\flujoDeEntrada\\copiaProhibido.jpg"));

			int cantidadBytes = 0;
			byte[] buffer = new byte[TAM];

			while ((cantidadBytes = bin.read(buffer, 0, TAM)) != -1) {
				bout.write(buffer, 0, cantidadBytes);
			}
			
			System.out.println("El fichero se ha copiado correctamente");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bin != null)
				try {bin.close();} catch (IOException e) {e.printStackTrace();}
			
			if (bout != null)
				try {bout.close();} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	
	
	
	// Para copiar ficheros pasandole los argumentos de entrada y salida las rutas del anterior ejemplo
		private static void bufferedInputStreamV2(String[]args) {
			if (args.length != 2) {
				System.err.println("Error de sintaxis. Se necesitan dos argumentos");
				return;
			}
			
			BufferedInputStream bin = null;
			BufferedOutputStream bout = null;

			final int TAM = 1024 * 16;

			try {
				bin = new BufferedInputStream(new FileInputStream(args[0]));
				bout = new BufferedOutputStream(new FileOutputStream(args[1]));

				int cantidadBytes = 0;
				byte[] buffer = new byte[TAM];

				while ((cantidadBytes = bin.read(buffer, 0, TAM)) != -1) {
					bout.write(buffer, 0, cantidadBytes);
				}
				
				System.out.println("El fichero se ha copiado correctamente");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bin != null)
					try {bin.close();} catch (IOException e) {e.printStackTrace();}
				
				if (bout != null)
					try {bout.close();} catch (IOException e) {e.printStackTrace();}
			}
		}
	

}
