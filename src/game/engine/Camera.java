package game.engine;

import math.Vector4D;

public interface Camera {

	public void getView();

	public void update(double dt);
	
	public Vector4D getCurrentCenter();
	
	public Vector4D getCurrentLookAt();
	
	public Vector4D getUpVector();
	
	public Vector4D getWantedCenter();
	
	public Vector4D getWantedLookAt();

}
