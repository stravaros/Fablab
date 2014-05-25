package EnvObjet;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL2;
import javax.media.opengl.GLException;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class Music extends ObjetGen{

	private Texture text_play;
	private File play = new File("ressources/textures/play.jpg") ;
	private Texture text_music;
	private File music = new File("ressources/textures/music.jpg") ;
	private boolean isLoadedTexture = false;
	
	public Music (){
		this.orientation= 0;
	}
	
	@Override
	public void drawObjet(GL2 gl, GLUT glut) {
		if (!getIsLoadedTexture())
			loadedTexture(gl);
		
		if (OnOff){
			text_play.enable(gl);
			text_play.bind(gl);	
		}else{
			text_music.enable(gl);
			text_music.bind(gl);
		}
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(1, 1, 1); // set the color of the quad
		gl.glTexCoord2d(0, 0); gl.glVertex3d(posX-2 , posY-2, 5); 		
		gl.glTexCoord2d(0, 1); gl.glVertex3d(posX-2 , posY+2, 5);		
		gl.glTexCoord2d(1, 1); gl.glVertex3d(posX+2 , posY+2, 5); 	
		gl.glTexCoord2d(1, 0); gl.glVertex3d(posX+2 , posY-2, 5);  
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(1, 1, 1); // set the color of the quad
		gl.glTexCoord2d(0, 0); gl.glVertex3d(posX-2 , posY-2, 5); 		
		gl.glTexCoord2d(0, 1); gl.glVertex3d(posX-2 , posY+2, 5);		
		gl.glTexCoord2d(1, 1); gl.glVertex3d(posX-2 , posY+2, 1); 	
		gl.glTexCoord2d(1, 0); gl.glVertex3d(posX-2 , posY-2, 1);  
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(1, 1, 1); // set the color of the quad
		gl.glTexCoord2d(0, 0); gl.glVertex3d(posX+2 , posY-2, 5); 		
		gl.glTexCoord2d(0, 1); gl.glVertex3d(posX+2 , posY+2, 5);		
		gl.glTexCoord2d(1, 1); gl.glVertex3d(posX+2 , posY+2, 1); 	
		gl.glTexCoord2d(1, 0); gl.glVertex3d(posX+2 , posY-2, 1);  
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(1, 1, 1); // set the color of the quad
		gl.glTexCoord2d(0, 0); gl.glVertex3d(posX-2 , posY-2, 5); 		
		gl.glTexCoord2d(0, 1); gl.glVertex3d(posX+2 , posY-2, 5);		
		gl.glTexCoord2d(1, 1); gl.glVertex3d(posX+2 , posY-2, 1); 	
		gl.glTexCoord2d(1, 0); gl.glVertex3d(posX-2 , posY-2, 1);  
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(1, 1, 1); // set the color of the quad
		gl.glTexCoord2d(0, 0); gl.glVertex3d(posX-2 , posY+2, 5); 		
		gl.glTexCoord2d(0, 1); gl.glVertex3d(posX+2 , posY+2, 5);		
		gl.glTexCoord2d(1, 1); gl.glVertex3d(posX+2 , posY+2, 1); 	
		gl.glTexCoord2d(1, 0); gl.glVertex3d(posX-2 , posY+2, 1);  
		gl.glEnd();
		gl.glDisable(GL2.GL_TEXTURE_2D);
	}
	
	public void loadedTexture (GL2 gl){
		gl.glEnable(GL2.GL_TEXTURE_2D);
		try {
			text_play= TextureIO.newTexture(play, true);
			text_music= TextureIO.newTexture(music, true);

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
