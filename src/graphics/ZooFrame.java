package graphics;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

/**
 * This class builds the GUI of the animals
 * @author - Leah Brodsky
 * @author - Ziv Gimani
 */

public class ZooFrame extends JFrame {
	/**
	 * the main frame
	 */
	private static ZooFrame zooFrame = new ZooFrame();
	/**
	 * panel
	 */
	private ZooPanel zooPanel = ZooPanel.getZooPanel();
	/**+
	 * height and width of the frame
	 */
	public static final int FRAME_WIDTH = 1000;
	public static final int FRAME_HEIGHT = 700;

	/**
	 * constructor of the zoo frame
	 * build menu bar and add options:
	 * file:exit - close window
	 * Background: set background image - choose image
	 * 							green - green background
	 * 						     none - without background
	 * help - dialog with HOMEWORK 2 GUI
	 * add zoo panel to the main frame
	 *
	 */

	public ZooFrame() {
		setTitle("Zoo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		JMenuItem menuItem = new JMenuItem("Exit");
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		menu = new JMenu("Background");
		menuBar.add(menu);
		menuItem = new JMenuItem("Image");
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(zooFrame, "Choose a file", FileDialog.LOAD);
				fd.setDirectory("assignment2_pictures");
				fd.setFile("*.png;*.gif;*.jpg");
				fd.setVisible(true);
				String filename = fd.getFile();
				if (filename != null) {
					String path = fd.getDirectory() + fd.getFile();
					zooPanel.setBackgroundImage(path);
				}
			}
		});
		menuItem = new JMenuItem("Green");
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				zooPanel.setBackgroundGreen();
			}
		});
		menuItem = new JMenuItem("None");
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				zooPanel.setBackgroundNone();
			}
		});

		menu = new JMenu("Help");
		menuBar.add(menu);
		menuItem = new JMenuItem("Help");
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(zooFrame, "Home Work 2\nGUI");
			}
		});

		add(zooPanel);
	}

	/**
	 * main function that activate the frame
	 * @param args - arguments the main function get
	 */

	public static void main(String[] args){
    	zooFrame.setVisible(true);
    }

}
