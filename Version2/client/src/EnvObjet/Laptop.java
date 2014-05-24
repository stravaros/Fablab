package EnvObjet;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.GLException;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class Laptop extends ObjetGen{

	private Texture text_laptop;
	private File laptop = new File("ressources/textures/Ensimag.jpg") ;
	private boolean isLoadedTexture = false;

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

		if (on == true){
			if (!getIsLoadedTexture())
				loadedTexture(gl);
			text_laptop.enable(gl);
			text_laptop.bind(gl);	

			if (orientation == 0){
				//ecran
				gl.glBegin(GL2.GL_QUADS);
				gl.glColor3f(1, 1, 1);
				gl.glTexCoord2d(0, 0); gl.glVertex3d(posX -2, posY -2, 2); // Top Left
				gl.glTexCoord2d(1, 0); gl.glVertex3d(posX +2, posY -2, 2); // Top Right
				gl.glTexCoord2d(1, 1); gl.glVertex3d(posX +2, posY -3, 4); // Bottom Right
				gl.glTexCoord2d(0, 1); gl.glVertex3d(posX -2, posY -3, 4); // Bottom Left
				gl.glEnd();
			}
			if (orientation == 1){
				//ecran
				gl.glBegin(GL2.GL_QUADS);
				gl.glColor3f(1, 1, 1);
				gl.glTexCoord2d(0, 0); gl.glVertex3d(posX -2, posY -2, 2); // Top Left
				gl.glTexCoord2d(1, 0); gl.glVertex3d(posX -2, posY +2, 2); // Top Right
				gl.glTexCoord2d(1, 1); gl.glVertex3d(posX -3, posY +2, 4); // Bottom Right
				gl.glTexCoord2d(0, 1); gl.glVertex3d(posX -3, posY -2, 4); // Bottom Left
				gl.glEnd();
			}
			if (orientation == 2){
				//ecran
				gl.glBegin(GL2.GL_QUADS);
				gl.glColor3f(1, 1, 1);
				gl.glTexCoord2d(0, 0); gl.glVertex3d(posX -2, posY +2, 2); // Top Left
				gl.glTexCoord2d(1, 0); gl.glVertex3d(posX +2, posY +2, 2); // Top Right
				gl.glTexCoord2d(1, 1); gl.glVertex3d(posX +2, posY +3, 4); // Bottom Right
				gl.glTexCoord2d(0, 1); gl.glVertex3d(posX -2, posY +3, 4); // Bottom Left
				gl.glEnd();
			}
			if (orientation == 3){
				//ecran
				gl.glBegin(GL2.GL_QUADS);
				gl.glColor3f(1, 1, 1);
				gl.glTexCoord2d(0, 0); gl.glVertex3d(posX +2, posY -2, 2); // Top Left
				gl.glTexCoord2d(1, 0); gl.glVertex3d(posX +2, posY +2, 2); // Top Right
				gl.glTexCoord2d(1, 1); gl.glVertex3d(posX +3, posY +2, 4); // Bottom Right
				gl.glTexCoord2d(0, 1); gl.glVertex3d(posX +3, posY -2, 4); // Bottom Left
				gl.glEnd();
			}
			
			gl.glDisable(GL2.GL_TEXTURE_2D);
		}
		else{
			if (orientation == 0){
				//ecran
				gl.glBegin(GL2.GL_QUADS);
				gl.glColor3f(1, 1, 1);
				gl.glVertex3d(posX -2, posY -2, 2); // Top Left
				gl.glVertex3d(posX +2, posY -2, 2); // Top Right
				gl.glVertex3d(posX +2, posY -3, 4); // Bottom Right
				gl.glVertex3d(posX -2, posY -3, 4); // Bottom Left
				gl.glEnd();
			}
			if (orientation == 1) {
				//ecran
				gl.glBegin(GL2.GL_QUADS);
				gl.glColor3f(1, 1, 1);
				gl.glVertex3d(posX -2, posY -2, 2); // Top Left
				gl.glVertex3d(posX -2, posY +2, 2); // Top Right
				gl.glVertex3d(posX -3, posY +2, 4); // Bottom Right
				gl.glVertex3d(posX -3, posY -2, 4); // Bottom Left
				gl.glEnd();
			}
			if (orientation == 2){
				//ecran
				gl.glBegin(GL2.GL_QUADS);
				gl.glColor3f(1, 1, 1);
				gl.glVertex3d(posX -2, posY +2, 2); // Top Left
				gl.glVertex3d(posX +2, posY +2, 2); // Top Right
				gl.glVertex3d(posX +2, posY +3, 4); // Bottom Right
				gl.glVertex3d(posX -2, posY +3, 4); // Bottom Left
				gl.glEnd();
			}
			if (orientation == 3) {
				//ecran
				gl.glBegin(GL2.GL_QUADS);
				gl.glColor3f(1, 1, 1);
				gl.glVertex3d(posX +2, posY -2, 2); // Top Left
				gl.glVertex3d(posX +2, posY +2, 2); // Top Right
				gl.glVertex3d(posX +3, posY +2, 4); // Bottom Right
				gl.glVertex3d(posX +3, posY -2, 4); // Bottom Left
				gl.glEnd();
			}

		}

	}

	public void loadedTexture (GL2 gl){
		gl.glEnable(GL2.GL_TEXTURE_2D);
		try {
			text_laptop= TextureIO.newTexture(laptop, true);

		} catch (GLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isLoadedTexture = true;
	}

	public boolean getIsLoadedTexture () {
		return isLoadedTexture;
	}

}
