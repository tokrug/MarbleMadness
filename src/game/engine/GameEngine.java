package game.engine;

import java.util.List;

import game.level.GameLevel;
import game.menu.GameCursor;
import game.menu.Menu;
import graphics.GameObject;

public interface GameEngine {
	
	public void clickOnScreen();
	
	public void startState();
	
	public void endState();
	
	public void exitGame();
	
	public void update(double dt);
	
	public Camera getCamera();
	
	public GameCursor getCursor();
	
	public void setGameSpeed(double speed);
	
	public Menu getMenu();
	
	public GameLevel getLevel();
	
	public List<GameObject> getObjectsToRender();

	public List<String> getTextToRender();
}
