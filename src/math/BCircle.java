package math;

public class BCircle implements Circle {

	private Vector2D _center;
	private double _radius;

	public BCircle(double x, double y, double radius) {
		_center = new Vector2(x, y);
		_radius = radius;
	}

	@Override
	public Vector2D getCenter() {
		return _center;
	}

	@Override
	public double getRadius() {
		return _radius;
	}

	public double[] getDistancesToIntesections(Ray ray) {
		double a = 1; // dlugosc kierunkowego wektora promienia
		double b = ray.getDirection().getX()
				* (ray.getStartingPoint().getX() - _center.getX())
				+ ray.getDirection().getY()
				* (ray.getStartingPoint().getY() - _center.getY());
		b = b * 2;
		double c = Math.pow(ray.getStartingPoint().getX() - _center.getX(), 2)
				+ Math.pow(ray.getStartingPoint().getY() - _center.getY(), 2)
				- (_radius * _radius);

		double delta = b * b - 4 * a * c;

		if (delta < 0)
			return new double[0];

		delta = Math.sqrt(delta);

		if (delta == 0) {
			double t = -b / 2;
			double[] ar = {t};
			return ar;
		}
		
		double t1 = (-b - delta) / 2;
		double t2 = (-b + delta) / 2;
		
		double[] arr = {t1,t2};
		return arr;
	}
	
	@Override
	public Vector2D[] getIntersections(Ray ray) {
		double a = 1; // dlugosc kierunkowego wektora promienia
		double b = ray.getDirection().getX()
				* (ray.getStartingPoint().getX() - _center.getX())
				+ ray.getDirection().getY()
				* (ray.getStartingPoint().getY() - _center.getY());
		b = b * 2;
		double c = Math.pow(ray.getStartingPoint().getX() - _center.getX(), 2)
				+ Math.pow(ray.getStartingPoint().getY() - _center.getY(), 2)
				- (_radius * _radius);

		double delta = b * b - 4 * a * c;

		if (delta < 0)
			return new Vector2D[0];

		delta = Math.sqrt(delta);

		if (delta == 0) {
			double t = -b / 2;
			Vector2 point = new Vector2(ray.getStartingPoint().getX()
					+ ray.getDirection().getX() * t, ray.getStartingPoint()
					.getY()
					+ ray.getDirection().getY() * t);
			Vector2D[] arr = {point};
			return arr;
		}
		
		double t1 = -b - delta / 2;
		double t2 = -b + delta / 2;
		
		Vector2 p1 = new Vector2(ray.getStartingPoint().getX()
				+ ray.getDirection().getX() * t1, ray.getStartingPoint()
				.getY()
				+ ray.getDirection().getY() * t1);
		
		Vector2 p2 = new Vector2(ray.getStartingPoint().getX()
				+ ray.getDirection().getX() * t2, ray.getStartingPoint()
				.getY()
				+ ray.getDirection().getY() * t2);
		
		Vector2D[] ar = {p1,p2};
		return ar;
	}

}
