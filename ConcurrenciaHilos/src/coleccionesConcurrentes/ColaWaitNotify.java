package coleccionesConcurrentes;

public class ColaWaitNotify {
	// Mensaje enviado por el productor al consumidor
    private String mensage;
    
    // Verdadero si el consumidor debe esperar a que el productor envíe un mensaje
    // Falso si el productor debe esperar a que el consumidor tome el mensaje
    private boolean vacia = true;

    public synchronized String tomar() {
    	
    	// Esperamos a que esté disponible
        while (vacia) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        // Modificamos el estado
        vacia = true;
        // Notificamos al productor que el estado ha cambiado
        notifyAll();
        return mensage;
    }

    public synchronized void poner(String message) {
        // Esperamos a que el mensaje haya sido leído
        while (!vacia) {
            try { 
                wait();
            } catch (InterruptedException e) {}
        }
        // Cambiamos el estado
        vacia = false;
        // Almacenamos el mensaje
        this.mensage = message;
              
        // Notificamos al consumidor que el estado ha cambiado.
        notifyAll();
    }
}
