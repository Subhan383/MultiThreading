package com.app.threading;

import java.util.Random;

class MyCounter2 implements Runnable {
	private int threadNo;

	public MyCounter2(int threadNo) {

		this.threadNo = threadNo;
	}

	public int getThreadNo() {
		return threadNo;
	}

	@Override
	public void run() {
		Random random = new Random();
		for (int i = 1; i <= 9; i++) {
			try {
				Thread.sleep(random.nextInt(500));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Thread value is:" + i + "Thread number is:" + threadNo);

		}
	}
}

public class RunnableInterfaceExample {

	public static void main(String[] args) throws InterruptedException {
		
		/*Thread thread1=new Thread(new MyCounter2(1));
		Thread thread2=new Thread(new MyCounter2(2));
		
		thread1.start();
		thread2.start();*/
		
		Thread thread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				for(int i=1;i<=10;i++) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};
					System.out.println(i);

			}
			}
			
		});
		
		thread.start();
	}
}
