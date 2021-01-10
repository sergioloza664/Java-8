package coleccionesConcurrentes;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class ProductorBlockingQueue implements Runnable{
	private final BlockingQueue<Integer> queue;

	public ProductorBlockingQueue(BlockingQueue<Integer> q) {
		this.queue = q;
	}

	@Override
	public void run() {
		Random r = new Random();

		try {
			while (true) {
				queue.put(r.nextInt(100));
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

	}
}
