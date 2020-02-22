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

package org.orange.java.exception;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;

/**
 *
 * @author mingle
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
