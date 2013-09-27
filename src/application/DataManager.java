package application;

import javax.media.Player;
import javax.media.protocol.DataSource;

import graphics.BasicModel;
import graphics.Model;

import com.sun.opengl.util.texture.TextureData;

/**
 * Klasa odpowiadająca za odpowiednie zarządzanie modelami
 * by tego nie wczytywac co obiekt i by byly juz dostepne jak sa potrzebne.
 * Na razie może mało uzyteczne ale z rozwojem projektu na bank sie przyda
 * Będzie jeszcze trzeba zrobić usuwanie nieużywanych modeli, żeby za dużo nie zżerało pamięci
 * @author Admin
 *
 */

public class DataManager {
	
	private TextureDataManager _texMgr;
	private ModelDataManager _modelMgr;
	private SoundDataManager _soundMgr;
	
	private Thread _texThread;
	private Thread _modelThread;
	private Thread _soundThread;
	
	//model constants
	public static final int M_FLOOR = 0;
	public static final int M_TRIANGLE = 1;
	public static final int M_MENU = 2;
	public static final int M_CURSOR = 3;
	public static final int M_EXITBUTTON = 4;
	public static final int M_BALL = 5;
	public static final int M_WALL = 6;
	public static final int M_CUBE = 7;
	public static final int M_FLOOR1 = 8;
	public static final int M_HOLE = 9;
	public static final int M_END = 10;

	
	//texture constants
	public static final int T_PANELE = 0;
	public static final int T_MARBLE = 1;
	
	//sound constants
	public static final int S_SLASH = 0;


	
	
	public DataManager() {
		_texMgr = new TextureDataManager();
		_modelMgr = new ModelDataManager();
		_soundMgr = new SoundDataManager();
		_texThread = new Thread(_texMgr);
		_modelThread = new Thread(_modelMgr);
		_soundThread = new Thread(_soundMgr);
		_texThread.setDaemon(true);
		_modelThread.setDaemon(true);
		_soundThread.setDaemon(true);
		_texThread.start();
		_modelThread.start();
		_soundThread.start();	
		setupManagers();
	}
	
	public BasicModel getModel(Integer modelNumber) {
		return _modelMgr.getModel(modelNumber);
	}
	
	public TextureData getTexture(Integer texNumber) {
		return _texMgr.getTexture(texNumber);
	}
	
	public TextureData getTexture(String name) {
		return _texMgr.getTexture(name);
	}
	
	public Player getPlayer(Integer soundNumber) {
		return _soundMgr.getPlayer(soundNumber);
	}
	
	public void placeModelRequest(Integer modelNumber) {
		_modelMgr.placeModelRequest(modelNumber);
		_modelThread.interrupt();
	}
	
	public void placeTextureRequest(Integer texNumber) {
		_texMgr.placeTextureRequest(texNumber);
		_texThread.interrupt();
	}
	
	public void placeSoundRequest(Integer soundNumber) {
		_soundMgr.placeSoundRequest(soundNumber);
		_soundThread.interrupt();
	}
	
	public int getTextureID(String texname) {
		return _texMgr.getTextureID(texname);
	}
	
	private void setupManagers() {
		_texMgr.setup();
	}

}
