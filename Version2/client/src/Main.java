


import java.net.InetAddress;

import Capteur.Capteur;
import Client.ClientToServer;
import App.MainAPP;
import Model.Mdl;

public class Main {

	
	public static void main(String argv[]) throws Exception {
		InetAddress adresseServeur = InetAddress.getByName(argv[0]);
		ClientToServer cl = new ClientToServer (adresseServeur);
		//MainAPP app = new MainAPP(cl);
		MainAPP app = new MainAPP();
		app.setVisible(true);
		
	
		
		String data = "Init";
		int length;
		byte buffer[] = data.getBytes();
		
		//Recupere nombre de capteur et metre a jour une variable
		
	/*	length = data.length();
		socket = new DatagramSocket();
		DatagramPacket donneesEmises = new DatagramPacket(buffer, length,
				serveur, length);
		
		String delims = "[ ]+";
		String[] tokens;
		try {
				
			data = "Longeur " + longueur + " Largeur " + largeur;
			length = data.length();
			buffer = data.getBytes();
			donneesEmises.setData(buffer);
			donneesEmises.setLength(length);
			donneesEmises.setAddress(serveur);
			donneesEmises.setPort(port);
			DatagramPacket donneesRecues = new DatagramPacket(new byte[taille],
					taille);
			do{
				Thread.sleep(10);
				socket.setSoTimeout(30000);
				socket.send(donneesEmises);
				socket.receive(donneesRecues);
				donnees = new String(donneesRecues.getData(), 0, taille);
				
				tokens = donnees.split(delims);
			}while(!(tokens[0].equals("ACK"))&&(!tokens[1].equals("dimension")));
			donnees="";
			System.out.println("Longeur et Largeur recues");
			data = "NombreCapteur " + nombreCapteurFixe;
			length = data.length();
			buffer = data.getBytes();
			donneesEmises.setData(buffer);
			donneesEmises.setLength(length);
			donneesEmises.setAddress(serveur);
			donneesEmises.setPort(port);
			do{
				Thread.sleep(10);
				socket.setSoTimeout(30000);
				socket.send(donneesEmises);
				socket.receive(donneesRecues);
				donnees = new String(donneesRecues.getData(), 0, taille);	
				tokens = donnees.split(delims);
			}while(!(tokens[0].equals("ACK"))&&(!tokens[1].equals("NombreCapteur")));
			donnees="";
			System.out.println("Nombre de capteur transferer");
			
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
				do{
					Thread.sleep(10);
					socket.setSoTimeout(30000);
					socket.send(donneesEmises);
					socket.receive(donneesRecues);
					donnees = new String(donneesRecues.getData(), 0, taille);	
					tokens = donnees.split(delims);
				}while(!(tokens[0].equals("ACK"))&&(!tokens[1].equals("Capteur")));
				donnees="";				
				System.out.println("Transmission capteur "
						+ tabCapteurFixe[i].getNumero());
			}
		} catch (SocketTimeoutException ste) {
			System.out.println("Le delai pour la reponse a expire");
		} catch (Exception e) {
			e.printStackTrace();}
		
		Thread.sleep(1000);
		
		
		while (true) {
			try {
				DatagramPacket donneesRecues = new DatagramPacket(new byte[taille],
						taille);
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
				socket.receive(donneesRecues);
				donnees = new String(donneesRecues.getData(), 0, taille);	
				tokens = donnees.split(delims);
				if(tokens[0].equals("X")){	
				capteurMouvement.setCoordoneeX(Double.parseDouble(tokens[1]));
				capteurMouvement.setCoordoneeY(Double.parseDouble(tokens[3]));
				}
				else if (tokens[0].equals("Matrice")){
					System.out.println("Matrice singuliere veuiller relancer le programme");
					System.exit(-1);
				}
			} catch (SocketTimeoutException ste) {
				System.out.println("Le delai pour la reponse a expire");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
	}
}