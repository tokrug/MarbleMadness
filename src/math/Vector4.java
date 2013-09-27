package math;

public class Vector4 extends BaseVector implements Vector4D, Cloneable {

	public Vector4(double[] values, boolean vertical) {
		super(values, vertical);
	}

	public Vector4(double[] values) {
		super(values, true);
	}

	public Vector4(double x, double y, double z) {
		super(x, y, z, 1);
	}

	public Vector4() {
		super(1, 0, 0, 1);
	}

	/**
	 * Maly workaround zanim nie rozkminie klonowania
	 * 
	 * @param vec
	 */
	public Vector4(Vector4D vec) {
		this(vec.xyzwToDoubleArray());
	}

	@Override
	public Vector4D add(double x, double y, double z) {
		setX(getX() + x);
		setY(getY() + y);
		setZ(getZ() + z);
		return this;
	}

	@Override
	/*
	 * Nie dodaje 4 współrzędnej (znowu kwaterniony, nie wiem jeszcze jak to
	 * dziala)
	 */
	public Vector4D add(Vector4D vector) {
		setX(getX() + vector.getX());
		setY(getY() + vector.getY());
		setZ(getZ() + vector.getZ());
		return this;
	}

	@Override
	public Vector4D add(double x, double y, double z, double w) {
		setX(getX() + x);
		setY(getY() + y);
		setZ(getZ() + z);
		setW(getW() + w);
		return this;
	}

	@Override
	public Vector4D arbitraryAxisRotation(double radian, Vector4D axis) {
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		double u = axis.getX();
		double v = axis.getY();
		double w = axis.getZ();
		double v2 = v * v;
		double w2 = w * w;
		double u2 = u * u;
		double sinT = Math.sin(radian);
		double cosT = Math.cos(radian);
		double l2 = u2 + v2 + w2;
		double l = Math.sqrt(l2);
		double[] result = new double[3];

		result[0] = u * (u * x + v * y + w * z)
				+ (u * (-v * y - w * z) + (v2 + w2) * x) * cosT + l
				* (-w * y + v * z) * sinT;
		setX(result[0] / l2);

		result[1] = v * (u * x + v * y + w * z)
				+ (-v * (-u * x - w * z) + (u2 + w2) * y) * cosT + l
				* (w * x - u * z) * sinT;
		setY(result[1] / l2);

		result[2] = w * (u * x + v * y + w * z)
				+ (w * (-u * x - v * y) + (u2 + v2) * z) * cosT + l
				* (-v * x + u * y) * sinT;
		setZ(result[2] / l2);
		return this;
	}

	@Override
	public double getZ() {
		double val = 0;
		try {
			val = getElementAt(3);
		} catch (OutOfBoundsException e) {
			// To samo
			e.printStackTrace();
		}
		return val;
	}

	@Override
	public Vector4D setZ(double z) {
		try {
			setElementAt(3, z);
		} catch (OutOfBoundsException e) {
			// As always
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public double getW() {
		double val = 0;
		try {
			val = getElementAt(4);
		} catch (OutOfBoundsException e) {
			// Jak zwykle, nie przejmować się
			e.printStackTrace();
		}
		return val;
	}

	@Override
	public Vector4D setW(double w) {
		try {
			setElementAt(4, w);
		} catch (OutOfBoundsException e) {
			// As always
			e.printStackTrace();
		}

		return this;
		
	}

	@Override
	public Vector normalize() {

		double length = 0;
		for (int i = 0; i < getFieldsNumber() - 1; i++) {
			try {
				length += (getElementAt(i + 1) * getElementAt(i + 1));
			} catch (OutOfBoundsException e) {
				// Też nie ma prawa się wydarzyć
				e.printStackTrace();
			}
		}
		Math.sqrt(length);
		length = Math.sqrt(length);
		for (int i = 0; i < getFieldsNumber() - 1; i++) {
			try {
				setElementAt(i + 1, this.getElementAt(i + 1) / length);
			} catch (OutOfBoundsException e) {
				// Też się nie zdarzy
				e.printStackTrace();
			}
		}
		return this;
	}

	@Override
	public double length() {
		double length = 0;
		for (int i = 0; i < getFieldsNumber() - 1; i++) {
			try {
				length += (getElementAt(i + 1) * getElementAt(i + 1));
			} catch (OutOfBoundsException e) {
				// Też nie ma prawa się wydarzyć
				e.printStackTrace();
			}
		}
		return Math.sqrt(length);
	}

	@Override
	public Vector4D multiply(MatrixS4 matrix) {
		double[] result = new double[4];
		double suma;
		try {
			for (int i = 0; i < 4; i++) {
				suma = 0;
				for (int j = 0; j < 4; j++) {
					suma += this.getElementAt(j + 1)
							* matrix.getElementAt(j + 1, i + 1);
				}
				result[i] = suma;
			}
		} catch (OutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setX(result[0]);
		setY(result[1]);
		setZ(result[2]);
		setW(result[3]);

		return this;
	}

	@Override
	public Vector4D xAxisRotation(double radian) {

		double[] result = this.xyzwToDoubleArray();

		double y = result[1];
		double z = result[2];

		setY(Math.cos(radian) * y - Math.sin(radian) * z);
		setZ(Math.sin(radian) * y + Math.cos(radian) * z);

		return this;
	}

	@Override
	public double[] xyzToDoubleArray() {
		double[] result = new double[3];
		result[0] = getX();
		result[1] = getY();
		result[2] = getZ();
		return result;
	}

	@Override
	public float[] xyzToFloatArray() {
		float[] result = new float[3];
		result[0] = (float) getX();
		result[1] = (float) getY();
		result[2] = (float) getZ();
		return result;
	}

	@Override
	public double[] xyzwToDoubleArray() {
		double[] result = new double[4];
		result[0] = getX();
		result[1] = getY();
		result[2] = getZ();
		result[3] = getW();
		return result;
	}

	@Override
	public float[] xyzwToFloatArray() {
		float[] result = new float[4];
		result[0] = (float) getX();
		result[1] = (float) getY();
		result[2] = (float) getZ();
		result[3] = (float) getW();
		return result;
	}

	@Override
	public Vector4D yAxisRotation(double radian) {

		double[] result = xyzwToDoubleArray();

		double x = result[0];
		double z = result[2];

		setX(Math.cos(radian) * x + Math.sin(radian) * z);
		setZ(-(Math.sin(radian)) * x + Math.cos(radian) * z);

		return this;
	}

	@Override
	public Vector4D zAxisRotation(double radian) {
		
		double[] result = xyzwToDoubleArray();

		double x = result[0];
		double y = result[1];

		setX(Math.cos(radian) * x - Math.sin(radian) * y);
		setY(Math.sin(radian) * x + Math.cos(radian) * y);

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
	public Vector4D setX(double x) {
		try {
			setElementAt(1, x);
		} catch (OutOfBoundsException e) {
			// As always
			e.printStackTrace();
		}
		
		return this;

	}

	@Override
	public Vector4D setY(double y) {
		try {
			setElementAt(2, y);
		} catch (OutOfBoundsException e) {
			// As always
			e.printStackTrace();
		}
		
		return this;
	}

	@Override
	public String toString() {
		return "Vector4: X - " + getX() + ", Y - " + getY() + ", Z - " + getZ()
				+ ", W - " + getW();
	}

	@Override
	public boolean isNull() {
		if (getX() != 0 || getY() != 0 || getZ() != 0)
			return false;
		return true;
	}
	
	public double dotProduct(Vector4D vec) {
		return getX() * vec.getX() + getY() * vec.getY() + getZ() * vec.getZ();
	}

}
