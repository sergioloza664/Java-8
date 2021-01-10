package coleccionesConcurrentes;

import java.util.Random;

public class ProductorWaitNotify implements Runnable{
	private ColaWaitNotify cola;

    public ProductorWaitNotify(ColaWaitNotify cola) {
        this.cola = cola;
    }

    public void run() {
        String importantInfo[] = {
            "Esta es la frase 1",
            "Esta es la frase 2",
            "Esta es la frase 3",
            "Esta es la frase 4"
        };
        Random random = new Random();

        for (int i = 0; i < importantInfo.length; i++) {
            cola.poner(importantInfo[i]);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {}
        }
        cola.poner("HECHO");
    }
}
