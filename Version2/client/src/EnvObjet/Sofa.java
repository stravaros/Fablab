package EnvObjet;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;

public class Sofa extends ObjetGen{

	public Sofa (){
		this.orientation= 0;
	}


	@Override
	public void drawObjet(GL2 gl, GLUT glut) {

		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(0, 0, 0); // set the color of the quad
		gl.glVertex3d(posX -8, posY-3, 2); // Top Left
		gl.glVertex3d(posX -8, posY+3, 2); // Top Right
		gl.glVertex3d(posX + 8,posY +3, 2); // Bottom Right
		gl.glVertex3d(posX + 8, posY-3, 2); // Bottom Left
		gl.glEnd();

		//pied1
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(1, 1, 1); // set the color of the quad
		gl.glVertex3d(posX-8 ,posY -3, 0); // Top Left
		gl.glVertex3d( posX-8 ,posY +3, 0); // Top Right
		gl.glVertex3d(posX-8 , posY+3, 4); // Bottom Right
		gl.glVertex3d(posX-8 ,posY -3, 4); // Bottom Left
		gl.glEnd();

		//pied2
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(1, 1, 1); // set the color of the quad
		gl.glVertex3d(posX+8 ,posY -3, 0); // Top Left
		gl.glVertex3d( posX+8 ,posY +3, 0); // Top Right
		gl.glVertex3d(posX+8 , posY+3, 4); // Bottom Right
		gl.glVertex3d(posX+8 ,posY -3, 4); // Bottom Left
		gl.glEnd();

		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(1, 1, 1); // set the color of the quad
		gl.glVertex3d(posX-8 ,posY -3, 0); // Top Left
		gl.glVertex3d( posX-8 ,posY -3, 5); // Top Right
		gl.glVertex3d(posX+8 , posY-3, 5); // Bottom Right
		gl.glVertex3d(posX+8 ,posY -3, 0); // Bottom Left
		gl.glEnd();
	}

}
