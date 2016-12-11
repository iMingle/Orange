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

package org.mingle.orange.java.exception;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author mingle
 */
public class RobotTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                // make frame with a button panel

                ButtonFrame frame = new ButtonFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                // attach a robot to the screen device

                GraphicsEnvironment environment = GraphicsEnvironment
                        .getLocalGraphicsEnvironment();
                GraphicsDevice screen = environment.getDefaultScreenDevice();

                try {
                    Robot robot = new Robot(screen);
                    runTest(robot);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Runs a sample test procedure
     * 
     * @param robot
     *            the robot attached to the screen device
     */
    public static void runTest(Robot robot) {
        // simulate a space bar press
         robot.keyPress(' ');
        robot.keyRelease(' ');

        // simulate a tab key followed by a space
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
         robot.keyPress(' ');
        robot.keyRelease(' ');

        // simulate a mouse click over the rightmost button
        robot.delay(2000);
        robot.mouseMove(200, 50);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);

        // capture the screen and show the resulting image
        robot.delay(2000);
        BufferedImage image = robot.createScreenCapture(new Rectangle(0, 0,
                1366, 768));

        ImageFrame frame = new ImageFrame(image);
        frame.setVisible(true);
    }
}

/**
 * A frame to display a captured image
 */
class ImageFrame extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 7465157418591905981L;

    /**
     * @param image the image to display
     */
    public ImageFrame(Image image) {
        setTitle("Capture");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JLabel label = new JLabel(new ImageIcon(image));
        add(label);
    }

    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 350;
}
