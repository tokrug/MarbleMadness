package game.menu;

import graphics.GameObject;

import java.util.List;

public interface Menu {
	
	public void mouseClick(double x, double y);
	
	public List<GameObject> getObjectsToRender();
	
	public void mousePresenceAt(double x, double y);
	
	public void addButton(MenuButton but);
	
	public void addBackground(GameObject back);
	
	public void clearBackground();
	
	public void clearButtons();

}
