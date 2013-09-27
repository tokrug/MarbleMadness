package game.engine;

import game.level.BasicLevel;
import game.level.GameLevel;
import game.level.LevelSegment;
import game.menu.BlankCursor;
import game.menu.BlankMenu;
import game.menu.GameCursor;
import game.menu.Menu;
import graphics.GameObject;
import graphics.collision.CollisionDetector;
import graphics.collision.MMCollistionDetector;


import input.actions.ChangeLevelXRot;
import input.actions.ChangeLevelZRot;
import input.actions.InputActionInterface;
import input.actions.MovingCursorX;
import input.actions.MovingCursorY;
import input.actions.ResumeGameAction;

import input.controllers.InputEvent;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import application.Application;


/**
 * Fizyka itp. akcje, nie informacje. Dzieki temu latwiej bedzie
 * ze zrobieniem serwera, czy wlaczaniem menu (podmiana update)
 * Przy dużych zmianach stanów, można zrobić podklasy
 */

public class GameEngineLevel implements GameEngine {
	
	private PhysicsEngine _physics = new PhysicsEngine();
	
	private double _gameSpeed;
	private double frameTime;
	private double frameTimeLeft;
	private Camera _camera;
	
	private GameCursor _cursor;
	
	private Menu _menu;

	// pomocniczy wskaznik na liste kolizji
	private List<LevelSegment> _colList;
	
	// czy bedzie wyswietlany lvl czy menu bedzie zalezalo od GameEngine
	// to jest aktualny level
	private GameLevel _level;
	

	
	public GameEngineLevel() {
		//ostatecznie bedzie tutaj inna kamera, taka ktora pod��a za kulk�
		//_camera = new BasicCamera();
		_gameSpeed = 1.0;
		_cursor = new BlankCursor();
		_menu = new BlankMenu();
		_level = new BasicLevel();
		_camera = new LevelCamera(_level.getBall().getPosition());
//		_camera = new BasicCamera();
		//testowe
		
	}
	
	public GameEngineLevel(GameLevel level) {
		this();
		_level = level;
	}
	
	private void processInputEvents(long time) {
		Application.getInput().translateEvents(time);
	}

	
	// tu bedzie cala fizyka itp. w kazdej kolejnej klatce, kazdy obiekt bedzie znal swoja fizyk�
	// fizyka kolizji po tej wst�pnej
	// processInputEvents powinno byc dosc czesto wykonywane zeby mi sie kolejka nie zapelnila
	public void update(double dt) {
		frameTime = dt * _gameSpeed;
		frameTimeLeft = frameTime;
		// reakcja na klawisze i myszke
		processInputEvents(System.currentTimeMillis());
		
		_physics.makePhysics(_level, frameTimeLeft);
		
		_level.checkFunctional();

		//relikt, zostawione na wszelki wypadek
//		for (GameObject object : Application.getGData().get3DObjectsToRender()) {
//			object.update(frameTimeLeft);
//		}
//		for (GameObject object : Application.getGData().get2DObjectsToRender()) {
//			object.update(frameTimeLeft);
//		}
		
		_level.update(frameTimeLeft);
		
		
		_camera.update(frameTime);
//	    ustawienie ostatecznego polozenia kulki jako listenera
//		Application.getSound().updateSounds(_level.getBall().getPosition());

	}

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
	
	public Camera getCamera() {
		return _camera;
	}
	
	public void setGameSpeed(double speed) {
		_gameSpeed = speed;
	}
	
	/**
	 * Nieobs�ugiwane w tym stanie
	 */
	public void clickOnScreen() {
		
	}
	
	public GameCursor getCursor() {
		return _cursor;
	}
	
	public void startState() {
		
		Application.getInput().setAction(KeyEvent.VK_UP, new InputActionInterface() {

			@Override
			public void action(InputEvent event) {
				_level.XRotate(-Math.PI);
				
			}
			
		});
		
		Application.getInput().setAction(KeyEvent.VK_DOWN, new InputActionInterface() {

			@Override
			public void action(InputEvent event) {
				_level.XRotate(Math.PI);
				
			}
			
		});
		
		Application.getInput().setAction(KeyEvent.VK_LEFT, new InputActionInterface() {

			@Override
			public void action(InputEvent event) {
				_level.ZRotate(Math.PI);
				
			}
			
		});
		
		Application.getInput().setAction(KeyEvent.VK_RIGHT, new InputActionInterface() {

			@Override
			public void action(InputEvent event) {
				_level.ZRotate(-Math.PI);
				
			}
			
		});
		
		Application.getInput().setAction(MouseEvent.MOUSE_MOVED, new ChangeLevelZRot());
		Application.getInput().setAction(MouseEvent.MOUSE_DRAGGED, new ChangeLevelXRot());
		
		Application.getGData().add3DObject(_level.getGraphicalObjects());
		
		setGameSpeed(0);
		Application.getInput().setAction(KeyEvent.VK_SPACE, new ResumeGameAction());
		
	}
	
	public void endState() {

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
		List<GameObject> list = _level.getGraphicalObjects();
		// tutaj bedzie podawanie pozostalych obiektow, np. tych 2D z GUI
		return list;
	}

	@Override
	public List<String> getTextToRender() {
		List<String> list = _level.getTexts();
		return list;
	}
	
}
