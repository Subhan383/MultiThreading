package com.app.threading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {

	public static void main(String[] args) {
		Lock lock1 = new ReentrantLock();
		Lock lock2 = new ReentrantLock();
		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				boolean flagLock1 = false;
				boolean flagLock2 = false;
				boolean doneFlag1 = false;
				boolean doneFlag2 = false;
				while (true) {
					try {
						if (!flagLock1) {
							flagLock1 = lock1.tryLock();
						}
						if (!flagLock2) {
							flagLock2 = lock2.tryLock();
						}
					} finally {
						if (flagLock1 && !doneFlag1) {
							System.out.println("Inside thread1 on lock 1");
							lock1.unlock();
							doneFlag1 = true;
						}

						if (flagLock2 && !doneFlag2) {
							System.out.println("Inside thread1 on lock 2");
							lock2.unlock();
							doneFlag2 = true;
						}
						if (flagLock1 && flagLock2) {
							break;
						}
					}
				}

			}

		});

		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {

				boolean flagLock1 = false;
				boolean flagLock2 = false;
				boolean doneFlag1 = false;
				boolean doneFlag2 = false;
				while (true) {
					try {
						if (!flagLock1) {
							flagLock1 = lock1.tryLock();
						}
						if (!flagLock2) {
							flagLock2 = lock2.tryLock();
						}
					} finally {
						if (flagLock1 && !doneFlag1) {
							System.out.println("Inside thread2 on lock 1");
							lock1.unlock();
							doneFlag1 = true;
						}

						if (flagLock2 && !doneFlag2) {
							System.out.println("Inside thread2 on lock 2");
							lock2.unlock();
							doneFlag2 = true;
						}
						if (flagLock1 && flagLock2) {
							break;
						}
					}
				}

			}

		});

		thread1.start();
		thread2.start();

		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		System.out.println("Inside main method");
	}

}
