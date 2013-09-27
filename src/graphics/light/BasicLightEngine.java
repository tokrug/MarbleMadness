package graphics.light;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;

import application.Application;

import math.Vector4;
import math.Vector4D;

import graphics.GameObject;

public class BasicLightEngine implements LightEngine {

	// stale GL dla poszczegolnych swiatel
	private int[] _GLLights = { GL.GL_LIGHT0, GL.GL_LIGHT1, GL.GL_LIGHT2,
			GL.GL_LIGHT3, GL.GL_LIGHT4, GL.GL_LIGHT5, GL.GL_LIGHT6,
			GL.GL_LIGHT7 };

	// zasieg swiatel
	private double _lightRange;

	// te dwie zmienne sa utworzone zeby co chwile nie tworzyc nowego obiektu
	private List<Light> lights;
	private Vector4D position;

	// czyli te ktore aktualnie sa ustawione w GL, i ktore z nich sa uaktywnione
	private Light[] _lightsInUse;
	private boolean[] _lightsEnabled = { false, false, false, false, false,
			false, false, false };

	// pomocnicze, wszystkie ktore sa wystarczająco blisko danego przedmiotu by
	// go oswietlac
	private List<Light> _lightsCloseEnough;

	public BasicLightEngine() {
		_lightsInUse = new Light[8];
		_lightsCloseEnough = new ArrayList<Light>();
		_lightRange = 50;
	}

	public void setProperLights(GameObject object, GL gl) {

		// potem przy wiekszym projekcie tutaj musi byc uwzglednienie siatki
		// obiektow, zeby wszystkich swiatel
		// nie sprawdzac za kazdym razem

		boolean[] lightsSetProperly = { false, false, false, false, false,
				false, false, false };
		List<Light> lightsToRemove = new ArrayList<Light>();

		lights = getLightsData();
		position = object.getPosition();

		_lightsCloseEnough.clear();

		for (Light light : lights) {
			Vector4D vec = new Vector4(light.getPosition());
			vec.substract(position);
			double distance = vec.length();
			if (distance < _lightRange) {
				_lightsCloseEnough.add(light);
			}
		}

		if (_lightsCloseEnough.size() < 9) {
			for (Light light : _lightsCloseEnough) {
				for (int i = 0; i < 8; i++) {
					if (_lightsInUse[i] == light) {
						gl.glLightfv(_GLLights[i], GL.GL_POSITION, light
								.getPosition().xyzwToFloatArray(), 0);
						if (!_lightsEnabled[i]) {
							gl.glEnable(_GLLights[i]);
							_lightsEnabled[i] = true;
						}
						lightsToRemove.add(light);
						lightsSetProperly[i] = true;
					}

				}
			}
		}

		for (Light light : lightsToRemove) {
			_lightsCloseEnough.remove(light);
		}

		for (Light light : _lightsCloseEnough) {
			for (int i = 0; i < 8; i++) {
				if (!lightsSetProperly[i]) {
					_lightsInUse[i] = light;

					gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, light
							.getDiffuse().toFloatArray(), 0);
					gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, light
							.getAmbient().toFloatArray(), 0);
					gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, light
							.getSpecular().toFloatArray(), 0);
					gl.glLightfv(GL.GL_LIGHT0, GL.GL_EMISSION, light
							.getEmissive().toFloatArray(), 0);
					gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, 
							light.getPosition().xyzwToFloatArray(), 0);

					if (!_lightsEnabled[i]) {
						gl.glEnable(_GLLights[i]);
						_lightsEnabled[i] = true;
					}

					lightsSetProperly[i] = true;

					break;
				}

			}
		}

		for (int i = 0; i < 8; i++) {
			if (!lightsSetProperly[i]) {
				gl.glDisable(_GLLights[i]);
				_lightsEnabled[i] = false;
			}
		}

	}

	private List<Light> getLightsData() {
		return Application.getGData().getLights();
	}

}
