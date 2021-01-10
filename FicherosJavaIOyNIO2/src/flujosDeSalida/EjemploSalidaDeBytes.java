package flujosDeSalida;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class EjemploSalidaDeBytes {
	
	// PATRON BASICO DE LOS FLUJOS DE SALIDA
	/* -----------------------------------------
	 * Abrir el flujo
	 * Mientras hay datos que escribir
	 * 	Escribir datos en el flujo
	 * Cerrar el flujo		
	*/
	
	//FLUJO DE SALIDA DE BYTES
	//----------------------------------------------------------------------------------------------------------
	// OutpuStream:				clase abstracta, padre de la mayoría de los flujos de bytes.
	// FileOutputStream:		flujo que permite escribir en un fichero, byte a byte.
	// BufferedOutputStream:	flujo que permite escribir grupos (buffers) de bytes.	
	// ByteArrayOutputStream:	flujo que permite escribir en memoria, obteniendo lo escrito en un array de bytes.
	
	public static void main(String[] args) {
		bufferedOutputStream();
		fileOutputStream();
		byteArrayOutputStream();
	}
	
	
	
	
	private static void bufferedOutputStream() {
		byte[] buffer = new byte[1024*32]; 
		Arrays.fill(buffer, Byte.parseByte("1")); //Ponemos un 1 en cada byte
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream("bufferedOutputStream.dat"));
			bos.write(buffer);
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bos != null)
				try {bos.close();} catch (IOException e) {e.printStackTrace();}
		}
		
	}
	
	
	
	
	private static void fileOutputStream() {
		OutputStream fOut = null;
		
		try {
			fOut = new FileOutputStream("fileOutputStream.dat");
			for(int i = 0; i < 1000; i++) {
				fOut.write(i);
			}			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fOut != null)
				try {fOut.close();} catch (IOException e) {e.printStackTrace();}
		}
		
	}
	
	
	
	private static void byteArrayOutputStream() {
		byte[] buffer = new byte[1024*32]; 
		Arrays.fill(buffer, Byte.parseByte("1")); //Ponemos un 1 en cada byte
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			baos.write(buffer);
			System.out.println(baos.toByteArray().length);			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (baos != null)
				try {baos.close();} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	
	

}
