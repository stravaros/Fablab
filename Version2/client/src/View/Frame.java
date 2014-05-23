package View;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GL2;
import javax.media.opengl.GLException;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import javax.media.opengl.glu.gl2.GLUgl2;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import Capteur.Capteur;
import Model.Mdl;

public class Frame implements GLEventListener {

	final int[] texId = new int[1];
	private Mdl mdl;
	GLAutoDrawable drawable;
	GLUquadric qobj;
	double longueur;
	double largeur;
	float taille = (float) 0.5;
	Texture text_fond;
	Texture text_mur;
	Texture text_table;
	Texture text_fenetre;
	Texture text_tv;
	File bois = new File("ressources/textures/parquet_comp.jpg") ;
	File mur = new File("ressources/textures/mur_comp.jpg") ;
	File table = new File("ressources/textures/table_comp.jpg") ;
	File fenetre = new File("ressources/textures/fenetre.jpg") ;
	File tv = new File("ressources/textures/tv.jpg") ;
	

	public Frame(GLAutoDrawable gld, Mdl m) {
		this.drawable = gld;
		this.mdl = m;
		this.longueur = 20;
		this.largeur = 20;
	}

	// IMPLEMENTE ELENVENTLISTENER
	@Override
	public void display(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
		GL2 gl = drawable.getGL().getGL2();
		GLUT glut = new GLUT();	//INSTANCIATION GLUT
		try {
			text_fond = TextureIO.newTexture(bois, true);
			text_mur = TextureIO.newTexture(mur, true);
			text_table= TextureIO.newTexture(table, true);
			text_fenetre= TextureIO.newTexture(fenetre, true);
			text_tv= TextureIO.newTexture(tv, true);
		} catch (GLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);  
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();     /* réinitialiation de la matrice de GL_MODELVIEW */
		

		gl.glTranslatef(0.0f, 0.0f, -mdl.getDistance());
		gl.glTranslatef(0.0f, mdl.getHauteur(), 0.0f);
		gl.glRotatef(mdl.getAngleElevation(), 1.0f, 0.0f, 0.0f);
		gl.glRotatef(mdl.getAngleAzimuth(), 0.0f, 1.0f, 0.0f);
		gl.glRotatef(mdl.getAngleDirection(), 0.0f, 0.0f, 1.0f);
		
		//DESSIN DE LA SCENNE
		gl.glEnable(GL2.GL_TEXTURE_2D);
		fond(gl);
		mur(gl);
		for (int i = 0; i<mdl.getListObjet().size(); i++){
				mdl.getListObjet().get(i).drawObjet(gl, glut, text_table, isNearTo (mdl.getListObjet().get(i).getPosX(), mdl.getListObjet().get(i).getPosY()));
		}
		gl.glDisable(GL2.GL_TEXTURE_2D);
		
		//DESSIN DU CAPTEUR
		capteur(gl, glut);
	}

	public void fond (GL2 gl){
		try {
			text_fond.enable(gl);
			text_fond.bind(gl);
		} catch (GLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

		gl.glBegin(GL2.GL_QUADS);
			gl.glColor4d(1, 1, 1, 1); // set the color of the quad,,
				gl.glTexCoord2d(0, 0); 		gl.glVertex3d(-longueur, -largeur, 0); // Top Left
				gl.glTexCoord2d(1, 0);		gl.glVertex3d(longueur, -largeur, 0); // Top Right
				gl.glTexCoord2d(1, 1); 	gl.glVertex3d(longueur, largeur, 0); // Bottom Right
				gl.glTexCoord2d(0, 1);  	gl.glVertex3d(-longueur, largeur, 0); // Bottom Left
			gl.glEnd();

	}
	
	public void mur (GL2 gl){
		try {
			text_mur.enable(gl);
			text_mur.bind(gl);
			
		} catch (GLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(1, 1, 1); // set the color of the quad
			gl.glTexCoord2d(0, 0); gl.glVertex3d(-longueur, -largeur, 0); 		
			gl.glTexCoord2d(1, 0); gl.glVertex3d(-longueur, -largeur, 10);		
			gl.glTexCoord2d(1, 1); gl.glVertex3d(-longueur, largeur, 10); 	
			gl.glTexCoord2d(0, 1); gl.glVertex3d(-longueur, largeur, 0);  
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(1, 1, 1); // set the color of the quad
			gl.glTexCoord2d(0, 0); gl.glVertex3d(-longueur, -largeur, 0); 		
			gl.glTexCoord2d(1, 0); gl.glVertex3d(-longueur, -largeur, 10);		
			gl.glTexCoord2d(1, 1); gl.glVertex3d(longueur, -largeur, 10); 	
			gl.glTexCoord2d(0, 1); gl.glVertex3d(longueur, -largeur, 0);  
		gl.glEnd();
		
	
		gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(1, 1, 1); // set the color of the quad
			gl.glTexCoord2d(0, 0); gl.glVertex3d(longueur, largeur, 0); 		
			gl.glTexCoord2d(1, 0); gl.glVertex3d(longueur, largeur, 10);		
			gl.glTexCoord2d(1, 1); gl.glVertex3d(-longueur, largeur, 10); 	
			gl.glTexCoord2d(0, 1); gl.glVertex3d(-longueur, largeur, 0);  
		gl.glEnd();

		gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(1, 1, 1); // set the color of the quad
			gl.glTexCoord2d(0, 0); gl.glVertex3d(longueur, -largeur, 0); 		
			gl.glTexCoord2d(1, 0); gl.glVertex3d(longueur, -largeur, 10);		
			gl.glTexCoord2d(1, 1); gl.glVertex3d(longueur, largeur, 10); 	
			gl.glTexCoord2d(0, 1); gl.glVertex3d(longueur, largeur, 0);  
		gl.glEnd();
		
		text_fenetre.enable(gl);
		text_fenetre.bind(gl);
		
		
		gl.glBegin(GL2.GL_QUADS);//fenetre
		gl.glColor3d(1, 0, 0); // set the color of the quad
			gl.glVertex3d(-longueur, -largeur/2-1, 2); 		
			gl.glVertex3d(-longueur, -largeur/2-1, 8);		
			gl.glVertex3d(-longueur, largeur/3+1, 8); 	
			 gl.glVertex3d(-longueur, largeur/3+1, 2);  
		gl.glEnd();
		
		gl.glBegin(GL2.GL_QUADS);//fenetre
		gl.glColor3d(1, 1, 1); // set the color of the quad
			gl.glTexCoord2d(0, 0); gl.glVertex3d(-longueur, -largeur/2, 3); 		
			gl.glTexCoord2d(0, 1); gl.glVertex3d(-longueur, -largeur/2, 7);		
			gl.glTexCoord2d(1, 1); gl.glVertex3d(-longueur, largeur/3, 7); 	
			gl.glTexCoord2d(1, 0); gl.glVertex3d(-longueur, largeur/3, 3);  
		gl.glEnd();
	}
	
	private void capteur (GL2 gl,  GLUT glut){
		for (int i =0; i<mdl.getTabCapteur().size();i++){
			gl.glPushMatrix();
			gl.glTranslated(mdl.getTabCapteur().get(i).getCoordoneeX() ,mdl.getTabCapteur().get(i).getCoordoneeY(), 3f ); //(x ,z,y)
			gl.glScalef(2, 2, 2);
			gl.glColor3d(0, 1, 0);
		    glut.glutSolidOctahedron();    
		    gl.glPopMatrix();
		}
		
		gl.glPushMatrix();
		gl.glTranslated(mdl.getCapteurMouvant().getCoordoneeX() ,mdl.getCapteurMouvant().getCoordoneeY(), 3f ); //(x ,z,y)
		gl.glColor3d(0, 1, 1);
	    glut.glutSolidTorus(0.5, 1.5 ,20, 20);    // middle teapot
	    gl.glPopMatrix();

	}
	
	private boolean isNearTo (int x, int y){
		boolean ret = (((x - mdl.getCapteurMouvant().getCoordoneeX()) * (x - mdl.getCapteurMouvant().getCoordoneeX()))
				+ ((y - mdl.getCapteurMouvant().getCoordoneeY()) * (y - mdl.getCapteurMouvant().getCoordoneeY())))<25 ;
		return ret;
	}
	
	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable glDrawable) {
		// TODO Auto-generated method stub
				final GL2 gl = glDrawable.getGL().getGL2();
				//GLUgl2 glu = new GLUgl2();
				initViewProjection(drawable, 0, 0, 500, 500);
				gl.glColor3f(0.0f, 200.0f, 0.0f);
				gl.glLineWidth(1);	

	}

	@Override
	public void reshape(GLAutoDrawable glDrawable, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		//x et y non utile
		//System.out.println("taille "+width+" "+height+" "+x+" "+y);
		//update of width and height 3d frame
		mdl.setMouseYMax(width);
		mdl.setMouseXMax(height);
		initViewProjection(glDrawable, x, y, width, height);
	}

	private void initViewProjection(GLAutoDrawable glDrawable, int x, int y, int width, int height) {
		final GL2 gl = glDrawable.getGL().getGL2();	
		GLUgl2 glu = new GLUgl2();
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(50.0f, (float)width/(float)height, 0.1, 200.0);
		System.out.println("proportion"+(float)width/(float)height);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		
		//POUR RAJOUTER DE LA LUMIERE
		/*float mat_ambient[] = {0.2f,0.2f,0.2f,0.2f};
		float mat_diffuse[] = {1.0f,1.0f,1.0f,1.0f};
		float mat_specular[] = {1.0f,1.0f,1.0f,1.0f};
		float mat_shininess = 110.0f;
		float light_position[]={0f, 0f, 300f, 0.0f};
		float model_ambient[] = {0.2f,0.2f,0.2f,1.0f};
		
		
		
		gl.glMaterialfv(GL2.GL_FRONT,  GL2.GL_AMBIENT, mat_ambient, 0);
		gl.glMaterialfv(GL2.GL_FRONT,  GL2.GL_DIFFUSE, mat_diffuse, 0);
		gl.glMaterialfv(GL2.GL_FRONT,  GL2.GL_SPECULAR, mat_specular, 0);
		gl.glMaterialf(GL2.GL_FRONT,  GL2.GL_SHININESS, mat_shininess);
		
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light_position, 0);
		
		gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, model_ambient, 0);
		gl.glEnable(GL2.GL_LIGHTING);
		gl.glEnable(GL2.GL_LIGHT0);
		gl.glEnable(GL2.GL_DEPTH_TEST);*/
	//	gl.glShadeModel(GL2.GL_SMOOTH);
	
	}


}
