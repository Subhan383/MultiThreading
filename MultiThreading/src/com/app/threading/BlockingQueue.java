package com.app.threading;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueue {
	public static int counter = 0;

	public static void main(String[] args) {

		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
		Producer produce = new Producer(queue);
		Thread producerThread = new Thread(produce);
		producerThread.start();

		Consumer consume = new Consumer(queue);
		Thread consumerThread = new Thread(consume);
		consumerThread.start();

	}

}

class Producer extends Thread {
	private ArrayBlockingQueue<Integer> queue;

	public Producer(ArrayBlockingQueue<Integer> queue) {

		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				queue.put(BlockingQueue.counter++);
				System.out.println("Value added in the queue is:" + BlockingQueue.counter);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Consumer extends Thread {
	private ArrayBlockingQueue<Integer> queue;

	public Consumer(ArrayBlockingQueue<Integer> queue) {

		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(2000);
				queue.put(BlockingQueue.counter--);
				System.out.println("Value removed from the queue is:" + BlockingQueue.counter);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
