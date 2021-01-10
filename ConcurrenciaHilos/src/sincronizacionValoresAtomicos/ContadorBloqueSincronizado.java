package sincronizacionValoresAtomicos;

public class ContadorBloqueSincronizado {

	private int count = 0;
	
	public void increment() {
		synchronized(this) {
			count++;
		}
	}
	
	public int get() {
		synchronized(this) {
			return count;
		}
	}
	
}
