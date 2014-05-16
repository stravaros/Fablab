package server;

import java.net.*;
import java.util.ArrayList;

import commxbee.ReceptionXBee;

import calculMath.CalculMath;
import calculMath.ExceptionSingularite;
import capteur.Capteur;

public class Main {

	final static int port = 9632;
	static int taille = 2048*1000;
	static byte buffer[];
	static double longeur;
	static double largeur;
	static ArrayList<Capteur> arrayCapteur;
	private static DatagramSocket socket;
	private static CalculMath calculMath;
	static int nombreCapteur = 10;
	private static ReceptionXBee xBeeReception;
	

	public static void main(String argv[]) throws Exception {
		socket = new DatagramSocket(port);
		buffer  = new byte[taille];
		String donnees = "";
		arrayCapteur = new ArrayList<Capteur>();
		xBeeReception = new ReceptionXBee();

		System.out.println("Lancement du serveur");		
				while (true) {
			DatagramPacket paquet = new DatagramPacket(buffer, buffer.length);
			socket.receive(paquet);
			taille = paquet.getLength();
			donnees = new String(paquet.getData(), 0, taille);
			String delims = "[ ]+";
			String[] tokens = donnees.split(delims);

			if (tokens[0].equals("Lancement")) {
				String message = "";
				message = Integer.toString(nombreCapteur);
				DatagramPacket envoi = new DatagramPacket(message.getBytes(),
						message.length(), paquet.getAddress(), paquet.getPort());
				socket.send(envoi);
			}
			if (tokens[0].equals("Capteur")) {
				arrayCapteur.add(new Capteur(Double.parseDouble(tokens[3]),
						Double.parseDouble(tokens[5]),Integer
								.parseInt(tokens[1])));
			}
			if (tokens[0].equals("Fin")) {
				String message = "";
				try {
				calculMath = new CalculMath(arrayCapteur,xBeeReception);
				xBeeReception.lancementReception();
				} catch (ExceptionSingularite e) {
					message = e.getMessage();
					DatagramPacket envoi = new DatagramPacket(
							message.getBytes(), message.length(),
							paquet.getAddress(), paquet.getPort());
					socket.send(envoi);
					
					System.out.println("Probleme pour l'initialisation du serveur");
					System.exit(-1);
				}
				while (true) {
					double[] pos = calculMath.getPosition();
					message = "X " + (int)(pos[0]) + " Y " + (int)(pos[1]);
					

					System.out.println("POSITION: "+message);
					DatagramPacket envoi = new DatagramPacket(
							message.getBytes(), message.length(),
							paquet.getAddress(), paquet.getPort());
					Thread.sleep(1000);				
					socket.send(envoi);
				}
			}
		}
	}
}
