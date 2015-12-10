/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.effectivejava;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 避免过度同步
 * 
 * @since 1.8
 * @author Mingle
 */
public class AvoidOverSynchronized {

	public static void main(String[] args) {
		ObservableSet<Integer> set = new ObservableSet<Integer>(new HashSet<>());
		set.addObserver(new SetObserver<Integer>() {

			@Override
			public void added(ObservableSet<Integer> set, Integer element) {
				System.out.println(element);
				if (element == 23)  {
//					set.removeObserver(this);	// ConcurrentModificationException
					ExecutorService executor = Executors.newSingleThreadExecutor();
					final SetObserver<Integer> observer = this;
					try {
						executor.submit(new Runnable() {
							
							@Override
							public void run() {
								set.removeObserver(observer);
							}
						}).get();
					} catch (InterruptedException e) {
						throw new AssertionError(e.getCause());
					} catch (ExecutionException e) {
						throw new AssertionError(e.getCause());
					} finally {
						executor.shutdown();
					}
				}
			}
		});
		
		for (int i = 0; i < 100; i++)
			set.add(i);
	}

}

interface SetObserver<E> {
	void added(ObservableSet<E> set, E element);
}

class ObservableSet<E> extends ForwardingSet<E> {

	public ObservableSet(Set<E> set) {
		super(set);
	}
	
//	private final List<SetObserver<E>> observers = new ArrayList<>();
	private final List<SetObserver<E>> observers = new CopyOnWriteArrayList<>();
	
	public void addObserver(SetObserver<E> observer) {
		synchronized (observers) {
			observers.add(observer);
		}
	}
	
	public boolean removeObserver(SetObserver<E> observer) {
		synchronized (observers) {
			return observers.remove(observer);
		}
	}
	
	private void notifyElementAdded(E element) {
		List<SetObserver<E>> snapshot = null;
		// 创建一份拷贝,可以使用CopyOnWriteArrayList
		synchronized (observers) {
			snapshot = new ArrayList<>(observers);
		}
		for (SetObserver<E> observer : snapshot)
			observer.added(this, element);
	}

	@Override
	public boolean add(E element) {
		boolean added = super.add(element);
		if (added)
			notifyElementAdded(element);
		return added;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean result = false;
		for (E element : c)
			result |= add(element);
		return result;
	}
}