package EnvObjet;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;

public class Meuble extends ObjetGen {
	public Meuble(String orientation) {
		this.orientation = orientation;
		// TODO Auto-generated constructor stub
	}
	
	public Meuble (int X, int Y, String orientation){
		if (X > 10)
			posX = 10;
		else if (X < -10)
			posX = -10;
		else
			posX = X;
		
		if (Y > 10)
			posY = 10;
		else if (Y < -10)
			posY = -10;
		else
			posY = Y;
		
		this.orientation = orientation;
		isOnable = false;
	}

	

	@Override
	public void drawObjet(GL2 gl, GLUT glut,  Texture text, boolean on) {
		if (orientation == "Horizontal"){
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(0, 0, 1); // set the color of the quad
			gl.glVertex3d(posX -10, posY +2, 0); // Top Left
			gl.glVertex3d(posX -10, posY -2, 0); // Top Right
			gl.glVertex3d(posX -10, posY -2, 10); // Bottom Right
			gl.glVertex3d(posX -10, posY +2, 10); // Bottom Left
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(0, 1, 1); // set the color of the quad
			gl.glVertex3d(posX -5, posY +2, 0); // Top Left
			gl.glVertex3d(posX -5, posY -2, 0); // Top Right
			gl.glVertex3d(posX -5, posY -2, 10); // Bottom Right
			gl.glVertex3d(posX -5, posY +2, 10); // Bottom Left
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(0, 1, 0); // set the color of the quad
			gl.glVertex3d(posX + 5, posY +2, 0); // Top Left
			gl.glVertex3d(posX + 5, posY -2, 0); // Top Right
			gl.glVertex3d(posX + 5, posY -2, 10); // Bottom Right
			gl.glVertex3d(posX + 5, posY +2, 10); // Bottom Left
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(0, 0, 1); // set the color of the quad
			gl.glVertex3d(posX + 10, posY +2, 0); // Top Left
			gl.glVertex3d(posX + 10, posY -2, 0); // Top Right
			gl.glVertex3d(posX + 10, posY -2, 10); // Bottom Right
			gl.glVertex3d(posX + 10, posY +2, 10); // Bottom Left
		gl.glEnd();
		
		//horizontal
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(0, 0, 1); // set the color of the quad
			gl.glVertex3d(posX -10, posY +2, 0); // Top Left
			gl.glVertex3d(posX + 10, posY +2, 0); // Top Right
			gl.glVertex3d(posX + 10, posY -2, 0); // Bottom Right
			gl.glVertex3d(posX -10, posY -2, 0); // Bottom Left
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(0, 0, 1); // set the color of the quad
			gl.glVertex3d(posX -10, posY +2, 2); // Top Left
			gl.glVertex3d(posX + 10, posY +2, 2); // Top Right
			gl.glVertex3d(posX + 10, posY -2, 2); // Bottom Right
			gl.glVertex3d(posX -10, posY -2, 2); // Bottom Left
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(0, 0, 1); // set the color of the quad
			gl.glVertex3d(posX -10, posY +2, 8); // Top Left
			gl.glVertex3d(posX + 10, posY +2, 8); // Top Right
			gl.glVertex3d(posX + 10, posY -2, 8); // Bottom Right
			gl.glVertex3d(posX -10, posY -2, 8); // Bottom Left
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(0, 0, 1); // set the color of the quad
			gl.glVertex3d(posX -10, posY +2, 10); // Top Left
			gl.glVertex3d(posX +10, posY +2, 10); // Top Right
			gl.glVertex3d(posX +10, posY -2, 10); // Bottom Right
			gl.glVertex3d(posX -10, posY -2, 10); // Bottom Left
		gl.glEnd();	
		}
		if (orientation == "Vertical"){
			gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(0, 0, 1); // set the color of the quad
				gl.glVertex3d(posX +2, posY -10, 0); // Top Left
				gl.glVertex3d(posX -2, posY -10, 0); // Top Right
				gl.glVertex3d(posX -2, posY -10, 10); // Bottom Right
				gl.glVertex3d(posX +2, posY -10, 10); // Bottom Left
			gl.glEnd();
			
			gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(0, 1, 1); // set the color of the quad
				gl.glVertex3d(posX +2, posY -5, 0); // Top Left
				gl.glVertex3d(posX -2, posY -5, 0); // Top Right
				gl.glVertex3d(posX -2, posY -5, 10); // Bottom Right
				gl.glVertex3d(posX +2, posY -5, 10); // Bottom Left
			gl.glEnd();
			
			gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(0, 1, 0); // set the color of the quad
				gl.glVertex3d(posX + 2, posY +5, 0); // Top Left
				gl.glVertex3d(posX -2, posY +5, 0); // Top Right
				gl.glVertex3d(posX -2, posY +5, 10); // Bottom Right
				gl.glVertex3d(posX + 2, posY +5, 10); // Bottom Left
			gl.glEnd();
			
			gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(0, 0, 1); // set the color of the quad
				gl.glVertex3d(posX + 2, posY +10, 0); // Top Left
				gl.glVertex3d(posX -2, posY +10, 0); // Top Right
				gl.glVertex3d(posX -2 , posY +10, 10); // Bottom Right
				gl.glVertex3d(posX + 2, posY +10, 10); // Bottom Left
			gl.glEnd();
			
			//horizontal
			gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(0, 0, 1); // set the color of the quad
				gl.glVertex3d(posX +2, posY -10, 0); // Top Left
				gl.glVertex3d(posX + 2, posY +10, 0); // Top Right
				gl.glVertex3d(posX -2, posY +10, 0); // Bottom Right
				gl.glVertex3d(posX -2, posY -10, 0); // Bottom Left
			gl.glEnd();
			
			gl.glBegin(GL2.GL_QUADS);
				gl.glColor3d(0, 0, 1); // set the color of the quad
				gl.glVertex3d(posX +2, posY -10, 2); // Top Left
				gl.glVertex3d(posX + 2, posY +10, 2); // Top Right
				gl.glVertex3d(posX -2, posY +10, 2); // Bottom Right
				gl.glVertex3d(posX -2, posY -10, 2); // Bottom Left
			gl.glEnd();
			
			gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(0, 0, 1); // set the color of the quad
				gl.glVertex3d(posX -10, posY +2, 8); // Top Left
				gl.glVertex3d(posX + 10, posY +2, 8); // Top Right
				gl.glVertex3d(posX + 10, posY -2, 8); // Bottom Right
				gl.glVertex3d(posX -10, posY -2, 8); // Bottom Left
			gl.glEnd();
			
			gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(0, 0, 1); // set the color of the quad
				gl.glVertex3d(posX +2, posY -10, 10); // Top Left
				gl.glVertex3d(posX + 2, posY +10, 10); // Top Right
				gl.glVertex3d(posX -2, posY +10, 10); // Bottom Right
				gl.glVertex3d(posX -2, posY -10, 10); // Bottom Left
			gl.glEnd();
		}
	}



}
