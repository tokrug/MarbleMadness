package math;

public interface Matrix {
	
	public Matrix add(Matrix matrix) throws IllegalArgumentException;
	
	public Matrix substract(Matrix matrix) throws IllegalArgumentException;
	
	public Matrix multiply(Matrix matrix) throws IllegalArgumentException;
	
	public Matrix multiply(double number);
	
	public Matrix transpose();
	
	//na razie nie musi byc obslugiwane, moze potem zrobie
	//public int getMatrixRank();
	
	public int getRowsNumber();

	public int getColumnsNumber();
	
	public double getElementAt(int row, int column) throws OutOfBoundsException;
	
	public void setElementAt(int row, int column, double value) throws OutOfBoundsException;
	
	public Vector getRowVector(int row) throws OutOfBoundsException;

	public Vector getColumnVector(int column) throws OutOfBoundsException;
}
