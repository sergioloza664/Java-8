package thread;

public class EjemploThread {
	
	// SE RECOMIENDA HACER USO DE RUNNABLE PARA TENER DISPONIBLE LA POSIBILIDAD DE EXTENDER DE OTRA CLASE
	
	// METODOS BASICOS
	//--------------------------------------------------------------------------------
	// public void run(): 	contiene el código que queremos ejecutar en el hilo. 
	//					  	No se debe invocar nunca directamente.
	// public void start(): lanza la ejecución del hilo.
	// Thread.sleep(1000):	pausa la ejecucion del hilo, en este caso 1 segundo.
	
	// CICLO DE VIDA DE UN THREAD
	// -------------------------------------------------------------------------------------------------
	/* 1 Se llama al constructor de Thread para crear el nuevo hilo.
	 * 2 Se llama al método start para designarlo como ejecutable
	 * 3 El planificador lo ejecuta en cuanto el procesador está disponible.
	 * 4 El hilo puede pasar a bloqueado por diferentes razones, y no vuelve hasta que pasa de nuevo a ejectuable.
	 * 5 Si se utiliza el método wait, se pone en estado de espera, y permanece ahí hasta que se ejecuta notify o notifyAll.
	 * 6 El hilo termina cuando finaliza la ejecución de su método run.
	 */
	

	public static void main(String[] args) {
		
		//Las ejecuciones de los 3 hilos se pueden intercalar
		//Podemos "comprobarlo" en la salida de la consola
		
		PrimoThread pt01 = new PrimoThread(1234567);
		pt01.start();
		
		PrimoThread pt02 = new PrimoThread(23456789);
		pt02.start();
		
		PrimoThread pt03 = new PrimoThread(34567891);
		pt03.start();


	}

}
