package math;

public interface Vector extends Matrix {
	
	public Vector normalize();

	public double length();
	
	public Vector add(Vector vector) throws IllegalArgumentException;
	
	public Vector substract(Vector vector) throws IllegalArgumentException;
	
	public double getElementAt(int nr) throws OutOfBoundsException;
	
	public Vector setElementAt(int nr, double value) throws OutOfBoundsException;
	
	public int getFieldsNumber();
	
	public boolean isVertical();
	
	public boolean isNull();
}
