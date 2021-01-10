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
	// * Acceso en modo lectura o escritura de dos o m�s hilos de ejecucion diferente (productor/consumidor)
	// * Operaciones de ordenaci�n.

	// PRODUCTOR/CONSUMIDOR
	// ----------------------------------------------------------------------
	// Problema clasico en concurrencia
	// * Colecci�n compartida de tama�o finito.
	// * varios hilos que producen datos.
	// * Problemas de condici�n de carrera (colecci�n vacia, colecci�n llena, ...).
	// * Requiere de espera y notificaci�n (wait(), notifyAll()).

	// WAIT Y NOTIFY
	// ---------------------------------------------------------------------------------
	// * Son m�todo de la clase Object, disponibles en cualquier clas
	// * Nos permiten poner en espera a un hilo, hasta que se cumpla una determinada condici�n.
	// * Posteriormente, podemos notificarle que ya puede continuar con su ejecuci�n, 
	//   ya que la condici�n bloqueante ha dejado de cumplirse.
	// * Por ejemplo: si la colecci�n se llena, los productores deben esperar a que los consumidores 
	//	 tomen elementos, para poder seguir produciendo.

	// Colecciones concurrentes
	// -------------------------------------------------------------------------------------------
	// Nos evitan programa soluciones como la del productor-consumidor
	// * BlockingQueue: 			estructura FIFO que bloquea si la cola se queda llena o vac�a.
	// * ConcurrentMap: 			Map con operaciones at�micas.
	// * ConcurrentNavigableMap: 	NaviagleMap con b�squedas aproximad

	// BLOCKINGQUEUE
	// --------------------------------------------------------------------------------
	// Estructura FIFO: first-in-first-out. Conocida como cola. Tenemos diferentes implementaciones:
	// * ArrayBlockingQueue: 	debemos construirla con una capacidad inicial fija.
	// * LinkedBlockingQueue: 	su capacidad inicial es Integer.MAX_VALUE (el mayor valor entero que podemos 
	//							almacenar en un int).
	// * PriorityBlockingQueue: permite ordenar los elementos seg�n prioridad.

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
		// Colecci�n compartida
		BlockingQueue<Integer> q = new ArrayBlockingQueue<>(20);
		
		// Productores y consumidores
		ExecutorService executor = Executors.newCachedThreadPool();
		
		executor.submit(new ProductorBlockingQueue(q));
		executor.submit(new ProductorBlockingQueue(q));
		executor.submit(new ConsumidorBlockingQueue(q));
	}

}
