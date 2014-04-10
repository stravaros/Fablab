package server;

import java.net.*;

import calculMath.CalculMath;
import capteur.Capteur;

public class TestServeurUDP {

	final static int port = 9632;
	final static int taille = 1024;
	static byte buffer[] = new byte[taille];
	static double longeur;
	static double largeur;
	private static Capteur[] tabCapteurFixe;
	private static CalculMath calculMath;
	private static DatagramSocket socket;
	private static boolean alreadyInstanciated = false;
	
	public static void main(String argv[]) throws Exception {
		socket = new DatagramSocket(port);
		String donnees = "";
		int taille = 0;
		System.out.println("Lancement du serveur");
		while (true) {
			DatagramPacket paquet = new DatagramPacket(buffer, buffer.length);
			socket.receive(paquet);
			taille = paquet.getLength();
			donnees = new String(paquet.getData(), 0, taille);
			String delims = "[ ]+";
			String[] tokens = donnees.split(delims);

			if (tokens[0].equals("Longeur")) {
				String message="";
				longeur = Double.parseDouble(tokens[1]);
				largeur =  Double.parseDouble(tokens[3]);
				System.out.println(longeur);
				System.out.println(largeur);
			message="ACK dimension";
			DatagramPacket envoi = new DatagramPacket(message.getBytes(), message.length(), paquet.getAddress(), paquet.getPort()); socket.send(envoi);
			socket.send(envoi);
			}
			if(tokens[0].equals("NombreCapteur")){
				String message="";
				tabCapteurFixe = new Capteur[Integer.parseInt(tokens[1])];
				message="ACK NombreCapteur";
				DatagramPacket envoi = new DatagramPacket(message.getBytes(), message.length(), paquet.getAddress(), paquet.getPort()); socket.send(envoi);
				socket.send(envoi);
				System.out.println(Integer.parseInt(tokens[1]));
			}
			if(tokens[0].equals("Capteur")){
				String message="";
				tabCapteurFixe[Integer.parseInt(tokens[1])-1]=new Capteur();
				tabCapteurFixe[Integer.parseInt(tokens[1])-1].setType(1);
				tabCapteurFixe[Integer.parseInt(tokens[1])-1].setNumero(Integer.parseInt(tokens[1]));
				tabCapteurFixe[Integer.parseInt(tokens[1])-1].setCoordoneeXCar(Double.parseDouble(tokens[3]));
				tabCapteurFixe[Integer.parseInt(tokens[1])-1].setCoordoneeYCar(Double.parseDouble(tokens[5]));
				message="ACK Capteur";
				DatagramPacket envoi = new DatagramPacket(message.getBytes(), message.length(), paquet.getAddress(), paquet.getPort()); socket.send(envoi);
				socket.send(envoi);
			}
			if(tokens[0].equals("Mouvement")){

				String message="";
				
				if(!alreadyInstanciated ) {
					calculMath = new CalculMath(tabCapteurFixe);
					alreadyInstanciated = true;
					System.out.println("APPEL AUX MATHS");
				}
				System.out.println("APPEL AUX MATHS");
				double[] pos = calculMath.getPosition();
				message= "X "+pos[0] +" Y " +pos[1] ;
				DatagramPacket envoi = new DatagramPacket(message.getBytes(), message.length(), paquet.getAddress(), paquet.getPort()); socket.send(envoi);
				socket.send(envoi);
			}	
		}
	}
}