package math;

public class BaseSquareMatrix extends BaseMatrix implements SquareMatrix {

	public BaseSquareMatrix(double[][] values) {
		super(values);
	}

	public BaseSquareMatrix(int size) {
		super(size);
	}

	@Override
	public SquareMatrix exponentiation(int exponent) {

		int row = this.getRowsNumber();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < row; j++) {
				double sum = 0;
				for (int k = 0; k < row; k++) {
					try {
						sum += this.getElementAt(i + 1, k + 1)
								* this.getElementAt(k + 1, j + 1);
					} catch (OutOfBoundsException e) {
						// tez nie powinno sie nic takiego stac
						e.printStackTrace();
					}
				}
				try {
					setElementAt(i + 1, j + 1, sum);
				} catch (OutOfBoundsException e) {
					// Się nie zdarzy
					e.printStackTrace();
				}
			}
		}
		return this;
	}

	@Override
	public double getDeterminant() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SquareMatrix getIdentityMatrix() {
		int size = getRowsNumber();
		try {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (i == j) {
						setElementAt(i + 1, j + 1, 1);
					} else {
						setElementAt(i + 1, j + 1, 0);
					}
				}
			}
		} catch (OutOfBoundsException e) {
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public SquareMatrix getInverseMatrix() throws SingularMatrixException {
		// TODO Auto-generated method stub
		return this;
	}

}
