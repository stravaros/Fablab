package Capteur;

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
