package capteur;

public class Capteur {

	private double coordoneeXCar;
	private double coordoneeYCar;
	private int type;
	private int numero;

	public Capteur(double coordoneeXCar, double coordoneeYCar, int type, int numero ) {
		this.coordoneeXCar=coordoneeXCar;
		this.coordoneeYCar=coordoneeYCar;
		this.type=type;
		this.numero=numero;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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