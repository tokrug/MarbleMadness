package graphics.collision;

import math.Vector4D;

public interface BoundingBox {
	
	public static final int CUBEBOX = 0;
	
	public static final int CYLINDERBOX = 1;
	
	public static final int PLANEBOX = 2;
	
	public static final int SPHEREBOX = 3;
	
	/**
	 * Kolizja nast�puje gdy si� dwa boxy przecinaj�, nie jak tylko si� stykaj� 
	 * Inaczej b�d� powstawa� p�tle niesko�czone
	 * @param sphere
	 * @return
	 */
	public boolean doesCollide(BasicBoundingSphere sphere);
	
	public int getBoxType();

	public void updateBox(Vector4D _position, double xrot,
			double yrot, double zrot);

}
