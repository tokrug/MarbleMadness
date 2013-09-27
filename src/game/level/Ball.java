package game.level;

import graphics.GameObject;
import graphics.collision.BoundingBox;
import math.Vector4D;

public interface Ball extends GameObject{
	
	public double getElasticity();

	public void setElasticity(double elast);

	public double getMass();

	public void setMass(double mass);
	
	public double getRadius();

	public Vector4D getVelocity();
	
}
