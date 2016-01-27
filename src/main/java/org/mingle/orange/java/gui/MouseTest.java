/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.gui;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class MouseTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                MouseFrame frame = new MouseFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * a frame containing a panel for testing mouse operations
 */
class MouseFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 4378954794179554107L;
    
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    
    public MouseFrame() {
        this.setTitle("MouseTest");
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        MouseComponent component = new MouseComponent();
        this.add(component);
    }
}

/**
 * a component with mouse operations for adding and removing squares
 */
class MouseComponent extends JComponent {

    /**
     * 
     */
    private static final long serialVersionUID = -8194335680511239720L;
    
    private static final int SIDELENGTH = 10;
    private List<Rectangle2D> squares;
    private Rectangle2D current;
    
    /**
     * 
     */
    public MouseComponent() {
        squares = new ArrayList<Rectangle2D>();
        current = null;
        
        this.addMouseListener(new MouseHandler());
        this.addMouseMotionListener(new MouseMotionHandler());
    }
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        for (Rectangle2D r : squares) {
            g2.draw(r);
        }
    }
    
    /**
     * find the first square containing a point
     * @param point
     * @return
     */
    public Rectangle2D find(Point point) {
        for (Rectangle2D r : squares) {
            if (r.contains(point)) return r;
        }
        return null;
    }
    
    /**
     * add a square to the collection
     * @param point
     */
    public void add(Point point) {
        double x = point.getX();
        double y = point.getY();
        
        current = new Rectangle2D.Double(x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
        squares.add(current);
        this.repaint();
    }
    
    /**
     * @param current2
     */
    public void remove(Rectangle2D current2) {
        if (null == current2) return;
        if (current2 == current) current = null;
        squares.remove(current2);
        this.repaint();
    }
    
    private class MouseHandler extends MouseAdapter {
        /* (non-Javadoc)
         * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
         */
        @Override
        public void mousePressed(MouseEvent e) {
            // add a new square if the cursor isn't inside a square
            current = MouseComponent.this.find(e.getPoint());
            
            if (null == current) {
                MouseComponent.this.add(e.getPoint());
            }
        }
        
        /* (non-Javadoc)
         * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            // remove the current square if double clicks
            current = MouseComponent.this.find(e.getPoint());
            if (current != null && e.getClickCount() >= 2) {
                MouseComponent.this.remove(current);
            }
        }
    }
    
    private class MouseMotionHandler extends MouseMotionAdapter {
        /* (non-Javadoc)
         * @see java.awt.event.MouseMotionAdapter#mouseMoved(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseMoved(MouseEvent e) {
            // set the mouse cursor to crosshair if it is inside a square
            if (MouseComponent.this.find(e.getPoint()) == null) {
                MouseComponent.this.setCursor(Cursor.getDefaultCursor());
            } else {
                MouseComponent.this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        }
        
        /* (non-Javadoc)
         * @see java.awt.event.MouseMotionAdapter#mouseDragged(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            if (current != null) {
                int x = e.getX();
                int y = e.getY();
                
                current.setFrame(x - SIDELENGTH / 2, y - SIDELENGTH / 2, SIDELENGTH, SIDELENGTH);
                MouseComponent.this.repaint();
            }
        }
    }
}