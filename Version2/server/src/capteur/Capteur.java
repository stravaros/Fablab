package capteur;

public class Capteur {

	private double coordoneeXCar;
	private double coordoneeYCar;
	private int numero;

	public Capteur(double coordoneeXCar, double coordoneeYCar, int numero ) {
		this.coordoneeXCar=coordoneeXCar;
		this.coordoneeYCar=coordoneeYCar;
		this.numero=numero;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getCoordoneeXCar() {
		return coordoneeXCar;
	}

	public void setCoordoneeXCar(double coordoneeXCar) {
		this.coordoneeXCar = coordoneeXCar;
	}

	public double getCoordoneeYCar() {
		return coordoneeYCar;
	}

	public void setCoordoneeYCar(double coordoneeYCar) {
		this.coordoneeYCar = coordoneeYCar;
	}
}