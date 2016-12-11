/*
 *
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * imitations under the License.
 *
 */

package org.mingle.orange.java.concurrent.state.protectedmethod;

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
