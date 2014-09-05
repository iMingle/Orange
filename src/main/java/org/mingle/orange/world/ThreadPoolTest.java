package org.mingle.orange.world;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

	public static void main(String[] args) {
		ExecutorService pool = Executors.newSingleThreadExecutor();
		
		pool.execute(new Runnable() {
			
			public void run() {
				System.out.println("Hello");
			}
		});
		
		pool.execute(new Runnable() {
			
			public void run() {
				System.out.println("World");
			}
		});
		
		pool.shutdown();
	}

}
