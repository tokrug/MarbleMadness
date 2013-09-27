package math;

public interface Plane {

	public Vector4D getNormalVector();
	
	public Vector4D getPointOnPlane();
	
	public double distanceToIntersection(Ray ray);
	
}
