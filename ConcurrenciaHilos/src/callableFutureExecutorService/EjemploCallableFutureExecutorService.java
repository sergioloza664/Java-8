package callableFutureExecutorService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EjemploCallableFutureExecutorService {
	
	// CALLABLE
	// ----------------------------------------------------
	// * Runnable no permite devolver ning�n dato a otro hilo.
	// * Complejos mecanismos de sincronizaci�n para devolver valores(Runnable).
	// * Callable es, b�sicamente, un Runnable que puede devolver un valor.
	
	// FUTURE
	// -----------------------------------------------------------------
	// * Para recoger el valor de un Callable, necesitamos un Future.
	// * Se trata de otra interfaz, que representa el resultado de una computaci�n as�ncrona.
	// * Nos permite operaciones como comprobar si la computaci�n ha terminado, cancelarla, 
	//   esperar a que termine, tomar el valor si ha terminado, � 
	// * El m�todo get nos permite obtener el valor de la ejecuci�n de un Callable.
	// * Existen diversas formas de obtener un Future a partir de un Callable, 
	//   pero siempre nos invita al uso de ejecutores.
	
	// EJECUTORES (ExecutorService,...)
	// ---------------------------------------------------------------------------------
	// * Para aplicaciones peque�as: el programador lanza los hilos de ejecuci�n seg�n su necesidad.
	// * para aplicaciones grandes:  separar la administraci�n de los hilos del resto de la aplicaci�n (Ejecutores).
	//
	// TIPOS DE EJECUTORES (cada uno hereda del anterior):
	// 	* Executor:					soporta el lanzamiento de nuevas tareas, bajo demanda. 
	// 	* ExecutorService:			a�ade a la anterior caracter�sticas que permiten administrar  
	//								el ciclo de vida. Este ser� el m�s utilizado.
	// 	* ScheduledExecutorService:	a�ade a la anterior la posibilidad de ejecutar tareas peri�dicas.
	
	// EXECUTORSERVICE
	// ----------------------------------------------------------------------------------------
	// * submit() acepta instancias de Runnable o Callable
	// * M�todos para finalizar el propio ejecutor (esten las tareas terminadas on no lo esten).
	// * Creaci�n a partir de un pool de hilos que haga uso de worker threads: hilos que son reutilizables, 
	//	 minimizando la sobrecarga de la creaci�n de hilos nuevos.
	// * Podemos finalizar el ejecutor con el m�todo shutdown.
	
	// POOLS DE HILOS
	// --------------------------------------------------------------------------------------
	// SINGLE:
	// 	* Con un solo hilo de ejecuci�n disponible.
	// 	* Si le pedimos (submit) m�s de una tarea a la vez, las pone en cola.
	// FIXED:
	// 	* Indicamos, en el momento de su creaci�n, el n�mero de hilos.
	// 	* Si dispone de n hilos, y enviamos n+1 tareas, las pone en cola.
	// CACHED:
	// 	* Crea hilos conforme enviamos tareas
	// 	* Reutiliza los hilos cuyas tareas han finalizado, para ejecutar tareas nuevas.
	// CREACI�N DE POOLS DE HILOS (m�todos de ejecutores):
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
				
		//Si nos fijamos bien, la aplicaci�n no finaliza cuando ha mostrado
		//por pantalla los dos primos. El ExecutorService se queda "disponible"
		//Podemos intentar "apagarlo" cuando ambas tareas hayan terminado
		
		//Este m�todo espera a que terminen las tareas enviadas, y posteriormente
		//destruye el ejecutor.
		executor.shutdown();
	}
	
	
	
	
	private static void fixedPool() {
		// Creamos nuestro ejecutor
		ExecutorService executor = Executors.newFixedThreadPool(3);

		// Como tenemos varios hilos de ejecuci�n, se solapa la ejecuci�n
		// de ambas tareas
		Future<Long> f1 = executor.submit(new PrimoCallable(1234585676));
		Future<Long> f2 = executor.submit(new PrimoCallable(123));
		try {
			System.out.println(f1.get());
			System.out.println(f2.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		// Este m�todo espera a que terminen las tareas enviadas, y posteriormente
		// destruye el ejecutor.
		executor.shutdown();
	}

}
