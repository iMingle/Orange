/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @since 1.0
 * @author Mingle
 */
public class ActionTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ActionFrame frame = new ActionFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

}

class ActionFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -6126494399094578198L;
    
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    
    private JPanel panel;
    
    public ActionFrame() {
        this.setTitle("ActionTest");
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        panel = new JPanel();
        
        // define action
        Action yellowAction = new ColorAction("YELLOW", new ImageIcon(ActionFrame.class.getResource("/images/yellow-ball.gif")), Color.YELLOW);
        Action blueAction = new ColorAction("BLUE", new ImageIcon(ActionFrame.class.getResource("/images/blue-ball.gif")), Color.BLUE);
        Action redAction = new ColorAction("RED", new ImageIcon(ActionFrame.class.getResource("/images/red-ball.gif")), Color.RED);
        
        // add buttons for these actions
        panel.add(new JButton(yellowAction));
        panel.add(new JButton(blueAction));
        panel.add(new JButton(redAction));
        
        // add panel to frame
        this.add(panel);
        
        // associate the Y, B and R keys with names
        InputMap imap = panel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        imap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
        imap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
        imap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");
        
        // associate names with actions
        ActionMap amap = panel.getActionMap();
        amap.put("panel.yellow", yellowAction);
        amap.put("panel.blue", blueAction);
        amap.put("panel.red", redAction);
    }
    
    class ColorAction extends AbstractAction {
        
        /**
         * 
         */
        private static final long serialVersionUID = 8116031809918835371L;

        /**
         * 
         */
        public ColorAction(String label, Icon icon, Color color) {
            this.putValue(Action.NAME, label);
            this.putValue(Action.SMALL_ICON, icon);
            this.putValue("color", color);
            this.putValue(Action.SHORT_DESCRIPTION, "set panel color to " + label.toLowerCase());
        }

        /* (non-Javadoc)
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent e) {
            Color color = (Color) this.getValue("color");
            panel.setBackground(color);
        }
    }
}

