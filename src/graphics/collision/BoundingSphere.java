package graphics.collision;

import math.Vector4D;

public interface BoundingSphere extends BoundingBox {
	
	public double getRadius();
	
	public Vector4D getPosition();

}
