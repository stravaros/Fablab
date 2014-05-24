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
	GLUT glut = new GLUT();	//INSTANCIATION GLUT
	GLU glu = new GLU(); 
	GLUquadric qobj;
	double longueur;
	double largeur;
	float taille = (float) 0.5;
	Texture text_fond;
	Texture text_mur;
	Texture text_fenetre;
	File bois = new File("ressources/textures/parquet_comp.jpg") ;
	File mur = new File("ressources/textures/mur_comp.jpg") ;
	File fenetre = new File("ressources/textures/fenetre.jpg") ;
	GL2 gl;
	private boolean isTextureLoaded = false;

	public Frame(GLAutoDrawable gld, Mdl m) {
		this.drawable = gld;
		this.mdl = m;
		this.longueur = 20;
		this.largeur = 20;
		
		//gl = drawable.getGL().getGL2();
		

	}

	// IMPLEMENTE ELENVENTLISTENER
	@Override
	public void display(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
		gl = drawable.getGL().getGL2();
		if (!getIsTextureLoaded())
			loadedTexture();
		
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); 
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();     /* réinitialiation de la matrice de GL_MODELVIEW */
		
		if (mdl.isHasFloatingObject()){
			gl.glTranslatef(0.0f, 0.0f, -50);
			gl.glTranslatef(0.0f, 0.0f, 0.0f);		
			gl.glRotatef(0.0f, 1.0f, 0.0f, 0.0f);
			gl.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
			gl.glRotatef(0.0f, 0.0f, 0.0f, 1.0f);

		}else {
			gl.glTranslatef(0.0f, 0.0f, -mdl.getDistance());
			gl.glTranslatef(0.0f, mdl.getHauteur(), 0.0f);		
			gl.glRotatef(mdl.getAngleElevation(), 1.0f, 0.0f, 0.0f);
			gl.glRotatef(mdl.getAngleAzimuth(), 0.0f, 1.0f, 0.0f);
			gl.glRotatef(mdl.getAngleDirection(), 0.0f, 0.0f, 1.0f);
		}
		
		//DESSIN DE LA SCENNE
		gl.glEnable(GL2.GL_TEXTURE_2D);
		fond(gl);
		mur(gl);
		gl.glDisable(GL2.GL_TEXTURE_2D);
		
		for (int i = 0; i<mdl.getListObjet().size(); i++){
				gl.glPushAttrib(GL2.GL_COLOR_BUFFER_BIT);
				mdl.getListObjet().get(i).drawObjet(gl, glut, isNearToAndOn (mdl.getListObjet().get(i).getPosX(), mdl.getListObjet().get(i).getPosY()));
				gl.glPopAttrib();
		}
		if (mdl.isHasFloatingObject()){
			double [] tmp = mousePosition (gl, glu); 
			mdl.getFloatingObject().setPosX(-(int)tmp[0]);
			mdl.getFloatingObject().setPosY(-(int)tmp[1]);
			gl.glPushAttrib(GL2.GL_COLOR_BUFFER_BIT);
			mdl.getFloatingObject().drawObjet(gl, glut, isNearToAndOn (-(int)tmp[0], -(int)tmp[1]));
			gl.glPopAttrib();
		}
		
		//gl.glDisable(GL2.GL_TEXTURE_2D);
		
		//DESSIN DU CAPTEUR
		gl.glPushAttrib(GL2.GL_CURRENT_BIT);
		capteur(gl, glut);
		gl.glPopAttrib();
		}
	
	public boolean getIsTextureLoaded (){
		return isTextureLoaded ;
	}
	
	public void loadedTexture () {
		try {
			
			
			text_mur = TextureIO.newTexture(mur, true);
			text_fenetre= TextureIO.newTexture(fenetre, true);
			text_fond = TextureIO.newTexture(bois, true);
			
		} catch (GLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		isTextureLoaded = true;
	}
	
	public double [] mousePosition (GL2 gl, GLU glu){
		int x = mdl.getMouseX();
		int y = mdl.getMouseY();

		int viewport[] = new int[4];
	    double mvmatrix[] = new double[16];
	    double projmatrix[] = new double[16];
	    int realy = 0;// GL y coord pos
	    double wcoord[] = new double[4];// wx, wy, wz;// returned xyz coords
		
		gl.glGetIntegerv(GL2.GL_VIEWPORT, viewport, 0);
        gl.glGetDoublev(GL2.GL_MODELVIEW_MATRIX, mvmatrix, 0);
        gl.glGetDoublev(GL2.GL_PROJECTION_MATRIX, projmatrix, 0);
        realy = viewport[3] - (int) y - 1;

        glu.gluUnProject((double) x, (double) realy,  1,
            mvmatrix, 0,
            projmatrix, 0,
            viewport, 0, 
            wcoord, 0);

	        double t  = (50) /(wcoord[2]-50); // on calcule t en fonction de la position de la camera(Az) et de (Bz)
	        double Mx = t *(wcoord[0]-0)+ 0; //on calcule les positions de M  avec t
	        double My = t *(wcoord[1]-0)+0 ;
        	//System.out.println("World coords at z=1.0 are (" + Mx + ", " + My + ", " + Mz+" ");
                        return new double [] {Mx, My};
	}
	

	public void fond (GL2 gl){
		gl.glPushAttrib(GL2.GL_COLOR_BUFFER_BIT);
		
		text_fond.enable(gl);
		text_fond.bind(gl);
		gl.glBegin(GL2.GL_QUADS);
			gl.glColor4d(1, 1, 1, 1); // set the color of the quad,,
				gl.glTexCoord2d(0, 0); 		gl.glVertex3d(-longueur, -largeur, 0); // Top Left
				gl.glTexCoord2d(1, 0);		gl.glVertex3d(longueur, -largeur, 0); // Top Right
				gl.glTexCoord2d(1, 1); 	gl.glVertex3d(longueur, largeur, 0); // Bottom Right
				gl.glTexCoord2d(0, 1);  	gl.glVertex3d(-longueur, largeur, 0); // Bottom Left
			gl.glEnd();
			gl.glPopAttrib();
	}
	
	public void mur (GL2 gl){
		gl.glPushAttrib(GL2.GL_COLOR_BUFFER_BIT);
		text_mur.enable(gl);
		text_mur.bind(gl);
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
		gl.glPopAttrib();
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
		/*
		gl.glPushMatrix();
		gl.glTranslated(mdl.getCapteurMouvant().getCoordoneeX() ,mdl.getCapteurMouvant().getCoordoneeY(), 3f ); //(x ,z,y)
		gl.glColor3d(0, 1, 1);
	    glut.glutSolidTorus(0.5, 1.5 ,20, 20);    // middle teapot
	    gl.glPopMatrix();*/
		
		gl.glPushMatrix();
		gl.glTranslated(mdl.getCapteurMouvant().getCoordoneeX() ,mdl.getCapteurMouvant().getCoordoneeY(), 3f );
		gl.glRotated(90,0.0,1.0,0.0);
		gl.glScaled(0.07, 0.07, 0.07);
		gl.glPushAttrib(GL2.GL_COLOR_BUFFER_BIT);
		gl.glColor3f(0, 0, 1);
		drawPeople(gl, glut);
		gl.glPopAttrib();
		gl.glPopMatrix();

	}
	
	private void drawPeople (GL2 gl,  GLUT glu) {
		//le tronc
		gl.glPushMatrix();
		
		gl.glScaled(30.0,6.0,16.0);
		//gl.glScaled(4.0,15.,30.0);
		glu.glutSolidCube(1);
		gl.glPopMatrix();
		//les bras
		   //bras gauche
		gl.glPushMatrix();
		gl.glTranslated(-15.0,0.0,-13.0);
		gl.glRotated(180,1.0,0.0,0.0);
		DessineBras(gl, glu);
		gl.glPopMatrix();
		   //bras droit
		gl.glPushMatrix();
		gl.glTranslated(-15.0,0.0,13.0);
		gl.glRotated(180,1.0,0.0,0.0);
		      DessineBras(gl, glu);
		      gl.glPopMatrix();
		//les jambes
		   //jambe gauche
		      gl.glPushMatrix();
		      gl.glTranslated(12.0,0.0,-7.0);
		      DessineJambe(gl, glu);
		      gl.glPopMatrix();
		   //jambe droite   
		      gl.glPushMatrix();
		      gl.glTranslated(12.0,0.0,7.0);
		      DessineJambe(gl, glu);
		      gl.glPopMatrix();
		//la tete
		      gl.glPushMatrix();
		      gl.glTranslated(-23.0,0.0,0.0);
		      //glRotatef(paramG.angle_tete,1.0,0.0,0.0);
		      gl.glRotated(90,0.0,1.0,0.0);
		      glu.glutSolidSphere(8.0,16,12);
		      gl.glPopMatrix();
		

	}



	void DessineAvantBras(GL2 gl, GLUT glu)
	{
		gl.glPushMatrix();
		gl.glScaled(18.0,3.0,5.0);
	      glu.glutSolidCube(1);      
	      gl.glPopMatrix();
	      gl.glPushMatrix();     
	      //glTranslatef(7.0,5.0,0.0);
	      //gl.glRotated(param.angle_twistmain+180,1.0,0.0,0.0);
	      //gl.glRotated(param.angle_poignet,0.0,0.0,1.0);
	      gl.glTranslated(9.0,0.0,0.0);
	      //glRotatef(90,1.0,0.0,1.0);
	      //glRotatef(90,1.0,0.0,0.0);
	     // DessineMain(gl, glu);
	      gl.glPopMatrix();
	}
	
	void DessineBras(GL2 gl, GLUT glu)
	{
		gl.glPushMatrix();
		//gl.glRotatef(param.angle_twistepaule,0.0,1.0,0.0);
		//gl.glRotatef(param.angle_epaule,0.0,0.0,1.0);
		gl.glTranslated(8.0,0.0,0.0);
		gl.glPushMatrix();
		gl.glScaled(18.0,4.0,6.0);
	         glu.glutSolidCube(1);      
	         gl.glPopMatrix();
	         gl.glPushMatrix();
	         gl.glTranslated(9.0,0.0,0.0);
	         //gl.glRotatef(param.angle_twistcoude,0.0,1.0,0.0);
	         //gl.glRotatef(param.angle_coude,0.0,0.0,1.0);
	         gl.glTranslated(9.0,0.0,0.0);
	         DessineAvantBras(gl, glu);
	         gl.glPopMatrix();
	   
	         gl.glPopMatrix();
	}
	
	
	void DessineJambe(GL2 gl, GLUT glu)
	{
		gl.glPushMatrix();
		//gl.glRotatef(param.angle_twistjambes,0.0,1.0,0.0);
		//gl.glRotatef(90f,0.0f,0.0f,1.0f);
		gl.glTranslated(12.0,0.0,0.0);
	      //dessin de la cuisse
		gl.glPushMatrix();
		gl.glScaled(18.0,4.0,6.0);
	         glu.glutSolidCube(1);  
	         gl.glPopMatrix();
	         gl.glPushMatrix();
	         gl.glTranslated(8.0,0.0,0.0);
	        //gl.glRotatef(-90.0f,0.0f,0.0f,1.0f);
	         gl.glTranslated(8.0,0.0,0.0);
	         //dessin du reste de la jambe
	         gl.glPushMatrix();
	         //gl.glRotatef(-90f,0.0f,0.0f,1.0f);
	         gl.glScaled(14.0,4.0,6.0);
	         glu.glutSolidCube(1);  
	            gl.glPopMatrix();   
	         gl.glPopMatrix();
	      gl.glPopMatrix();
	}
	
	private boolean isNearToAndOn (int x, int y){
		boolean ret = (((x - mdl.getCapteurMouvant().getCoordoneeX()) * (x - mdl.getCapteurMouvant().getCoordoneeX()))
				+ ((y - mdl.getCapteurMouvant().getCoordoneeY()) * (y - mdl.getCapteurMouvant().getCoordoneeY())))<25 ;
		return ret & mdl.getTurnOnOff();
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
		//final GL2 gl = glDrawable.getGL().getGL2();
		initViewProjection(glDrawable, x, y, width, height);
	}

	private void initViewProjection(GLAutoDrawable glDrawable, int x, int y, int width, int height) {
		final GL2 gl = glDrawable.getGL().getGL2();	
		GLUgl2 glu = new GLUgl2();
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(50, (float)width/(float)height, 0.1, 200.0);
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
