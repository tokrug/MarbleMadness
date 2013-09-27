package graphics;

import graphics.collision.BoundingBox;

import math.Vector4D;

public interface GameObject {

	public void update(double dt);

	public Vector4D getPosition();

	public void setPosition(Vector4D position);

	public void setX(double xposition);

	public void setY(double yposition);

	public void setZ(double zposition);

	public double getX();

	public double getY();

	public double getZ();

	public double getXRotationAngle();

	public void setXRotationAngle(double rotationAngle);

	public double getYRotationAngle();

	public void setYRotationAngle(double rotationAngle);

	public double getZRotationAngle();

	public void setZRotationAngle(double rotationAngle);

	public double getXRotationSpeed();

	public void setXRotationSpeed(double rotationSpeed);

	public double getYRotationSpeed();

	public void setYRotationSpeed(double rotationSpeed);

	public double getZRotationSpeed();

	public void setZRotationSpeed(double rotationSpeed);

	public int getCurrentAnimationFrame();

	public void setCurrentAnimationFrame(int animationFrame);

	public Model getAnimatedModel();

	public void setModel(Model _model);

	public void setModel(int modelNr);

	public BoundingBox getBoundingBox();

	public void setBox(BoundingBox box);

	public void setWidth(double x);

	public void setHeight(double y);

	public void setThick(double z);

	public void setDimensions(double x, double y, double z);

	public void setXScale(double x);

	public void setYScale(double y);

	public void setZScale(double z);
	
	public void setXScaleSpeed(double speed);
	
	public void setYScaleSpeed(double speed);
	
	public void setZScaleSpeed(double speed);

}
