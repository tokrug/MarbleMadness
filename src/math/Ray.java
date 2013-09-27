package math;

public interface Ray {

	public Vector4D getStartingPoint();
	
	public Vector4D getDirection();
	
	public Vector4D getPointOnRay(double distance);
	
}
