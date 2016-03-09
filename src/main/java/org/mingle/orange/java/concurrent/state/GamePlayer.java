/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.concurrent.state;

/**
 * 通知
 * 
 * @since 1.0
 * @author Mingle
 */
public class GamePlayer implements Runnable {
    protected GamePlayer other;
    protected boolean myturn = false;

    protected synchronized void setOther(GamePlayer p) {
        other = p;
    }

    synchronized void giveTurn() { // called by other player
        myturn = true;
        notify(); // unblock thread
    }

    void releaseTurn() {
        GamePlayer p;
        synchronized (this) {
            myturn = false;
            p = other;
        }
        p.giveTurn(); // open call
    }

    synchronized void awaitTurn() throws InterruptedException {
        while (!myturn)
            wait();
    }

    void move() { /* ... perform one move ... */
    }

    @Override
    public void run() {
        try {
            for (;;) {
                awaitTurn();
                move();
                releaseTurn();
            }
        } catch (InterruptedException ie) {
        } // die
    }

    public static void main(String[] args) {
        GamePlayer one = new GamePlayer();
        GamePlayer two = new GamePlayer();
        one.setOther(two);
        two.setOther(one);
        one.giveTurn();
        new Thread(one).start();
        new Thread(two).start();
    }
}
