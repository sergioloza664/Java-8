package callableFutureExecutorService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EjemploCallableFutureExecutorService {
	
	// CALLABLE
	// ----------------------------------------------------
	// * Runnable no permite devolver ningún dato a otro hilo.
	// * Complejos mecanismos de sincronización para devolver valores(Runnable).
	// * Callable es, básicamente, un Runnable que puede devolver un valor.
	
	// FUTURE
	// -----------------------------------------------------------------
	// * Para recoger el valor de un Callable, necesitamos un Future.
	// * Se trata de otra interfaz, que representa el resultado de una computación asíncrona.
	// * Nos permite operaciones como comprobar si la computación ha terminado, cancelarla, 
	//   esperar a que termine, tomar el valor si ha terminado, … 
	// * El método get nos permite obtener el valor de la ejecución de un Callable.
	// * Existen diversas formas de obtener un Future a partir de un Callable, 
	//   pero siempre nos invita al uso de ejecutores.
	
	// EJECUTORES (ExecutorService,...)
	// ---------------------------------------------------------------------------------
	// * Para aplicaciones pequeñas: el programador lanza los hilos de ejecución según su necesidad.
	// * para aplicaciones grandes:  separar la administración de los hilos del resto de la aplicación (Ejecutores).
	//
	// TIPOS DE EJECUTORES (cada uno hereda del anterior):
	// 	* Executor:					soporta el lanzamiento de nuevas tareas, bajo demanda. 
	// 	* ExecutorService:			añade a la anterior características que permiten administrar  
	//								el ciclo de vida. Este será el más utilizado.
	// 	* ScheduledExecutorService:	añade a la anterior la posibilidad de ejecutar tareas periódicas.
	
	// EXECUTORSERVICE
	// ----------------------------------------------------------------------------------------
	// * submit() acepta instancias de Runnable o Callable
	// * Métodos para finalizar el propio ejecutor (esten las tareas terminadas on no lo esten).
	// * Creación a partir de un pool de hilos que haga uso de worker threads: hilos que son reutilizables, 
	//	 minimizando la sobrecarga de la creación de hilos nuevos.
	// * Podemos finalizar el ejecutor con el método shutdown.
	
	// POOLS DE HILOS
	// --------------------------------------------------------------------------------------
	// SINGLE:
	// 	* Con un solo hilo de ejecución disponible.
	// 	* Si le pedimos (submit) más de una tarea a la vez, las pone en cola.
	// FIXED:
	// 	* Indicamos, en el momento de su creación, el número de hilos.
	// 	* Si dispone de n hilos, y enviamos n+1 tareas, las pone en cola.
	// CACHED:
	// 	* Crea hilos conforme enviamos tareas
	// 	* Reutiliza los hilos cuyas tareas han finalizado, para ejecutar tareas nuevas.
	// CREACIÓN DE POOLS DE HILOS (métodos de ejecutores):
	// 	* new SingleThreadExecutor():	crea un ejecutor de sipo single.
	// 	* newFixedThreadPool(int n):		crea un ejecutor de tipo fixed con n hilos disponibles.
	// 	* newCachedThreadPool():			crea un ejecutor de tipo cached.
	
	
	public static void main(String[] args) {
		singlePool();
		fixedPool();

	}
	
	
	
	
	private static void singlePool() {
		//Creamos nuestro ejecutor
		ExecutorService executor = Executors.newSingleThreadExecutor();
				
		//Como solo dispone de un hilo, si enviamos dos tareas, la segunda
		//se ejecuta al terminar la primera
		Future<Long> f1 = executor.submit(new PrimoCallable(1234585676));
		Future<Long> f2 = executor.submit(new PrimoCallable(123));
		try {
			System.out.println(f1.get());
			System.out.println(f2.get());
		} catch (InterruptedException | ExecutionException e) {			
			e.printStackTrace();
		}
				
		//Si nos fijamos bien, la aplicación no finaliza cuando ha mostrado
		//por pantalla los dos primos. El ExecutorService se queda "disponible"
		//Podemos intentar "apagarlo" cuando ambas tareas hayan terminado
		
		//Este método espera a que terminen las tareas enviadas, y posteriormente
		//destruye el ejecutor.
		executor.shutdown();
	}
	
	
	
	
	private static void fixedPool() {
		// Creamos nuestro ejecutor
		ExecutorService executor = Executors.newFixedThreadPool(3);

		// Como tenemos varios hilos de ejecución, se solapa la ejecución
		// de ambas tareas
		Future<Long> f1 = executor.submit(new PrimoCallable(1234585676));
		Future<Long> f2 = executor.submit(new PrimoCallable(123));
		try {
			System.out.println(f1.get());
			System.out.println(f2.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		// Este método espera a que terminen las tareas enviadas, y posteriormente
		// destruye el ejecutor.
		executor.shutdown();
	}

}
