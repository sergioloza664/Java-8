package sincronizacionValoresAtomicos;

public class ContadorSincronizado {
	private int c = 0;

	public synchronized void incrementar() {
		c++;
	}

	public synchronized void decrementar() {
		c--;
	}

	public synchronized int valor() {
		return c;
	}

}
