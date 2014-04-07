package server;

import java.net.*;

import capteur.Capteur;

public class TestServeurUDP {

	final static int port = 9632;
	final static int taille = 1024;
	static byte buffer[] = new byte[taille];
	static float longeur;
	static float largeur;
	private static Capteur[] tabCapteurFixe;

	public static void main(String argv[]) throws Exception {
		DatagramSocket socket = new DatagramSocket(port);
		String donnees = "";
		String message = "";
		int taille = 0;

		System.out.println("Lancement du serveur");
		while (true) {
			DatagramPacket paquet = new DatagramPacket(buffer, buffer.length);
			DatagramPacket envoi = null;
			socket.receive(paquet);
			taille = paquet.getLength();
			donnees = new String(paquet.getData(), 0, taille);
			String delims = "[ ]+";
			String[] tokens = donnees.split(delims);

			if (tokens[0].equals("Longeur")) {
				longeur = Float.parseFloat(tokens[1]);
				largeur = Float.parseFloat(tokens[3]);
			}
			if(tokens[0].equals("NombreCapteur")){
				tabCapteurFixe = new Capteur[Integer.parseInt(tokens[1])];
			}
			if(tokens[0].equals("Capteur")){
				tabCapteurFixe[Integer.parseInt(tokens[1])]=new Capteur();
				tabCapteurFixe[Integer.parseInt(tokens[1])].setType(1);
				tabCapteurFixe[Integer.parseInt(tokens[1])].setNumero(Integer.parseInt(tokens[1]));
				tabCapteurFixe[Integer.parseInt(tokens[1])].setCoordoneeXPol(Integer.parseInt(tokens[3]));
				tabCapteurFixe[Integer.parseInt(tokens[1])].setCoordoneeYPol(Integer.parseInt(tokens[5]));
			System.out.println(tabCapteurFixe[Integer.parseInt(tokens[1])].getCoordoneeYPol());
			}
		}
		/*
		 * 
		 * 
		 * 
		 * System.out.println("\n"+paquet.getAddress());
		 * 
		 * 
		 * System.out.println("Donnees reçues = "+donnees);
		 * 
		 * message = "Bonjour "+donnees;
		 * System.out.println("Donnees envoyees = "+message); envoi = new
		 * DatagramPacket(message.getBytes(), message.length(),
		 * paquet.getAddress(), paquet.getPort()); socket.send(envoi); }
		 */
	}
}