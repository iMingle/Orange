/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.speciality.concurrency;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 任务间使用管道进行输入输出
 *
 * @since 1.8
 * @author Mingle
 */
class Sender implements Runnable {
	private Random rand = new Random(47);
	private PipedWriter out = new PipedWriter();

	public PipedWriter getPipedWriter() {
		return out;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			while (true)
				for (char c = 'A'; c <= 'z'; c++) {
					out.write(c);
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				}
		} catch (IOException e) {
			System.out.println(e + " Sender write exception");
		} catch (InterruptedException e) {
			System.out.println(e + " Sender sleep interrupted");
		}
	}
	
}

class Receiver implements Runnable {
	private PipedReader in;
	
	public Receiver(Sender sender) throws IOException {
		in = new PipedReader(sender.getPipedWriter());
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			while (true) {
				System.out.print("Read: " + (char)in.read() + ", ");
			}
		} catch (IOException e) {
			System.out.println(e + " Receiver read exception");
		}
	}
	
}

public class PipedIO {

	public static void main(String[] args) throws Exception {
		Sender sender = new Sender();
		Receiver receiver = new Receiver(sender);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(sender);
		exec.execute(receiver);
		TimeUnit.SECONDS.sleep(4);
		exec.shutdownNow();
	}

}
