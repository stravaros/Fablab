package EnvObjet;

import java.io.File;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;

public class Teapot extends ObjetGen {
	
	
	public Teapot (){
		this.orientation= 0;
	}

	@Override
	public void drawObjet(GL2 gl, GLUT glut, boolean on) {
		//dessin de la teapot
		gl.glPushMatrix();
		gl.glRotatef(90f ,1f, 0f, 0f);
		gl.glTranslatef(posX ,3f, -posY); //(x ,z,y)
		gl.glColor3d(0, 1, 0);
	    glut.glutSolidTeapot(1.5);     // middle teapot
	    gl.glPopMatrix();
		
	}

}
