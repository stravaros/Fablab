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
	private JComboBox choice;
	public boolean isActivated=false;
	private JTextField posX;
	private JTextField posY;
	
	public FrameMenu (Mdl m) {
		super(new GridLayout(4,1));
		
		mdl = m;
		
		
		JPanel panel = new JPanel(new GridLayout(0,1));
	
	  //Placer meuble
		JPanel panelMeuble = new JPanel(new GridLayout(3,2));
		posX = new JTextField();
		posY = new JTextField();
		panelMeuble.add(posX);
		panelMeuble.add(posY);
		
		Border borderMeuble = BorderFactory.createTitledBorder("Objet set");
		panelMeuble.setBorder(borderMeuble);
	    ButtonGroup groupMeuble = new ButtonGroup();
		    
	/*    String[] items = {"table", "little table", "television", "light"};
	    choice = new JComboBox<>(items);
	    panelMeuble.add(choice);
	    
	    //POS X
		JTextField meubleX = new JTextField("Valeur par défaut");
		panelMeuble.add(meubleX);
		    
		//POS Y
		JTextField meubleY = new JTextField("Valeur par défaut");
		panelMeuble.add(meubleY);
	    JSeparator meubleSp = new JSeparator(SwingConstants.HORIZONTAL);
	    panel.add(meubleSp);*/
		    
	    AbstractButton table = new JButton("Table");
	    panelMeuble.add(table);
		groupMeuble.add(table);
		table.addMouseListener(new CtrlMenu(mdl, this));
		
		
		AbstractButton TV = new JButton("TV");
	    panelMeuble.add(TV);
		groupMeuble.add(TV);
		TV.addMouseListener(new CtrlMenu(mdl, this));
	   // this.add(panelMeuble, BorderLayout.CENTER);
		
	/*	JSeparator meubleSp = new JSeparator(SwingConstants.HORIZONTAL);
		panelMeuble.add(meubleSp);*/
		
		
	    AbstractButton window = new JButton("Window");
	    panelMeuble.add(window);
		groupMeuble.add(window);
		window.addMouseListener(new CtrlMenu(mdl, this));
		
		
		
		
		AbstractButton meuble = new JButton("Meuble");
	    panelMeuble.add(meuble);
		groupMeuble.add(meuble);
		meuble.addMouseListener(new CtrlMenu(mdl, this));
	    
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
  		    
  	    this.add(panelCamera, BorderLayout.CENTER);
	    
	}
	
	
	public String getCurrentElement(){
		return choice.getSelectedItem().toString();
	}
	
	public String getPosXField(){
		return posX.getText();
	}

	public String getPosYField(){
		return posY.getText();
	}
	
	public int getPosXFieldToInt(){
		try{
			int tmp = Integer.parseInt(posX.getText());
			return tmp;
		} catch (Exception e){
			JOptionPane.showMessageDialog(this,"Invalid X position","Error",JOptionPane.ERROR_MESSAGE);
		}
		return -1;
	}

	public int getPosYFieldToInt(){
		try{
			int tmp = Integer.parseInt(posY.getText());
			return tmp;
		} catch (Exception e){
			JOptionPane.showMessageDialog(this,"Invalid Y position","Error",JOptionPane.ERROR_MESSAGE);
		}
		return -1;
	}
}
