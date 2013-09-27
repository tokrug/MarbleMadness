package math;

public interface Vector4D extends Vector, Cloneable{
	
	public double getX();

	public Vector4D setX(double x);

	public double getY();

	public Vector4D setY(double y);

	public double getW();

	public Vector4D setW(double w);
	
	public double getZ();

	public Vector4D setZ(double z);
	
	public Vector4D add(double x, double y, double z);
	
	public Vector4D add(Vector4D vector);
	
	public Vector4D multiply(MatrixS4 matrix);
	
	public Vector4D arbitraryAxisRotation(double radian, Vector4D axis);
	
	public Vector4D yAxisRotation(double radian);

	public Vector4D xAxisRotation(double radian);
	
	public Vector4D zAxisRotation(double radian);
	
	public double[] xyzToDoubleArray();
	
	public float[] xyzToFloatArray();

	public Vector4D add(double x, double y, double z, double w);
	
	//moze jeszcze jakies obroty potem dodane jak sie polapie w kwaterionach
	
	public float[] xyzwToFloatArray();
	
	public double[] xyzwToDoubleArray();
	
	public double dotProduct(Vector4D vec);

}
