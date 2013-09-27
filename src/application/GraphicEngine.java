package application;

import java.awt.Font;
import java.util.List;

import graphics.Color;
import graphics.GameObject;
import graphics.Material;
import graphics.ModelSubset;
import graphics.ModelSubsetFlag;
import graphics.SubsetOrderEngine;
import graphics.light.BasicLightEngine;
import graphics.light.LightEngine;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.j2d.TextRenderer;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;

/**
 * Fobs4JMF ! Pamietac o tym, moze sie przydac w przyszlosci. Biblioteka do renderowania avi itp.
 * @author edhendil
 *
 */

public class GraphicEngine implements Renderer {

	// pamietac ze za kazdym razem musi byc od nowa pobierany
	private GL gl;

	private TextRenderer renderer;

	private LightEngine _lightsMgr;
	private boolean _transparencyEnabled;

	// pola zawierajace informacje o aktualnym stanie GL, tak zeby bylo jak
	// najmniej wywolan funkcji natywnych
	private Color _materialAmbient;
	private Color _materialSpecular;
	private Color _materialDiffuse;
	private Color _materialEmissive;
	private double _materialShininess;

	private int _currentTexID;
	private Texture _currentTex;

	// kolor wyjsciowy - czarny, na samym poczatku display ustawiany jako
	// aktualny

	private Color _beginColor;

	// silnik ukladajacy odpowiednio subsety w liscie, tak zeby jak najszybciej
	// sie renderowalo
	// na razie bedzie tylko ustawianie wg tekstur, zeby bylo jak najmniej zmian
	// tekstur
	private SubsetOrderEngine _subEngine;

	public GraphicEngine() {
		_lightsMgr = new BasicLightEngine();
		_transparencyEnabled = false;
		_beginColor = new Color();
		_subEngine = new SubsetOrderEngine();
		renderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 45));
	}

	@Override
	public void display(GLAutoDrawable drawable) {

		gl = drawable.getGL();
		
		// Enable VSync
		gl.setSwapInterval(1);
		
		// Setup the drawing area and shading mode
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see
		// what happens.

		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glEnable(GL.GL_LIGHTING);
		gl.glEnable(GL.GL_POINT_SMOOTH);
		gl.glEnable(GL.GL_LINE_SMOOTH);
		gl.glEnable(GL.GL_POLYGON_SMOOTH);
		gl.glEnable(GL.GL_COLOR_MATERIAL);
		
		// World light
		float[] ambientWorld = { 1f, 1f, 1f, 1f };
		// float[] ambientWorld = { 0.0f, 0.0f, 0.0f, 0.0f };
		gl.glLightModelfv(GL.GL_LIGHT_MODEL_AMBIENT, ambientWorld, 0);

		resetMaterial();

		// Clear the drawing area
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		// reset variables
		_transparencyEnabled = false;

		// Reset the current matrix to the "identity"

		gl.glLoadIdentity();
		Application.getGame().getCamera().getView();

		// List<GameObject> objects = get3DObjectList();

		List<GameObject> objects = Application.getGame().getObjectsToRender();

		List<ModelSubset> orderedSubs = _subEngine.orderSubsetsInList(objects);

		for (ModelSubset sub : orderedSubs) {
			renderSubset(sub);
		}

		// for (GameObject object : objects) {
		// _lightsMgr.setProperLights(object, gl);
		// renderObject3D(object);
		// }

		// long oldtime = System.nanoTime();

		enable2D();

		// long dt = System.nanoTime() - oldtime;
		// System.out.println("Rendering: " + (double)dt/1000000000);

		objects = get2DObjectList();

		for (GameObject object : objects) {
			renderObject2D(object);
		}

		disable2D();

		List<String> texts = Application.getGame().getTextToRender();

		renderer.beginRendering(drawable.getWidth(), drawable.getHeight());
		renderer.setSmoothing(true);

		for (String str : texts) {
			renderer.draw(str, drawable.getWidth()/3, drawable.getHeight()/2);
		}
		
		renderer.endRendering();

		// Flush all drawing operations to the graphics card
		gl.glFlush();

	}

	@Override
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
			boolean deviceChanged) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// Use debug pipeline
		// drawable.setGL(new DebugGL(drawable.getGL()));

		gl = drawable.getGL();
		System.err.println("INIT GL IS: " + gl.getClass().getName());

		// gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

		/*
		 * float[] fogColor = {1,1,1,0.5f};
		 * 
		 * gl.glFogi(GL.GL_FOG_MODE, GL.GL_LINEAR); // Fog Mode
		 * gl.glFogfv(GL.GL_FOG_COLOR, fogColor, 0); // Set Fog Color
		 * gl.glFogf(GL.GL_FOG_DENSITY, 0.35f); // How Dense Will The Fog Be
		 * gl.glHint(GL.GL_FOG_HINT, GL.GL_NICEST); // Fog Hint Value
		 * gl.glFogf(GL.GL_FOG_START, 70.0f); // Fog Start Depth
		 * gl.glFogf(GL.GL_FOG_END, 200.0f); // Fog End Depth
		 * gl.glEnable(GL.GL_FOG);
		 */

	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		gl = drawable.getGL();

		GLU glu = new GLU();

		if (height <= 0) { // avoid a divide by zero error!

			height = 1;
		}
		final float h = (float) width / (float) height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(45.0f, h, 0.1, 100.0);
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();

		// System.out.println("Width: " + width);
		// System.out.println("Height: " + height);

	}

	protected List<GameObject> get3DObjectList() {
		return Application.getGData().get3DObjectsToRender();
	}

	protected List<GameObject> get2DObjectList() {
		return Application.getGData().get2DObjectsToRender();
	}

	private void renderObject2D(GameObject object) {
		ModelSubset[] subsets = object.getAnimatedModel().getSubsets();
		for (int i = 0; i < subsets.length; i++) {
			renderSubset(subsets[i]);
		}
	}

	private void renderObject3D(GameObject object) {
		ModelSubset[] subsets = object.getAnimatedModel().getSubsets();
		for (int i = 0; i < subsets.length; i++) {
			renderSubset(subsets[i]);
		}
	}

	private void renderSubset(ModelSubset sub) {
		processSubsetFlag(sub.getFlags());
		Material mat = sub.getMaterial();
		setMaterial(mat);

		Texture tex = null;

		if (mat.getTextureID() == -1) {
			if (_currentTexID != -1) {
				_currentTex.disable();
				_currentTex.dispose();
				_currentTexID = -1;
				_currentTex = null;
			}
		} else if (_currentTexID != mat.getTextureID()) {

			if (_currentTexID != -1) {
				_currentTex.dispose();

			}

			tex = TextureIO.newTexture(mat.getTexData());
			tex.enable();
			tex.bind();
			tex.setTexParameteri(GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
			tex.setTexParameteri(GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
			_currentTexID = mat.getTextureID();
			_currentTex = tex;
		}

		sub.render(gl);
	}

	/**
	 * Prawdopodobnie bedzie ustawiac odpowiedni zestaw shader�w dla danego
	 * subseta np. dla lustra czy szyby, jesli ofc napisze te shadery
	 * 
	 * @param flag
	 */
	private void processSubsetFlag(ModelSubsetFlag flag) {

	}

	/**
	 * Funkcje wlaczajace i wylaczajace tryb 2D
	 */
	private void enable2D() {

		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(0, Application.getWindow().getWidth(), 0, Application
				.getWindow().getHeight(), -50, 50);
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glDisable(GL.GL_DEPTH_TEST);
		gl.glDisable(GL.GL_LIGHTING);
		gl.glEnable(GL.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE);
		gl.glColor4d(1, 1, 1, 1);

	}

	private void disable2D() {

		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU glu = new GLU();
		glu.gluPerspective(45.0f, Application.getWindow().getWidth()
				/ Application.getWindow().getHeight(), 0.1, 100.0);
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glEnable(GL.GL_LIGHTING);
		gl.glDisable(GL.GL_BLEND);

	}

	private void resetMaterial() {
		_materialAmbient = _beginColor;
		_materialDiffuse = _beginColor;
		_materialEmissive = _beginColor;
		_materialSpecular = _beginColor;
		_materialShininess = 0;
		_currentTexID = -1;
		if (_currentTex != null) {
			_currentTex.dispose();
			_currentTex.disable();
		}
	}

	public void setMaterial(Material mat) {
		if (!mat.getAmbient().equals(_materialAmbient)) {
			_materialAmbient = mat.getAmbient();
			gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, _materialAmbient
					.toFloatArray(), 0);
		}
		if (!mat.getDiffuse().equals(_materialDiffuse)) {
			_materialDiffuse = mat.getDiffuse();
			gl.glMaterialfv(GL.GL_FRONT, GL.GL_DIFFUSE, _materialDiffuse
					.toFloatArray(), 0);
		}
		if (!mat.getEmissive().equals(_materialEmissive)) {
			_materialEmissive = mat.getEmissive();
			gl.glMaterialfv(GL.GL_FRONT, GL.GL_EMISSION, _materialEmissive
					.toFloatArray(), 0);
		}
		if (!mat.getSpecular().equals(_materialSpecular)) {
			_materialSpecular = mat.getSpecular();
			gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, _materialSpecular
					.toFloatArray(), 0);
		}
		if (!(mat.getShininess() == _materialShininess)) {
			_materialShininess = mat.getShininess();
			gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS,
					(float) _materialShininess);
		}
	}

}
