package math;

public interface Circle {

	public double getRadius();
	
	public Vector2D getCenter();
	
	/**
	 * 
	 * @param ray - bierze pod uwage tylko x i y
	 * @return
	 */
	public Vector2D[] getIntersections(Ray ray);
	
	public double[] getDistancesToIntesections(Ray ray);
	
}
