package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Scanner;

import Capteur.Capteur;

public class ClientToServer {
	Scanner in;
	final static int port = 9632;
	private int taille;
	byte buffer[];
	String donnees;
	private DatagramSocket socket;
	InetAddress adresseServeur;

	public ClientToServer(InetAddress adresseServeur) {
		this.adresseServeur = adresseServeur;
		this.in = new Scanner(System.in);
		this.buffer = new byte[taille];
		this.donnees = "";
		this.taille = 1024;
	}

	public int lancement(int nbCapteur) {
		String delims = "[ ]+";
		String[] tokens;
		String data = "Lancement";
		int length = data.length();
		byte buffer[] = data.getBytes();
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
		return Math.min(Integer.parseInt(tokens[0]), nbCapteur);
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
				DatagramPacket donneesEmises = new DatagramPacket(buffer,
						length);
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
			donnees = "";
			System.out.println("Transmission capteur "
					+ listCapteur.get(i).getNumero());
		}
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

	public void lectureXY(Capteur capteurMouvant) throws ExceptionSingularite {
		String delims = "[ ]+";
		String[] tokens = null;
		DatagramPacket paquet = new DatagramPacket(buffer, buffer.length);
		try {
			socket.receive(paquet);
			taille = paquet.getLength();
			donnees = new String(paquet.getData(), 0, taille);
			tokens = donnees.split(delims);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (tokens[0].equals("X")) {
			capteurMouvant.setCoordoneeX(Double.parseDouble(tokens[1]));
			capteurMouvant.setCoordoneeY(Double.parseDouble(tokens[3]));
		} else if (tokens[0].equals("Matrice")) {
			System.out.println("Matrice singuliere");
			throw new ExceptionSingularite("Matrice singuliere");
		}
	}
}
