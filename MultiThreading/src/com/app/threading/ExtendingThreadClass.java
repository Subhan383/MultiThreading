package com.app.threading;

class MyCounter extends Thread {
	private int threadNo;

	public MyCounter(int threadNo) {

		this.threadNo = threadNo;
	}

	@Override
	public void run() {
		countMe();
	}

	public void countMe() {
		for (int i = 1; i <= 9; i++) {

			try {
				sleep(500);
			} catch (InterruptedException e) {

			}
			System.out.println("The value of i is:" + i + "and the thread number is:" + threadNo);

		}
	}
}

public class ExtendingThreadClass {

	public static void main(String[] args) throws InterruptedException {
		MyCounter counter1 = new MyCounter(1);
		MyCounter counter2 = new MyCounter(2);

		long starttime = System.currentTimeMillis();
		counter1.start();
		System.out.println("******************");
		counter2.start();
		Thread.sleep(4520);
		long endtime = System.currentTimeMillis();

		System.out.println("Total time required to process:" + (endtime - starttime));

	}

}
