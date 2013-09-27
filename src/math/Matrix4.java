package math;

public class Matrix4 extends BaseSquareMatrix implements MatrixS4 {

	public Matrix4() {
		super(4);
	}

	public Matrix4(double[][] matrix) {
		super(matrix);
	}

	public Matrix4(MatrixS4 mat) {
		super(4);
		try {
			for (int i = 1; i <= 4; i++) {
				for (int j = 1; j <= 4; j++) {
					setElementAt(i, j, mat.getElementAt(i, j));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception

		}

	}

	@Override
	public MatrixS4 getArbitraryAxisRotationMatrix(double radian, Vector4D axis) {
		double c = Math.cos(radian);
		double s = Math.sin(radian);
		double x = axis.getX();
		double y = axis.getY();
		double z = axis.getZ();

		getIdentityMatrix();

		try {
			setElementAt(1, 1, x * x * (1 - c) + c);
			setElementAt(2, 2, y * y * (1 - c) + c);
			setElementAt(3, 3, z * z * (1 - c) + c);
			setElementAt(2, 1, x * y * (1 - c) - z * s);
			setElementAt(3, 1, x * z * (1 - c) + y * s);
			setElementAt(1, 2, x * y * (1 - c) + z * s);
			setElementAt(1, 3, x * z * (1 - c) - y * s);
			setElementAt(3, 2, y * z * (1 - c) - x * s);
			setElementAt(2, 3, y * z * (1 - c) + x * s);
		} catch (OutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this;
	}

	public MatrixS4 getArbitraryAxisRotationMatrix(double radian, double x,
			double y, double z) {
		double c = Math.cos(radian);
		double s = Math.sin(radian);

		getIdentityMatrix();

		try {
			setElementAt(1, 1, x * x * (1 - c) + c);
			setElementAt(2, 2, y * y * (1 - c) + c);
			setElementAt(3, 3, z * z * (1 - c) + c);
			setElementAt(2, 1, x * y * (1 - c) - z * s);
			setElementAt(3, 1, x * z * (1 - c) + y * s);
			setElementAt(1, 2, x * y * (1 - c) + z * s);
			setElementAt(1, 3, x * z * (1 - c) - y * s);
			setElementAt(3, 2, y * z * (1 - c) - x * s);
			setElementAt(2, 3, y * z * (1 - c) + x * s);
		} catch (OutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this;
	}

	@Override
	public MatrixS4 getTranslationMatrix(Vector4D translate) {
		getIdentityMatrix();
		try {
			setElementAt(4, 1, translate.getX());
			setElementAt(4, 2, translate.getY());
			setElementAt(4, 3, translate.getZ());
		} catch (OutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this;
	}

	@Override
	public MatrixS4 getTranslationMatrix(double x, double y, double z) {
		getIdentityMatrix();
		try {
			setElementAt(4, 1, x);
			setElementAt(4, 2, y);
			setElementAt(4, 3, z);
		} catch (OutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this;
	}

	@Override
	public MatrixS4 getXAxisRotationMatrix(double radian) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public MatrixS4 getYAxisRotationMatrix(double radian) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public MatrixS4 getZAxisRotationMatrix(double radian) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public MatrixS4 translate(double x, double y, double z) {
		MatrixS4 tempMatrix = new Matrix4();
		tempMatrix.getIdentityMatrix();
		try {
			tempMatrix.setElementAt(4, 1, x);
			tempMatrix.setElementAt(4, 2, y);
			tempMatrix.setElementAt(4, 3, z);
		} catch (OutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		multiply(tempMatrix);

		return this;
	}

	@Override
	public MatrixS4 rotate(double radian, double x, double y, double z) {
		double c = Math.cos(radian);
		double s = Math.sin(radian);
		MatrixS4 tempMatrix = new Matrix4();
		tempMatrix.getIdentityMatrix();

		try {
			tempMatrix.setElementAt(1, 1, x * x * (1 - c) + c);
			tempMatrix.setElementAt(2, 2, y * y * (1 - c) + c);
			tempMatrix.setElementAt(3, 3, z * z * (1 - c) + c);
			tempMatrix.setElementAt(2, 1, x * y * (1 - c) - z * s);
			tempMatrix.setElementAt(3, 1, x * z * (1 - c) + y * s);
			tempMatrix.setElementAt(1, 2, x * y * (1 - c) + z * s);
			tempMatrix.setElementAt(1, 3, x * z * (1 - c) - y * s);
			tempMatrix.setElementAt(3, 2, y * z * (1 - c) - x * s);
			tempMatrix.setElementAt(2, 3, y * z * (1 - c) + x * s);
		} catch (OutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		multiply(tempMatrix);

		return this;
	}

	@Override
	public MatrixS4 getScalingMatrix(double x, double y, double z) {
		getIdentityMatrix();
		try {
			setElementAt(1, 1, x);
			setElementAt(2, 2, y);
			setElementAt(3, 3, z);
		} catch (OutOfBoundsException e) {
			// impossible
		}
		return this;
	}

}
