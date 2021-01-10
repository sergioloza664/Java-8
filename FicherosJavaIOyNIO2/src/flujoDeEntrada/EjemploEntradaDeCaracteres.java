package flujoDeEntrada;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EjemploEntradaDeCaracteres {
	
	// PATRON BASICO DE LOS FLUJOS DE ENTRADA
	/* -----------------------------------------
	 * Abrir el flujo
	 * Mientras hay datos que leer
	 * 	Leer datos del flujo
	 * 	Procesar los datos
	 * Cerrar el flujo		
	*/
	
	//FLUJO DE ENTRADA DE CARACTERES
	//----------------------------------------------------------------------------------------------------------
	// Reader:				clase abstracta, padre de la mayoría de los flujos de caracteres.
	// FileReader:			flujo que permite leer de un fichero, caracter a caracter.
	// BufferedReader:		flujo que permite leer líneas de texto.	
	// StringReader:		flujo que permite leer desde la memoria.
	// InputStreamReader:	flujo que permite transformar un InputStream en un Reader.	

	public static void main(String[] args) {
		bufferedInputStream();

	}
	
	
	
	//La codificación César transforma un mensaje, cambiando las letras por aquellas
	//que ocupan 3 posiciones déspues en el abedcedario
	private static void bufferedInputStream() {
		final int OFFSET = 3; // Posiciones del nuevo valor de la letra

		BufferedReader br = null;
		BufferedWriter bw = null;

		try {
			br = new BufferedReader(new FileReader("src\\flujoDeEntrada\\mensaje.txt"));
			bw = new BufferedWriter(new FileWriter("src\\\\flujoDeEntrada\\\\mensaje_cifrado.txt"));

			String linea = null;

			while ((linea = br.readLine()) != null) {
				StringBuilder sb = new StringBuilder(linea.length());
				for (char c : linea.toUpperCase().toCharArray()) {
					char result;
					if (Character.isLetter(c)) {
						int intValue = (int) c - 'A'; // Diferencia entre las 2 letras
						int intResult = (intValue + OFFSET) % 26; //Le sumamos 3 y obtenemos el resto de dividir entre 26
						result = (char) ('A' + intResult); // Le sumamos las posiciones para la nueva letra
					}
					else
						result = c; // Espacio en blanco
					sb.append(result);
				}
				bw.write(sb.toString());
				bw.newLine();

			}
			System.out.println("El mensaje ha sido cifrado correctamente");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {br.close();} catch (IOException e) {e.printStackTrace();}
			
			if (bw != null)
				try {bw.close();} catch (IOException e) {e.printStackTrace();}
		}
		
	}
	
	
	
	

}
