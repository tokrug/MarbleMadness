package graphics.collision;

import math.BRectangle;
import math.Plane;
import math.Rectangle;
import math.SRay;
import math.Vector2;
import math.Vector2D;
import math.Vector4;
import math.Vector4D;

public class BasicBoundingPlane implements BoundingPlane {

	private Plane _mainPlane;
	private Rectangle _limit;
	private Vector4D _limitCenter;

	public BasicBoundingPlane(Plane main, Vector4D center, double height,
			double width) {
		_mainPlane = main;
		_limitCenter = center;
		_limit = new BRectangle(new Vector2(0, 0), height, width);

	}

	@Override
	public boolean doesCollide(BasicBoundingSphere sphere) {
		Vector4D planenormal = new Vector4(_mainPlane.getNormalVector());
		double dist = _mainPlane.distanceToIntersection(new SRay(sphere
				.getPosition(), planenormal));
		if (Math.abs(dist) >= sphere.getRadius())
			return false;

		// w 3D punkt
		Vector4D circlecenter = (Vector4D) new Vector4(sphere.getPosition())
				.add(planenormal.multiply(dist));
		//		
		// Vector2D circlecent = new Vector2(circlecenter.getX(),
		// circlecenter.getY());
		// promien przekroju kuli
		double radius = Math.sqrt(sphere.getRadius() * sphere.getRadius()
				- dist * dist);
		
		circlecenter.substract(_limitCenter);
		double length = circlecenter.length();
		circlecenter.setZ(0).normalize().multiply(length);

		// Vector2D kwadratcenter = new Vector2(circlecent);
		// kwadratcenter.add(new
		// Vector2((Vector4D)(circlecenter.setZ(0).normalize().multiply(length))));
		//		
		// System.out.println("X: " + circlecenter.getX() + " Y: " +
		// circlecenter.getY());
		// System.out.println("Limit X: " + -_limit.getWidth()/2);
		// System.out.println("Limit Y: " + -_limit.getHeight()/2);
		if (circlecenter.getX() <= -_limit.getWidth() / 2 - radius)
			return false;
		if (circlecenter.getX() >= +_limit.getWidth() / 2 + radius)
			return false;
		if (circlecenter.getY() <= -_limit.getHeight() / 2 - radius)
			return false;
		if (circlecenter.getY() >= +_limit.getHeight() / 2 + radius)
			return false;

		return true;
	}

	@Override
	public int getBoxType() {
		return BoundingBox.PLANEBOX;
	}

	public Plane getMainPlane() {
		return _mainPlane;
	}

	@Override
	public void updateBox(Vector4D _position, double xrot, double yrot,
			double zrot) {

		_mainPlane.getNormalVector().setX(0);
		_mainPlane.getNormalVector().setY(0);
		_mainPlane.getNormalVector().setZ(1);
		_mainPlane.getNormalVector().xAxisRotation(xrot);
		_mainPlane.getNormalVector().yAxisRotation(yrot);
		_mainPlane.getNormalVector().zAxisRotation(zrot);

	}

}
