package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.Border;

import Controler.CtrlMenu;
import Model.Mdl;

public class FrameMenu extends JPanel{
	
	private Mdl mdl;
	public boolean isActivated=false;
	private JTextField posX;
	private JTextField posY;
	private JComboBox orientation;
	
	private AbstractButton table;
	private AbstractButton teapot;
	private AbstractButton Sofa;
	private AbstractButton TV;
	private AbstractButton window;
	private AbstractButton music;
	private AbstractButton light;
	private AbstractButton littleLight;
	private AbstractButton meuble;
	
	public FrameMenu (Mdl m) {
		super(new BorderLayout());
		mdl = m;
	
		//Placer meuble
		JPanel panelMeuble = new JPanel(new BorderLayout());
		JPanel panelMeubleList = new JPanel (new GridLayout(8, 1));
		JPanel panelPositionMeuble = new JPanel(new GridLayout (3,1));
		
		posX = new JTextField();
		posY = new JTextField();
		orientation = new JComboBox();
		orientation.addItem("Horizontal");
		orientation.addItem("Vertical");
		
		panelPositionMeuble.add(posX);
		panelPositionMeuble.add(posY);
		panelPositionMeuble.add(orientation);
		Border borderMeuble = BorderFactory.createTitledBorder("Objet set");
		panelMeuble.setBorder(borderMeuble);
	    
		ButtonGroup groupMeuble = new ButtonGroup();
		
	    table = new JButton("Table");
	    panelMeubleList.add(table);
		groupMeuble.add(table);
		table.addMouseListener(new CtrlMenu(mdl, this));
		
		teapot = new JButton("Teapot");
	    panelMeubleList.add(teapot);
		groupMeuble.add(teapot);
		teapot.addMouseListener(new CtrlMenu(mdl, this));
		
		Sofa = new JButton("Sofa");
	    panelMeubleList.add(Sofa);
		groupMeuble.add(Sofa);
		Sofa.addMouseListener(new CtrlMenu(mdl, this));
		
		TV = new JButton("TV");
		panelMeubleList.add(TV);
		groupMeuble.add(TV);
		TV.addMouseListener(new CtrlMenu(mdl, this));
		
	    window = new JButton("Light");
	    panelMeubleList.add(window);
		groupMeuble.add(window);
		window.addMouseListener(new CtrlMenu(mdl, this));
		
		littleLight = new JButton("Little Light");
	    panelMeubleList.add(littleLight);
		groupMeuble.add(littleLight);
		littleLight.addMouseListener(new CtrlMenu(mdl, this));
		
		meuble = new JButton("Meuble");
		panelMeubleList.add(meuble);
		groupMeuble.add(meuble);
		meuble.addMouseListener(new CtrlMenu(mdl, this));
		
		music = new JButton("Music");
		panelMeubleList.add(music);
		groupMeuble.add(music);
		music.addMouseListener(new CtrlMenu(mdl, this));
	    
		panelMeuble.add(panelPositionMeuble, BorderLayout.NORTH);
		panelMeuble.add(panelMeubleList, BorderLayout.CENTER);
		this.add(panelMeuble, BorderLayout.CENTER);
	    
	    
	    //camera choice
  		JPanel panelCamera = new JPanel(new GridLayout(0, 1));
  		Border borderCamera = BorderFactory.createTitledBorder("Camera set");
  		panelCamera.setBorder(borderCamera);
  	    ButtonGroup groupCamera = new ButtonGroup();

  		    
		AbstractButton Camera1 = new JButton("Camera 1");
		panelCamera.add(Camera1);
		panelCamera.add(Camera1);
			
		AbstractButton Camera2 = new JButton("Camera 2");
		panelCamera.add(Camera2);
		groupCamera.add(Camera2);
		
		AbstractButton Camera3 = new JButton("Camera 3");
		panelCamera.add(Camera3);
		groupCamera.add(Camera3);

		
		Camera1.addMouseListener(new CtrlMenu(mdl, this));
		Camera2.addMouseListener(new CtrlMenu(mdl, this));
		Camera3.addMouseListener(new CtrlMenu(mdl, this));
  		    
  	    this.add(panelCamera, BorderLayout.SOUTH);
	    
	}
	

	
	
	public String getOrientation(){
		return orientation.getSelectedItem().toString();
	}
	
	public String getPosXField(){
		return posX.getText();
	}

	public String getPosYField(){
		return posY.getText();
	}
	
	public int getPosXFieldToInt() throws NumberFormatException{
		try{
			int tmp = Integer.parseInt(posX.getText());
			return tmp;
		} catch (Exception e){
			JOptionPane.showMessageDialog(this,"Invalid X position","Error",JOptionPane.ERROR_MESSAGE);
			throw new NumberFormatException ();
		}
	}

	public int getPosYFieldToInt() throws NumberFormatException {
		try{
			int tmp = Integer.parseInt(posY.getText());
			return tmp;
		} catch (Exception e){
			JOptionPane.showMessageDialog(this,"Invalid Y position","Error",JOptionPane.ERROR_MESSAGE);
			throw new NumberFormatException ();
		}
	}
}
