package EnvObjet;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;

public class Meuble extends ObjetGen {

	public Meuble() {
		this.orientation= 0;
	}

	@Override
	public void drawObjet(GL2 gl, GLUT glut) {
		if (orientation == 0 || orientation == 2){

			gl.glBegin(GL2.GL_QUADS);

			gl.glVertex3d(posX -10, posY +2, 0); // Top Left
			gl.glVertex3d(posX -10, posY -2, 0); // Top Right
			gl.glVertex3d(posX -10, posY -2, 10); // Bottom Right
			gl.glVertex3d(posX -10, posY +2, 10); // Bottom Left
			gl.glEnd();

			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX -5, posY +2, 0); // Top Left
			gl.glVertex3d(posX -5, posY -2, 0); // Top Right
			gl.glVertex3d(posX -5, posY -2, 10); // Bottom Right
			gl.glVertex3d(posX -5, posY +2, 10); // Bottom Left
			gl.glEnd();

			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX + 5, posY +2, 0); // Top Left
			gl.glVertex3d(posX + 5, posY -2, 0); // Top Right
			gl.glVertex3d(posX + 5, posY -2, 10); // Bottom Right
			gl.glVertex3d(posX + 5, posY +2, 10); // Bottom Left
			gl.glEnd();

			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX + 10, posY +2, 0); // Top Left
			gl.glVertex3d(posX + 10, posY -2, 0); // Top Right
			gl.glVertex3d(posX + 10, posY -2, 10); // Bottom Right
			gl.glVertex3d(posX + 10, posY +2, 10); // Bottom Left
			gl.glEnd();

			//horizontal
			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX -10, posY +2, 0); // Top Left
			gl.glVertex3d(posX + 10, posY +2, 0); // Top Right
			gl.glVertex3d(posX + 10, posY -2, 0); // Bottom Right
			gl.glVertex3d(posX -10, posY -2, 0); // Bottom Left
			gl.glEnd();

			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX -10, posY +2, 2); // Top Left
			gl.glVertex3d(posX + 10, posY +2, 2); // Top Right
			gl.glVertex3d(posX + 10, posY -2, 2); // Bottom Right
			gl.glVertex3d(posX -10, posY -2, 2); // Bottom Left
			gl.glEnd();

			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX -10, posY +2, 8); // Top Left
			gl.glVertex3d(posX + 10, posY +2, 8); // Top Right
			gl.glVertex3d(posX + 10, posY -2, 8); // Bottom Right
			gl.glVertex3d(posX -10, posY -2, 8); // Bottom Left
			gl.glEnd();

			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX -10, posY +2, 10); // Top Left
			gl.glVertex3d(posX +10, posY +2, 10); // Top Right
			gl.glVertex3d(posX +10, posY -2, 10); // Bottom Right
			gl.glVertex3d(posX -10, posY -2, 10); // Bottom Left
			gl.glEnd();	
		}
		if (orientation == 1 || orientation == 3){
			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX +2, posY -10, 0); // Top Left
			gl.glVertex3d(posX -2, posY -10, 0); // Top Right
			gl.glVertex3d(posX -2, posY -10, 10); // Bottom Right
			gl.glVertex3d(posX +2, posY -10, 10); // Bottom Left
			gl.glEnd();

			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX +2, posY -5, 0); // Top Left
			gl.glVertex3d(posX -2, posY -5, 0); // Top Right
			gl.glVertex3d(posX -2, posY -5, 10); // Bottom Right
			gl.glVertex3d(posX +2, posY -5, 10); // Bottom Left
			gl.glEnd();

			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX + 2, posY +5, 0); // Top Left
			gl.glVertex3d(posX -2, posY +5, 0); // Top Right
			gl.glVertex3d(posX -2, posY +5, 10); // Bottom Right
			gl.glVertex3d(posX + 2, posY +5, 10); // Bottom Left
			gl.glEnd();

			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX + 2, posY +10, 0); // Top Left
			gl.glVertex3d(posX -2, posY +10, 0); // Top Right
			gl.glVertex3d(posX -2 , posY +10, 10); // Bottom Right
			gl.glVertex3d(posX + 2, posY +10, 10); // Bottom Left
			gl.glEnd();

			//horizontal
			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX +2, posY -10, 0); // Top Left
			gl.glVertex3d(posX + 2, posY +10, 0); // Top Right
			gl.glVertex3d(posX -2, posY +10, 0); // Bottom Right
			gl.glVertex3d(posX -2, posY -10, 0); // Bottom Left
			gl.glEnd();

			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX +2, posY -10, 2); // Top Left
			gl.glVertex3d(posX + 2, posY +10, 2); // Top Right
			gl.glVertex3d(posX -2, posY +10, 2); // Bottom Right
			gl.glVertex3d(posX -2, posY -10, 2); // Bottom Left
			gl.glEnd();

			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX +2, posY -10, 8); // Top Left
			gl.glVertex3d(posX +2, posY +10, 8); // Top Right
			gl.glVertex3d(posX -2, posY +10, 8); // Bottom Right
			gl.glVertex3d(posX -2, posY -10, 8); // Bottom Left
			gl.glEnd();

			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX +2, posY -10, 10); // Top Left
			gl.glVertex3d(posX + 2, posY +10, 10); // Top Right
			gl.glVertex3d(posX -2, posY +10, 10); // Bottom Right
			gl.glVertex3d(posX -2, posY -10, 10); // Bottom Left
			gl.glEnd();
		}
	}



}
