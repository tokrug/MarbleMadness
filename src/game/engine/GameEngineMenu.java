package game.engine;

import game.level.BlankLevel;
import game.level.GameLevel;
import game.menu.ExitButton;
import game.menu.GameCursor;
import game.menu.MainMenu;
import game.menu.Menu;
import game.menu.MenuButton;
import game.menu.MenuCursor;
import graphics.GameObject;

import input.actions.MouseClick;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import math.Vector4;
import math.Vector4D;

import application.Application;

public class GameEngineMenu implements GameEngine {
	
	// poszczegolne id do menu
	private static int MAINMENU = 0;
	private static int OPTIONS  = 1;
	private static int HIGHSCORES = 2;
	private static int NEWGAME = 3;
	private static int CUSTOMIZE = 4;
	
	// tablica ze wsyzstkimi menu
	Menu[] _menuList;

	// aktualne menu
	private Menu _menu;
	
	private Camera _camera;
	
	//oczywiscie go nie ma, tylko dla zgodnosci interfejsow
	private GameLevel _level;
	
	private double _gameSpeed;
	
	//kursor, potrzebny np. w menu
	private GameCursor _cursor;
	
	// pozycja listenera w menu, aktualnie na 0,0,0 ustawione
	private Vector4D _tempPosVec;
	
	public GameEngineMenu() {
		_cursor = new MenuCursor();
		_camera = new StaticCamera();
		_level = new BlankLevel();
		MenuButton[] buts = new MenuButton[1];
		buts[0] = new ExitButton(100,100);
		_menu = new MainMenu(buts);
		_gameSpeed = 1.0;
		_tempPosVec = new Vector4(0,0,0);
	}
	
	/**
	 * Sama sobie pobierze wspolrzedne na ktorych aktualnie jest kursor
	 * tylko klikanie na obiekty 2D obs�ugiwane na razie
	 */
	@Override
	public void clickOnScreen() {
		_menu.mouseClick(_cursor.getX(), _cursor.getY());
		
	}

	@Override
	public void exitGame() {
		if (System.getProperty("os.name").toLowerCase().contains("linux")) {
			Runtime mycommand=java.lang.Runtime.getRuntime();
			try {
				mycommand.exec("xset r");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		System.exit(0);
		
	}

	@Override
	public void update(double dt) {
		dt = dt * _gameSpeed;
		Application.getInput().translateEvents(System.currentTimeMillis());
		// zeby mogl sie wlaczyc actionOnEnter czy Exit
		_menu.mousePresenceAt(_cursor.getX(), _cursor.getY());

		for (GameObject object : Application.getGData().get2DObjectsToRender()) {
			object.update(dt);
		}
		
		
		Application.getSound().updateSounds(_tempPosVec);
	}
	
	public void setCursor(GameCursor cursor) {
		_cursor = cursor;
	}

	@Override
	public void startState() {
		Application.getGData().add2DObject(_cursor);
		Application.getGData().add2DObject(_menu.getObjectsToRender());
		Application.getInput().setAction(MouseEvent.BUTTON1, new MouseClick());

		
	}

	@Override
	public void endState() {
		Application.getGData().remove2DObject(_cursor);
		List<GameObject> list = _menu.getObjectsToRender();
		for (GameObject object : list) {
			Application.getGData().remove2DObject(object);
		}
	}

	@Override
	public Camera getCamera() {
		return _camera;
	}

	@Override
	public GameCursor getCursor() {
		return _cursor;
	}

	@Override
	public void setGameSpeed(double speed) {
		_gameSpeed = speed;
	}

	@Override
	public GameLevel getLevel() {
		return _level;
	}

	@Override
	public Menu getMenu() {
		return _menu;
	}

	@Override
	public List<GameObject> getObjectsToRender() {
		List<GameObject> list = _menu.getObjectsToRender();
		list.add(_cursor);
		return list;
	}

	@Override
	public List<String> getTextToRender() {
		// TODO Auto-generated method stub
		return null;
	}

}
