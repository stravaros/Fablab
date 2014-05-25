package EnvObjet;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;

public class LittleLight extends ObjetGen {

	public LittleLight() {
		this.orientation= 0;
	}

	@Override
	public void drawObjet(GL2 gl, GLUT glut, boolean on) {
		gl.glPushMatrix();
		gl.glTranslated(this.posX ,this.posY, 4f ); //(x ,z,y)
		if (on == true){
			gl.glColor3d(1, 1, 0);
			}
		else
			gl.glColor3d(0.4, 0.8, 0.8);
		
	    glut.glutSolidTorus(0.5, 1.5 ,20, 20);   
	    gl.glTranslated(0, 0, -2f ); //(x ,z,y)
	    glut.glutSolidCone(0.6, 4.0, 20, 20);
	    gl.glPopMatrix();
		
	}

}
