package EnvObjet;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;

public class Chaise extends ObjetGen{

	public Chaise () {
		this.orientation = 0;
	}

	@Override
	public void drawObjet(GL2 gl, GLUT glut) {

		//dessin de la chaise
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(0.2f,0.2f,0.2f); // set the color of the quad
		gl.glVertex3d(posX -1, posY -1, 1); // Top Left
		gl.glVertex3d(posX -1, posY +1, 1); // Top Right
		gl.glVertex3d(posX +1, posY +1, 1); // Bottom Right
		gl.glVertex3d(posX +1, posY -1, 1); // Bottom Left
		gl.glEnd();

		//pied1
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3d(posX -1, posY -1, 0); // Top Left
		gl.glVertex3d(posX -1, posY +1, 0); // Top Right
		gl.glVertex3d(posX -1, posY +1, 1); // Bottom Right
		gl.glVertex3d(posX -1, posY -1, 1); // Bottom Left
		gl.glEnd();

		//pied2
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3d(posX + 1, posY -1, 0); // Top Left
		gl.glVertex3d(posX + 1, posY +1, 0); // Top Right
		gl.glVertex3d(posX + 1, posY +1, 1); // Bottom Right
		gl.glVertex3d(posX + 1, posY -1, 1); // Bottom Left
		gl.glEnd();
		gl.glColor3d(0.13f, 0.26f, 0.48f); // set the color of the quad
		if (orientation == 0 ){
			//dessin dossier
			gl.glBegin(GL2.GL_QUADS);

			gl.glVertex3d(posX -1, posY -1, 1); // Top Left
			gl.glVertex3d(posX -1, posY -1.5, 3); // Top Right
			gl.glVertex3d(posX +1, posY -1.5, 3); // Bottom Right
			gl.glVertex3d(posX +1, posY -1, 1); // Bottom Left
			gl.glEnd();
		}
		if (orientation == 1){

			//dessin dossier
			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX -1, posY -1, 1); // Top Left
			gl.glVertex3d(posX -1.5, posY -1, 3); // Top Right
			gl.glVertex3d(posX -1.5, posY +1, 3); // Bottom Right
			gl.glVertex3d(posX -1, posY +1, 1); // Bottom Left
			gl.glEnd();
		}
		if (orientation == 2){

			//dessin dossier
			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX -1, posY +1, 1); // Top Left
			gl.glVertex3d(posX -1, posY +1.5, 3); // Top Right
			gl.glVertex3d(posX +1, posY +1.5, 3); // Bottom Right
			gl.glVertex3d(posX +1, posY +1, 1); // Bottom Left
			gl.glEnd();
		}
		if (orientation == 3){

			//dessin dossier
			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX +1, posY -1, 1); // Top Left
			gl.glVertex3d(posX +1.5, posY -1, 3); // Top Right
			gl.glVertex3d(posX +1.5, posY +1, 3); // Bottom Right
			gl.glVertex3d(posX +1, posY +1, 1); // Bottom Left
			gl.glEnd();
		}

	}

}
