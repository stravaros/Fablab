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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.sun.org.apache.xpath.internal.FoundIndex;

import Controler.CtrlInitWindow;
import Model.Mdl;


public final class InitWindow extends JFrame { //fenetre initialisation
	private static final InitWindow initWindow = new InitWindow();
	private final JPanel panel;
	private static JFrame frame;
	private static Mdl mdl;
	private static JTextField jtfX;
	private static JTextField jtfY;


	
	private InitWindow() {
		panel = new JPanel(new GridLayout(1,2));
		frame = new JFrame("Initialisation detection");
		frame.setResizable(false);
		frame.setSize(new Dimension(400, 300));
		JPanel panelConfig = new JPanel(new GridLayout(0, 1));
		Border borderConfig = BorderFactory.createTitledBorder("Configuration");
		panelConfig.setBorder(borderConfig);
	    ButtonGroup Config = new ButtonGroup();
	    
	  //Placer camera
		//JPanel panel = new JPanel(new GridLayout(0, 1));
	    Border border = BorderFactory.createTitledBorder("Sensor set");
	    panelConfig.setBorder(border);
	    ButtonGroup group = new ButtonGroup();
	    
	    //POS X
	    jtfX = new JTextField("Position X");
	    panelConfig.add(jtfX);
	    jtfX.addMouseListener(new MouseListener (){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				jtfX.setText("");
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
	    
	    //POS Y
	    jtfY = new JTextField("Position Y");
	    panelConfig.add(jtfY);
	    jtfY.addMouseListener(new MouseListener (){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				jtfY.setText("");
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });

	    JSeparator sp = new JSeparator(SwingConstants.HORIZONTAL);
	    panelConfig.add(sp);
	    
	    AbstractButton abstract1 = new JButton("Create sensor");
	    panelConfig.add(abstract1);
	    group.add(abstract1);
	    abstract1.addActionListener(new CtrlInitWindow(mdl, this));
	    frame.add(panelConfig, BorderLayout.CENTER);
	    
	    
	    
		panel.add(panelConfig);
		
		
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

	
	public static InitWindow getInstance(Mdl m) {
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
	

}