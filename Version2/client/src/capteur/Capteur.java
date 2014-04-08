package capteur;

public class Capteur {

	private int coordoneeX;
	private int coordoneeY;
	private int type;
	private int numero;

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

	public int getCoordoneeX() {
		return coordoneeX;
	}

	public int getCoordoneeY() {
		return coordoneeY;
	}

	public boolean setCoordoneeY(float distance, float angle, float largeur) {
		coordoneeY = (int) (distance * Math.sin(Math.PI * angle / 180.0));
		if (coordoneeY <= largeur / 2 && coordoneeY >= -largeur / 2) {
			return true;
		} else {
			System.out.println("Largeur erronée : Capteur hors de la pièce");
			System.out.println("Rentrez à nouveaux les valeurs");
			return false;
		}
	}

	public boolean setCoordoneeX(float distance, float angle, float longueur) {
		this.coordoneeX = (int) (distance * (Math.cos(Math.PI * angle / 180.0)));
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

	public void setCoordoneeX(int coordoneeX) {
		this.coordoneeX = coordoneeX;		
	}
	public void setCoordoneeY(int coordoneeY) {
		this.coordoneeY = coordoneeY;		
	}
}
