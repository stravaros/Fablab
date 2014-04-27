package EnvObjet;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;

public abstract class ObjetGen {
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
	
	public abstract void drawObjet (GL2 gl, GLUT glut,  Texture tex);
	
}
