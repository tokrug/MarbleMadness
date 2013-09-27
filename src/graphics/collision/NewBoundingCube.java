package graphics.collision;

import math.Vector4;
import math.Vector4D;

public class NewBoundingCube implements BoundingCube {

	private static double EPSILON = 0.0000000001;
	
	private Vector4D _position;


	// wysokosc sciany, szerokosc i grubosc klocka (bounding nie musi byc z
	// podstawa kwadratowa)
	private double _height;
	private double _width;
	private double _thick;

	// aktualny obrot bounding, wazna do wektorow normalnych
	// tylko yrot, inne mnie tutaj nie obchodza
	private double _yrot;

	// ostatni wektor normalny
	private Vector4D _lastNormal;

	public NewBoundingCube(Vector4D pos, double width, double height,
			double thick, double yrot) {
		_position = pos;
		_height = height;
		_width = width;
		_thick = thick;
	}

	public NewBoundingCube(Vector4D pos, double width, double height,
			double thick) {
		this(pos, width, height, thick, 0);
	}

	@Override
	public Vector4D getLastNormal() {
		return _lastNormal;
	}

	@Override
	public BoundingPlane getLastPlane() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean doesCollide(BasicBoundingSphere sphere) {
		Vector4 ballpos = new Vector4(sphere.getPosition());
		ballpos.substract(_position); // wzgledem srodka cube
		ballpos.yAxisRotation(-_yrot); // znormalizowanie polozenia

		// testowanie wysokosci, wg roznicy miedzy srodkami obiektow bez wzgledu
		// na promien
		if (!((ballpos.getY() < +_height / 2) && (ballpos.getY() > -_height / 2))) {
			return false;
		}

		// tutaj juz mnie y nie interesuje (przynajmniej w tym programie)
		ballpos.setY(0);

		// testowanie na osi Z
		if ((ballpos.getX() > -_width / 2) && (ballpos.getX() < +_width / 2)) {
			if (ballpos.getZ() > -_thick / 2 - sphere.getRadius()
					&& ballpos.getZ() < _thick / 2 + sphere.getRadius()) {
				return true;
			} else
				return false;
		}

		// testowanie na osi X
		if ((ballpos.getZ() > -_thick / 2) && (ballpos.getZ() < +_thick / 2)) {
			if (ballpos.getX() > -_width / 2 - sphere.getRadius()
					&& ballpos.getX() < _width / 2 + sphere.getRadius()) {
				return true;
			} else
				return false;
		}

		// testowanie na rogach
		Vector4 ru = new Vector4(_width / 2, 0, -_thick / 2);
		Vector4 rd = new Vector4(_width / 2, 0, _thick / 2);
		Vector4 lu = new Vector4(-_width / 2, 0, -_thick / 2);
		Vector4 ld = new Vector4(-_width / 2, 0, _thick / 2);

		if (ru.substract(ballpos).length() < sphere.getRadius() - EPSILON
				|| rd.substract(ballpos).length() < sphere.getRadius() - EPSILON
				|| lu.substract(ballpos).length() < sphere.getRadius() - EPSILON
				|| ld.substract(ballpos).length() < sphere.getRadius() - EPSILON) {

			
			return true;
		}

		return false;
		
		// normalny wektor nie jest ustawiony, trzeba go ustawic pozniej w
		// physicsEngine, inaczej
		// bym dwa razy te same obliczenia wykonywal

	}

	@Override
	public int getBoxType() {
		return BoundingBox.CUBEBOX;
	}

	@Override
	public void updateBox(Vector4D position, double xrot, double yrot,
			double zrot) {
		_position = position;
		_yrot = yrot;

	}

	@Override
	public void setLastNormal(Vector4D normal) {
		_lastNormal = normal;
	}
	
	public double getYAxisRotation() {
		return _yrot;
	}
	
	public double getCubeHeight() {
		return _height;
	}
	
	public double getCubeWidth() {
		return _width;
	}
	
	public double getCubeThick() {
		return _thick;
	}

}
