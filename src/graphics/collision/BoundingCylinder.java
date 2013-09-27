package graphics.collision;

import math.Vector4D;

public class BoundingCylinder implements BoundingBox {


	public boolean doesCollide(BasicBoundingSphere sphere) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getBoxType() {
		return BoundingBox.CYLINDERBOX;
	}

	@Override
	public void updateBox(Vector4D _position, double xrot, double yrot,
			double zrot) {
		// TODO Auto-generated method stub
		
	}

}
