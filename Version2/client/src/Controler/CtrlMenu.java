package Controler;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import Capteur.Capteur;
import EnvObjet.Laptop;
import EnvObjet.LittleLight;
import EnvObjet.Meuble;
import EnvObjet.Sofa;
import EnvObjet.TV;
//import EnvObjet.Meuble;
//import EnvObjet.TV;
import EnvObjet.Table;
import EnvObjet.Light;
import EnvObjet.Teapot;
//import EnvObjet.Window;
import Model.Mdl;
import View.FrameMenu;

public class CtrlMenu implements MouseListener {
	private Mdl mdl;
	private FrameMenu fm;
	
	public CtrlMenu (Mdl m, FrameMenu f){
		mdl = m;
		fm = f;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e)  {
		int X;
		int Y;
		String orientation;
		JButton button = ((JButton)(e.getSource()));
		System.out.println("coucou "+((JButton)(e.getSource())).getText());
				switch (button.getText()){
					case  "Table" :
							orientation = fm.getOrientation();
							mdl.setHasFloatingObject(true);
							mdl.setFloatingObject(new Table());
						break;
					case  "Sofa" :
							orientation = fm.getOrientation();
							mdl.setHasFloatingObject(true);
							mdl.setFloatingObject(new Sofa());
						break;
						
					case  "Light" :
							orientation = fm.getOrientation();
							mdl.setHasFloatingObject(true);
							mdl.setFloatingObject(new Light());
						break;
					case  "Little Light" :
							orientation = fm.getOrientation();
							mdl.setHasFloatingObject(true);
							mdl.setFloatingObject(new LittleLight());
						break;
						
					case  "Meuble" :
							orientation = fm.getOrientation();
							mdl.setHasFloatingObject(true);
							mdl.setFloatingObject(new Meuble());
						break;
					
					case  "Teapot" :
							orientation = fm.getOrientation();
							mdl.setHasFloatingObject(true);
							mdl.setFloatingObject(new Teapot());
						break;
							
							
					case  "TV" :
							orientation = fm.getOrientation();
							mdl.setHasFloatingObject(true);
							mdl.setFloatingObject(new TV());
						
						break;
							
					case  "Window" :
						System.out.println("Window");
						orientation = fm.getOrientation();
						mdl.getListObjet().add(new Light());
						break;
					case  "Laptop" :
						orientation = fm.getOrientation();
						mdl.setHasFloatingObject(true);
						mdl.setFloatingObject(new Laptop());
						break;
					case "Turn ON/OFF" :
						mdl.setTurnOnOff();
						if (mdl.getTurnOnOff()==true)
							button.setBackground(Color.GREEN);
						else
							button.setBackground(Color.RED);
						break;
					case  "Camera 1" :
						mdl.changeCamera(0);
						break;
					
					case  "Camera 2" :
						mdl.changeCamera(1);
						break;
					case  "Camera 3" :
						mdl.changeCamera(2);
						break;
				}
				mdl.notifyChanges("camera");
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("exit1 " +fm.isActivated);
		fm.isActivated = false;
		System.out.println("exit2 " +fm.isActivated);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
