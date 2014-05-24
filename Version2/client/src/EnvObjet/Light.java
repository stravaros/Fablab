package EnvObjet;

import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.GLException;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class Light extends ObjetGen{
	public Light(String orientation){
		this.orientation= orientation;
	}
	
	public Light(int X, int Y, String orientation) {
		this.orientation = orientation;
		
		
		if (X > 19)
			posX = 19;
		else if (X < -19)
			posX = -19;
		else
			posX = X;
		
		if (Y > 19)
			posY = 19;
		else if (Y < -19)
			posY = -19;
		else
			posY = Y;
		
		
		isOnable = false;
	}

	@Override
	public void drawObjet(GL2 gl, GLUT glut,  Texture text, boolean on) {
		gl.glPushMatrix();
		gl.glTranslated(this.posX ,this.posY, 6f ); //(x ,z,y)
		if (on == true){
			gl.glColor3d(0, 1, 0);
			}
		else
			gl.glColor3d(1, 0, 1);
		
	    glut.glutSolidTorus(0.5, 1.5 ,20, 20);   
	    gl.glTranslated(0, 0, -6f ); //(x ,z,y)
	    glut.glutSolidCone(0.6, 8.0, 20, 20);
	    gl.glPopMatrix();
		
	}
}
