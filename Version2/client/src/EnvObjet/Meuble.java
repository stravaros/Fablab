package EnvObjet;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;

public class Meuble extends ObjetGen {
	
	public Meuble (int X, int Y){
		super (X, Y);
	}

	@Override
	public void drawObjet(GL2 gl, GLUT glut,  Texture text) {
		// TODO Auto-generated method stub
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(0, 0, 1); // set the color of the quad
			gl.glVertex3d(-10, 20, 0); // Top Left
			gl.glVertex3d(-10, 15, 0); // Top Right
			gl.glVertex3d(-10, 15, 10); // Bottom Right
			gl.glVertex3d(-10, 20, 10); // Bottom Left
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(0, 1, 1); // set the color of the quad
			gl.glVertex3d(-5, 20, 0); // Top Left
			gl.glVertex3d(-5, 15, 0); // Top Right
			gl.glVertex3d(-5, 15, 10); // Bottom Right
			gl.glVertex3d(-5, 20, 10); // Bottom Left
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(0, 1, 0); // set the color of the quad
			gl.glVertex3d(5, 20, 0); // Top Left
			gl.glVertex3d(5, 15, 0); // Top Right
			gl.glVertex3d(5, 15, 10); // Bottom Right
			gl.glVertex3d(5, 20, 10); // Bottom Left
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(0, 0, 1); // set the color of the quad
			gl.glVertex3d(10, 20, 0); // Top Left
			gl.glVertex3d(10, 15, 0); // Top Right
			gl.glVertex3d(10, 15, 10); // Bottom Right
			gl.glVertex3d(10, 20, 10); // Bottom Left
		gl.glEnd();
		
		//horizontal
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(0, 0, 1); // set the color of the quad
			gl.glVertex3d(-10, 20, 0); // Top Left
			gl.glVertex3d(10, 20, 0); // Top Right
			gl.glVertex3d(10, 15, 0); // Bottom Right
			gl.glVertex3d(-10, 15, 0); // Bottom Left
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(0, 0, 1); // set the color of the quad
			gl.glVertex3d(-10, 20, 5); // Top Left
			gl.glVertex3d(10, 20, 5); // Top Right
			gl.glVertex3d(10, 15, 5); // Bottom Right
			gl.glVertex3d(-10, 15, 5); // Bottom Left
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(0, 0, 1); // set the color of the quad
			gl.glVertex3d(-10, 20, 10); // Top Left
			gl.glVertex3d(10, 20, 10); // Top Right
			gl.glVertex3d(10, 15, 10); // Bottom Right
			gl.glVertex3d(-10, 15, 10); // Bottom Left
		gl.glEnd();	
	}

}
