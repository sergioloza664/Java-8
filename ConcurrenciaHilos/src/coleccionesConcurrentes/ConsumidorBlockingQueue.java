package coleccionesConcurrentes;

import java.util.concurrent.BlockingQueue;

public class ConsumidorBlockingQueue implements Runnable{
	private final BlockingQueue<Integer> queue;

	public ConsumidorBlockingQueue(BlockingQueue<Integer> q) {
		this.queue = q;
	}

	@Override
	public void run() {

		try {
			while (true) {
				System.out.println(queue.take());
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

	}
}
