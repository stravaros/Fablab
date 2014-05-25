package View;

import java.awt.BorderLayout;
import java.awt.Color;
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
	private AbstractButton turnON;
	
	private AbstractButton table;
	private AbstractButton chaise;
	private AbstractButton teapot;
	private AbstractButton Sofa;
	private AbstractButton TV;
	private AbstractButton music;
	private AbstractButton light;
	private AbstractButton littleLight;
	private AbstractButton meuble;
	private AbstractButton laptop;
	
	public FrameMenu (Mdl m) {
		super(new BorderLayout());
		
		mdl = m;
	
		//Placer meuble
		JPanel panelMeuble = new JPanel(new BorderLayout());
		JPanel panelMeubleList = new JPanel (new GridLayout(10, 1));

		Border borderMeuble = BorderFactory.createTitledBorder("Objet set");
		panelMeuble.setBorder(borderMeuble);
	    
		ButtonGroup groupMeuble = new ButtonGroup();

	    table = new JButton("Table");
	    panelMeubleList.add(table);
		groupMeuble.add(table);
		table.addMouseListener(new CtrlMenu(mdl, this));
		
		chaise = new JButton("Chaise");
	    panelMeubleList.add(chaise);
		groupMeuble.add(chaise);
		chaise.addMouseListener(new CtrlMenu(mdl, this));
		
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
		
		light = new JButton("Light");
	    panelMeubleList.add(light);
		groupMeuble.add(light);
		light.addMouseListener(new CtrlMenu(mdl, this));
		
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
		
		laptop = new JButton("Laptop");
		panelMeubleList.add(laptop);
		groupMeuble.add(laptop);
		laptop.addMouseListener(new CtrlMenu(mdl, this));
	    
		//panelMeuble.add(panelPositionMeuble, BorderLayout.NORTH);
		turnON = new JButton ("Turn ON/OFF");
		turnON.addMouseListener(new CtrlMenu(mdl, this));
		turnON.setOpaque(true);
		turnON.setBackground(Color.GREEN);
		panelMeuble.add(panelMeubleList, BorderLayout.CENTER);
		this.add(turnON, BorderLayout.NORTH);
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

}
