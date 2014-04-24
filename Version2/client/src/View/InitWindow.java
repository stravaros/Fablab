package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.sun.org.apache.xpath.internal.FoundIndex;

import Capteur.Capteur;
import Controler.CtrlInitWindow;
import Model.Mdl;


public final class InitWindow extends JFrame { //fenetre initialisation
	private static final InitWindow initWindow = new InitWindow();
	private final JPanel panel;
	private static JFrame frame;
	private  Mdl mdl;
	private static JTextField jtfX;
	private static JTextField jtfY;
	private static JTextField jtfServer;


	


	private InitWindow() {
		panel = new JPanel(new GridLayout(0,2));
		frame = new JFrame("Initialisation detection");
		frame.setResizable(false);
		frame.setSize(new Dimension(400, 300));
		
		JPanel panelServer = new JPanel(new GridLayout(0,1));
		Border borderServer = BorderFactory.createTitledBorder("Server configuration");
		panelServer.setBorder(borderServer);

		jtfServer = new JTextField("IP serveur");
		panelServer.add(jtfServer);
		jtfServer.addMouseListener(new MouseListener (){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jtfServer.setText("");
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {				
			}
			@Override
			public void mouseExited(MouseEvent arg0) {				
			}
			@Override
			public void mousePressed(MouseEvent arg0) {				
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {				
			}	    	
	    });
		
		panel.add(panelServer);
		ButtonGroup buttonGroupServer = new ButtonGroup();
	    AbstractButton startButton = new JButton("Start");
	    buttonGroupServer.add(startButton);
	    panelServer.add(startButton);
	    startButton.addActionListener(new CtrlInitWindow(mdl, this));

	    
	    
		JPanel panelConfig = new JPanel(new GridLayout(0, 1));
		Border borderConfig = BorderFactory.createTitledBorder("Sensors Configuration");
		panelConfig.setBorder(borderConfig);
	    
	    //Placer camera
		//JPanel panel = new JPanel(new GridLayout(0, 1));
	    //Border border = BorderFactory.createTitledBorder("Sensor set");
	    // panelConfig.setBorder(border);
	    ButtonGroup group = new ButtonGroup();
	    
	    /*
	    JComboBox<String> listeCapteur = new JComboBox<String>();
	    for(int i = 0; i< mdl.getNbCapteurServeur(); i++) {
	    	listeCapteur.addItem("Capteur " +i);
	    }*/
	    
	    //POS X
	    jtfX = new JTextField("Position X");
	    panelConfig.add(jtfX);
	    jtfX.addMouseListener(new MouseListener (){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jtfX.setText("");
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {				
			}
			@Override
			public void mouseExited(MouseEvent arg0) {				
			}
			@Override
			public void mousePressed(MouseEvent arg0) {				
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {				
			}   	
	    });
	    
	    
	    //POS Y
	    jtfY = new JTextField("Position Y");
	    panelConfig.add(jtfY);
	    jtfY.addMouseListener(new MouseListener (){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jtfY.setText("");			
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}	    	
	    });


	    
	    AbstractButton abstract1 = new JButton("Create sensor");
	    panelConfig.add(abstract1);
	    group.add(abstract1);
	    abstract1.addActionListener(new CtrlInitWindow(mdl, this));
	    frame.add(panelConfig, BorderLayout.CENTER);
	    
	    
	    
		panel.add(panelConfig);
		
	    JSeparator sp = new JSeparator(SwingConstants.VERTICAL);
	    panel.add(sp);		
		
		JPanel panelStatus = new JPanel(new GridLayout(1, 1));
		Border borderStatus = BorderFactory.createTitledBorder("Status");
		panelStatus.setBorder(borderStatus);
	    ButtonGroup Status = new ButtonGroup();
	    JTextArea text = new JTextArea();
	    panelStatus.add(text);
	    text.setEditable(false);
	    text.append("initialisation...");
		panel.add(panelStatus);
	
		frame.add(panel);
	}

	
	public  InitWindow getInstance(Mdl m) {
		mdl = m;
		frame.setVisible(true);
		return initWindow;
	}

	
	public int getSensorX(){
		return Integer.parseInt(jtfX.getText());
	}

	public int getSensorY() {
		return Integer.parseInt(jtfY.getText());
	}


	public JTextField getJtfX() {
		return jtfX;
	}


	public JTextField getJtfY() {
		return jtfY;
	}
	
	public static JTextField getJtfServer() {
		return jtfServer;
	}
}