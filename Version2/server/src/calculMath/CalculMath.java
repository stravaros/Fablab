package calculMath;

import Jama.*;
import capteur.Capteur;

public final class CalculMath {
	// Tableau de capteur avec leurs coordonnées
	private Capteur[] tabCapteur;

	// Coefficient de "proportionnalité" RSSI/rayon (distance)
	private final static double K = 0.5;

	// Tableau et matrice H
	private double[][] tabH;
	private Matrix matrixH;
	// Matrice produit constante
	private Matrix constMatrix;

	// Tableau et matrice C des différences entre coord des capteurs
	private double[] tabC;
	private Matrix matrixC;

	// Tableau et matrice coordonées du point mobile
	private double[] objectCoord = { 0, 0 };
	private Matrix position = new Matrix(objectCoord, 2);

	
	/**
	 * Renvoie la position du point mobile sous forme d'un tableau [x y]
	 * @return double [2]
	 */
	public double[] getPosition() {
		double[] tabRayons = new double[tabCapteur.length
				* (tabCapteur.length - 1) / 2];
		int indiceLigne = 0;

		// VALEURS DE RSSI EN DUR
		double[] RSSI = { 20, 20, 20 };
		// double[] RSSI = {10,22,22};

		for (int i = 0; i < tabCapteur.length - 1; i++) {
			for (int j = i + 1; j < tabCapteur.length; j++) {
				tabRayons[indiceLigne] = RSSI[j] * RSSI[j] * K * K - RSSI[i]
						* RSSI[i] * K * K;
				indiceLigne++;
			}
		}
		Matrix matrixRayon = new Matrix(tabRayons, tabCapteur.length
				* (tabCapteur.length - 1) / 2);
		position = constMatrix.times(matrixRayon.plus(matrixC));

		return position.getColumnPackedCopy();
	}

	/**
	 * Initialise les matrices pour le calcul de la position suivant les capteurs présents dans tabCapteur
	 * @param tabCapteur
	 */
	public CalculMath(Capteur[] tabCapteur) {
		this.tabCapteur = tabCapteur;
		int nbLignes = tabCapteur.length * (tabCapteur.length - 1) / 2;
		if (tabCapteur.length > 1) {
			System.out.println("tab length " +tabCapteur.length);
			// Initialisation de la matrice H
			tabH = new double[nbLignes][2];
			int indiceLigne = 0;
			for (int i = 0; i < tabCapteur.length - 1; i++) {
				for (int j = i + 1; j < tabCapteur.length; j++) {
					// PB tabCapteur[i] null pointer exception
					System.out.println("INDICE I: " +i +" INDICE J: " +j);
					tabH[indiceLigne][0] = 2 * (tabCapteur[i]
							.getCoordoneeXCar() - tabCapteur[j]
							.getCoordoneeXCar());
					tabH[indiceLigne][1] = 2 * (tabCapteur[i]
							.getCoordoneeYCar() - tabCapteur[j]
							.getCoordoneeYCar());
					indiceLigne++;
				}
			}
			matrixH = new Matrix(tabH, nbLignes, 2);

			// Calcul de (transp(H)*H)^-1 * transp(H)
			constMatrix = matrixH.transpose().times(matrixH).inverse()
					.times(matrixH.transpose());

			// Initialisation de la matrice C
			tabC = new double[nbLignes];
			indiceLigne = 0;
			for (int i = 0; i < tabCapteur.length - 1; i++) {
				for (int j = i + 1; j < tabCapteur.length; j++) {
					tabC[indiceLigne] = tabCapteur[i].getCoordoneeXCar()
							* tabCapteur[i].getCoordoneeXCar()
							- tabCapteur[j].getCoordoneeXCar()
							* tabCapteur[j].getCoordoneeXCar()
							+ tabCapteur[i].getCoordoneeYCar()
							* tabCapteur[i].getCoordoneeYCar()
							- tabCapteur[j].getCoordoneeYCar()
							* tabCapteur[j].getCoordoneeYCar();
					indiceLigne++;
				}
			}
			matrixC = new Matrix(tabC, nbLignes);
		}

	}

	
	
	public static void main(String argv[]) {
		Capteur[] tabCapteur = new Capteur[3];
		for (int i = 0; i < tabCapteur.length; i++)
			tabCapteur[i] = new Capteur();
		// Situation 1
		tabCapteur[0].setCoordoneeXCar(0);
		tabCapteur[0].setCoordoneeYCar(10);
		tabCapteur[1].setCoordoneeXCar(10);
		tabCapteur[1].setCoordoneeYCar(0);
		tabCapteur[2].setCoordoneeXCar(-10);
		tabCapteur[2].setCoordoneeYCar(0);
		CalculMath math = new CalculMath(tabCapteur);
		System.out.println("Position: x=" + math.getPosition()[0]);
		System.out.println("Position: y=" + math.getPosition()[1]);

		// Situation 2
		/*
		 * tabCapteur[0].setCoordoneeXCar(0);
		 * tabCapteur[0].setCoordoneeYCar(10);
		 * tabCapteur[1].setCoordoneeXCar(7);
		 * tabCapteur[1].setCoordoneeYCar(-7);
		 * tabCapteur[2].setCoordoneeXCar(-7);
		 * tabCapteur[2].setCoordoneeYCar(-7); CalculMath math = new
		 * CalculMath(tabCapteur); System.out.println("Position: x="
		 * +math.getPosition()[0]); System.out.println("Position: y="
		 * +math.getPosition()[1]);
		 */

		// Situation 3 (changer la valeur des RSSI en haut)
		/*
		 * tabCapteur[0].setCoordoneeXCar(0);
		 * tabCapteur[0].setCoordoneeYCar(10);
		 * tabCapteur[1].setCoordoneeXCar(10);
		 * tabCapteur[1].setCoordoneeYCar(0);
		 * tabCapteur[2].setCoordoneeXCar(-10);
		 * tabCapteur[2].setCoordoneeYCar(0); CalculMath math = new
		 * CalculMath(tabCapteur); System.out.println("Position: x="
		 * +math.getPosition()[0]); System.out.println("Position: y="
		 * +math.getPosition()[1]);
		 */
	}
}
