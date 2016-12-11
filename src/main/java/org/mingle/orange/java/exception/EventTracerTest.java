package org.mingle.orange.java.exception;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;

/**
 *
 * @author Mingle
 */
public class EventTracerTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new EventTracerFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class EventTracerFrame extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -4033846728066390756L;

    public EventTracerFrame() {
        setTitle("EventTracerTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // add a slider and a button
        add(new JSlider(), BorderLayout.NORTH);
        add(new JButton("Test"), BorderLayout.SOUTH);

        // trap all events of components inside the frame
        EventTracer tracer = new EventTracer();
        tracer.add(this);
    }

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 400;
}
