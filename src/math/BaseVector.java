package math;

public class BaseVector extends BaseMatrix implements Vector {

	public BaseVector(double[] values, boolean vertical) {
		super(values, vertical);
	}

	public BaseVector(double x, double y, double z, double w) {
		super(4, 1);
		try {
			setElementAt(1, 1, x);
			setElementAt(2, 1, y);
			setElementAt(3, 1, z);
			setElementAt(4, 1, w);
		} catch (OutOfBoundsException e) {
			// Prawie jak zawsze na bank sie to nie pojawi, a jak tak tzn. ze
			// mam blad gdzies w konstruktorach
			e.printStackTrace();
		}
	}

	/**
	 * Makes vertical unit vector, if you want a horizontal one, transpose it
	 * after constructing
	 * 
	 * @param rows
	 */
	public BaseVector(int rows) {
		super(rows, 1);
	}

	public double getElementAt(int nr) throws OutOfBoundsException {
		if (isVertical()) {
			return getElementAt(nr, 1);
		} else {
			return getElementAt(1, nr);
		}
	}

	@Override
	public Vector setElementAt(int nr, double value) throws OutOfBoundsException {
		if (isVertical()) {
			setElementAt(nr, 1, value);
		} else {
			setElementAt(1, nr, value);
		}
		return this;
	}

	public int getFieldsNumber() {
		if (isVertical()) {
			return getRowsNumber();
		} else {
			return getColumnsNumber();
		}
	}

	@Override
	public double length() {
		double length = 0;
		for (int i = 0; i < getFieldsNumber(); i++) {
			try {
				length += getElementAt(i + 1) * getElementAt(i + 1);
			} catch (OutOfBoundsException e) {
				// Też nie ma prawa się wydarzyć
				e.printStackTrace();
			}
		}
		return Math.sqrt(length);
	}

	@Override
	public Vector normalize() {
		double length = this.length();
		for (int i = 0; i < getFieldsNumber(); i++) {
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
	public Vector add(Vector vector) throws IllegalArgumentException {
		if (this.getFieldsNumber() != vector.getFieldsNumber()) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < getFieldsNumber(); i++) {
			try {
				setElementAt(i + 1, this.getElementAt(i + 1) + vector.getElementAt(i + 1));
			} catch (OutOfBoundsException e) {
				// Jak zwykle nie wystapi
				e.printStackTrace();
			}
		}
		return this;
	}

	@Override
	public Vector substract(Vector vector) throws IllegalArgumentException {
		if (this.getFieldsNumber() != vector.getFieldsNumber()) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < getFieldsNumber(); i++) {
			try {
				setElementAt(i + 1, this.getElementAt(i + 1) - vector.getElementAt(i + 1));
			} catch (OutOfBoundsException e) {
				// Jak zwykle nie wystapi
				e.printStackTrace();
			}
		}
		return this;
	}

	@Override
	public boolean isVertical() {
		if (getColumnsNumber() == 1) {
			return true;
		}
		return false;
	}

	public boolean isNull() {
		try {
			for (int i = 0; i < getFieldsNumber(); i++) {
				if (getElementAt(i + 1) != 0) {
					return false;
				}
			}
		} catch (OutOfBoundsException e) {
			e.printStackTrace();
		}
		return true;
	}
	


}
