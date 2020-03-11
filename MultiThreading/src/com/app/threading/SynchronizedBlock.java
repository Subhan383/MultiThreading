package com.app.threading;

class Threaded {
	public void bracket() {
		Object lock="lock";
		synchronized (lock) {
			for (int i = 1; i <= 10; i++) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (i <= 5) {
					System.out.print("[");
				} else {
					System.out.println("]");
				}
			}
			System.out.println();
		}
		
		for (int j = 1; j < 10; j++) {
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static class SynchronizedBlock {

		public static void main(String[] args) {
			Threaded threaded = new Threaded();
			new Thread(new Runnable() {

				@Override
				public void run() {
					long starttime = System.currentTimeMillis();
					for (int i = 1; i <= 5; i++) {
						threaded.bracket();
					}
					long endtime = System.currentTimeMillis();
					System.out.println("Total time required to process thread 1:" + (endtime - starttime));
				}

			}).start();
			new Thread(new Runnable() {

				@Override
				public void run() {
					long starttime = System.currentTimeMillis();
					for (int i = 1; i <= 5; i++) {
						threaded.bracket();
					}
					long endtime = System.currentTimeMillis();
					System.out.println("Total time required to process thread 2:" + (endtime - starttime));
				}

			}).start();

		}
	}
}
