package EnvObjet;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.GLException;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class Window extends ObjetGen{

	private boolean isLoadedTexture = false;
	private Texture text_fenetre;
	private File fenetre = new File("ressources/textures/fenetre.jpg") ;
	private Texture text_volet;
	private File volet = new File("ressources/textures/volet.jpg") ;

	public Window (int X, int Y){
		posX=X;
		posY=Y;
		OnOff = true;
	}

	@Override
	public void drawObjet(GL2 gl, GLUT glut) {


		gl.glBegin(GL2.GL_QUADS);//fenetre
		gl.glColor3d(0.5, 0.5, 0.5); // set the color of the quad
		gl.glVertex3d(posX, posY -5, 0); 		
		gl.glVertex3d(posX, posY -5, 10);		
		gl.glVertex3d(posX, posY +5, 10); 	
		gl.glVertex3d(posX, posY +5, 0);  
		gl.glEnd();

		if (OnOff == true){
			if (!getIsLoadedTexture())
				loadedTexture(gl);


			text_fenetre.enable(gl);
			text_fenetre.bind(gl);
			gl.glBegin(GL2.GL_QUADS);//fenetre
			gl.glColor3d(1, 1, 1); // set the color of the quad
			gl.glTexCoord2d(0, 0); gl.glVertex3d(posX,posY -4, 0); 		
			gl.glTexCoord2d(0, 1); gl.glVertex3d(posX, posY-4, 9);		
			gl.glTexCoord2d(1, 1); gl.glVertex3d(posX,posY +4, 9); 	
			gl.glTexCoord2d(1, 0); gl.glVertex3d(posX,posY +4, 0);  
			gl.glEnd();
			gl.glPopAttrib();
			
		}else{
			if (!getIsLoadedTexture())
				loadedTexture(gl);


			text_volet.enable(gl);
			text_volet.bind(gl);
			gl.glBegin(GL2.GL_QUADS);//fenetre
			gl.glColor3d(1, 1, 1); // set the color of the quad
			gl.glTexCoord2d(0, 0); gl.glVertex3d(posX, posY-4, 0); 		
			gl.glTexCoord2d(0, 1); gl.glVertex3d(posX, posY-4, 9);		
			gl.glTexCoord2d(1, 1); gl.glVertex3d(posX,posY +4, 9); 	
			gl.glTexCoord2d(1, 0); gl.glVertex3d(posX,posY +4, 0);  
			gl.glEnd();
			gl.glPopAttrib();
		}
		gl.glDisable(GL2.GL_TEXTURE_2D);
	}

	public void loadedTexture (GL2 gl){
		gl.glEnable(GL2.GL_TEXTURE_2D);
		try {
			text_fenetre= TextureIO.newTexture(fenetre, true);
			text_volet= TextureIO.newTexture(volet, true);
		} catch (GLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isLoadedTexture  = true;
	}

	public boolean getIsLoadedTexture () {
		return isLoadedTexture;
	}

}
