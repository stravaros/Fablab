package Model;

import java.util.Observable;

import capteur.Capteur;
import Controler.CtrlMouse;

public class Mdl extends Observable {
	
	private float angleElevation;
	private float angleAzimuth;
	
	private float distance;
	
	private int mouseX;
	private int mouseY;
	
	float vitesse;
	
	private Capteur tabCapteurFixe[];
	private Capteur tabCapteurMouvant[];
	
	int parametreAnimation=0;
	
	public Mdl(Capteur tabCapteurFixe[], Capteur[] tabCapteurMouvant){
		setAngleElevation(30.0f);
		setAngleAzimuth(30.0f);
		
		setVitesse(1);
		setDistance(50);
		
		setMouseX(0);
		setMouseY(0);
		this.setTabCapteurFixe(tabCapteurFixe);
		this.setTabCapteurMouvant(tabCapteurMouvant);
	}
	
	//QUAND ON BOUGE LA SOURIS EN CLIQUANT
	public void moveDragged (int newMouseX, int newMouseY, boolean buttonPressed []){
		if(buttonPressed[CtrlMouse.LEFT_BTN]){ //CLIQUE GAUCHE
			System.err.println("modif elev et azim");
			angleElevation+=vitesse/5.0*(newMouseY-mouseY);
			angleAzimuth+=vitesse/5.0*(newMouseX-mouseX);
		}
		
		if(buttonPressed[CtrlMouse.MIDDLE_BTN]){ //CLIQUE CENTRE
			System.err.println("modif dist");
			distance+=vitesse*(newMouseY-mouseY);
		}
		
		if(buttonPressed[CtrlMouse.LEFT_BTN] || buttonPressed[CtrlMouse.MIDDLE_BTN]){ //UN DES DEUX BOUTON
			mouseX=newMouseX;//ON MET A JOUR ET ON NOTIFIE
			mouseY=newMouseY;
			notifyChanges();
		}
		
	}
	
	public void moveMouse(int newMouseX, int newMouseY, boolean[] buttonPressed) {
		// TODO Auto-generated method stub
		
	}
	
	
	private void notifyChanges() {
		// TODO Auto-generated method stub
		System.err.println("notification...");
		setChanged();
		notifyObservers();
	}
	


	//GETTER
	public float getAngleElevation() {
		return angleElevation;
	}
	public float getAngleAzimuth() {
		return angleAzimuth;
	}
	public float getDistance() {
		return distance;
	}
	public int getMouseX() {
		return mouseX;
	}
	public int getMouseY() {
		return mouseY;
	}
	public float getVitesse() {
		return vitesse;
	}
	public int getParametreAnimation() {
		return parametreAnimation;
	}
	
	//SETTER
	public void setAngleElevation(float angleElevation) {
		this.angleElevation = angleElevation;
	}
	public void setAngleAzimuth(float angleAzimuth) {
		this.angleAzimuth = angleAzimuth;
	}
	public void setDistance(float distance) {
		this.distance = distance;
	}
	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}
	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}
	public void setVitesse(float vitesse) {
		this.vitesse = vitesse;
	}
	public void setParametreAnimation(int parametreAnimation) {
		this.parametreAnimation = parametreAnimation;
	}

	public void setCapteur(int numeroCapteur, int coordonneX, int coordonneY){
		
	}

	public Capteur[] getTabCapteurFixe() {
		return tabCapteurFixe;
	}

	public void setTabCapteurFixe(Capteur tabCapteurFixe[]) {
		this.tabCapteurFixe = tabCapteurFixe;
	}

	public Capteur[] getTabCapteurMouvant() {
		return tabCapteurMouvant;
	}

	public void setTabCapteurMouvant(Capteur tabCapteurMouvant[]) {
		this.tabCapteurMouvant = tabCapteurMouvant;
	}


	
	
}
