package graphics.collision;

import math.Vector4;
import math.Vector4D;

public class BasicBoundingSphere implements BoundingSphere {

	private Vector4D _center;
	private double _radius;
	
	public BasicBoundingSphere(Vector4D pos, double radius) {
		_center = pos;
		_radius = radius;
	}
	
	public boolean doesCollide(BasicBoundingSphere sphere) {
		Vector4D distance = new Vector4(sphere.getPosition());
		distance.substract(this._center);
		double length = distance.length();
		if (length < this._radius + sphere.getRadius()) {
			return true;
		}
		return false;
	}
	
	public double getRadius() {
		return _radius;
	}
	
	public Vector4D getPosition() {
		return _center;
	}

	@Override
	public int getBoxType() {
		return BoundingBox.SPHEREBOX;
	}

	@Override
	public void updateBox(Vector4D _position, double xrot, double yrot,
			double zrot) {
		// TODO Auto-generated method stub
		
	}

}
