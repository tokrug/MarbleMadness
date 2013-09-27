package math;

public class Vector2 extends BaseVector implements Vector2D {

	public Vector2(double[] values, boolean vertical) {
		super(values, vertical);
	}

	public Vector2(double[] values) {
		super(values, true);
	}
	
	public Vector2(double x, double y) {
		super(2);
		setX(x);
		setY(y);
	}
	
	public Vector2(Vector2D vec) {
		super(2);
		setX(vec.getX());
		setY(vec.getY());
	}

	public Vector2(Vector4D vec) {
		super(2);
		setX(vec.getX());
		setY(vec.getY());
	}

	@Override
	public Vector2D add(double x, double y) {
		setX(getX() + x);
		setY(getY() + y);
		return this;
	}

	@Override
	public Vector2D add(Vector2D vector) {
		setX(getX() + vector.getX());
		setY(getY() + vector.getY());
		return this;
	}

	@Override
	public double getX() {
		double val = 0;
		try {
			val = getElementAt(1);
		} catch (OutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return val;
	}

	@Override
	public double getY() {
		double val = 0;
		try {
			val = getElementAt(2);
		} catch (OutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return val;
	}

	@Override
	public void setX(double x) {
		try {
			setElementAt(1, x);
		} catch (OutOfBoundsException e) {
			// As always
			e.printStackTrace();
		}

	}

	@Override
	public void setY(double y) {
		try {
			setElementAt(2, y);
		} catch (OutOfBoundsException e) {
			// As always
			e.printStackTrace();
		}
	}

	@Override
	public double[] xyToDoubleArray() {
		double[] result = new double[2];
		result[0] = getX();
		result[1] = getY();
		return result;
	}

	@Override
	public float[] xyToFloatArray() {
		float[] result = new float[2];
		result[0] = (float) getX();
		result[1] = (float) getY();
		return result;
	}

}
