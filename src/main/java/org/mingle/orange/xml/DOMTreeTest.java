/**
 * Copyright (c) 2015, Mingle. All rights reserved.
 */
package org.mingle.orange.xml;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.event.TreeModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CDATASection;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * This program displays an XML document as a tree.
 * @author <a href="mailto:jinminglei@yeah.net">mingle</a>
 * @version 1.0
 */
public class DOMTreeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame frame = new DOMTreeFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}

/**
 * This frame contains a tree that displays the contents of an XML document.
 */
class DOMTreeFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6893222649477614471L;
	
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 400;
	private DocumentBuilder builder;

	public DOMTreeFrame() {
		this.setTitle("DOMTreeTest");
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");
		openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});
		fileMenu.add(openItem);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu.add(exitItem);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		this.setJMenuBar(menuBar);
	}
	
	/**
	 * Open a file and load the document.
	 */
	public void openFile() {
		JFileChooser chooser = new JFileChooser(new File("."));
		chooser.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return "XML files";
			}
			
			@Override
			public boolean accept(File f) {
				return f.isDirectory() | f.getName().toLowerCase().endsWith("xml");
			}
		});
		
		int r = chooser.showOpenDialog(this);
		if (r != JFileChooser.APPROVE_OPTION) return;
		final File file = chooser.getSelectedFile();
		
		new SwingWorker<Document, Void>() {

			@Override
			protected Document doInBackground() throws Exception {
				if (null == builder) {
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					builder = factory.newDocumentBuilder();
				}
				return builder.parse(file);
			}

			/* (non-Javadoc)
			 * @see javax.swing.SwingWorker#done()
			 */
			@Override
			protected void done() {
				try {
					Document doc = this.get();
					JTree tree = new JTree(new DOMTreeModel(doc));
					tree.setCellRenderer(new DOMTreeCellRenderer());
					
					setContentPane(new JScrollPane(tree));
					validate();
				} catch (InterruptedException | ExecutionException e) {
					JOptionPane.showMessageDialog(DOMTreeFrame.this, e);
				}
			}
			
			
		}.execute();;
	}
}

/**
 * This tree model describes the tree structure of an XML document.
 */
class DOMTreeModel implements TreeModel {
	private Document doc;
	
	/**
	 * @param doc
	 */
	public DOMTreeModel(Document doc) {
		super();
		this.doc = doc;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getRoot()
	 */
	@Override
	public Object getRoot() {
		return doc.getDocumentElement();
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getChild(java.lang.Object, int)
	 */
	@Override
	public Object getChild(Object parent, int index) {
		Node node = (Node) parent;
		NodeList list = node.getChildNodes();
		return list.item(index);
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getChildCount(java.lang.Object)
	 */
	@Override
	public int getChildCount(Object parent) {
		Node node = (Node) parent;
		NodeList list = node.getChildNodes();
		return list.getLength();
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#isLeaf(java.lang.Object)
	 */
	@Override
	public boolean isLeaf(Object node) {
		return this.getChildCount(node) == 0;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getIndexOfChild(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int getIndexOfChild(Object parent, Object child) {
		Node node = (Node) parent;
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			if (this.getChild(node, i) == child) return i;
		}
		return -1;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#valueForPathChanged(javax.swing.tree.TreePath, java.lang.Object)
	 */
	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#addTreeModelListener(javax.swing.event.TreeModelListener)
	 */
	@Override
	public void addTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#removeTreeModelListener(javax.swing.event.TreeModelListener)
	 */
	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
		
	}
	
}

/**
 * This class renders an XML node.
 */
class DOMTreeCellRenderer extends DefaultTreeCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5648862236969396527L;

	/* (non-Javadoc)
	 * @see javax.swing.tree.DefaultTreeCellRenderer#getTreeCellRendererComponent(javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int, boolean)
	 */
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		Node node = (Node) value;
		if (node instanceof Element) return elementPanel((Element)node);
		
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);
		if (node instanceof CharacterData) this.setText(this.characterString((CharacterData)node));
		else this.setText(node.getClass() + ":" + node.toString());
		return this;
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	private JPanel elementPanel(Element e) {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Element: " + e.getTagName()));
		final NamedNodeMap map = e.getAttributes();
		panel.add(new JTable(new AbstractTableModel() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1812354315570746703L;

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				return columnIndex == 0 ? map.item(rowIndex).getNodeName() : map.item(rowIndex).getNodeName();
			}
			
			@Override
			public int getRowCount() {
				return map.getLength();
			}
			
			@Override
			public int getColumnCount() {
				return 2;
			}
		}));
		return panel;
	}
	
	/**
	 * @param node
	 * @return
	 */
	private String characterString(CharacterData node) {
		StringBuilder builder = new StringBuilder(node.getData());
		for (int i = 0; i < builder.length(); i++) {
			if (builder.charAt(i) == '\r') {
				builder.replace(i, i + 1, "\\r");
				i++;
			} else if (builder.charAt(i) == '\n') {
				builder.replace(i, i + 1, "\\n");
				i++;
			} else if (builder.charAt(i) == '\t') {
				builder.replace(i, i + 1, "\\t");
				i++;
			}
		}
		
		if (node instanceof CDATASection) builder.insert(0, "CDATASection: ");
		else if (node instanceof Text) builder.insert(0, "Text: ");
		else if (node instanceof Comment) builder.insert(0, "Comment: ");
		
		return builder.toString();
	}
	
}