import java.util.Scanner;

import capteur.Capteur;
import App.MainAPP;

public class Main {

	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		// / TODO Supprimer les commenatire et enlever les = des attributs

		int nombreCapteurFixe = 1;
		int nombreCapteurMouvant = 1;

		Capteur tabCapteurFixe[];
		Capteur tabCapteurMouvant[];
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
		tabCapteurMouvant = new Capteur[nombreCapteurMouvant];

		for (int i = 0; i < nombreCapteurFixe; i++) {
			tabCapteurFixe[i] = new Capteur(1, i);
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
		for (int i = 0; i < nombreCapteurMouvant; i++) {
			tabCapteurMouvant[i] = new Capteur(2, i);
			System.out
					.println("Il s'agit d'un capteur. Aucune donnée n'est demandé");
		}

		MainAPP app = new MainAPP(tabCapteurFixe, tabCapteurMouvant,
				longueur / 2, largeur / 2);
		app.setVisible(true);

	}
}