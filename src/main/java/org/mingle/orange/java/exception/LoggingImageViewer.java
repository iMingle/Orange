/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.java.exception;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @since 1.8
 * @author Mingle
 */
public class LoggingImageViewer {

    /**
     * @param args
     */
    public static void main(String[] args) {
        if ((null == System.getProperty("java.util.logging.config.class")) && 
            (null == System.getProperty("java.util.logging.config.file"))) {
            try {
                Logger.getLogger("org.mingle.orange").setLevel(Level.ALL);
                final int LOG_ROTATION_COUNT = 10;
                Handler handler = new FileHandler("%h/LoggingImageViewer.log", 0, LOG_ROTATION_COUNT);
                Logger.getLogger("org.mingle.orange").addHandler(handler);
            } catch (IOException e) {
                Logger.getLogger("org.mingle.orange").log(Level.SEVERE, "Can't create log file handler", e);
            }
        }
        
        EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                Handler windowHandler = new WindowHandler();
                windowHandler.setLevel(Level.ALL);
                Logger.getLogger("org.mingle.orange").addHandler(windowHandler);
                
                JFrame frame = new ImageViewerFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                Logger.getLogger("org.mingle.orange").fine("Showing frame");
                frame.setVisible(true);
            }
        });
    }
}
/**
 * the frame that show the image
 */
class ImageViewerFrame extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 7168648373571152759L;
    
    private JLabel label;
    private static final Logger logger = Logger.getLogger("org.mingle.orange");
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;

    /**
     * 
     */
    public ImageViewerFrame() {
        logger.entering("ImageViewerFrame", "<init>");
        this.setTitle("LoggingImageViewer");
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        // set up menu bar
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        
        JMenuItem openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new FileOpenListener());
        
        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.fine("Exiting.");
                System.exit(0);
            }
        });
        
        label = new JLabel();
        this.add(label);
        logger.exiting("ImageViewerFrame", "<init>");
    }
    
    private class FileOpenListener implements ActionListener {

        /* (non-Javadoc)
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            logger.entering("ImageViewerFrame.FileOpenListener", "actionPerformed", e);
            
            // set up file chooser
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            
            // accept all files ending with .gif
            chooser.setFileFilter(new FileFilter() {
                
                @Override
                public String getDescription() {
                    return "GIF images";
                }
                
                @Override
                public boolean accept(File f) {
                    return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
                }
            });
            
            // show file chooser dialog
            int r = chooser.showOpenDialog(ImageViewerFrame.this);
            
            // if image file accepted, set it as icon of the label
            if (JFileChooser.APPROVE_OPTION == r) {
                String name = chooser.getSelectedFile().getPath();
                logger.log(Level.FINE, "Reading file {0}", name);
                label.setIcon(new ImageIcon(name));
            } else {
                logger.fine("File open dialog canceled.");
            }
            
            logger.exiting("ImageViewerFrame.FileOpenListener", "actionPerformed");
        }
    }
}

/**
 * A handler for displaying log record in a window.
 */
class WindowHandler extends StreamHandler {
    private JFrame frame;
    /**
     * 
     */
    public WindowHandler() {
        frame = new JFrame();
        final JTextArea output = new JTextArea();
        output.setEditable(false);
        frame.setSize(200, 200);
        frame.add(new JScrollPane(output));
        frame.setFocusableWindowState(false);
        frame.setVisible(true);
        this.setOutputStream(new OutputStream() {
            
            @Override
            public void write(int b) throws IOException {}
            
            /* (non-Javadoc)
             * @see java.io.OutputStream#write(byte[], int, int)
             */
            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                output.append(new String(b, off, len));
            }
        });
    }
    
    /* (non-Javadoc)
     * @see java.util.logging.StreamHandler#publish(java.util.logging.LogRecord)
     */
    @Override
    public void publish(LogRecord record) {
        if (!this.frame.isVisible()) return;
        super.publish(record);
        flush();
    }
}