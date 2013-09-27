package graphics.collision;

import math.Vector4D;

public class NoCollisionBoundingBox implements BoundingBox {

	@Override
	public boolean doesCollide(BasicBoundingSphere sphere) {
		return false;
	}

	@Override
	public int getBoxType() {
		return 0;
	}

	@Override
	public void updateBox(Vector4D _position, double xrot, double yrot,
			double zrot) {
		// TODO Auto-generated method stub
		
	}

}
