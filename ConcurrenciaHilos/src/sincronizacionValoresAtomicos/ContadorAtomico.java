package sincronizacionValoresAtomicos;

import java.util.concurrent.atomic.AtomicInteger;

public class ContadorAtomico {
	
	private AtomicInteger c = new AtomicInteger(0);
	
	public int incrementar() {
		return c.incrementAndGet();
	}
	
	public int decrementar() {
		return c.decrementAndGet();
	}
	
	public int valor() {
		return c.get();
	}

}
