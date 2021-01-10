package coleccionesConcurrentes;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import coleccionesConcurrentes.ConsumidorBlockingQueue;
import coleccionesConcurrentes.ProductorBlockingQueue;
import coleccionesConcurrentes.ColaWaitNotify;
import coleccionesConcurrentes.ConsumidorWaitNotify;
import coleccionesConcurrentes.ProductorWaitNotify;

	// PROBLEMAS DE CONCURRENCIA EN COLECCIONES
	// -----------------------------------------------------------------
	// * Acceso en modo lectura o escritura de dos o más hilos de ejecucion diferente (productor/consumidor)
	// * Operaciones de ordenación.

	// PRODUCTOR/CONSUMIDOR
	// ----------------------------------------------------------------------
	// Problema clasico en concurrencia
	// * Colección compartida de tamaño finito.
	// * varios hilos que producen datos.
	// * Problemas de condición de carrera (colección vacia, colección llena, ...).
	// * Requiere de espera y notificación (wait(), notifyAll()).

	// WAIT Y NOTIFY
	// ---------------------------------------------------------------------------------
	// * Son método de la clase Object, disponibles en cualquier clas
	// * Nos permiten poner en espera a un hilo, hasta que se cumpla una determinada condición.
	// * Posteriormente, podemos notificarle que ya puede continuar con su ejecución, 
	//   ya que la condición bloqueante ha dejado de cumplirse.
	// * Por ejemplo: si la colección se llena, los productores deben esperar a que los consumidores 
	//	 tomen elementos, para poder seguir produciendo.

	// Colecciones concurrentes
	// -------------------------------------------------------------------------------------------
	// Nos evitan programa soluciones como la del productor-consumidor
	// * BlockingQueue: 			estructura FIFO que bloquea si la cola se queda llena o vacía.
	// * ConcurrentMap: 			Map con operaciones atómicas.
	// * ConcurrentNavigableMap: 	NaviagleMap con búsquedas aproximad

	// BLOCKINGQUEUE
	// --------------------------------------------------------------------------------
	// Estructura FIFO: first-in-first-out. Conocida como cola. Tenemos diferentes implementaciones:
	// * ArrayBlockingQueue: 	debemos construirla con una capacidad inicial fija.
	// * LinkedBlockingQueue: 	su capacidad inicial es Integer.MAX_VALUE (el mayor valor entero que podemos 
	//							almacenar en un int).
	// * PriorityBlockingQueue: permite ordenar los elementos según prioridad.

public class EjemploWaitNotifyBlockingQueue {

	public static void main(String[] args) {
		waitNotifyProductorConsumidor();
		blockingQueue();

	}
	
	
	
	
	
	private static void waitNotifyProductorConsumidor() {
		ColaWaitNotify cola = new ColaWaitNotify();
		(new Thread(new ProductorWaitNotify(cola))).start();
		(new Thread(new ConsumidorWaitNotify(cola))).start();
	}
	
	
	
	
	private static void blockingQueue() {
		// Colección compartida
		BlockingQueue<Integer> q = new ArrayBlockingQueue<>(20);
		
		// Productores y consumidores
		ExecutorService executor = Executors.newCachedThreadPool();
		
		executor.submit(new ProductorBlockingQueue(q));
		executor.submit(new ProductorBlockingQueue(q));
		executor.submit(new ConsumidorBlockingQueue(q));
	}

}
