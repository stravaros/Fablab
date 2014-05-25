package Model;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.Semaphore;

import View.InitWindow;
import Capteur.Capteur;
import Client.ClientToServer;
import Controler.CtrlMouse;
import EnvObjet.ObjetGen;
import EnvObjet.Table;

public class Mdl extends Observable {

	private boolean connected = false;
	private ClientToServer cl;
	private Thread thread;

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

	int parametreAnimation = 0;

	private ArrayList<Capteur> listCapteur;
	private ArrayList<ObjetGen> listObjet;
	
	private ObjetGen floatingObject;
	private boolean hasFloatingObject =false;
	private int angleElevationForFloatingObject = 0;
	private int angleAzimuthForFloatingObject = 0;
	private int distanceForFloatingObject = 50;
	private int angleDirectionForFloatingObject = 0;
	private int hauteurForFloatingObject = 0;
	private boolean isOnOff = false;

	public Mdl() {
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
		listCapteur = new ArrayList<Capteur>();
		listObjet = new ArrayList<ObjetGen>();
		capteurMouvant = new Capteur(3);

	}

	private void setAngleDirection(int i) {
		angleDirection = i;

	}

	private void setHauteur(int i) {
		hauteur = i;

	}

	// QUAND ON BOUGE LA SOURIS EN CLIQUANT
	public void moveDragged(int newMouseX, int newMouseY,
			boolean buttonPressed[]) {
		if (buttonPressed[CtrlMouse.LEFT_BTN]) { // CLIQUE GAUCHE
			angleElevation += vitesse / 5.0 * (newMouseY - mouseY);
			angleAzimuth += vitesse / 5.0 * (newMouseX - mouseX);
			if (angleElevation < -90)
				angleElevation = -90;
		}

		if (buttonPressed[CtrlMouse.MIDDLE_BTN]) { // CLIQUE CENTRE
			distance += vitesse * (newMouseY - mouseY);
		}

		if (buttonPressed[CtrlMouse.LEFT_BTN]
				|| buttonPressed[CtrlMouse.MIDDLE_BTN]) { // UN DES DEUX BOUTON
			mouseX = newMouseX;// ON MET A JOUR ET ON NOTIFIE
			mouseY = newMouseY;
			notifyChanges("camera");
		}
		//System.out.println("souris " + mouseX + " " + mouseY);

	}

	public void moveMouse(int newMouseX, int newMouseY, boolean[] buttonPressed) {
		if (hasFloatingObject){
			mouseX = newMouseX;// ON MET A JOUR ET ON NOTIFIE
			mouseY = newMouseY;
			notifyChanges("camera");
		}
	}

	public void drawTable() {

	}

	// quand on change la camera
	public void setSensor() {
		// il faut un attribut frameMenu pour choper les valeurs des champs pour
		// la position
		notifyChanges("camera");
	}

	// quand on change la camera
	public void createObjet(int id) {
		// il faut un attribut frameMenu pour choper les valeurs des champs et
		// le type de meuble
		notifyChanges("camera");
	}

	// quand on change la camera
	public void changeCamera(int id) {
		switch (id) {
		case 0:
			angleElevation = 0;
			angleAzimuth = 0;
			distance = 50;
			angleDirection = 0;
			hauteur = 0;
			break;
		case 1:
			angleElevation = -90.0f;
			angleAzimuth = 0;
			distance = 15;
			hauteur = -3.0f;
			angleDirection = 0;
			break;
		case 2:
			angleElevation = -45.0f;
			angleAzimuth = 0;
			distance = 60;
			hauteur = 5.0f;
			angleDirection = -45;
			break;
		}
		notifyChanges("camera");
	}

	public void left() {
		angleDirection--;
		notifyChanges("camera");
	}

	public void up() {
		distance--;
		notifyChanges("camera");
	}

	public void right() {
		angleDirection++;
		notifyChanges("camera");
	}

	public void down() {
		distance++;
		notifyChanges("camera");
	}

	public void addCapteur(int posX, int posY) {
		if (posX != -10000 && posY != -10000) {
			this.listCapteur.add(new Capteur(0, posX, posY));
			notifyChanges("addCapteur");
		} else {
			notifyChanges("pbAddCapteur");
		}
	}

	public void notifyChanges(String s) {
		setChanged();
		notifyObservers(s);
	}

	// GETTER
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
	

	public int getAngleElevationForFloatingObject() {
		return angleElevationForFloatingObject;
	}

	public void setAngleElevationForFloatingObject(
			int angleElevationForFloatingObject) {
		this.angleElevationForFloatingObject = angleElevationForFloatingObject;
	}

	public int getAngleAzimuthForFloatingObject() {
		return angleAzimuthForFloatingObject;
	}

	public void setAngleAzimuthForFloatingObject(int angleAzimuthForFloatingObject) {
		this.angleAzimuthForFloatingObject = angleAzimuthForFloatingObject;
	}

	public int getDistanceForFloatingObject() {
		return distanceForFloatingObject;
	}

	public void setDistanceForFloatingObject(int distanceForFloatingObject) {
		this.distanceForFloatingObject = distanceForFloatingObject;
	}

	public int getAngleDirectionForFloatingObject() {
		return angleDirectionForFloatingObject;
	}

	public void setAngleDirectionForFloatingObject(
			int angleDirectionForFloatingObject) {
		this.angleDirectionForFloatingObject = angleDirectionForFloatingObject;
	}

	public int getHauteurForFloatingObject() {
		return hauteurForFloatingObject;
	}

	public void setHauteurForFloatingObject(int hauteurForFloatingObject) {
		this.hauteurForFloatingObject = hauteurForFloatingObject;
	}

	// SETTER
	public void setAngleElevation(float angleElevation) {
		if (angleElevation > -90)
			this.angleElevation = angleElevation;
		else
			this.angleElevation = -90;
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

	public void setCapteur(int numeroCapteur, int coordonneX, int coordonneY) {

	}

	public ArrayList<Capteur> getTabCapteur() {
		return listCapteur;
	}

	public Capteur getCapteurMouvant() {
		return capteurMouvant;
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
		notifyChanges("nbCapteurServeur");
	}

	public void setCoordXY(double x, double y) {
		capteurMouvant.setCoordoneeX(x);
		capteurMouvant.setCoordoneeY(y);
		notifyChanges("camera");
	}

	public void setMouseYMax(int i) {
		mouseYMax = i;
	}

	public void setMouseXMax(int i) {
		mouseXMax = i;
	}

	public ArrayList<ObjetGen> getListObjet() {
		return this.listObjet;
	}

	public ArrayList<Capteur> getListCapteur() {
		return listCapteur;
	}
	
	
	
	public ObjetGen getFloatingObject() {
		return floatingObject;
	}

	public void setFloatingObject(ObjetGen floatingObject) {
		this.floatingObject = floatingObject;
	}
	

	public boolean isHasFloatingObject() {
		return hasFloatingObject;
	}

	public void setHasFloatingObject(boolean hasFloatingObject) {
		this.hasFloatingObject = hasFloatingObject;
	}

	public void connect() {
		if (!connected) {
			connected = true;
			Semaphore sem1 = new Semaphore(1, true);
			try {
				cl = new ClientToServer(InetAddress.getByName(InitWindow
						.getJtfServer().getText()), this, sem1);
				cl.lancement();
				notifyChanges("connected");
			} catch (SocketException | UnknownHostException e) {
				e.printStackTrace();
				connected = false;
				notifyChanges("pbConnection");
			}
		} else {
			notifyChanges("alreadyConnected");
		}
	}

	public void send() {
		if (connected) {
			if (listCapteur.size() >= 3) {
				cl.chargementCapteur(listCapteur);
				notifyChanges("send");
			}
			else {
				notifyChanges("sensorListSize");
			}
		} else {
			notifyChanges("notConnected");
		}
	}
	
	public void run() {		
		//thread = new Thread(cl);
		//thread.start();
		//cl.run();
		cl.lancementLecture();
		cl.fin();

		notifyChanges("run");
	}
	
	@SuppressWarnings("deprecation")
	public void stop() {
		thread.stop();
		listCapteur.clear();
		connected = false;
		notifyChanges("stop");
	}

	public boolean getTurnOnOff() {
		// TODO Auto-generated method stub
		return isOnOff ;
	}
	
	public void setTurnOnOff() {
		// TODO Auto-generated method stub
		if (isOnOff==false)
			isOnOff = true;
		else
			isOnOff = false;
		notifyChanges("camera");
	}
	
	public void setTurnOnOff(boolean b) {
			isOnOff = b;
	}

	public void incrementOrientation() {
		floatingObject.setOrientation((floatingObject.getOrientation()+1)%4);
		notifyChanges("camera");
	}
	
	

}
