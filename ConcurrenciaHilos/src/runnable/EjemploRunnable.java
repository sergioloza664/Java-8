package runnable;

public class EjemploRunnable {
	
	// RUNNABLE
	// ---------------------------------------------------------
	// * Es un interfaz que nos permite crear tareas para ser ejecutadas en hilos secundarios.
	// * Si nuestra clase ya hereda de otra, no puede heredar a la vez de Thread.
	// *  Runnable nos permite solventar esa dificultad.
	// * Thread tiene un constructor que recibe como argumento un runnable.
	//
	// Podemos utilizar las clases an�nimas o las expresiones lambda para crear c�digo m�s conciso.
	
	

	public static void main(String[] args) {
		runnableLanzamiento();
		System.out.println("");
		runnableAnonimo();

	}
	
	
	
	
	private static void runnableLanzamiento() {
		//Creaci�n y lanzamiento de PrimoRunnable
		//a trav�s de un thread.
		Thread t = new Thread(new PrimoRunnable(12345));
		t.start();
		
		//Otra forma de hacerlo, a trav�s de una 
		//instancia an�nima
		new Thread(new PrimoRunnable(1234567)).start();
	}
	
	
	
	
	
	private static void runnableAnonimo() {
		//Este trozo de c�digo puede ser muy com�n si
		//el proceso de lanzamiento del hilo est� dentro
		//de un manejador de eventos, como el click de un
		//bot�n.
		new Thread(new Runnable() {

		@Override
			public void run() {
				System.out.println("Hola desde Runnable+Thread!!");
						
			}
					
		}).start();
				
		//Este c�digo es como el anterior, pero aprovecha la potencia
		//de las expresiones lambda.
		new Thread(() -> System.out.print("Hola desde Lambda Runnable+Thread")).start();
	}

}
