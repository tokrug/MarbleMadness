package math;

public interface MatrixS4 extends SquareMatrix {
	
	public MatrixS4 getXAxisRotationMatrix(double radian);
	
	public MatrixS4 getYAxisRotationMatrix(double radian);
	
	public MatrixS4 getZAxisRotationMatrix(double radian);

	public MatrixS4 getArbitraryAxisRotationMatrix(double radian, Vector4D axis);
	
	public MatrixS4 getArbitraryAxisRotationMatrix(double radian, double x, double y, double z);
	
	public MatrixS4 getTranslationMatrix(Vector4D translate);
	
	public MatrixS4 getTranslationMatrix(double x, double y, double z);
	
	public MatrixS4 getScalingMatrix(double x, double y, double z);
	
	public MatrixS4 rotate(double radian, double x, double y, double z);
	
	public MatrixS4 translate(double x, double y, double z);

}
