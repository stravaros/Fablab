package View;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLUquadric;
import javax.media.opengl.glu.gl2.GLUgl2;

import com.jogamp.opengl.util.gl2.GLUT;

import capteur.Capteur;
import Model.Mdl;

public class Frame implements GLEventListener {

	final int[] texId = new int[1];
	private Mdl mdl;
	GLAutoDrawable drawable;
	GLUquadric qobj;
	double longueur;
	double largeur;
	float taille = (float) 0.5;

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
		final GL2 gl = drawable.getGL().getGL2();
		GLUgl2 glu = new GLUgl2(); //INSTANCIATION GLU
		GLUT glut = new GLUT();	//INSTANCIATION GLUT
		
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);  
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();     /* réinitialiation de la matrice de GL_MODELVIEW */


		gl.glTranslatef(0.0f, 0.0f, -mdl.getDistance());
		gl.glTranslatef(0.0f, mdl.getHauteur(), 0.0f);
		gl.glRotatef(mdl.getAngleElevation(), 1.0f, 0.0f, 0.0f);
		gl.glRotatef(mdl.getAngleAzimuth(), 0.0f, 1.0f, 0.0f);
		gl.glRotatef(mdl.getAngleDirection(), 0.0f, 0.0f, 1.0f);
		
		
		//capteurfixe(gl);
		//capteurmouvant(gl);
		fond(gl);
		mur(gl);
		//glut.glutSolidCube(5);
		table (gl);
		chaise (gl);
		capteur(gl);
		//glut.glutSolidTeapot(4);
		
	}

	/*private void capteurfixe(GL2 gl) {
			Capteur capteur = mdl.getCapteurMouvant();
			gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(1, 1, 0); // set the color of the quad
				gl.glVertex3d(-taille+mdl.getCapteurMouvant().getCoordoneeX(), taille+mdl.getCapteurMouvant().getCoordoneeY(), 0.5); // Top Left
				gl.glVertex3d(taille+mdl.getCapteurMouvant().getCoordoneeX(), taille+mdl.getCapteurMouvant().getCoordoneeY(), 0.5); // Top Right
				gl.glVertex3d(taille+mdl.getCapteurMouvant().getCoordoneeX(), -taille+mdl.getCapteurMouvant().getCoordoneeY(), 0.5); // Bottom Right
				gl.glVertex3d(-taille+mdl.getCapteurMouvant().getCoordoneeX(), -taille+mdl.getCapteurMouvant().getCoordoneeY(), 0.5); // Bottom Left
			gl.glEnd();	
	}

	public void capteurmouvant(GL2 gl) {
		for (int i = 0; i < mdl.getTabCapteurFixe().length; i++) {
			gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(0, 0, 1); // set the color of the quad
				gl.glVertex3d(-taille + mdl.getTabCapteurFixe()[i].getCoordoneeX(),
						taille + mdl.getTabCapteurFixe()[i].getCoordoneeY(), 0.5); // Top
																					// Left
				gl.glVertex3d(taille + mdl.getTabCapteurFixe()[i].getCoordoneeX(),
						taille + mdl.getTabCapteurFixe()[i].getCoordoneeY(), 0.5); // Top
																					// Right
				gl.glVertex3d(taille + mdl.getTabCapteurFixe()[i].getCoordoneeX(),
						-taille + mdl.getTabCapteurFixe()[i].getCoordoneeY(), 0.5); // Bottom
																					// Right
				gl.glVertex3d(-taille + mdl.getTabCapteurFixe()[i].getCoordoneeX(),
						-taille + mdl.getTabCapteurFixe()[i].getCoordoneeY(), 0.5); // Bottom
																					// Left
			// Done Drawing The Quad
			gl.glEnd();
		}
		
	}*/
	
	

	public void fond (GL2 gl){
	//	float mat_diffuse[] = {0.0f,255.0f,0.0f,1.0f};
		//gl.glMaterialfv(GL2.GL_FRONT,  GL2.GL_DIFFUSE, mat_diffuse, 0);
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(0, 0, 1); // set the color of the quad
			gl.glVertex3d(-longueur+1, largeur, 0); // Top Left
			gl.glVertex3d(longueur, largeur, 0); // Top Right
			gl.glVertex3d(longueur, -largeur+1, 0); // Bottom Right
			gl.glVertex3d(-longueur+1, -largeur+1, 0); // Bottom Left
		gl.glEnd();
	}
	
	public void mur (GL2 gl){
		float mat_diffuse[] = {0.0f,255.0f,255.0f,1.0f};
		gl.glMaterialfv(GL2.GL_FRONT,  GL2.GL_DIFFUSE, mat_diffuse, 0);
		cubeLongeur(gl, longueur, largeur);
		cubeLongeur(gl, longueur, -largeur);
		cubeLargeur(gl, longueur, largeur);
		cubeLargeur(gl, -longueur, largeur);
	}
	
	private void capteur (GL2 gl){
		for (int i =0; i<mdl.getTabCapteur().size();i++){
			gl.glBegin(GL2.GL_TRIANGLES);
			gl.glColor3d(1, 1, 0); // set the color of the quad
				gl.glVertex3d(mdl.getTabCapteur().get(i).getCoordoneeX(), mdl.getTabCapteur().get(i).getCoordoneeY()-2, 2); // Top Left
				gl.glVertex3d(mdl.getTabCapteur().get(i).getCoordoneeX(), mdl.getTabCapteur().get(i).getCoordoneeY(), 4); // Top Right
				gl.glVertex3d(mdl.getTabCapteur().get(i).getCoordoneeX(), mdl.getTabCapteur().get(i).getCoordoneeY()+2, 2); // Bottom Right
			gl.glEnd();
		}
	}
	
	
	public void table (GL2 gl){
	//	float mat_diffuse[] = {255.0f,0.0f,0.0f,1.0f};
		//gl.glMaterialfv(GL2.GL_FRONT,  GL2.GL_DIFFUSE, mat_diffuse, 0);
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3f(1, 0, 0); // set the color of the quad
		
			gl.glVertex3d(0, 0, 2); // Top Left
			gl.glVertex3d(0, 8, 2); // Top Right
			gl.glVertex3d(8, 8, 2); // Bottom Right
			gl.glVertex3d(8, 0, 2); // Bottom Left
		gl.glEnd();
		
		//pied1
		gl.glBegin(GL2.GL_QUADS);
		//gl.glColor3d(1, 1, 0); // set the color of the quad
			gl.glVertex3d(0, 0, 0); // Top Left
			gl.glVertex3d(1, 0, 0); // Top Right
			gl.glVertex3d(1, 0, 2); // Bottom Right
			gl.glVertex3d(0, 0, 2); // Bottom Left
		gl.glEnd();
		
		//pied2
		gl.glBegin(GL2.GL_QUADS);
	//	gl.glColor3d(1, 1, 0); // set the color of the quad
			gl.glVertex3d(0, 8, 0); // Top Left
			gl.glVertex3d(1, 8, 0); // Top Right
			gl.glVertex3d(1, 8, 2); // Bottom Right
			gl.glVertex3d(0, 8, 2); // Bottom Left
		gl.glEnd();
		
		//pied3
		gl.glBegin(GL2.GL_QUADS);
	//	gl.glColor3d(1, 1, 0); // set the color of the quad
			gl.glVertex3d(8, 0, 0); // Top Left
			gl.glVertex3d(7, 0, 0); // Top Right
			gl.glVertex3d(7, 0, 2); // Bottom Right
			gl.glVertex3d(8, 0, 2); // Bottom Left
		gl.glEnd();
		
		//pied4
		gl.glBegin(GL2.GL_QUADS);
	//	gl.glColor3d(1, 1, 0); // set the color of the quad
			gl.glVertex3d(8, 8, 0); // Top Left
			gl.glVertex3d(7, 8, 0); // Top Right
			gl.glVertex3d(7, 8, 2); // Bottom Right
			gl.glVertex3d(8, 8, 2); // Bottom Left
		gl.glEnd();
	
	}
	
	public void chaise (GL2 gl){
		float mat_diffuse[] = {.0f,1.0f,255.0f,1.0f};
		gl.glMaterialfv(GL2.GL_FRONT,  GL2.GL_DIFFUSE, mat_diffuse, 0);
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(1, 0, 0); // set the color of the quad
			gl.glVertex3d(3, -2, 1); // Top Left
			gl.glVertex3d(3, -3, 1); // Top Right
			gl.glVertex3d(6, -3, 1); // Bottom Right
			gl.glVertex3d(6, -2, 1); // Bottom Left
		gl.glEnd();
		
		//pied1
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(1, 0, 0); // set the color of the quad
			gl.glVertex3d(3, -2, 0); // Top Left
			gl.glVertex3d(3, -3, 0); // Top Right
			gl.glVertex3d(3, -3, 1); // Bottom Right
			gl.glVertex3d(3, -2, 1); // Bottom Left
		gl.glEnd();
		
	//pied2
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(1, 0, 0); // set the color of the quad
			gl.glVertex3d(6, -2, 0); // Top Left
			gl.glVertex3d(6, -3, 0); // Top Right
			gl.glVertex3d(6, -3, 1); // Bottom Right
			gl.glVertex3d(6, -2, 1); // Bottom Left
		gl.glEnd();
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
		initViewProjection(glDrawable, x, y, width, height);
	}

	private void initViewProjection(GLAutoDrawable glDrawable, int x, int y, int width, int height) {
		final GL2 gl = glDrawable.getGL().getGL2();	
		GLUgl2 glu = new GLUgl2();
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(45.0f, (float)width/(float)height, 0.1, 200.0);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
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

	static void cubeLongeur(GL2 gl, double i, double j) {
		gl.glColor3d(0, 1, 0);
		/* draws the sides of a unit cube (0,0,0)-(1,1,1) */
		gl.glBegin(GL2.GL_POLYGON);/* f1: front */
		gl.glNormal3f(-1.0f, 0.0f, 0.0f);
			gl.glVertex3d(-i, -j, 0.0f);
			gl.glVertex3d(-i, -j, 3.0f);
			gl.glVertex3d(i, -j, 3.0f);
			gl.glVertex3d(i, -j, 0.0f);
		gl.glEnd();
		gl.glBegin(GL2.GL_POLYGON);/* f2: bottom */
		gl.glNormal3f(-2.0f, 0.0f, -1.0f);
			gl.glVertex3d(-i, -j, 0.0f);
			gl.glVertex3d(i, -j, 0.0f);
			gl.glVertex3d(i, -j + 1.0f, 0.0f);
			gl.glVertex3d(-i, -j + 1.0f, 0.0f);
		gl.glEnd();
		gl.glBegin(GL2.GL_POLYGON);/* f3:back */
		gl.glNormal3f(1.0f, 0.0f, 0.0f);
			gl.glVertex3d(-i, -j + 1.0f, 0.0f);
			gl.glVertex3d(i, -j + 1.0f, 3.0f);
			gl.glVertex3d(-i, -j + 1.0f, 3.0f);
			gl.glVertex3d(-i, -j + 1.0f, 0.0f);
		gl.glEnd();
		gl.glBegin(GL2.GL_POLYGON);/* f4: top */
		gl.glNormal3f(0.0f, 0.0f, 1.0f);
			gl.glVertex3d(i, -j + 1.0f, 3.0f);
			gl.glVertex3d(i, -j, 3.0f);
			gl.glVertex3d(-i, -j, 3.0f);
			gl.glVertex3d(-i, -j + 1.0f, 3.0f);
		gl.glEnd();
		gl.glBegin(GL2.GL_POLYGON);/* f5: left */
		gl.glNormal3f(0.0f, 1.0f, 0.0f);
			gl.glVertex3d(-i, -j, 0.0f);
			gl.glVertex3d(-i, -j + 1.0f, 0.0f);
			gl.glVertex3d(-i, -j + 1.0f, 3.0f);
			gl.glVertex3d(-i, -j, 3.0f);
		gl.glEnd();
		gl.glBegin(GL2.GL_POLYGON);/* f6: right */
		gl.glNormal3f(0.0f, -1.0f, 0.0f);
			gl.glVertex3d(i, -j, 0.0f);
			gl.glVertex3d(i, -j, 3.0f);
			gl.glVertex3d(i, -j + 1.0f, 3.0f);
			gl.glVertex3d(i, -j + 1.0f, 0.0f);
		gl.glEnd();
	}

	static void cubeLargeur(GL2 gl, double i, double j) {
		gl.glColor3d(0, 1, 0);

		/* draws the sides of a unit cube (0,0,0)-(1,1,1) */
		gl.glBegin(GL2.GL_POLYGON);/* f1: front */
		gl.glNormal3f(-1.0f, 0.0f, 0.0f);
			gl.glVertex3d(i, -j, 0.0f);
			gl.glVertex3d(i, -j, 1.0f);
			gl.glVertex3d(i + 1, -j, 1.0f);
			gl.glVertex3d(i + 1, -j, 0.0f);
		gl.glEnd();
		gl.glBegin(GL2.GL_POLYGON);/* f2: bottom */
		gl.glNormal3f(0.0f, 0.0f, -1.0f);
			gl.glVertex3d(i, -j, 0.0f);
			gl.glVertex3d(i + 1, -j, 0.0f);
			gl.glVertex3d(i + 1, j + 1, 0.0f);
			gl.glVertex3d(i, j + 1, 0.0f);
		gl.glEnd();
		gl.glBegin(GL2.GL_POLYGON);/* f3:back */
		gl.glNormal3f(-1.0f, 0.0f, 0.0f);
			gl.glVertex3d(i, j + 1, 0.0f);
			gl.glVertex3d(i, j + 1, 1.0f);
			gl.glVertex3d(i + 1, j + 1, 1.0f);
			gl.glVertex3d(i + 1, j + 1, 0.0f);
		gl.glEnd();
		gl.glBegin(GL2.GL_POLYGON);/* f4: top */
		gl.glNormal3f(0.0f, 0.0f, 1.0f);
			gl.glVertex3d(i + 1, j + 1, 1.0f);
			gl.glVertex3d(i + 1, -j, 1.0f);
			gl.glVertex3d(i, -j, 1.0f);
			gl.glVertex3d(i, j + 1, 1.0f);
		gl.glEnd();
		gl.glBegin(GL2.GL_POLYGON);/* f5: left */
		gl.glNormal3f(0.0f, 1.0f, 0.0f);
			gl.glVertex3d(i, -j, 0.0f);
			gl.glVertex3d(i, j + 1, 0.0f);
			gl.glVertex3d(i, j + 1, 1.0f);
			gl.glVertex3d(i, -j, 1.0f);
		gl.glEnd();
		gl.glBegin(GL2.GL_POLYGON);/* f6: right */
		gl.glNormal3f(0.0f, -1.0f, 0.0f);
			gl.glVertex3d(i, -j, 0.0f);
			gl.glVertex3d(i, -j, 1.0f);
			gl.glVertex3d(i, j + 1, 1.0f);
			gl.glVertex3d(i, j + 1, 0.0f);
		gl.glEnd();
	}

}
