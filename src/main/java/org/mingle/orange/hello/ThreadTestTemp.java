package org.mingle.orange.hello;

public class ThreadTestTemp {
	
	public static int count = 0;
	private byte[] lock = new byte[0];
	
	public void printCount(String s) {
		synchronized (lock) {
			System.out.println(s + "count = " + count);
		}
	}

	public static void main(String[] args) {
		final ThreadTestTemp tt = new ThreadTestTemp();
		
		Runnable run1 = new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					count++;
					tt.printCount("t1");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		Thread t1 = new Thread(run1);
		t1.start();
		
		try {
			t1.wait();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Runnable run2 = new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					count--;
					tt.printCount("t2");
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		Thread t2 = new Thread(run2);
		t2.start();
		
		t1.notify();
	}
}
