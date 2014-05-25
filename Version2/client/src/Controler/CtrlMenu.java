package Controler;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import Capteur.Capteur;
import EnvObjet.Chaise;
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
		JButton button = ((JButton)(e.getSource()));
		System.out.println("Button "+((JButton)(e.getSource())).getText());
		switch (button.getText()){
		case  "Table" :
			mdl.setHasFloatingObject(true);
			mdl.setFloatingObject(new Table());
			break;
		case  "Chaise" :
			mdl.setHasFloatingObject(true);
			mdl.setFloatingObject(new Chaise());
			break;
		case  "Sofa" :
			mdl.setHasFloatingObject(true);
			mdl.setFloatingObject(new Sofa());
			break;
		case  "Light" :
			mdl.setHasFloatingObject(true);
			mdl.setFloatingObject(new Light());
			break;
		case  "Little Light" :
			mdl.setHasFloatingObject(true);
			mdl.setFloatingObject(new LittleLight());
			break;
		case  "Meuble" :
			mdl.setHasFloatingObject(true);
			mdl.setFloatingObject(new Meuble());
			break;
		case  "Teapot" :
			mdl.setHasFloatingObject(true);
			mdl.setFloatingObject(new Teapot());
			break;
		case  "TV" :
			mdl.setHasFloatingObject(true);
			mdl.setFloatingObject(new TV());
			break;
		case  "Window" :
			mdl.getListObjet().add(new Light());
			break;
		case  "Laptop" :
			mdl.setHasFloatingObject(true);
			mdl.setFloatingObject(new Laptop());
			break;
		case "Turn ON/OFF" :
			mdl.setTurnOnOff();
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
