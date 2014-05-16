package calculMath;

import java.util.ArrayList;

import Jama.*;
import capteur.Capteur;
import commxbee.*; 

public final class CalculMath {
	// Communication avec les xBee
	private ReceptionXBee xbeeReception;
	
	// Tableau de capteur avec leurs coordonnées
	private ArrayList<Capteur> tabCapteur;

	// Coefficient de "proportionnalité" RSSI/rayon (distance)
	private final static double K = 1/6.0;

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
	 * 
	 * @return double [2]
	 */
	public double[] getPosition() {
		double[] tabRayons = new double[tabCapteur.size()
				* (tabCapteur.size() - 1) / 2];
		int indiceLigne = 0;

		// VALEURS DE RSSI EN DUR
		//double[] RSSI = { 20, 20, 20 };
		//double[] RSSI = { 10, 22, 22 };

		for (int i = 0; i < tabCapteur.size() - 1; i++) {
			for (int j = i + 1; j < tabCapteur.size(); j++) {
				/*tabRayons[indiceLigne] = RSSI[j] * RSSI[j] * K * K - RSSI[i]
						* RSSI[i] * K * K;*/
				tabRayons[indiceLigne] = xbeeReception.getRSSI(j) * xbeeReception.getRSSI(j) * K * K - xbeeReception.getRSSI(i)
				* xbeeReception.getRSSI(i) * K * K;
				System.out.println("RSSI " +j + " " +xbeeReception.getRSSI(j) +" RSSI " +i +" " +xbeeReception.getRSSI(i));
				indiceLigne++;
			}
		}
		Matrix matrixRayon = new Matrix(tabRayons, tabCapteur.size()
				* (tabCapteur.size() - 1) / 2);
		position = constMatrix.times(matrixRayon.plus(matrixC));
		return position.getColumnPackedCopy();
	}

	/**
	 * Initialise les matrices pour le calcul de la position suivant les
	 * capteurs présents dans tabCapteur
	 * 
	 * @param tabCapteur
	 * @throws ExceptionSingularite
	 */
	public CalculMath(ArrayList<Capteur> tabCapteur, ReceptionXBee xbeeReception) throws ExceptionSingularite {
		this.tabCapteur = tabCapteur;
		this.xbeeReception = xbeeReception;
		
		int nbLignes = tabCapteur.size() * (tabCapteur.size() - 1) / 2;
		if (tabCapteur.size() > 1) {
			// Initialisation de la matrice H
			tabH = new double[nbLignes][2];
			int indiceLigne = 0;
			for (int i = 0; i < tabCapteur.size() - 1; i++) {
				for (int j = i + 1; j < tabCapteur.size(); j++) {
					// PB tabCapteur[i] null pointer exception
					tabH[indiceLigne][0] = 2 * (tabCapteur.get(i)
							.getCoordoneeXCar() - tabCapteur.get(j)
							.getCoordoneeXCar());
					tabH[indiceLigne][1] = 2 * (tabCapteur.get(i)
							.getCoordoneeYCar() - tabCapteur.get(j)
							.getCoordoneeYCar());
					indiceLigne++;
				}
			}
			matrixH = new Matrix(tabH, nbLignes, 2);
			try {
				// Calcul de (transp(H)*H)^-1 * transp(H)
				constMatrix = matrixH.transpose().times(matrixH).inverse()
						.times(matrixH.transpose());
			} catch (Exception e) {
				throw new ExceptionSingularite("Matrice singuliere");
			}
			// Initialisation de la matrice C
			tabC = new double[nbLignes];
			indiceLigne = 0;
			for (int i = 0; i < tabCapteur.size() - 1; i++) {
				for (int j = i + 1; j < tabCapteur.size(); j++) {
					tabC[indiceLigne] = tabCapteur.get(i).getCoordoneeXCar()
							* tabCapteur.get(i).getCoordoneeXCar()
							- tabCapteur.get(j).getCoordoneeXCar()
							* tabCapteur.get(j).getCoordoneeXCar()
							+ tabCapteur.get(i).getCoordoneeYCar()
							* tabCapteur.get(i).getCoordoneeYCar()
							- tabCapteur.get(j).getCoordoneeYCar()
							* tabCapteur.get(j).getCoordoneeYCar();
					indiceLigne++;
				}
			}
			matrixC = new Matrix(tabC, nbLignes);
		}

	}

	public static void main(String argv[]) throws ExceptionSingularite {
		ArrayList<Capteur> tabCapteur = new ArrayList<Capteur>();

		// Situation 1
		tabCapteur.add(new Capteur(0,10,0));
		tabCapteur.add(new Capteur(10,0,1));
		tabCapteur.add(new Capteur(-10,0,2));

		CalculMath math = new CalculMath(tabCapteur,null);
		System.out.println("Position: x=" + math.getPosition()[0]);
		System.out.println("Position: y=" + math.getPosition()[1]);

		// Situation 2
		 /*tabCapteur.add(new Capteur(0,10,1,0));
		 tabCapteur.add(new Capteur(7,-7,1,1));
		 tabCapteur.add(new Capteur(-7,-7,1,2));
		 CalculMath math = new CalculMath(tabCapteur); 
		 System.out.println("Position: x="+math.getPosition()[0]);
		 System.out.println("Position: y="+math.getPosition()[1]);*/
		 


	}
}
