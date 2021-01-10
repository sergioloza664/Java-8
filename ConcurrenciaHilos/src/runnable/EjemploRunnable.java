package runnable;

public class EjemploRunnable {
	
	// RUNNABLE
	// ---------------------------------------------------------
	// * Es un interfaz que nos permite crear tareas para ser ejecutadas en hilos secundarios.
	// * Si nuestra clase ya hereda de otra, no puede heredar a la vez de Thread.
	// *  Runnable nos permite solventar esa dificultad.
	// * Thread tiene un constructor que recibe como argumento un runnable.
	//
	// Podemos utilizar las clases anónimas o las expresiones lambda para crear código más conciso.
	
	

	public static void main(String[] args) {
		runnableLanzamiento();
		System.out.println("");
		runnableAnonimo();

	}
	
	
	
	
	private static void runnableLanzamiento() {
		//Creación y lanzamiento de PrimoRunnable
		//a través de un thread.
		Thread t = new Thread(new PrimoRunnable(12345));
		t.start();
		
		//Otra forma de hacerlo, a través de una 
		//instancia anónima
		new Thread(new PrimoRunnable(1234567)).start();
	}
	
	
	
	
	
	private static void runnableAnonimo() {
		//Este trozo de código puede ser muy común si
		//el proceso de lanzamiento del hilo está dentro
		//de un manejador de eventos, como el click de un
		//botón.
		new Thread(new Runnable() {

		@Override
			public void run() {
				System.out.println("Hola desde Runnable+Thread!!");
						
			}
					
		}).start();
				
		//Este código es como el anterior, pero aprovecha la potencia
		//de las expresiones lambda.
		new Thread(() -> System.out.print("Hola desde Lambda Runnable+Thread")).start();
	}

}
