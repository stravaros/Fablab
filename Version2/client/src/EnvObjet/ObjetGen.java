package EnvObjet;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;

public abstract class ObjetGen {

	protected int posX;
	protected int posY;
	protected boolean isOnable = false;
	protected boolean OnOff = false;
	protected int orientation;
	
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
	
	public int getOrientation (){
		return orientation;
	}
	
	public void setOrientation (int orientation){
		this.orientation = orientation%4;
	}
	
	public boolean isOnable (){
		return isOnable;
	}
	
	public void turnOn (){
		OnOff = true;
	}
	
	public void turnOff (){
		OnOff = false;
	}
	
	public void turnOnOff (){
		if (OnOff == true)
			OnOff = false;
		else
			OnOff = true;
	}
	
	public boolean getOnOff (){
		return OnOff;
	}
	
	public abstract void drawObjet (GL2 gl, GLUT glut);
	
}
