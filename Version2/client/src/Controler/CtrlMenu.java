package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import Capteur.Capteur;
import EnvObjet.Meuble;
import EnvObjet.TV;
import EnvObjet.Table;
import EnvObjet.Window;
import Model.Mdl;
import View.FrameMenu;

public class CtrlMenu implements MouseListener {
	private Mdl mdl;
	private FrameMenu fm;
	
	public CtrlMenu (Mdl m, FrameMenu f){
		mdl = m;
		fm = f;
	}

	//@Override
	/*public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("coucou "+e.getActionCommand());
		switch (e.getActionCommand()){
			
			case  "Create objet" :
				//System.out.println(fm.getCurrentElement());
				mdl.addTable(0, 0); //changer coord
				fm.isActivated = true;
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
		
	}*/

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("coucou "+((JButton)(e.getSource())).getText());
				switch (((JButton)(e.getSource())).getText()){
					case  "Table" :
					System.out.println("table");
					mdl.getListObjet().add(new Table(fm.getPosXFieldToInt(), fm.getPosYFieldToInt()));
						break;
						
					case  "Meuble" :
						System.out.println("Meuble");
						mdl.getListObjet().add(new Meuble(fm.getPosXFieldToInt(),fm.getPosYFieldToInt()));

							break;
							
					case  "TV" :
						System.out.println("TV");
						mdl.getListObjet().add(new TV(fm.getPosXFieldToInt(), fm.getPosYFieldToInt()));

							break;
							
					case  "Window" :
						System.out.println("Window");
						mdl.getListObjet().add(new Window(fm.getPosXFieldToInt(), fm.getPosYFieldToInt()));
						
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
				mdl.notifyChanges();
		
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
