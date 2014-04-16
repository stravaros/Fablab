package App;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

import com.jogamp.newt.event.KeyEvent;

import Capteur.Capteur;
import Controler.CtrlKeyboard;
import Controler.CtrlMouse;
import Model.Mdl;
import View.Frame;
import View.FrameMenu;

public class MainAPP extends JFrame implements Observer {
	
	private static final long serialVersionUID = 9167791876718956063L;

	private static GLCanvas cv =null;

	
	public MainAPP(){
		createMenu();
		
		cv=new GLCanvas(); //CREATION D'UN CANVAS
		Mdl mdl= new Mdl();
		
		mdl.addObserver(this);
		Frame fr = new Frame (cv, mdl); //FENETRE
		cv.addGLEventListener(fr);
		setSize(800, 600);
		
		
		
		MyWindowAdapter winAdt = new MyWindowAdapter();
		addWindowListener(winAdt);
		CtrlMouse ctrlM = new CtrlMouse(mdl);
		CtrlKeyboard ctrlK = new CtrlKeyboard(mdl);
		cv.addMouseMotionListener(ctrlM);
		cv.addMouseListener(ctrlM);		
		cv.addKeyListener(ctrlK);
		
		JPanel pan = new FrameMenu(mdl);
	//	add(cv);
		JSplitPane js = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pan, cv);
		
		//js.setDividerSize(100);
		js.setResizeWeight(0.3);
		add(js, BorderLayout.CENTER);
		js.setOneTouchExpandable(false);
	
		
	}

	//GETTER
	public static GLCanvas getCv() {
		return cv;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.err.println("MISE A JOUR ECRAN");
		cv.repaint();
		
	}
	
	public void createMenu (){
		//Where the GUI is created:
		JMenuBar menuBar;
		JMenu menu, submenu;
		JMenuItem menuItem;
		JRadioButtonMenuItem rbMenuItem;
		JCheckBoxMenuItem cbMenuItem;

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("A Menu");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		menuBar.add(menu);

		//a group of JMenuItems
		menuItem = new JMenuItem("New detection",
		                         KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "This doesn't really do anything");
		menuItem.addActionListener(new InitWindow());
		menu.add(menuItem);

		/*menuItem = new JMenuItem("Both text and icon",
		                         new ImageIcon("images/middle.gif"));
		menuItem.setMnemonic(KeyEvent.VK_B);
		menu.add(menuItem);

		menuItem = new JMenuItem(new ImageIcon("images/middle.gif"));
		menuItem.setMnemonic(KeyEvent.VK_D);
		menu.add(menuItem);

		//a group of radio button menu items
		menu.addSeparator();
		ButtonGroup group = new ButtonGroup();
		rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
		rbMenuItem.setSelected(true);
		rbMenuItem.setMnemonic(KeyEvent.VK_R);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Another one");
		rbMenuItem.setMnemonic(KeyEvent.VK_O);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		//a group of check box menu items
		menu.addSeparator();
		cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
		cbMenuItem.setMnemonic(KeyEvent.VK_C);
		menu.add(cbMenuItem);

		cbMenuItem = new JCheckBoxMenuItem("Another one");
		cbMenuItem.setMnemonic(KeyEvent.VK_H);
		menu.add(cbMenuItem);

		//a submenu
		menu.addSeparator();
		submenu = new JMenu("A submenu");
		submenu.setMnemonic(KeyEvent.VK_S);

		menuItem = new JMenuItem("An item in the submenu");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_2, ActionEvent.ALT_MASK));
		submenu.add(menuItem);

		menuItem = new JMenuItem("Another item");
		submenu.add(menuItem);
		menu.add(submenu);*/

		//Build second menu in the menu bar.
		menu = new JMenu("Help");
		menuItem = new JMenuItem ("About visualisation FabLab");
		menuItem.addActionListener(new AboutWindow());
		menu.add(menuItem);
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
		        "This menu does nothing");
		
		menuBar.add(menu);

		this.setJMenuBar(menuBar);
	}
	
	public class AboutWindow  implements ActionListener {
		ImageIcon grassIcon = new ImageIcon("ressources/logo_ensimag3.png"); 
		JPanel panel = new JPanel(new GridLayout(2,1));
		JFrame frame = new JFrame("About Visualisation FabLab");
		JLabel labelText = new JLabel();
		JLabel labelImage ;
		
		public AboutWindow() {
			frame.setResizable(false);
			frame.setSize(new Dimension(400, 300));
		}

		
		@Override
		public void actionPerformed(ActionEvent e) {
			labelImage = new JLabel(grassIcon);
			
			String text = "Projet Fablab réalisé par :\n A1,\n A2, \n A3\n, A4,\n\n Version :";

			
			panel.add(labelImage);
			panel.add(labelText);
			
			frame.add(panel);
			frame.pack();
			frame.setVisible(true);
		}

	}
	
	public class InitWindow  implements ActionListener {
		JPanel panel = new JPanel(new GridLayout(2,2));
		JFrame frame = new JFrame("Initialisation detection");

		
		public InitWindow() {
			frame.setResizable(false);
			frame.setSize(new Dimension(400, 300));
		}

		
		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel panelStatus = new JPanel(new GridLayout(0, 1));
			Border borderStatus = BorderFactory.createTitledBorder("Status");
			panelStatus.setBorder(borderStatus);
		    ButtonGroup Status = new ButtonGroup();
			panel.add(panelStatus);
			
			JPanel panelConfig = new JPanel(new GridLayout(0, 1));
			Border borderConfig = BorderFactory.createTitledBorder("Configuration");
			panelConfig.setBorder(borderConfig);
		    ButtonGroup Config = new ButtonGroup();
			panel.add(panelConfig);
			
			
			frame.add(panel);
			frame.setVisible(true);
		}

	}
}
