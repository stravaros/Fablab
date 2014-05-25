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
	File tv = new File("ressources/textures/LBF.png") ;
	private boolean isLoadedTexture = false;

	public TV (){
		this.orientation= 0;
	}

	@Override
	public void drawObjet(GL2 gl, GLUT glut) {
		//dessin du meuble
		if (orientation == 0 || orientation == 2){
			gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(0.4f, 0.4f, 0.4f); // set the color of the quad
			gl.glVertex3d(posX -4, posY-1, 2); // Top Left
			gl.glVertex3d(posX -4, posY+1, 2); // Top Right
			gl.glVertex3d(posX + 4,posY +1, 2); // Bottom Right
			gl.glVertex3d(posX + 4, posY-1, 2); // Bottom Left
			gl.glEnd();

			//pied1
			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX -4 ,posY -1, 0); // Top Left
			gl.glVertex3d( posX -4 ,posY -1, 2); // Top Right
			gl.glVertex3d(posX -4 , posY+1, 2); // Bottom Right
			gl.glVertex3d(posX -4 ,posY +1, 0); // Bottom Left
			gl.glEnd();

			//pied2
			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX + 4, posY -1, 0); // Top Left
			gl.glVertex3d(posX + 4,posY -1, 2); // Top Right
			gl.glVertex3d(posX + 4,posY +1, 2); // Bottom Right
			gl.glVertex3d(posX + 4,posY +1, 0); // Bottom Left
			gl.glEnd();


			//TV
			gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(0, 0, 0); // set the color of the quad
			gl.glVertex3d(posX - 2, posY, 2); // Top Left
			gl.glVertex3d(posX - 2, posY, 5); // Top Right
			gl.glVertex3d(posX + 2 ,posY, 5); // Bottom Right
			gl.glVertex3d(posX + 2, posY, 2); // Bottom Left
			gl.glEnd();
			if (OnOff == true){
				if (!getIsLoadedTexture())
					loadedTexture(gl);
				text_tv.enable(gl);
				text_tv.bind(gl);	

				//texture gl.glBegin(GL2.GL_QUADS);//fenetre
				gl.glBegin(GL2.GL_QUADS);
				gl.glColor3d(1, 1, 1); // set the color of the quad
				gl.glTexCoord2d(0, 0); gl.glVertex3d(posX - 2, posY, 2); 		
				gl.glTexCoord2d(0, 1); gl.glVertex3d(posX - 2, posY, 5);		
				gl.glTexCoord2d(1, 1); gl.glVertex3d(posX + 2, posY, 5); 	
				gl.glTexCoord2d(1, 0); gl.glVertex3d(posX + 2, posY, 2);  
				gl.glEnd();
				gl.glDisable(GL2.GL_TEXTURE_2D);
			} 
		}
		if (orientation == 1 || orientation == 3){
			gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(0.4f, 0.4f, 0.4f); // set the color of the quad
			gl.glVertex3d(posX -1, posY-4, 2); // Top Left
			gl.glVertex3d(posX +1, posY-4, 2); // Top Right
			gl.glVertex3d(posX + 1,posY +4, 2); // Bottom Right
			gl.glVertex3d(posX -1, posY+4, 2); // Bottom Left
			gl.glEnd();

			//pied1
			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX -1 ,posY -4, 0); // Top Left
			gl.glVertex3d( posX -1 ,posY -4, 2); // Top Right
			gl.glVertex3d(posX +1 , posY-4, 2); // Bottom Right
			gl.glVertex3d(posX +1 ,posY -4, 0); // Bottom Left
			gl.glEnd();

			//pied2
			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3d(posX -1, posY +4, 0); // Top Left
			gl.glVertex3d(posX -1,posY +4, 2); // Top Right
			gl.glVertex3d(posX + 1,posY +4, 2); // Bottom Right
			gl.glVertex3d(posX + 1,posY +4, 0); // Bottom Left
			gl.glEnd();


			//TV
			gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(0, 0, 0); // set the color of the quad
			gl.glVertex3d(posX , posY-2, 2); // Top Left
			gl.glVertex3d(posX , posY-2, 5); // Top Right
			gl.glVertex3d(posX ,posY+2, 5); // Bottom Right
			gl.glVertex3d(posX , posY+2, 2); // Bottom Left
			gl.glEnd();
			if (OnOff == true){
				if (!getIsLoadedTexture())
					loadedTexture(gl);
				text_tv.enable(gl);
				text_tv.bind(gl);	

				gl.glBegin(GL2.GL_QUADS);
				gl.glColor3d(1, 1, 1); // set the color of the quad
				gl.glTexCoord2d(0, 0); gl.glVertex3d(posX , posY-2, 2); 		
				gl.glTexCoord2d(0, 1); gl.glVertex3d(posX , posY-2, 5);		
				gl.glTexCoord2d(1, 1); gl.glVertex3d(posX , posY+2, 5); 	
				gl.glTexCoord2d(1, 0); gl.glVertex3d(posX , posY+2, 2);  
				gl.glEnd();
				gl.glDisable(GL2.GL_TEXTURE_2D);
			} 
		}



	}

	public void loadedTexture (GL2 gl){
		gl.glEnable(GL2.GL_TEXTURE_2D);
		try {
			text_tv= TextureIO.newTexture(tv, true);

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
