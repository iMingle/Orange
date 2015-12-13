/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.livenessperformance;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

/**
 * 通过开放调用使协作对象之间不发生死锁
 * 
 * @since 1.8
 * @author Mingle
 */
public class CooperatingNoDeadlock {
	class Taxi {
		private Point location, destination;
		private final Dispatcher dispatcher;

		public Taxi(Dispatcher dispatcher) {
			this.dispatcher = dispatcher;
		}

		public synchronized Point getLocation() {
			return location;
		}

		/**
		 * 只锁定自己的状态,不锁定外部对象的状态
		 * 
		 * @param location
		 */
		public void setLocation(Point location) {
			boolean reachedDestination;
			synchronized (this) {
				this.location = location;
				reachedDestination = location.equals(destination);
			}
			if (reachedDestination)
				dispatcher.notifyAvailable(this);
		}

		public synchronized Point getDestination() {
			return destination;
		}

		public synchronized void setDestination(Point destination) {
			this.destination = destination;
		}
	}

	class Dispatcher {
		private final Set<Taxi> taxis;
		private final Set<Taxi> availableTaxis;

		public Dispatcher() {
			taxis = new HashSet<Taxi>();
			availableTaxis = new HashSet<Taxi>();
		}

		public synchronized void notifyAvailable(Taxi taxi) {
			availableTaxis.add(taxi);
		}

		/**
		 * 只锁定自己的状态,不锁定外部对象的状态
		 * 
		 * @return
		 */
		public Image getImage() {
			Set<Taxi> copy;
			synchronized (this) {
				copy = new HashSet<Taxi>(taxis);
			}
			Image image = new Image();
			for (Taxi t : copy)
				image.drawMarker(t.getLocation());
			return image;
		}
	}

	class Image {
		public void drawMarker(Point p) {
		}
	}
}
