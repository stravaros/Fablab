package EnvObjet;

public class ObjetGen {
	protected int posX;
	protected int posY;
	protected int rayonAllumage;
	protected boolean isOnable;
	
	public ObjetGen (int X, int Y){
		posX = X;
		posY = Y;
		isOnable = false;
		rayonAllumage = 0;
		
	}
	
	public ObjetGen (int X, int Y, int allumage){
		posX = X;
		posY = Y;
		rayonAllumage = allumage;
		isOnable = true;
	}
	
	public int getPosX(){
		return posX;
	}
	
	public int getPosY(){
		return posY;
	}
	
	public int getRayonAllumage (){
		return rayonAllumage;
	}
	
	public boolean isOnable (){
		return isOnable;
	}
	
}
