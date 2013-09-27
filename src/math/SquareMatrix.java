package math;

public interface SquareMatrix extends Matrix {
	
	public SquareMatrix getIdentityMatrix();
	
	public SquareMatrix exponentiation(int exponent);
	
	public double getDeterminant();
	
	public SquareMatrix getInverseMatrix() throws SingularMatrixException;
}
