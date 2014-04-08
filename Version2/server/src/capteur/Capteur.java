package capteur;

public class Capteur {

	private int coordoneeXCar;
	private int coordoneeYCar;
	private int type;
	private int numero;

	public Capteur() {
		
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

	public int getCoordoneeXCar() {
		return coordoneeXCar;
	}

	public void setCoordoneeXCar(int coordoneeXCar) {
		this.coordoneeXCar = coordoneeXCar;
	}

	public int getCoordoneeYCar() {
		return coordoneeYCar;
	}

	public void setCoordoneeYCar(int coordoneeYCar) {
		this.coordoneeYCar = coordoneeYCar;
	}
}