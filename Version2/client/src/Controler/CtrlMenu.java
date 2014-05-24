package Controler;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import Capteur.Capteur;
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
						if (e.getButton()==MouseEvent.BUTTON3){
							orientation = fm.getOrientation();
							mdl.setHasFloatingObject(true);
							mdl.setFloatingObject(new Table(orientation));
						}else{
							System.out.println("table");
							X = fm.getPosXFieldToInt();
							Y = fm.getPosYFieldToInt();
							orientation = fm.getOrientation();
							mdl.getListObjet().add(new Table(X, Y, orientation) );
						}
						break;
					case  "Sofa" :
						if (e.getButton()==MouseEvent.BUTTON3){
							orientation = fm.getOrientation();
							mdl.setHasFloatingObject(true);
							mdl.setFloatingObject(new Sofa(orientation));
						}else{
							System.out.println("light");
							X = fm.getPosXFieldToInt();
							Y = fm.getPosYFieldToInt();
							orientation = fm.getOrientation();
							mdl.getListObjet().add(new Sofa(X, Y, orientation));
						}
						break;
						
					case  "Light" :
						if (e.getButton()==MouseEvent.BUTTON3){
							orientation = fm.getOrientation();
							mdl.setHasFloatingObject(true);
							mdl.setFloatingObject(new Light(orientation));
						}else{
							System.out.println("light");
							X = fm.getPosXFieldToInt();
							Y = fm.getPosYFieldToInt();
							orientation = fm.getOrientation();
							mdl.getListObjet().add(new Light(X, Y, orientation));
						}
						break;
					case  "Little Light" :
						if (e.getButton()==MouseEvent.BUTTON3){
							orientation = fm.getOrientation();
							mdl.setHasFloatingObject(true);
							mdl.setFloatingObject(new LittleLight(orientation));
						}else{
							System.out.println("light");
							X = fm.getPosXFieldToInt();
							Y = fm.getPosYFieldToInt();
							orientation = fm.getOrientation();
							mdl.getListObjet().add(new Light(X, Y, orientation));
						}
						break;
						
					case  "Meuble" :
						if (e.getButton()==MouseEvent.BUTTON3){
							orientation = fm.getOrientation();
							mdl.setHasFloatingObject(true);
							mdl.setFloatingObject(new Meuble(orientation));
						}else{
							System.out.println("Meuble");
							X = fm.getPosXFieldToInt();
							Y = fm.getPosYFieldToInt();
							orientation = fm.getOrientation();
							mdl.getListObjet().add(new Meuble(X, Y, orientation));
						}
						break;
					
					case  "Teapot" :
						if (e.getButton()==MouseEvent.BUTTON3){
							orientation = fm.getOrientation();
							mdl.setHasFloatingObject(true);
							mdl.setFloatingObject(new Teapot(orientation));
						}else{
							System.out.println("light");
							X = fm.getPosXFieldToInt();
							Y = fm.getPosYFieldToInt();
							orientation = fm.getOrientation();
							mdl.getListObjet().add(new Teapot(X, Y, orientation));
						}
						break;
							
							
					case  "TV" :
						if (e.getButton()==MouseEvent.BUTTON3){
							orientation = fm.getOrientation();
							mdl.setHasFloatingObject(true);
							mdl.setFloatingObject(new TV(orientation));
						}else{
							System.out.println("TV");
							X = fm.getPosXFieldToInt();
							Y = fm.getPosYFieldToInt();
							orientation = fm.getOrientation();
							mdl.getListObjet().add(new TV(X, Y, orientation));
						}
						break;
							
					case  "Window" :
						System.out.println("Window");
						X = fm.getPosXFieldToInt();
						Y = fm.getPosYFieldToInt();
						orientation = fm.getOrientation();
						mdl.getListObjet().add(new Light(X, Y, orientation));
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
