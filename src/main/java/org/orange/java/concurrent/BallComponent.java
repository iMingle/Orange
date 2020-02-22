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

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * The component that draws the balls.
 *
 * @author mingle
 * @date 2014年9月13日
 */
public class BallComponent extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -541334173689699317L;

    private ArrayList<Ball> balls = new ArrayList<Ball>();

    /**
     * add a ball to the component.
     *
     * @param b
     */
    public void add(Ball b) {
        balls.add(b);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Ball b : balls) {
            g2.fill(b.getShape());
        }
    }
}
