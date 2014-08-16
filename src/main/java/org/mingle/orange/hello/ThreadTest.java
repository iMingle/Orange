package org.mingle.orange.hello;

public class ThreadTest {
	
	public static int count = 0;
	
	public void printCount() {
		synchronized (this) {
			System.out.println("count = " + count);
		}
	}

	public static void main(String[] args) {
		final ThreadTest tt = new ThreadTest();
		Runnable run1 = new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					count++;
					tt.printCount();
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
		
		Runnable run2 = new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					count--;
					tt.printCount();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		Thread t2 = new Thread(run2);
		t2.start();
		
		Runnable run3 = new Runnable() {

			public void run() {
				for (int i = 0; i < 100; i++) {
					count++;
					tt.printCount();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		};
		
		Thread t3 = new Thread(run3);
		t3.start();
		
		Runnable run4 = new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					count--;
					tt.printCount();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		Thread t4 = new Thread(run4);
		t4.start();
		
	}
}
