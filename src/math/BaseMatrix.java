package math;

public class BaseMatrix implements Matrix {

	private double[][] _values;

	private int _rowNum;
	private int _colNum;

	public BaseMatrix(int rows, int columns) {
		_values = new double[rows][columns];
		_rowNum = rows;
		_colNum = columns;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (i==j) {
					_values[i][j] = 1;
				} else {
					_values[i][j] = 0;
				}
			}
		}
	}

	public BaseMatrix(int rows, int columns, int value) {
		_values = new double[rows][columns];
		_rowNum = rows;
		_colNum = columns;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				_values[i][j] = value;
			}
		}
	}

	public BaseMatrix(double[][] values) {
		_values = values;
		_rowNum = values.length;
		_colNum = values[0].length;
	}
	
	protected BaseMatrix(double[] values, boolean vertical) {
		double[][] vals;
		if (vertical) {
			vals = new double[values.length][1];
			for (int i = 0; i < values.length; i++) {
				vals[i][0] = values[i];
			}
			_rowNum = values.length;
			_colNum = 1;
		} else {
			vals = new double[1][values.length];
			for (int i = 0; i < values.length; i++) {
				vals[0][i] = values[i];
			}
			_rowNum = 1;
			_colNum = values.length;
		}
		_values = vals;
	}
	
	protected BaseMatrix(int size) {
		_values = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j< size; j++) {
				if (i==j) {
					_values[i][j] = 1;
				} else {
					_values[i][j] = 0;
				}
			}
		}

		_rowNum = size;
		_colNum = size;
	}

	@Override
	public Matrix add(Matrix matrix) throws IllegalArgumentException {
		int rows = this.getRowsNumber();
		int cols = this.getColumnsNumber();
		if (matrix.getRowsNumber() != rows || matrix.getColumnsNumber() != cols) {
			throw new IllegalArgumentException("Macierze o różnych rozmiarach");
		}

		try {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					_values[i][j] += matrix.getElementAt(i+1, j+1);
				}
			}
		} catch (OutOfBoundsException e) {
			// Well, tutaj ten exception na pewno sie nie pojawi,
			// skoro sprawdzilem czy sa rownych rozmiarów te macierze
			e.printStackTrace();
		}
		
		return this;
	}

	@Override
	public Vector getColumnVector(int column) throws OutOfBoundsException {
		if (column > getColumnsNumber() || column < 1) {
			throw new OutOfBoundsException();
		}
		
		int length = _values.length;
		double[] vals = new double[length];
		for (int i = 0; i < length; i++) {
			vals[i] = _values[i][column-1];
		}

		return new BaseVector(vals, true);
	}

	@Override
	public int getColumnsNumber() {
		return _colNum;
	}

	@Override
	public double getElementAt(int row, int column) throws OutOfBoundsException {
		if (row > _rowNum || column > _colNum || row < 1 || column < 1) {
			throw new OutOfBoundsException();
		}
		return _values[row-1][column-1];
	}
	
	@Override
	public void setElementAt(int row, int column, double value) throws OutOfBoundsException {
		if (row > _rowNum || column > _colNum || row < 1 || column < 1) {
			throw new OutOfBoundsException();
		}
		_values[row-1][column-1] = value;
		
	}

	@Override
	public Vector getRowVector(int row) throws OutOfBoundsException {
		if (row > getRowsNumber() || row < 1) {
			throw new OutOfBoundsException();
		}

		double[] vals = _values[row-1];
		return new BaseVector(vals, false);
	}

	@Override
	public int getRowsNumber() {
		return _rowNum;
	}

	@Override
	public Matrix multiply(Matrix matrix) throws IllegalArgumentException {
		if (this.getColumnsNumber() != matrix.getRowsNumber()) {
			throw new IllegalArgumentException();
		}
		
		int row = this.getRowsNumber();
		int col = matrix.getColumnsNumber();
		
		//ile mnozen trzeba wykonac dla kazdego pola
		int length = this.getColumnsNumber();
		
		double[][] vals = new double[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				double sum = 0;
				for (int k = 0; k < length; k++) {
					try {
						sum += this.getElementAt(i+1, k+1) * matrix.getElementAt(k+1, j+1);
					} catch (OutOfBoundsException e) {
						// tez nie powinno sie nic takiego stac
						e.printStackTrace();
					}
				}
				vals[i][j] = sum;
			}
		}
		setNewValues(vals);
		return this;
	}

	@Override
	public Matrix multiply(double number) {
		for (int i = 0; i < getRowsNumber(); i++) {
			for (int j = 0; j< getColumnsNumber(); j++) {
				_values[i][j] *= number ;
			}
		}
		return this;
	}

	@Override
	public Matrix substract(Matrix matrix) throws IllegalArgumentException {
		int rows = getRowsNumber();
		int cols = getColumnsNumber();
		if (matrix.getRowsNumber() != rows || matrix.getColumnsNumber() != cols) {
			throw new IllegalArgumentException("Macierze o różnych rozmiarach");
		}

		try {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					_values[i][j] -= matrix.getElementAt(i+1, j+1);
				}
			}
		} catch (OutOfBoundsException e) {
			// Well, tutaj ten exception na pewno sie nie pojawi,
			// skoro sprawdzilem czy sa rownych rozmiarów te macierze
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public Matrix transpose() {
		double[][] result = new double[getColumnsNumber()][getRowsNumber()];
		for (int i = 0; i < getRowsNumber(); i++) {
			for (int j = 0; j < getColumnsNumber(); j++) {
				result[j][i] = _values[i][j];
			}
		}
		setNewValues(result);
		return this;
	}

	/**
	 * Do uzytku przy macierzach
	 * @param values
	 */
	protected void setNewValues(double[][] values) {
		_values = values;
		_rowNum = _values.length;
		_colNum = _values[0].length;
	}

}
