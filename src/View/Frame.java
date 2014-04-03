package View;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLUquadric;
import javax.media.opengl.glu.gl2.GLUgl2;

import capteur.Capteur;

import Model.Mdl;

public class Frame implements GLEventListener {

	final int[] texId = new int[1];
	Mdl mdl;
	GLAutoDrawable drawable;
	GLUquadric qobj;
	float longueur;
	float largeur;
	float taille = (float) 0.5;

	public Frame(GLAutoDrawable gld, Mdl m, float longueur, float largeur) {
		this.drawable = gld;
		this.mdl = m;
		this.longueur = longueur;
		this.largeur = largeur;
	}

	// IMPLEMENTE ELENVENTLISTENER
	@Override
	public void display(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

		final GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity(); /* réinitialiation de la matrice de GL_MODELVIEW */

		gl.glTranslatef(0.0f, 0.0f, -mdl.getDistance());
		gl.glRotatef(mdl.getAngleElevation(), 1.0f, 0.0f, 0.0f);
		gl.glRotatef(mdl.getAngleAzimuth(), 0.0f, 1.0f, 0.0f);
		// gl.glRotatef(mdl, arg1, arg2, arg3)

		// glut.glutSolidTeapot(5); /* dessin d'une théière */

		// ///////////////


		capteurfixe(gl);
		capteurmouvant(gl);
		fond(gl);
		mur(gl);
	}

	private void capteurfixe(GL2 gl) {
		for (int i = 0; i < mdl.getTabCapteurMouvant().length; i++) {
			gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d(1, 1, 0); // set the color of the quad
				gl.glVertex3d(-taille, taille, 0.5); // Top Left
				gl.glVertex3d(taille, taille, 0.5); // Top Right
				gl.glVertex3d(taille, -taille, 0.5); // Bottom Right
				gl.glVertex3d(-taille, -taille, 0.5); // Bottom Left
			gl.glEnd();
		}
		
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
		
	}

	public void fond (GL2 gl){
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d(1, 1, 1); // set the color of the quad
			gl.glVertex3d(-longueur+1, largeur, 0); // Top Left
			gl.glVertex3d(longueur, largeur, 0); // Top Right
			gl.glVertex3d(longueur, -largeur+1, 0); // Bottom Right
			gl.glVertex3d(-longueur+1, -largeur+1, 0); // Bottom Left
		gl.glEnd();
	}
	
	public void mur (GL2 gl){
		cubeLongeur(gl, longueur, largeur);
		cubeLongeur(gl, longueur, -largeur);
		cubeLargeur(gl, longueur, largeur);
		cubeLargeur(gl, -longueur, largeur);
	}
	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GLAutoDrawable glDrawable) {
		// TODO Auto-generated method stub
		final GL2 gl = glDrawable.getGL().getGL2();
		GLUgl2 glu = new GLUgl2();
		initViewProjection(glDrawable, 0, 0, 500, 500);
		gl.glColor3d(0.0f, 0.0f, 0.0f);
		gl.glLineWidth(1);
		qobj = glu.gluNewQuadric();
		glu.gluQuadricDrawStyle(qobj, GLUgl2.GLU_FILL);
		glu.gluQuadricNormals(qobj, GLUgl2.GLU_SMOOTH);
		glu.gluQuadricTexture(qobj, true);
		gl.glEnable(GL2.GL_TEXTURE_2D);

		gl.glGenTextures(1, texId, 0);

		gl.glBindTexture(GL2.GL_TEXTURE_2D, texId[0]);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER,
				GL2.GL_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER,
				GL2.GL_LINEAR);

	}

	@Override
	public void reshape(GLAutoDrawable glDrawable, int x, int y, int width,
			int height) {
		// TODO Auto-generated method stub
		// x et y non utile
		initViewProjection(glDrawable, x, y, width, height);
	}

	private void initViewProjection(GLAutoDrawable glDrawable, int x, int y,
			int width, int height) {
		// TODO Auto-generated method stub
		final GL2 gl = drawable.getGL().getGL2();
		GLUgl2 glu = new GLUgl2();
		gl.glMatrixMode(GL2.GL_PROJECTION); /* passage en mode GL_PROJECTION */
		gl.glLoadIdentity(); /* réinitialisation de la matrice de transformation */
		glu.gluPerspective(45.0f, (float) width / (float) height, 0.01, 200.0); // param
																				// :
																				// angle,
																				// ratio
																				// (largeur/hauteur),
																				// zproche,
																				// zeloigne)
		gl.glMatrixMode(GL2.GL_MODELVIEW); /* repassage en mode GL_MODELVIEW */
		gl.glLoadIdentity();

		float mat_ambient[] = { 0.2f, 0.2f, 0.2f, 1.0f };
		float mat_diffuse[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		float mat_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
		float mat_shininess = 110.0f;

		float light_position[] = { 0f, 0f, 300f, 0.0f };
		float specular_light[] = { 0.4f, 0.4f, 0.4f, 0.4f };
		float diffuse_light[] = { 0.4f, 0.4f, 0.4f, 0.4f };
		float model_ambient[] = { 0.2f, 0.2f, 0.2f, 1.0f };

		gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, mat_ambient, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, mat_diffuse, 0);
		gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, mat_specular, 0);
		gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, mat_shininess);
		gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light_position, 0);
		gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, model_ambient, 0);
		gl.glEnable(GL2.GL_LIGHT0);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_SMOOTH);

	}

	static void cubeLongeur(GL2 gl, float i, float j) {
		gl.glColor3d(0, 1, 0);
		/* draws the sides of a unit cube (0,0,0)-(1,1,1) */
		gl.glBegin(GL2.GL_POLYGON);/* f1: front */
		gl.glNormal3f(-1.0f, 0.0f, 0.0f);
			gl.glVertex3f(-i, -j, 0.0f);
			gl.glVertex3f(-i, -j, 1.0f);
			gl.glVertex3f(i, -j, 1.0f);
			gl.glVertex3f(i, -j, 0.0f);
		gl.glEnd();
		gl.glBegin(GL2.GL_POLYGON);/* f2: bottom */
		gl.glNormal3f(-2.0f, 0.0f, -1.0f);
			gl.glVertex3f(-i, -j, 0.0f);
			gl.glVertex3f(i, -j, 0.0f);
			gl.glVertex3f(i, -j + 1.0f, 0.0f);
			gl.glVertex3f(-i, -j + 1.0f, 0.0f);
		gl.glEnd();
		gl.glBegin(GL2.GL_POLYGON);/* f3:back */
		gl.glNormal3f(1.0f, 0.0f, 0.0f);
			gl.glVertex3f(-i, -j + 1.0f, 0.0f);
			gl.glVertex3f(i, -j + 1.0f, 1.0f);
			gl.glVertex3f(-i, -j + 1.0f, 1.0f);
			gl.glVertex3f(-i, -j + 1.0f, 0.0f);
		gl.glEnd();
		gl.glBegin(GL2.GL_POLYGON);/* f4: top */
		gl.glNormal3f(0.0f, 0.0f, 1.0f);
			gl.glVertex3f(i, -j + 1.0f, 1.0f);
			gl.glVertex3f(i, -j, 1.0f);
			gl.glVertex3f(-i, -j, 1.0f);
			gl.glVertex3f(-i, -j + 1.0f, 1.0f);
		gl.glEnd();
		gl.glBegin(GL2.GL_POLYGON);/* f5: left */
		gl.glNormal3f(0.0f, 1.0f, 0.0f);
			gl.glVertex3f(-i, -j, 0.0f);
			gl.glVertex3f(-i, -j + 1.0f, 0.0f);
			gl.glVertex3f(-i, -j + 1.0f, 1.0f);
			gl.glVertex3f(-i, -j, 1.0f);
		gl.glEnd();
		gl.glBegin(GL2.GL_POLYGON);/* f6: right */
		gl.glNormal3f(0.0f, -1.0f, 0.0f);
			gl.glVertex3f(i, -j, 0.0f);
			gl.glVertex3f(i, -j, 1.0f);
			gl.glVertex3f(i, -j + 1.0f, 1.0f);
			gl.glVertex3f(i, -j + 1.0f, 0.0f);
		gl.glEnd();
	}

	static void cubeLargeur(GL2 gl, float i, float j) {
		gl.glColor3d(0, 1, 0);

		/* draws the sides of a unit cube (0,0,0)-(1,1,1) */
		gl.glBegin(GL2.GL_POLYGON);/* f1: front */
		gl.glNormal3f(-1.0f, 0.0f, 0.0f);
			gl.glVertex3f(i, -j, 0.0f);
			gl.glVertex3f(i, -j, 1.0f);
			gl.glVertex3f(i + 1, -j, 1.0f);
			gl.glVertex3f(i + 1, -j, 0.0f);
		gl.glEnd();
		gl.glBegin(GL2.GL_POLYGON);/* f2: bottom */
		gl.glNormal3f(0.0f, 0.0f, -1.0f);
			gl.glVertex3f(i, -j, 0.0f);
			gl.glVertex3f(i + 1, -j, 0.0f);
			gl.glVertex3f(i + 1, j + 1, 0.0f);
			gl.glVertex3f(i, j + 1, 0.0f);
		gl.glEnd();
		gl.glBegin(GL2.GL_POLYGON);/* f3:back */
		gl.glNormal3f(-1.0f, 0.0f, 0.0f);
			gl.glVertex3f(i, j + 1, 0.0f);
			gl.glVertex3f(i, j + 1, 1.0f);
			gl.glVertex3f(i + 1, j + 1, 1.0f);
			gl.glVertex3f(i + 1, j + 1, 0.0f);
		gl.glEnd();
		gl.glBegin(GL2.GL_POLYGON);/* f4: top */
		gl.glNormal3f(0.0f, 0.0f, 1.0f);
			gl.glVertex3f(i + 1, j + 1, 1.0f);
			gl.glVertex3f(i + 1, -j, 1.0f);
			gl.glVertex3f(i, -j, 1.0f);
			gl.glVertex3f(i, j + 1, 1.0f);
		gl.glEnd();
		gl.glBegin(GL2.GL_POLYGON);/* f5: left */
		gl.glNormal3f(0.0f, 1.0f, 0.0f);
			gl.glVertex3f(i, -j, 0.0f);
			gl.glVertex3f(i, j + 1, 0.0f);
			gl.glVertex3f(i, j + 1, 1.0f);
			gl.glVertex3f(i, -j, 1.0f);
		gl.glEnd();
		gl.glBegin(GL2.GL_POLYGON);/* f6: right */
		gl.glNormal3f(0.0f, -1.0f, 0.0f);
			gl.glVertex3f(i, -j, 0.0f);
			gl.glVertex3f(i, -j, 1.0f);
			gl.glVertex3f(i, j + 1, 1.0f);
			gl.glVertex3f(i, j + 1, 0.0f);
		gl.glEnd();
	}

}
