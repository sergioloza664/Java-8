package flujosDeSalida;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EjemploSalidaDeCaracteres {

	// PATRON BASICO DE LOS FLUJOS DE SALIDA
	/* -----------------------------------------
	* Abrir el flujo
	* Mientras hay datos que escribir
	* 	Escribir datos en el flujo
	* Cerrar el flujo		
	*/
		
	//FLUJO DE SALIDA DE CARACTERES
	//----------------------------------------------------------------------------------------------------------
	// Writer:				clase abstracta, padre de la mayoría de los flujos de caracteres.
	// FileWriter:			flujo que permite escribir en un fichero, caracter a caracter.
	// BufferedWriter:		flujo que permite escribir líneas de texto.	
	// StringWriter:		flujo que permite escribir en memoria, obteniendo lo escrito en un String
	// OutputStreamWriter:	flujo que permite transformar un OutputStream en un Writer.
	// PrintWriter:			flujo que permite escribir tipos básicos Java.
	
	public static void main(String[] args) {
		fileWriter();
		bufferedWriter();	
						
	}
	
	
	
	
	private static void fileWriter() {
		FileWriter fw = null;
		String intro = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.";
		
		try {
			fw = new FileWriter("fileWriter.txt");
			for(char c : intro.toCharArray())
				fw.write(c);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null)
				try {fw.close();} catch (IOException e) {e.printStackTrace();}
		}

	}
	
	
	
	
	private static void bufferedWriter() {
		BufferedWriter bw = null;

		List<String> quijote = Arrays.asList(new String[] { "Lorem Ipsum is simply dummy text,"
				, "of the printing and typesetting industry."
				, "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,"
				, ", when an unknown printer took a galley of type and scrambled"
				, " it to make a type specimen book." });

		try {
			bw = new BufferedWriter(new FileWriter("bufferedWriter.txt"));
			for (String s : quijote) {
				bw.write(s);
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null)
				try {bw.close();} catch (IOException e) {e.printStackTrace();}
		}
	}
	
}
