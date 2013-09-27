package graphics.collision;

import math.Vector4D;

public interface BoundingCube extends BoundingBox {

	public BoundingPlane getLastPlane();

	public Vector4D getLastNormal();
	
	/**
	 * Utworzone bo czasami potrzebne jest ustawienie później z zewnątrz, żeby
	 * dwa razy nie liczyć niektórych rzeczy
	 * 
	 * @param normal
	 */
	public void setLastNormal(Vector4D normal);
	
	public double getYAxisRotation();
	
	public double getCubeHeight();
	
	public double getCubeWidth();
	
	public double getCubeThick();

}
