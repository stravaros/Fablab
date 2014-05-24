package EnvObjet;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.GLException;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class Table extends ObjetGen {
	private Texture text_table;
	File table = new File("ressources/textures/table_comp.jpg") ;
	
	public Table (){
		this.orientation= 0;
	}

	@Override
	public void drawObjet(GL2 gl, GLUT glut, boolean on) {
		// TODO Auto-generated method stub
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3f(1, 1, 1); // set the color of the quad
		
			gl.glVertex3d(posX -4, posY -4, 2); // Top Left
			gl.glVertex3d(posX + 4, posY -4, 2); // Top Right
			gl.glVertex3d(posX + 4, posY + 4, 2); // Bottom Right
			gl.glVertex3d(posX -4, posY + 4, 2); // Bottom Left
		gl.glEnd();
		gl.glEnable(GL2.GL_TEXTURE_2D);
		
		//pied1
		gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX -4, posY + 4, 0); // Top Left
			gl.glVertex3d(posX -3, posY + 4, 0); // Top Right
			gl.glVertex3d(posX -3, posY + 4, 2); // Bottom Right
			gl.glVertex3d(posX -4, posY + 4, 2); // Bottom Left
		gl.glEnd();
		
		//pied2
		gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX -4, posY -4, 0); // Top Left
			gl.glVertex3d(posX -3, posY -4, 0); // Top Right
			gl.glVertex3d(posX -3, posY -4, 2); // Bottom Right
			gl.glVertex3d(posX -4, posY -4, 2); // Bottom Left
		gl.glEnd();
		
		//pied3
		gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX + 4, posY -4, 0); // Top Left
			gl.glVertex3d(posX + 3, posY -4, 0); // Top Right
			gl.glVertex3d(posX + 3, posY -4, 2); // Bottom Right
			gl.glVertex3d(posX + 4, posY -4, 2); // Bottom Left
		gl.glEnd();
		
		//pied4
		gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX +4, posY +4, 0); // Top Left
			gl.glVertex3d(posX +3, posY +4, 0); // Top Right
			gl.glVertex3d(posX +3, posY +4, 2); // Bottom Right
			gl.glVertex3d(posX +4, posY +4, 2); // Bottom Left
		gl.glEnd();

	    
	    if (orientation == 0 || orientation == 2){
	    //dessin de la chaise
	    gl.glBegin(GL2.GL_QUADS);
	    gl.glColor3d(1, 0, 0); // set the color of the quad
			gl.glVertex3d(posX -2, posY -6, 1); // Top Left
			gl.glVertex3d(posX -2, posY -7, 1); // Top Right
			gl.glVertex3d(posX +2, posY -7, 1); // Bottom Right
			gl.glVertex3d(posX +2, posY -6, 1); // Bottom Left
		gl.glEnd();
		
		//pied1
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(1, 0, 0); // set the color of the quad
			gl.glVertex3d(posX -2, posY -6, 0); // Top Left
			gl.glVertex3d(posX -2, posY -7, 0); // Top Right
			gl.glVertex3d(posX -2, posY -7, 1); // Bottom Right
			gl.glVertex3d(posX -2, posY -6, 1); // Bottom Left
		gl.glEnd();
		
		//pied2
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(1, 0, 0); // set the color of the quad
			gl.glVertex3d(posX + 2, posY -6, 0); // Top Left
			gl.glVertex3d(posX + 2, posY -7, 0); // Top Right
			gl.glVertex3d(posX + 2, posY -7, 1); // Bottom Right
			gl.glVertex3d(posX + 2, posY -6, 1); // Bottom Left
		gl.glEnd();
	    } else{
	    	//dessin de la chaise
		    gl.glBegin(GL2.GL_QUADS);
		    gl.glColor3d(1, 0, 0); // set the color of the quad
				gl.glVertex3d(posX -6, posY -2, 1); // Top Left
				gl.glVertex3d(posX -7, posY -2, 1); // Top Right
				gl.glVertex3d(posX -7, posY +2, 1); // Bottom Right
				gl.glVertex3d(posX -6, posY +2, 1); // Bottom Left
			gl.glEnd();
			
			//pied1
			gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(1, 0, 0); // set the color of the quad
				gl.glVertex3d(posX -6, posY -2, 0); // Top Left
				gl.glVertex3d(posX -7, posY -2, 0); // Top Right
				gl.glVertex3d(posX -7, posY -2, 1); // Bottom Right
				gl.glVertex3d(posX -6, posY -2, 1); // Bottom Left
			gl.glEnd();
			
			//pied2
			gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(1, 0, 0); // set the color of the quad
				gl.glVertex3d(posX -6, posY + 2, 0); // Top Left
				gl.glVertex3d(posX -7, posY + 2, 0); // Top Right
				gl.glVertex3d(posX -7, posY + 2, 1); // Bottom Right
				gl.glVertex3d(posX -6, posY + 2, 1); // Bottom Left
			gl.glEnd();
			
	    }
	}
	
}
