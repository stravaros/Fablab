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
	public void drawObjet(GL2 gl, GLUT glut) {
		//dessin de la teapot
		gl.glPushMatrix();
		gl.glRotatef(90f ,1f, 0f, 0f);
		gl.glTranslatef(posX ,2.7f, -posY); //(x ,z,y)
		gl.glColor3d(0.25, 0.51, 0.4);
	    glut.glutSolidTeapot(1);     // middle teapot
	    gl.glPopMatrix();
		
	}

}
