package EnvObjet;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;

public class Laptop extends ObjetGen{

	public Laptop() {
		this.orientation= 0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawObjet(GL2 gl, GLUT glut, boolean on) {
		// TODO Auto-generated method stub
		//clavier
		gl.glBegin(GL2.GL_QUADS);
			gl.glColor3f(0, 0, 0);
			gl.glVertex3d(posX -2, posY - 2, 2); // Top Left
			gl.glVertex3d(posX -2, posY + 2, 2); // Top Right
			gl.glVertex3d(posX +2, posY + 2, 2); // Bottom Right
			gl.glVertex3d(posX +2, posY -2, 2); // Bottom Left
		gl.glEnd();
		
		//ecran
		gl.glBegin(GL2.GL_QUADS);
			gl.glColor3f(0, 0, 0);
			gl.glVertex3d(posX -2, posY -2, 2); // Top Left
			gl.glVertex3d(posX -2, posY +2, 2); // Top Right
			gl.glVertex3d(posX -3, posY +2, 4); // Bottom Right
			gl.glVertex3d(posX -3, posY -2, 4); // Bottom Left
		gl.glEnd();
		
	}

}
