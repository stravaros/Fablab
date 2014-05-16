package Capteur;

public class Capteur {

	private double coordoneeX;
	private double coordoneeY;
	private int numero;

	public Capteur(int numero, int X, int Y) {
		this.numero=numero;
		this.coordoneeX = X;
		this.coordoneeY = Y;
	}
	
	public Capteur(int numero) {
		this.numero=numero;
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
