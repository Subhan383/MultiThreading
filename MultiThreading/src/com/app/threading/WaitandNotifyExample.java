package com.app.threading;

public class WaitandNotifyExample {

	public static int balance = 0;

	public void withdraw(int amount) {
		synchronized (this) {
			if (balance <= 0) {
				try {
					System.out.println("Waiting for balance updation");
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				balance = balance - amount;

			}
		}
		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Withdrawed amount sucessfully and the current balance is:" + balance);
	}

	public void deposit(int amount) {
		System.out.println("Depositing the amount");
		balance = balance + amount;
		synchronized (this) {
			notify();
		}
	}

	public static void main(String[] args) {
		WaitandNotifyExample obj = new WaitandNotifyExample();
		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				obj.withdraw(10000);

			}

		});
		thread1.start();

		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				obj.deposit(100000);

			}

		});

		thread2.start();

	}

}
