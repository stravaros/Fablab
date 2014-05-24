package EnvObjet;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;

public class Teapot extends ObjetGen {
	
	public Teapot (String orientation){
		this.orientation= orientation;
	}

	public Teapot(int x, int y, String orientation) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawObjet(GL2 gl, GLUT glut, Texture tex, boolean on) {
		//dessin de la teapot
		gl.glPushMatrix();
		gl.glRotatef(90f ,1f, 0f, 0f);
		gl.glTranslatef(posX ,3f, -posY); //(x ,z,y)
		gl.glColor3d(0, 1, 0);
	    glut.glutSolidTeapot(1.5);     // middle teapot
	    gl.glPopMatrix();
		
	}

}
