package game.menu;

import graphics.AbstractGameObject;
import application.Application;
import application.DataManager;

public class MenuCursor extends AbstractGameObject implements GameCursor {
	
	private int maxX = Application.getWindow().getWidth();
	private int maxY = Application.getWindow().getHeight();
	
	private final int minX = 0;
	private final int minY = 0;
	
	public MenuCursor() {
		super(Application.getWindow().getWidth()/2,Application.getWindow().getHeight()/2,0);
		setModel(DataManager.M_CURSOR);
	}
	
	public void moveXRelative(double xChange) {
		refreshWindowSize();
		setX(getX() + xChange);
		setX(Math.max(minX, Math.min(getX(), maxX)));
	}
	
	public void moveYRelative(double yChange) {
		refreshWindowSize();
		setY(getY() + yChange);
		setY(Math.max(minY, Math.min(getY(), maxY)));
	}
	
	private void refreshWindowSize() {
		maxX = Application.getWindow().getWidth();
		maxY = Application.getWindow().getHeight();
//		System.out.println("Mouse X: " + getX() + ", Y: " + getY());
	}
	
	
}
