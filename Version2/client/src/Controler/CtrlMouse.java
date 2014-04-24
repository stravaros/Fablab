package Controler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Model.Mdl;

public class CtrlMouse implements MouseListener, MouseMotionListener{
	private Mdl mdl;	
	
	public final static int LEFT_BTN=0;
	public final static int MIDDLE_BTN=1;
	public final static int RIGHT_BTN=2;
	
	private boolean buttonPressed[]={false, false, false};
	
	public CtrlMouse(Mdl m){
		mdl=m;
	}

	//IMPLEMENTE LES METHODES DE MOUSELISTENER ET MOUSEMOTIONLISTENER
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.err.println("MouseMoved");
		mdl.moveDragged(arg0.getX(), arg0.getY(), buttonPressed);
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.err.println("MouseMoved");
		mdl.moveMouse(arg0.getX(), arg0.getY(), buttonPressed);
	//	System.out.println(arg0.getX()+" "+ arg0.getY());
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	//QUAND ON CLIQUE
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.err.println("Mouse Pressed : "+arg0.getX()+" "+arg0.getY());
		if (arg0.getButton()==MouseEvent.BUTTON1)
			buttonPressed[LEFT_BTN] =true;
		if (arg0.getButton()==MouseEvent.BUTTON2)
			buttonPressed[MIDDLE_BTN] =true;
		if (arg0.getButton()==MouseEvent.BUTTON3)
			buttonPressed[RIGHT_BTN] =true;
		
		mdl.setMouseX(arg0.getX());
		mdl.setMouseY(arg0.getY());
		
	}

	//QUAND ON RELACHE
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.err.println("Mouse Pressed : "+arg0.getX()+" "+arg0.getY());
		if (arg0.getButton()==MouseEvent.BUTTON1)
			buttonPressed[LEFT_BTN] =false;
		if (arg0.getButton()==MouseEvent.BUTTON2)
			buttonPressed[MIDDLE_BTN] =false;
		if (arg0.getButton()==MouseEvent.BUTTON3)
			buttonPressed[RIGHT_BTN] =false;
		
	}
		

}
