package stringsTipos;

public class String {

	public static void main(java.lang.String[] args) {
		
		//Si la variable no cambia o cambia poco, es la m�s com�n
		java.lang.String cliente = "string";
		
		//Si el valor puede ser cambiado por m�s de un hilo.Esta clase nos asegura que un hilo no puede
		//modificar la clase mientras otro hilo este trabajando con ella
		StringBuffer stringBuffer = new StringBuffer("StringBuffer"); 
		
		//Si el valor del objeto puede cambiar varias veces y solo o modifica un hilo (lo m�s habitual)
		//Este es mas rapido que StringBuffer al no ser sincronizado.
		StringBuilder stringBuilder = new StringBuilder("StringBuilder");
		

	}

}
