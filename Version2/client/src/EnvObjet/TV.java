package EnvObjet;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.GLException;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class TV extends ObjetGen {

	private Texture text_tv;
	File tv = new File("ressources/textures/tv.jpg") ;
	
	public TV (String orientation){
		this.orientation= orientation;
	}
	
	public TV(int X, int Y, String orientation) {
		if (X > 15)
			posX = 15;
		else if (X < -15)
			posX = -15;
		else
			posX = X;
		
		if (Y > 15)
			posY = 15;
		else if (Y < -15)
			posY = -15;
		else
			posY = Y;
		
		this.orientation = orientation;
		isOnable = true;
		
	}

	@Override
	public void drawObjet(GL2 gl, GLUT glut, Texture text, boolean on) {
		// TODO Auto-generated method stub
		 //dessin du meuble
	    gl.glBegin(GL2.GL_QUADS);
	    gl.glColor3d(1, 0, 0); // set the color of the quad
			gl.glVertex3d(posX + 0, posY-1, 2); // Top Left
			gl.glVertex3d(posX + 0, posY-3, 2); // Top Right
			gl.glVertex3d(posX + 8,posY -3, 2); // Bottom Right
			gl.glVertex3d(posX + 8, posY-1, 2); // Bottom Left
		gl.glEnd();
		
		//pied1
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(1, 0, 0); // set the color of the quad
			gl.glVertex3d(posX ,posY -1, 0); // Top Left
			gl.glVertex3d( posX ,posY -3, 0); // Top Right
			gl.glVertex3d(posX , posY-3, 2); // Bottom Right
			gl.glVertex3d(posX ,posY -1, 2); // Bottom Left
		gl.glEnd();
		
		//pied2
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(1, 0, 0); // set the color of the quad
			gl.glVertex3d(posX + 8, posY -1, 0); // Top Left
			gl.glVertex3d(posX + 8,posY -3, 0); // Top Right
			gl.glVertex3d(posX + 8,posY -3, 2); // Bottom Right
			gl.glVertex3d(posX + 8,posY -1, 2); // Bottom Left
		gl.glEnd();
		
		//TV
	    gl.glBegin(GL2.GL_QUADS);
	    gl.glColor3d(1, 0, 0); // set the color of the quad
			gl.glVertex3d(posX + 2, posY-2, 2); // Top Left
			gl.glVertex3d(posX + 2, posY-2, 5); // Top Right
			gl.glVertex3d(posX + 6,posY -2, 5); // Bottom Right
			gl.glVertex3d(posX + 6, posY-2, 2); // Bottom Left
		gl.glEnd();
		
				//gl.glBegin(GL2.GL_QUADS);
	/*	gl.glColor3d(1, 0, 0); // set the color of the quad
			gl.glVertex3d(posX ,posY -1, 0); // Top Left
			gl.glVertex3d( posX ,posY -3, 0); // Top Right
			gl.glVertex3d(posX , posY-3, 2); // Bottom Right
			gl.glVertex3d(posX ,posY -1, 2); // Bottom Left
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(1, 0, 0); // set the color of the quad
			gl.glVertex3d(posX + 8, posY -1, 0); // Top Left
			gl.glVertex3d(posX + 8,posY -3, 0); // Top Right
			gl.glVertex3d(posX + 8,posY -3, 2); // Bottom Right
			gl.glVertex3d(posX + 8,posY -1, 2); // Bottom Left
		gl.glEnd();*/
		if (on == true){
		try {
			text_tv= TextureIO.newTexture(tv, true);
			text_tv.enable(gl);
			text_tv.bind(gl);
		} catch (GLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
				
		
		//texture gl.glBegin(GL2.GL_QUADS);//fenetre
		 gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(1, 1, 1); // set the color of the quad
			gl.glTexCoord2d(0, 0); gl.glVertex3d(posX + 2, posY-2, 2); 		
			gl.glTexCoord2d(0, 1); gl.glVertex3d(posX + 2, posY-2, 5);		
			gl.glTexCoord2d(1, 1); gl.glVertex3d(posX + 6, posY-2, 5); 	
			gl.glTexCoord2d(1, 0); gl.glVertex3d(posX + 6, posY-2, 2);  
		gl.glEnd();
		
	
	} 
	}

}
