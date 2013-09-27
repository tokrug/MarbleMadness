package graphics.collision;

import math.BRectangle;
import math.BasicPlane;
import math.Plane;
import math.Rectangle;
import math.Vector4;
import math.Vector4D;

public class BasicBoundingCube implements BoundingCube {

	// 4 plaszczyzny, sciany klocka
	private BasicBoundingPlane[] planes = new BasicBoundingPlane[4];

	// takich rozmiar�w jest ka�da �ciana (musza by� szesciany)
	private Rectangle _recLimit;

	// srodek klocka, na podstawie tego obliczane sa pozycje box�w dla
	// poszczeg�lnych scian
	private Vector4D _limitCenter;

	// ostatni plane, ktorego dotyczyla kolizja, z iloma scianami byla ostatnio kolizja
	private BasicBoundingPlane _lastPlane;
	private int _lastNumberOfCols;
	
	// wymiary scian, kazda taka sama
	private double _height;
	private double _width;

	public BasicBoundingCube(Vector4D _center, Rectangle rec) {
		_limitCenter = _center;
		_recLimit = rec;
		Vector4D planecent = (new Vector4(_center)).setZ(_center.getZ()
				+ rec.getWidth() / 2);
		planes[0] = new BasicBoundingPlane(new BasicPlane(planecent, new Vector4(0,
				0, 1)), planecent, rec.getWidth(), rec.getHeight());
		
		planecent = (new Vector4(_center)).setZ(_center.getZ()
				- rec.getWidth() / 2);
		planes[1] = new BasicBoundingPlane(new BasicPlane(planecent, new Vector4(0,
				0, -1)), planecent, rec.getWidth(), rec.getHeight());
		
		planecent = (new Vector4(_center)).setX(_center.getX()
				+ rec.getWidth() / 2);
		planes[2] = new BasicBoundingPlane(new BasicPlane(planecent, new Vector4(1,
				0, 0)), planecent, rec.getWidth(), rec.getHeight());
		
		planecent = (new Vector4(_center)).setX(_center.getX()
				- rec.getWidth() / 2);
		planes[3] = new BasicBoundingPlane(new BasicPlane(planecent, new Vector4(-1,
				0, 0)), planecent, rec.getWidth(), rec.getHeight());
		
		_width = rec.getWidth();
		_height = rec.getHeight();
	}
	
	public BasicBoundingCube(Vector4D _center, double width, double height) {
		this(_center, new BRectangle(width, height));
	}

	public boolean doesCollide(BasicBoundingSphere sphere) {
		int j = 0;
		for (int i = 0; i < planes.length; i++) {
			if (planes[i].doesCollide(sphere)) {
				_lastPlane = planes[i];
				++j;
				
			}
		}
		System.out.println(j);
//		if (j>1) {
//			System.out.println("Podwojna kolizja");
//		}
		if (j>=1) {
//			System.out.println("Pojedyncza kolizja");
			return true;
		}
		return false;
	}

	@Override
	public int getBoxType() {
		return BoundingBox.CUBEBOX;
	}

	@Override
	public void updateBox(Vector4D _position, double xrot, double yrot,
			double zrot) {
		_limitCenter = _position;
		Vector4D planecent = (new Vector4(_limitCenter)).setZ(_limitCenter.getZ()
				+ _width / 2);
		planes[0] = new BasicBoundingPlane(new BasicPlane(planecent, new Vector4(0,
				0, 1)), planecent, _width, _height);
		
		planecent = (new Vector4(_limitCenter)).setZ(_limitCenter.getZ()
				- _width / 2);
		planes[1] = new BasicBoundingPlane(new BasicPlane(planecent, new Vector4(0,
				0, -1)), planecent, _width, _height);
		
		planecent = (new Vector4(_limitCenter)).setX(_limitCenter.getX()
				+ _width / 2);
		planes[2] = new BasicBoundingPlane(new BasicPlane(planecent, new Vector4(1,
				0, 0)), planecent, _width, _height);
		
		planecent = (new Vector4(_limitCenter)).setX(_limitCenter.getX()
				- _width / 2);
		planes[3] = new BasicBoundingPlane(new BasicPlane(planecent, new Vector4(-1,
				0, 0)), planecent, _width, _height);

	}
	
	public BoundingPlane getLastPlane() {
		return _lastPlane;
	}
	
	public Vector4D getLastNormal() {
		// TODO
		return null;
	}
	
	public void setLastNormal(Vector4D normal) {
		// TODO
	}

	@Override
	public double getYAxisRotation() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCubeHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCubeThick() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCubeWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

}
