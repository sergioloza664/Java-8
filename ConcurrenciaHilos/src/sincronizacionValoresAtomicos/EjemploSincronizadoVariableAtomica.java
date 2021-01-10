package sincronizacionValoresAtomicos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class EjemploSincronizadoVariableAtomica {
	
	// INTERFERENCIA ENTRE HILOS
	// ------------------------------------------------------
	// Cuando varios hilos comparten acceso a atributos, pueden aparecer 2 errores:
	// * Interferencia entre hilos.
	// * Errores de consistencia de memoria
	// Eston errores se pueden evitar con la sincronizaci�n de estos atributos.
	
	// SYNCHRONIZED
	// ------------------------------------------------------------------------
	// El c�digo consynchronized solo puede ser ejecutado por un hilo a la vez. El otro hilo debe esperar.
	// Dos formas de sincronizaci�n b�sica:
	// * m�todos sincronizados:			colocando synchronized en los metodos que trabajen 
	//									con el atributo que pueda tener interferencias.
	// * declaraciones sincronizadas:	en lugar de poner synchronized en los m�todos lo ponemos un bloque
	//									sincronizado en el la parte del codigo de los m�todos afectados
	
	// CERROJOS INTR�SECOS O MONITOR
	// ----------------------------------------------------------------------------------------------
	// * La sincronizaci�n se construye alrededor de una entidad interna conocido como el 
	//	 cerrojo intr�nseco o monitor de bloque.
	// * Cada objeto tiene un cerrojo intr�nseco asociado con �l.
	// * un hilo que tiene acceso exclusivo y constante a los campos de un objeto, tiene que adquirir el 
	// 	 cerrojo intr�nseco del objeto antes de acceder a ellos, y luego liberarlo cuando termine con ellos.
	// * Un hilo se dice que es due�o del cerrojo intr�nseco entre el tiempo que se haya adquirido el cerrojo 
	// 	 y el que lo haya liberado.
	// * Mientras un hilo posee un cerrojo intr�nseco, ning�n otro hilo puede adquirir el mismo cerrojo.
	// * El otro hilo se bloquea cuando intenta adquirir el cerrojo.
	
	// VARIABLES AT�MICAS
	// --------------------------------------------------------------------------
	// * En el paquete java util.concurrent.atomic
	// * Tipos b�sicos que ya est�n sincronizados (thread-safe).
	// * M�todos get, set, counterAndSet, ...
	// * AtomicBoolean, AtomicInteger, AtomicLong, LongAdder, DoubleAdder, ...
	// Los atomic le indicamos nosotros el numero de inicio, en los adder comienza desde cero.
	

	public static void main(String[] args) {
		metodosSincronizados();
		try {bloqueSincronizado();} catch (InterruptedException e) {e.printStackTrace();}
		atomicInteger();
		longAdder();
	}
	
	
	
	
	private static void metodosSincronizados() {
		//Contador compartido y sincronizado
		ContadorSincronizado c = new ContadorSincronizado();

		//Tambi�n podemos tener diferentes resultados, pero
		//nos aseguramos de que ninguno va a ser inconsistente.
		new Thread(() -> {
			c.incrementar();
			System.out.println("Incrementrado en uno: " + c.valor());
			c.incrementar();
			System.out.println("Incrementrado en uno: " + c.valor());
		}).start();
				

		new Thread(() -> {
			c.decrementar();
			System.out.println("Decrementado en uno: " + c.valor());
			c.decrementar();
			System.out.println("Decrementado en uno: " + c.valor());
		}).start();
	}
	
	
	
	
	private static void bloqueSincronizado() throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		ContadorBloqueSincronizado sync = new ContadorBloqueSincronizado();
		
		IntStream
			.range(0, 10000)
			.forEach(i -> executor.submit(sync::increment));
		
		executor.shutdown();
		executor.awaitTermination(10, TimeUnit.SECONDS);
		
		System.out.println(sync.get());
	}
	
	
	
	
	private static void atomicInteger() {
		ContadorAtomico contador = new ContadorAtomico();
		
		new Thread(()->{
			contador.incrementar();
			contador.incrementar();
			System.out.println(contador.valor());
		}).start();
		
		new Thread(()->{
			contador.decrementar();
			contador.decrementar();
			System.out.println(contador.valor());
		}).start();

	}
	
	
	
	
	private static void longAdder() {
		LongAdder longAdder = new LongAdder();
		
		new Thread(()->{
			longAdder.increment();
			longAdder.increment();
			System.out.println(longAdder.intValue());
		}).start();
		
		new Thread(()->{
			longAdder.decrement();
			longAdder.decrement();
			System.out.println(longAdder.intValue());
		}).start();
	}

}
