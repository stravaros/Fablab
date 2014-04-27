package Model;

import java.util.ArrayList;
import java.util.Observable;

import Capteur.Capteur;
import Controler.CtrlMouse;
import EnvObjet.ObjetGen;
import EnvObjet.Table;

public class Mdl extends Observable {
	
	private float angleElevation;
	private float angleAzimuth;
	private float angleDirection;
	
	private float distance;
	private float hauteur;
	
	private int mouseX;
	private int mouseY;
	private int mouseXMax;
	private int mouseYMax;
	
	float vitesse;
	
	private Capteur capteurMouvant;
	private int nbCapteur;
	private int nbCapteurServeur = 3;
		
	int parametreAnimation=0;
	
	private ArrayList <Capteur> listCapteur;
	private ArrayList <ObjetGen> listObjet;
	
	public Mdl(){
		setAngleElevation(0.0f);
		setAngleAzimuth(0.0f);
		
		setVitesse(1);
		setDistance(50);
		setHauteur(0);
		setAngleDirection(0);
		
		setMouseX(0);
		setMouseY(0);
		setMouseXMax(0);
		setMouseYMax(0);
		listCapteur = new ArrayList <Capteur> ();
		listObjet = new ArrayList <ObjetGen> ();

	}
	

	private void setAngleDirection(int i) {
		angleDirection = i;
		
	}

	private void setHauteur(int i) {
		hauteur = i;
		
	}

	//QUAND ON BOUGE LA SOURIS EN CLIQUANT
	public void moveDragged (int newMouseX, int newMouseY, boolean buttonPressed []){
		if(buttonPressed[CtrlMouse.LEFT_BTN]){ //CLIQUE GAUCHE
			//System.err.println("modif elev et azim");
			angleElevation+=vitesse/5.0*(newMouseY-mouseY);
			angleAzimuth+=vitesse/5.0*(newMouseX-mouseX);
		}
		
		if(buttonPressed[CtrlMouse.MIDDLE_BTN]){ //CLIQUE CENTRE
			//System.err.println("modif dist");
			distance+=vitesse*(newMouseY-mouseY);
		}
		
		if(buttonPressed[CtrlMouse.LEFT_BTN] || buttonPressed[CtrlMouse.MIDDLE_BTN]){ //UN DES DEUX BOUTON
			mouseX=newMouseX;//ON MET A JOUR ET ON NOTIFIE
			mouseY=newMouseY;
			notifyChanges();
		}
		System.out.println("souris "+mouseX+" "+mouseY);
		
	}
	
	public void moveMouse(int newMouseX, int newMouseY, boolean[] buttonPressed) {
		// TODO Auto-generated method stub
		
	}
	
	public void drawTable (){
		
	}
	
	//quand on change la camera
	public void setSensor (){
		//il faut un attribut frameMenu pour choper les valeurs des champs pour la position
		notifyChanges();
	}
	
	//quand on change la camera
	public void createObjet (int id){
		//il faut un attribut frameMenu pour choper les valeurs des champs et le type de meuble
		notifyChanges();
	}
	
	//quand on change la camera
	public void changeCamera (int id){
		switch (id) {
		case 0:
			angleElevation = 0;
			angleAzimuth = 0;
			distance =50;
			angleDirection = 0;
			hauteur =0;
			break;
		case 1:
			angleElevation = -90.0f;
			angleAzimuth = 0;
			distance =15;
			hauteur = -3.0f;
			angleDirection = 0;
			break;
		case 2:
			angleElevation = -45.0f;
			angleAzimuth = 0;
			distance =60;
			hauteur = 5.0f;
			angleDirection = -45;
			break;
		}
		notifyChanges();
	}
	
	public void left (){
		angleDirection --;
		notifyChanges();
	}
	
	public void up (){
		distance --;
		notifyChanges();
	}
	
	public void right (){
		angleDirection ++;
		notifyChanges();
	}
	
	public void down (){
		distance ++;
		notifyChanges();
	}
	
	public void addCapteur (int posX, int posY){
		this.listCapteur.add(new Capteur(0, 0, posX, posY));
		notifyChanges();
	}
	
	public void addTable (int posX, int posY){
		listObjet.add(new Table( posX, posY));
		notifyChanges();
	}
	
	
	
	private void notifyChanges() {
		// TODO Auto-generated method stub
		//System.err.println("notification...");
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

	public ArrayList<Capteur> getTabCapteur() {
		return listCapteur;
	}


	public Capteur getCapteurMouvant() {
		return capteurMouvant;
	}

	public void setCapteurMouvant(Capteur capteurMouvant) {
		this.capteurMouvant = capteurMouvant;
	}

	public float getHauteur() {
		return hauteur;
	}

	public float getAngleDirection() {
		return angleDirection;
	}

	public int getNbCapteur() {
		return nbCapteur;
	}

	public void setNbCapteur(int nbCapteur) {
		this.nbCapteur = nbCapteur;
	}

	public int getNbCapteurServeur() {
		return nbCapteurServeur;
	}

	public void setNbCapteurServeur(int nbCapteurServeur) {
		this.nbCapteurServeur = nbCapteurServeur;
	}

	public void setCoordXY(double x, double y) {
			capteurMouvant.setCoordoneeX(x);
			capteurMouvant.setCoordoneeY(y);
			notifyChanges();
	}


	public void setMouseYMax(int i) {
		mouseYMax = i;
	}

	public void setMouseXMax(int i) {
		mouseXMax = i;
	}
	
	public ArrayList<ObjetGen> getListObjet(){
		return this.listObjet;
	}
	
}
