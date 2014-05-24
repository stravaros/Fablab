package EnvObjet;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;

public abstract class ObjetGen {

	protected int posX;
	protected int posY;
	protected int rayonAllumage = 3;
	protected boolean isOnable;
	protected String orientation;
	
	public int getPosX(){
		return posX;
	}
	
	public int getPosY(){
		return posY;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public int getRayonAllumage (){
		return rayonAllumage;
	}
	
	public String getOrientation (){
		return orientation;
	}
	
	public boolean isOnable (){
		return isOnable;
	}
	
	public abstract void drawObjet (GL2 gl, GLUT glut,  Texture tex, boolean on);
	
}
