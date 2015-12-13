/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.livenessperformance;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

/**
 * 协作对象之间发生的死锁
 * 
 * @since 1.8
 * @author Mingle
 */
public class CooperatingDeadlock {
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
		 * 先获取Taxi的锁,再获取Dispatcher的锁
		 * 
		 * @param location
		 */
		public synchronized void setLocation(Point location) {
			this.location = location;
			if (location.equals(destination))
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
		 * 先获取Dispatcher的锁,再获取Taxi的锁
		 * 
		 * @return
		 */
		public synchronized Image getImage() {
			Image image = new Image();
			for (Taxi t : taxis)
				image.drawMarker(t.getLocation());
			return image;
		}
	}

	class Image {
		public void drawMarker(Point p) {
		}
	}
}
