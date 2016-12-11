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

package org.mingle.orange.java.gui;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author mingle
 */
public class ImageTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ImageFrame frame = new ImageFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

}

/**
 * A frame with an image component
 */
class ImageFrame extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -1687664252613579093L;
    
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    public ImageFrame() {
        setTitle("ImageTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // add component to frame

        ImageComponent component = new ImageComponent();
        add(component);
    }
}

/**
 * A component that displays a tiled image
 */
class ImageComponent extends JComponent {
    /**
     * 
     */
    private static final long serialVersionUID = -3644076776952333651L;
    
    private Image image;

    public ImageComponent() {
        // acquire the image
        try {
            image = ImageIO.read(new File(
                    "src/main/resources/images/blue-ball.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        if (image == null)
            return;

        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);

        // draw the image in the upper-left corner

        g.drawImage(image, 0, 0, null);
        // tile the image across the component

        for (int i = 0; i * imageWidth <= getWidth(); i++)
            for (int j = 0; j * imageHeight <= getHeight(); j++)
                if (i + j > 0)
                    g.copyArea(0, 0, imageWidth, imageHeight, i * imageWidth, j
                            * imageHeight);
    }
}
