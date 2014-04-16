package Controler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.Mdl;

public class CtrlKeyboard implements KeyListener {
	private Mdl mdl;
	
	public CtrlKeyboard (Mdl m){
		mdl = m;
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("keyT "+e.getKeyCode());
		//pause();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println("keyP "+e.getKeyCode());
	
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("keyR "+e.getKeyCode());
		int code = e.getKeyCode();
		switch (code){
		case 37 : //gauche
			mdl.left();
			break;
		case 38 : //haut
			mdl.up();
			break;
		case 39 : //droite
			mdl.right();
			break;
		case 40 : //bas
			mdl.down();
			break;
		}
		//pause();
		
	}
	
	private void pause(){
	    try {
	      Thread.sleep(50);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	  } 

}
