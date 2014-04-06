package App;



import java.util.Observable;
import java.util.Observer;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

import capteur.Capteur;
import Controler.CtrlMouse;
import Model.Mdl;
import View.Frame;

public class MainAPP extends JFrame implements Observer {
	
	private static final long serialVersionUID = 9167791876718956063L;

	private static GLCanvas cv =null;

	
	public MainAPP( Capteur tabCapteurFixe[], Capteur capteurMouvant, float longueur, float largeur){
		cv=new GLCanvas(); //CREATION D'UN CANVAS
		Mdl mdl= new Mdl(tabCapteurFixe,capteurMouvant);
		
		mdl.addObserver(this);
		Frame fr = new Frame (cv, mdl, longueur, largeur); //FENETRE
		cv.addGLEventListener(fr);
		setSize(600, 600);
		
		MyWindowAdapter winAdt = new MyWindowAdapter();
		addWindowListener(winAdt);
		CtrlMouse ctrlM = new CtrlMouse(mdl);
		add(cv);
		cv.addMouseMotionListener(ctrlM);
		cv.addMouseListener(ctrlM);		
	}

	//GETTER
	public static GLCanvas getCv() {
		return cv;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.err.println("MISE A JOUR ECRAN");
		cv.repaint();
		
	}
}
