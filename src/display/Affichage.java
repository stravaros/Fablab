package display;

import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

import javax.swing.*;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import capteur.Capteur;

import com.jogamp.opengl.util.FPSAnimator;

import static javax.media.opengl.GL.*; // GL constants
import static javax.media.opengl.GL2.*; // GL2 constants




/**
 * JOGL 2.0 Program Template (GLCanvas) This is a "Component" which can be added
 * into a top-level "Container". It also handles the OpenGL events to render
 * graphics.
 */
@SuppressWarnings("serial")
public class Affichage extends GLCanvas implements GLEventListener {
	
	static Scanner in = new Scanner(System.in);
	static int nombreCapteur =2;
	static Capteur tabCapteur[] = new Capteur[nombreCapteur];

	
	// Define constants for the top-level container
	private static String TITLE = "Affichage"; // window's title
	private static final int CANVAS_WIDTH = 640; // width of the drawable
	private static final int CANVAS_HEIGHT = 480; // height of the drawable
	private static final int FPS = 60; // animator's target frames per second

	/** The entry main() method to setup the top-level container and animator */
	public static void main(String[] args) {
		// Run the GUI codes in the event-dispatching thread for thread safety
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				//System.out.println("Combien de capteur \n ");
				//nombreCapteur = in.nextInt();
				for(int i=0;i<nombreCapteur;i++){
					tabCapteur[i]=new Capteur(0,i);
					System.out.println("Si le capteur " + i + " est un recepteur tapez 1, sinon si il s'agit de l'emetteur tapez 2");
					tabCapteur[i].setType(in.nextInt());
					if(tabCapteur[i].getType()==1){
						System.out.println("Il s'agit d'un recepteur");
					System.out.println("Entrer les coordonnées polaires du points (la distance et l'angle par rapport au centre de la classe");
					System.out.println("Tous d'abord la distance");
				//	tabCapteur[i].setDistance(in.nextInt());
					System.out.println("Puis l'angle");
				//	tabCapteur[i].setAngle(in.nextInt());
					}
					else{
						System.out.println("Il s'agit d'un capteur. Aucune donnée n'est demandé");
					}
				}
				
				// Create the OpenGL rendering canvas
			GLCanvas canvas = new Affichage();
			canvas.setPreferredSize(new Dimension(CANVAS_WIDTH,
						CANVAS_HEIGHT));

				// Create a animator that drives canvas' display() at the
				// specified FPS.
				final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);

				// Create the top-level container
				final JFrame frame = new JFrame(); // Swing's JFrame or AWT's
													// Frame
				frame.getContentPane().add(canvas);
				frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						// Use a dedicate thread to run the stop() to ensure
						// that the
						// animator stops before program exits.
						new Thread() {
							@Override
							public void run() {
								if (animator.isStarted())
									animator.stop();
								System.exit(0);
							}
						}.start();
					}
				});
				frame.setTitle(TITLE);
				frame.pack();
				frame.setVisible(true);
				animator.start(); // start the animation loop
			}
		});
	}

	// Setup OpenGL Graphics Renderer

	private GLU glu; // for the GL Utility

	/** Constructor to setup the GUI for this Component */
	public Affichage() {
		this.addGLEventListener(this);
	}

	// ------ Implement methods declared in GLEventListener ------

	/**
	 * Called back immediately after the OpenGL context is initialized. Can be
	 * used to perform one-time initialization. Run only once.
	 */
	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2(); // get the OpenGL graphics context
		glu = new GLU(); // get GL Utilities
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
		gl.glClearDepth(1.0f); // set clear depth value to farthest
		gl.glEnable(GL_DEPTH_TEST); // enables depth testing
		gl.glDepthFunc(GL_LEQUAL); // the type of depth test to do
		gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best
																// perspective
																// correction
		gl.glShadeModel(GL_SMOOTH); // blends colors nicely, and smoothes out
									// lighting
	}

	/**
	 * Call-back handler for window re-size event. Also called when the drawable
	 * is first set to visible.
	 */
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		GL2 gl = drawable.getGL().getGL2(); // get the OpenGL 2 graphics context

		if (height == 0)
			height = 1; // prevent divide by zero
		float aspect = (float) width / height;

		// Set the view port (display area) to cover the entire window
		gl.glViewport(0, 0, width, height);

		// Setup perspective projection, with aspect ratio matches viewport
		gl.glMatrixMode(GL_PROJECTION); // choose projection matrix
		gl.glLoadIdentity(); // reset projection matrix
		glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear,
														// zFar

		// Enable the model-view transform
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity(); // reset
	}


	/**
	 * Called back by the animator to perform rendering.
	 */

	@Override
	public void display(GLAutoDrawable gLDrawable) {
		final GL2 gl = gLDrawable.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		
		gl.glTranslated(0,0,-50);
		
	for (int i = 0; i < nombreCapteur; i++) {
			
			// Draw A Quad
		gl.glBegin(GL2.GL_QUADS);
			if(tabCapteur[i].getType()==1){
				gl.glColor3d(0,0,1);; // set the color of the quad
			}
			else {
				gl.glColor3d(1,1,0); // set the color of the quad
			}
			
			gl.glVertex3d(-1+tabCapteur[i].getCoordoneeX(), 1+tabCapteur[i].getCoordoneeY(), 0); // Top Left
			gl.glVertex3d(1+tabCapteur[i].getCoordoneeX(), 1+tabCapteur[i].getCoordoneeY(), 0); // Top Right
			gl.glVertex3d(1+tabCapteur[i].getCoordoneeX(), -1+tabCapteur[i].getCoordoneeY(), 0); // Bottom Right
			gl.glVertex3d(-1+tabCapteur[i].getCoordoneeX(), -1+tabCapteur[i].getCoordoneeY(), 0); // Bottom Left
			// Done Drawing The Quad
			gl.glEnd(); 
		}	
	}

	/**
	 * Called back before the OpenGL context is destroyed. Release resource such
	 * as buffers.
	 */
	@Override
	public void dispose(GLAutoDrawable drawable) {
	}
}