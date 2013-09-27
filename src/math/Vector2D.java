package math;

public interface Vector2D extends Vector {
	
	public double getX();

	public void setX(double x);

	public double getY();

	public void setY(double y);
	
	public Vector2D add(double x, double y);
	
	public Vector2D add(Vector2D vector);
	
	public float[] xyToFloatArray();
	
	public double[] xyToDoubleArray();

}
