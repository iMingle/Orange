/*
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
 * limitations under the License.
 */

package org.orange.java.concurrent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * shows animated bouncing ball.
 *
 * @author mingle
 * @date 2014年9月13日
 */
public class BounceThread {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new BounceFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

}

/**
 * a runnable that animates a bouncing ball.
 */
class BourceRunnable implements Runnable {
    private Ball ball;
    private Component component;
    private static final int STEPS = 1000;
    private static final int DELAY = 5;

    /**
     * @param ball
     * @param component
     */
    public BourceRunnable(Ball ball, Component component) {
        super();
        this.ball = ball;
        this.component = component;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < STEPS; i++) {
                ball.move(component.getBounds());
                component.repaint();
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
    }

}

/**
 * the frame with ball component and buttons.
 */
class BounceFrame extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1032733806982376494L;

    private BallComponent comp;
    private static final int DEFAULT_WIDTH = 450;
    private static final int DEFAULT_HEIGHT = 350;

    /**
     * Constructs the frame with the component for showing the bouncing ball and
     * Start and Close buttons
     */
    public BounceFrame() {
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setTitle("Bounce");

        comp = new BallComponent();
        this.add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        this.addButton(buttonPanel, "Start", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addBall();
            }

        });

        this.addButton(buttonPanel, "Close", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });

        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Adds a button to a container.
     *
     * @param c
     * @param title
     * @param listener
     */
    private void addButton(Container c, String title,
                           ActionListener listener) {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    /*
     * Adds a bouncing ball to the panel and starts a thread to make it bounce.
     */
    public void addBall() {
        Ball b = new Ball();
        comp.add(b);
        Runnable r = new BourceRunnable(b, comp);
        Thread t = new Thread(r);
        t.start();
    }
}
