package EnvObjet;

import javax.media.opengl.GL2;
import javax.media.opengl.GLException;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;

public class Table extends ObjetGen {
	

	public Table (int X, int Y){
		super (X, Y);
	}

	@Override
	public void drawObjet(GL2 gl, GLUT glut, Texture text_table) {
		// TODO Auto-generated method stub
		try {
			
			text_table.enable(gl);
			text_table.bind(gl);
		} catch (GLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3f(1, 1, 1); // set the color of the quad
		
		gl.glTexCoord2d(0, 0); gl.glVertex3d(posX, posY, 2); // Top Left
		gl.glTexCoord2d(1, 0); gl.glVertex3d(posX + 8, posY, 2); // Top Right
		gl.glTexCoord2d(1, 1); gl.glVertex3d(posX + 8, posY + 8, 2); // Bottom Right
		gl.glTexCoord2d(0, 1); gl.glVertex3d(posX, posY + 8, 2); // Bottom Left
		gl.glEnd();
		gl.glEnable(GL2.GL_TEXTURE_2D);
		
		//pied1
		gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX, posY + 8, 0); // Top Left
			gl.glVertex3d(posX + 1, posY + 8, 0); // Top Right
			gl.glVertex3d(posX + 1, posY + 8, 2); // Bottom Right
			gl.glVertex3d(posX, posY + 8, 2); // Bottom Left
		gl.glEnd();
		
		//pied2
		gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX, posY, 0); // Top Left
			gl.glVertex3d(posX + 1, posY, 0); // Top Right
			gl.glVertex3d(posX + 1, posY, 2); // Bottom Right
			gl.glVertex3d(posX, posY, 2); // Bottom Left
		gl.glEnd();
		
		//pied3
		gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX + 8, posY, 0); // Top Left
			gl.glVertex3d(posX + 7, posY, 0); // Top Right
			gl.glVertex3d(posX + 7, posY, 2); // Bottom Right
			gl.glVertex3d(posX + 8, posY, 2); // Bottom Left
		gl.glEnd();
		
		//pied4
		gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX + 8, posY +8, 0); // Top Left
			gl.glVertex3d(posX +7, posY +8, 0); // Top Right
			gl.glVertex3d(posX +7, posY + 8, 2); // Bottom Right
			gl.glVertex3d(posX + 8, posY +8, 2); // Bottom Left
		gl.glEnd();
		
		gl.glPushMatrix();
		gl.glRotatef(90f ,1f, 0f, 0f);
		gl.glTranslatef(posX + 4f ,3f, -posY -4f); //(x ,z,y)
		gl.glColor3d(0, 1, 0);
	    glut.glutSolidTeapot(1.5);     // middle teapot
	    gl.glPopMatrix();
		
	}
}
