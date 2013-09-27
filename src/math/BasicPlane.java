package math;

public class BasicPlane implements Plane {

	private Vector4D _pointOnPlane;
	private Vector4D _normalVector;
	
	public BasicPlane(Plane pl) {
		_pointOnPlane = new Vector4(pl.getPointOnPlane());
		_normalVector = new Vector4(pl.getNormalVector());
	}
	
	public BasicPlane(Vector4D pop, Vector4D normal) {
		_pointOnPlane = pop;
		_normalVector = normal;
	}

	public Vector4D getPointOnPlane() {
		return _pointOnPlane;
	}
	
	public Vector4D getNormalVector() {
		return _normalVector;
	}
	
	public double getD() {
		return -1 * _pointOnPlane.dotProduct(_normalVector);
	}

	@Override
	public double distanceToIntersection(Ray ray) {
		/*
		 * stara wersja
		Vector4D tempPOP = new Vector4(getPointOnPlane());
		Vector4D tempdirection = new Vector4(ray.getDirection());
		double dist = (getNormalVector()
				.dotProduct((Vector4D) tempPOP
						.substract(ray.getStartingPoint())))
				/ (getNormalVector().dotProduct(tempdirection));
		return dist;
		*/
		
		double dist = _normalVector.dotProduct(ray.getStartingPoint()) + getD();
		dist *= -1;
		dist /= _normalVector.dotProduct(ray.getDirection());
		return dist;
	}

}
