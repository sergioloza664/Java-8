package coleccionesConcurrentes;

import java.util.Random;

public class ConsumidorWaitNotify implements Runnable{
	 private ColaWaitNotify cola;

	    public ConsumidorWaitNotify(ColaWaitNotify cola) {
	        this.cola = cola;
	    }

	    public void run() {
	        Random random = new Random();
	        for (String mensaje = cola.tomar();  ! mensaje.equals("HECHO"); mensaje = cola.tomar()) {
	            System.out.format("MENSAJE RECIBIDO: %s%n", mensaje);
	            try {
	                Thread.sleep(random.nextInt(5000));
	            } catch (InterruptedException e) {}
	        }
	    }
	
}
