package application;

import game.level.MetalBall;
import graphics.Color;
import graphics.GameObject;
import graphics.light.AttachedLight;
import graphics.light.Light;
import graphics.objects.Podloga;

import java.util.ArrayList;
import java.util.List;

import math.Vector4;

import sound.SoundEmiter;

/**
 * Info dotyczace rozgrywki, w sumie to bedzie opakowanie dla wielu danych
 * Tak zrobione zeby samo wrzucalo do odpowiedniego pojemnika 
 * 
 */
public class GameData {
	
	// wszystkie obiekty ktore renderowalne
	private List<GameObject> _3DObjects;
	
	// obiekty 2D do renderowania (one beda na koncu renderowane)
	private List<GameObject> _2DObjects;

	// wszystkie zrodla swiatla
	private List<Light> _lights;

	// wszystkie zrodla dzwi�k�w
	private List<SoundEmiter> _sounds;

	public GameData() {
		_sounds = new ArrayList<SoundEmiter>();
		
		_3DObjects = new ArrayList<GameObject>();
		_2DObjects = new ArrayList<GameObject>();

		_lights = new ArrayList<Light>();
		_lights.add(new AttachedLight(new Vector4(5, 8, 8),
				new Color(0, 0, 0, 0), new Color(1, 1, 1, 1), new Color(0.6,0.6,0.6,0.6), new Color()));

	}

	public List<GameObject> get3DObjectsToRender() {
		return _3DObjects;
	}
	
	public List<GameObject> get2DObjectsToRender() {
		return _2DObjects;
	}

	public void add3DObject(GameObject object) {
		_3DObjects.add(object);
	}
	
	public void add3DObject(List<GameObject> list) {
		_3DObjects.addAll(list);
	}
	
	public void add2DObject(GameObject object) {
		_2DObjects.add(object);
	}
	
	public void add2DObject(List<GameObject> list) {
		_2DObjects.addAll(list);
	}
	
	public void remove2DObject(GameObject object) {
		_2DObjects.remove(object);
	}
	
	public void remove3DObject(GameObject object) {
		_3DObjects.remove(object);
	}

	public List<Light> getLights() {
		return _lights;
	}
	
	public void addLight(Light light) {
		_lights.add(light);
	}
	
	public void removeLight(Light light) {
		_lights.remove(light);
	}
	
	public void addSoundEmiter(SoundEmiter sound) {
		_sounds.add(sound);
	}
	
	public void removeSoundEmiter(SoundEmiter sound) {
		_sounds.remove(sound);
	}
	
	public List<SoundEmiter> getSounds() {
		return _sounds;
	}
	
}
