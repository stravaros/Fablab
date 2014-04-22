package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Capteur.Capteur;
import Model.Mdl;
import View.FrameMenu;

public class CtrlMenu implements ActionListener {
	private Mdl mdl;
	private FrameMenu fm;
	
	public CtrlMenu (Mdl m, FrameMenu f){
		mdl = m;
		fm = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("coucou "+e.getActionCommand());
		switch (e.getActionCommand()){
			
			case  "Create objet" :
				//System.out.println(fm.getCurrentElement());
				mdl.addTable(0, 0); //changer coord
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
		
	}
}
