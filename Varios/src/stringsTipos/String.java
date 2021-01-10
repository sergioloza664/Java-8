package stringsTipos;

public class String {

	public static void main(java.lang.String[] args) {
		
		//Si la variable no cambia o cambia poco, es la más común
		java.lang.String cliente = "string";
		
		//Si el valor puede ser cambiado por más de un hilo.Esta clase nos asegura que un hilo no puede
		//modificar la clase mientras otro hilo este trabajando con ella
		StringBuffer stringBuffer = new StringBuffer("StringBuffer"); 
		
		//Si el valor del objeto puede cambiar varias veces y solo o modifica un hilo (lo más habitual)
		//Este es mas rapido que StringBuffer al no ser sincronizado.
		StringBuilder stringBuilder = new StringBuilder("StringBuilder");
		

	}

}
