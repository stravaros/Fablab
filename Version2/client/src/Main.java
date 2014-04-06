import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

import capteur.Capteur;
import App.MainAPP;

public class Main {

	static Scanner in = new Scanner(System.in);
	final static int port = 9632;
	final static int taille = 1024;
	static byte buffer[] = new byte[taille];

	public static void main(String argv[]) throws Exception {
		// / TODO Supprimer les commenatire et enlever les = des attributs
		InetAddress serveur = InetAddress.getByName(argv[0]);

		int nombreCapteurFixe = 1;

		Capteur tabCapteurFixe[];

		float longueur = 20;
		float largeur = 20;

		/*
		 * System.out.println("Taille de la pièce : \n ");
		 * System.out.println("La longueur (en m) \n "); longueur =
		 * in.nextInt(); System.out.println("La largeur (en m) \n "); largeur =
		 * in.nextInt();
		 * 
		 * System.out.println("Combien de capteur \n "); nombreCapteur =
		 * in.nextInt();
		 */

		tabCapteurFixe = new Capteur[nombreCapteurFixe];

		Capteur capteurMouvement = new Capteur(2, 0); // Initialise le recepteur
														// il est le capteur 0
		// Les autres capteurs sont entre 1 et nombreCapteurFixe + 1

		for (int i = 0; i < nombreCapteurFixe; i++) {
			tabCapteurFixe[i] = new Capteur(1, i + 1);
			System.out.println("Le capteur est un recepteur");
			System.out
					.println("Entrer les coordonnées polaires du points (la distance et l'angle par rapport au centre de la classe");
			System.out.println("Tous d'abord la distance");
			boolean coordoneeCorect = false;
			while (coordoneeCorect == false) {
				float distance = in.nextFloat();
				System.out.println("Puis l'angle");
				float angle = in.nextFloat();
				coordoneeCorect = tabCapteurFixe[i].setCoordoneeX(distance,
						angle, longueur);
				coordoneeCorect = tabCapteurFixe[i].setCoordoneeY(distance,
						angle, largeur);

			}
		}

		MainAPP app = new MainAPP(tabCapteurFixe, capteurMouvement,
				longueur / 2, largeur / 2);
		app.setVisible(true);

		// /////////////////////////////server

		String data = "Init";
		int length;
		byte buffer[] = data.getBytes();
		;
		length = data.length();
		DatagramSocket socket = new DatagramSocket();
		DatagramPacket donneesEmises = new DatagramPacket(buffer, length,
				serveur, length);
		DatagramPacket donneesRecues = new DatagramPacket(new byte[taille],
				taille);
		try {
			data = "Longeur " + longueur + " Largeur " + largeur;
			length = data.length();
			buffer = data.getBytes();
			donneesEmises.setData(buffer);
			donneesEmises.setLength(length);
			donneesEmises.setAddress(serveur);
			donneesEmises.setPort(port);

			Thread.sleep(10);
			socket.setSoTimeout(30000);
			socket.send(donneesEmises);
			socket.receive(donneesRecues);// TODO Faire un test dessus
			System.out.println("Longeur/Largeur recue");
			for (int i = 0; i < nombreCapteurFixe; i++) {

				data = "Capteur " + tabCapteurFixe[i].getNumero() + " X  "
						+ tabCapteurFixe[i].getCoordoneeX() + " Y  "
						+ tabCapteurFixe[i].getCoordoneeY();
				length = data.length();
				buffer = data.getBytes();
				donneesEmises.setData(buffer);
				donneesEmises.setLength(length);
				donneesEmises.setAddress(serveur);
				donneesEmises.setPort(port);
				Thread.sleep(10);
				socket.setSoTimeout(30000);
				socket.send(donneesEmises);
				socket.receive(donneesRecues);// TODO Faire un test dessus
				System.out.println("Transmission capteur"
						+ tabCapteurFixe[i].getNumero());
			}

			data = "Fin capteur";
			length = data.length();
			buffer = data.getBytes();
			donneesEmises.setData(buffer);
			donneesEmises.setLength(length);
			donneesEmises.setAddress(serveur);
			donneesEmises.setPort(port);
			Thread.sleep(10);
			socket.setSoTimeout(30000);
			socket.send(donneesEmises);
			socket.receive(donneesRecues);// TODO Faire un test dessus
			System.out.println("Fin recue");

		} catch (SocketTimeoutException ste) {
			System.out.println("Le delai pour la reponse a expire");
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				data = "Mouvement";
				length = data.length();
				buffer = data.getBytes();
				donneesEmises.setData(buffer);
				donneesEmises.setLength(length);
				donneesEmises.setAddress(serveur);
				donneesEmises.setPort(port);
				Thread.sleep(10);
				socket.setSoTimeout(30000);
				socket.send(donneesEmises);
				socket.receive(donneesRecues);// TODO Faire un test dessus
				// TODO parser la réponse
				System.out.println("Ack");

			} catch (SocketTimeoutException ste) {
				System.out.println("Le delai pour la reponse a expire");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}