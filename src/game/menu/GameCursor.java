package game.menu;

import graphics.GameObject;

public interface GameCursor extends GameObject{
	
	public void moveXRelative(double xChange);
	
	public void moveYRelative(double yChange);

}
