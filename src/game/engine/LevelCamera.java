package game.engine;

import javax.media.opengl.glu.GLU;

import application.Application;

import math.Vector4;
import math.Vector4D;

/**
 * Kamera uzywana podczas gry, różny kąt nachylenia do płaszczyzny lvlu w
 * zależności od ruchów myszki (przechylanie poziomu), inny silnik, punkt
 * oglądany jest stały tj. środek kulki, przez mysz zmienia się center, tylko do
 * wykorzystania w lvlu (pobieranie danych z obiektu typu Level)
 * 
 * @author Admin
 * 
 */
public class LevelCamera implements Camera {

	// stan do jakiego dąży kamera
	private Vector4D _wantedCenter;
	private Vector4D _wantedLookAt;

	// faktyczne, aktualne polozenie i kierunek patrzenia
	private Vector4D _center;
	private Vector4D _lookAt;
	private Vector4D _up;

	// to jest wektor wyjsciowy dla relacji lookAt i center
	private Vector4D _initialEyePos;

	public LevelCamera(Vector4D ballpos) {
		_lookAt = ballpos;
		_initialEyePos = new Vector4(0, 40, 0.000001);
		_center = new Vector4(_initialEyePos);
		_up = new Vector4(0, 1, 0);
	}

	@Override
	public void getView() {
		GLU glu = new GLU();
		glu.gluLookAt(_center.getX() + _lookAt.getX(), _center.getY()
				+ _lookAt.getY(), _center.getZ() + _lookAt.getZ(), _lookAt
				.getX(), _lookAt.getY(), _lookAt.getZ(), _up.getX(),
				_up.getY(), _up.getZ());
	}

	@Override
	public void update(double dt) {
		_center = new Vector4(_initialEyePos);
		_up.setX(0);
		_up.setY(1);
		_up.setZ(0);

		double yRot = Application.getGame().getLevel().getZAxisRotation();
		double xRot = Application.getGame().getLevel().getXAxisRotation();
		if (xRot > 0) {
			_up.multiply(-1);
		}

		
		_center.xAxisRotation(-xRot);
		_center.zAxisRotation(yRot);
		_up.zAxisRotation(yRot);

	}
	
	public Vector4D getCurrentCenter() {
		return _center;
	}
	
	public Vector4D getCurrentLookAt() {
		return _lookAt;
	}
	
	public Vector4D getUpVector() {
		return _up;
	}
	
	public Vector4D getWantedCenter() {
		return _wantedCenter;
	}
	
	public Vector4D getWantedLookAt() {
		return _wantedLookAt;
	}

}
