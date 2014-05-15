package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import Capteur.Capteur;
import Model.Mdl;

public class ClientToServer implements Runnable {
	Scanner in;
	final static int port = 9632;
	private int taille;
	byte buffer[];
	String donnees;
	private DatagramSocket socket;
	InetAddress adresseServeur;
	Mdl mdl;
	Capteur capteurMouvant;
	private Semaphore sem1;

	public ClientToServer(InetAddress adresseServeur, Mdl mdl, Semaphore sem1)
			throws SocketException {
		this.socket = new DatagramSocket();
		this.adresseServeur = adresseServeur;
		this.in = new Scanner(System.in);
		this.buffer = new byte[taille];
		this.donnees = "";
		this.taille = 1024;
		this.mdl = mdl;
		this.sem1 = sem1;
	}

	public void lancement() {
		String delims = "[ ]+";
		String[] tokens = null;
		String data = "Lancement";
		int length = data.length();
		buffer = data.getBytes();
		DatagramPacket donneesEmises = new DatagramPacket(buffer, length,
				adresseServeur, port);
		donneesEmises.setData(buffer);
		donneesEmises.setLength(length);
		donneesEmises.setAddress(adresseServeur);
		donneesEmises.setPort(port);
		DatagramPacket donneesRecues = new DatagramPacket(new byte[taille],
				taille);
		try {
			Thread.sleep(10);
			socket.setSoTimeout(30000);
			socket.send(donneesEmises);
			socket.receive(donneesRecues);
		} catch (SocketTimeoutException ste) {
			System.out.println("Le delai pour la reponse a expire");
		} catch (Exception e) {
			e.printStackTrace();
		}
		donnees = new String(donneesRecues.getData(), 0, taille);
		tokens = donnees.split(delims);
		
		mdl.setNbCapteurServeur((int)  Double.parseDouble(tokens[1]));
	}

	public void chargementCapteur(ArrayList<Capteur> listCapteur) {
		String data;
		int length;
		byte buffer[];

		DatagramPacket donneesRecues = new DatagramPacket(new byte[taille],
				taille);
		for (int i = 0; i < listCapteur.size(); i++) {
			try {
				data = "Capteur " + listCapteur.get(i).getNumero() + " X  "
						+ listCapteur.get(i).getCoordoneeX() + " Y  "
						+ listCapteur.get(i).getCoordoneeY();
				length = data.length();
				buffer = data.getBytes();
				System.out.print(data);
				DatagramPacket donneesEmises = new DatagramPacket(buffer,
						length);
				donneesEmises.setAddress(adresseServeur);
				donneesEmises.setPort(port);
				Thread.sleep(10);
				socket.setSoTimeout(30000);
				socket.send(donneesEmises);
				donnees = new String(donneesRecues.getData(), 0, taille);
			} catch (SocketTimeoutException ste) {
				System.out.println("Le delai pour la reponse a expire");
			} catch (Exception e) {
				e.printStackTrace();
			}
			donnees = "";
			System.out.println("Transmission capteur "
					+ listCapteur.get(i).getNumero());
		}
	}

	public void fin() {
		String data;
		int length;
		byte buffer[];

		DatagramPacket donneesRecues = new DatagramPacket(new byte[taille],
				taille);
		try {
			data = "Fin ";
			length = data.length();
			buffer = data.getBytes();
			DatagramPacket donneesEmises = new DatagramPacket(buffer, length);
			donneesEmises.setAddress(adresseServeur);
			donneesEmises.setPort(port);
			Thread.sleep(10);
			socket.setSoTimeout(30000);
			socket.send(donneesEmises);
			socket.receive(donneesRecues);
			donnees = new String(donneesRecues.getData(), 0, taille);
		} catch (SocketTimeoutException ste) {
			System.out.println("Le delai pour la reponse a expire");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public synchronized void lectureXY() throws ExceptionSingularite,
			InterruptedException {
		String delims = "[ ]+";
		String[] tokens = null;
		DatagramPacket paquet = new DatagramPacket(buffer, buffer.length);
		try {
			
			
			Thread.sleep(10);
			socket.receive(paquet);
			
			
			taille = paquet.getLength();
			donnees = new String(paquet.getData(), 0, taille);
		} catch (IOException e) {
			e.printStackTrace();
		}
		tokens = donnees.split(delims);
		if (tokens[0].equals("X")) {
			System.out.println("Coordonnees");
			System.out.println(donnees);
			sem1.acquire();
			mdl.setCoordXY(Double.parseDouble(tokens[1]),
					Double.parseDouble(tokens[3]));
			sem1.release();
			
		} else if (tokens[0].equals("Matrice")) {
			System.out.println("Matrice singuliere");
			throw new ExceptionSingularite("Matrice singuliere");
		}
	}

	public void lancementLecture() {
		ExecutorService exect = Executors.newFixedThreadPool(1);
		exect.execute(this);
	}

	@Override
	public void run() {
		while (true) {
			try {
				lectureXY();

			} catch (ExceptionSingularite e) {
				System.out.println("Erreur : Matrice singulière");
				System.exit(-1);
			} catch (InterruptedException e) {
				System.out.println("Erreur : Partage de données (sémaphore)");
				System.exit(-1);
			}
		}
	}
}
