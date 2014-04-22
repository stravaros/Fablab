package Controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Mdl;
import View.FrameMenu;
import View.InitWindow;



public class CtrlInitWindow implements ActionListener {
	private Mdl mdl;
	private InitWindow iw;
	
	public CtrlInitWindow(Mdl m, InitWindow initWindow) {
		mdl = m;
		iw = initWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("coucou "+e.getActionCommand());

		switch (e.getActionCommand()){
		case  "Create sensor" :
			System.out.println("coucou "+e.getActionCommand());
			mdl.addCapteur( iw.getSensorX(), iw.getSensorY());

			break;
		case  "Position X" :
			iw.getJtfX().setText("");
			System.out.println("coucou "+e.getActionCommand());
			break;
		
		case  "Position Y" :
			System.out.println("coucou "+e.getActionCommand());
			break;
		
	}
	
	}
}

