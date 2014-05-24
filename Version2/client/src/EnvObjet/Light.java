package EnvObjet;

import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.GLException;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class Light extends ObjetGen{
	
	public Light(){
		this.orientation= 0;
	}
	

	@Override
	public void drawObjet(GL2 gl, GLUT glut, boolean on) {
		gl.glPushMatrix();
		gl.glTranslated(this.posX ,this.posY, 6f ); //(x ,z,y)
		if (on == true){
			gl.glColor3d(1, 1, 0);
			}
		else
			gl.glColor3d(0, 0, 0);
		
	    glut.glutSolidTorus(0.5, 1.5 ,20, 20);   
	    gl.glTranslated(0, 0, -6f ); //(x ,z,y)
	    glut.glutSolidCone(0.6, 8.0, 20, 20);
	    gl.glPopMatrix();
		
	}
}
