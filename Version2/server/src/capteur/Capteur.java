package capteur;

public class Capteur {

	private int coordoneeXPol;
	private int coordoneeYPol;
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

	public int getCoordoneeXPol() {
		return coordoneeXPol;
	}

	public void setCoordoneeXPol(int coordoneeXPol) {
		this.coordoneeXPol = coordoneeXPol;
	}

	public int getCoordoneeYPol() {
		return coordoneeYPol;
	}

	public void setCoordoneeYPol(int coordoneeYPol) {
		this.coordoneeYPol = coordoneeYPol;
	}


}