package App;



import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;

import com.jogamp.newt.event.KeyEvent;

import capteur.Capteur;
import Controler.CtrlMouse;
import Model.Mdl;
import View.Frame;
import View.FrameMenu;

public class MainAPP extends JFrame implements Observer {
	
	private static final long serialVersionUID = 9167791876718956063L;

	private static GLCanvas cv =null;

	
	public MainAPP( Capteur tabCapteurFixe[], Capteur capteurMouvant, double longueur, double largeur){
		createMenu();
		
		cv=new GLCanvas(); //CREATION D'UN CANVAS
		Mdl mdl= new Mdl(tabCapteurFixe,capteurMouvant);
		
		mdl.addObserver(this);
		Frame fr = new Frame (cv, mdl, longueur, largeur); //FENETRE
		cv.addGLEventListener(fr);
		setSize(800, 600);
		
		
		
		MyWindowAdapter winAdt = new MyWindowAdapter();
		addWindowListener(winAdt);
		CtrlMouse ctrlM = new CtrlMouse(mdl);
		cv.addMouseMotionListener(ctrlM);
		cv.addMouseListener(ctrlM);		
		
		JPanel pan = new FrameMenu();
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
		menuItem = new JMenuItem("A text-only menu item",
		                         KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "This doesn't really do anything");
		menu.add(menuItem);

		menuItem = new JMenuItem("Both text and icon",
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
		menu.add(submenu);

		//Build second menu in the menu bar.
		menu = new JMenu("Another Menu");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
		        "This menu does nothing");
		menuBar.add(menu);

		this.setJMenuBar(menuBar);
	}
}
