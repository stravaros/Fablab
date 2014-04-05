package client;

import java.io.*;
import java.net.*;

public class TestClientUDP {

	final static int port = 9632;
	final static int taille = 1024;
	static byte buffer[] = new byte[taille];
static String alpha = "plop";
	public static void main(String argv[]) throws Exception {
		InetAddress serveur = InetAddress.getByName(argv[0]);
		int length = alpha.length();
		System.out.print(alpha.getBytes() + "plop");
		byte buffer[] =alpha.getBytes();
		DatagramSocket socket = new DatagramSocket();
		DatagramPacket donneesEmises = new DatagramPacket(buffer, length,
				serveur, port);
		DatagramPacket donneesRecues = new DatagramPacket(new byte[taille],
				taille);
		while (true) {
			Thread.sleep(10);

			try {
				socket.setSoTimeout(30000);
				socket.send(donneesEmises);
				socket.receive(donneesRecues);

				System.out.println("Message : "
						+ new String(donneesRecues.getData(), 0, donneesRecues
								.getLength()));
				System.out.println("de : " + donneesRecues.getAddress() + ":"
						+ donneesRecues.getPort());
			} catch (SocketTimeoutException ste) {
				System.out.println("Le delai pour la reponse a expire");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}