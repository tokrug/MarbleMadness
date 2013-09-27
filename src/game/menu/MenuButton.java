package game.menu;

import graphics.GameObject;

public interface MenuButton extends GameObject{

	public double getWidth();
	
	public double getHeight();
	
	public void actionOnClick();
	
	public void actionOnEnter();
	
	public void actionOnExit();
	
	public boolean isMouseWithin(double x, double y);
	
}
