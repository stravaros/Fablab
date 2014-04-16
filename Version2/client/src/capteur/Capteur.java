package capteur;

public class Capteur {

	private double coordoneeX;
	private double coordoneeY;
	private int type;
	private int numero;

	public Capteur(int type, int numero, int X, int Y) {
		this.type = type;
		this.numero=numero;
		this.coordoneeX = X;
		this.coordoneeY = Y;
	}
	
	public Capteur(int type, int numero) {
		this.type = type;
		this.numero=numero;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getCoordoneeX() {
		return coordoneeX;
	}

	public double getCoordoneeY() {
		return coordoneeY;
	}

	//a supprimer
	public boolean setCoordoneeY(double distance, double angle, double largeur) {
		coordoneeY =  (distance * Math.sin(Math.PI * angle / 180.0));
		if (coordoneeY <= largeur / 2 && coordoneeY >= -largeur / 2) {
			return true;
		} else {
			System.out.println("Largeur erronée : Capteur hors de la pièce");
			System.out.println("Rentrez à nouveaux les valeurs");
			return false;
		}
	}

	public boolean setCoordoneeX(double distance, double angle, double longueur) {
		this.coordoneeX = (distance * (Math.cos(Math.PI * angle / 180.0)));
		if (coordoneeX <= longueur / 2 && coordoneeX >= -longueur / 2) {
			return true;
		} else {
			System.out.println("Longueur erronée : Capteur hors de la pièce");
			System.out.println("Rentrez à nouveaux les valeurs");
			return false;
		}
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setCoordoneeX(double coordoneeX) {
		this.coordoneeX = coordoneeX;		
	}
	public void setCoordoneeY(double coordoneeY) {
		this.coordoneeY = coordoneeY;		
	}
}
