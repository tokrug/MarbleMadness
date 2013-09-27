package game.engine;

import javax.media.opengl.glu.GLU;

import application.Application;

import math.Vector4;
import math.Vector4D;

public class BasicCamera implements Camera {

	// lookAt jest relatywny w stosunku do center, latwiej z poruszaniem

	private Vector4D _center;
	private Vector4D _lookAt;
	private Vector4D _up;

	private double _cameraMoveSpeed;
	private double _cameraRotateSpeedMouse;
	private double _cameraRotateSpeedKeyboard;

	private int _xAxisMovement;
	private int _zAxisMovement;
	private int _yAxisMovement;
	private int _xAxisRotate;
	private int _yAxisRotate;
	private int _zAxisRotate;

	private Vector4D translatePosition;
	private Vector4D translateLookAt;
	private Vector4D localXAxis;

	public BasicCamera() {
		_center = new Vector4(0, 50, 3);
		_lookAt = new Vector4(0, -1, -0.1);
		_up = new Vector4(0, 1, 0);
		_cameraMoveSpeed = 10;
		_cameraRotateSpeedMouse = Math.PI / 1600;
		_cameraRotateSpeedKeyboard = Math.PI / 2;
		_xAxisMovement = 0;
		_xAxisRotate = 0;
		_zAxisMovement = 0;
		_yAxisRotate = 0;
		translatePosition = new Vector4();
		translateLookAt = new Vector4();
		localXAxis = new Vector4();
		_lookAt.normalize();
		localXAxis.setX(_lookAt.getZ() * -1);
		localXAxis.setZ(_lookAt.getX());
		localXAxis.normalize();
	}

	// jeszcze powinny byc ograniczenia zeby nie przeskakiwal view przy
	// patrzeniu 90 stopni w gore lub dol
	public void update(double dt) {
		translatePosition = new Vector4(0, 0, 0);
		translateLookAt = new Vector4(0, 0, 0);
		// moze sie troche dziwne wydawac ale dziala poprawnie
		localXAxis.setX(_lookAt.getZ() * -1);
		localXAxis.setZ(_lookAt.getX());
		localXAxis.normalize();

		Vector4D newXAxis = new Vector4(localXAxis);
		newXAxis.multiply(_xAxisMovement * dt);
		Vector4D newlook = new Vector4(_lookAt);

		if (_xAxisMovement != 0) {
			translatePosition.add(newXAxis);
		}

		if (_zAxisMovement != 0) {
			translatePosition.add(newlook.multiply(_zAxisMovement * dt));
		}

		if (_xAxisRotate != 0) {

			_lookAt.arbitraryAxisRotation(_xAxisRotate
					* _cameraRotateSpeedKeyboard * dt, localXAxis);

		}
		if (_yAxisRotate != 0) {
			_lookAt.yAxisRotation(-_yAxisRotate * _cameraRotateSpeedKeyboard
					* dt);
		}
		

		if (!translatePosition.isNull()) {
			translatePosition.normalize();
			_center.add(translatePosition.multiply(_cameraMoveSpeed * dt));
		}
		
	}

	/**
	 * U�ywa� tylko podczas display() w GLEventListener !
	 */
	public void getView() {
		GLU glu = new GLU();
		glu.gluLookAt(_center.getX(), _center.getY(), _center.getZ(), _center
				.getX()
				+ _lookAt.getX(), _center.getY() + _lookAt.getY(), _center
				.getZ()
				+ _lookAt.getZ(), _up.getX(), _up.getY(), _up.getZ());
	}

	@Override
	public Vector4D getCurrentCenter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector4D getCurrentLookAt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector4D getUpVector() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector4D getWantedCenter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector4D getWantedLookAt() {
		// TODO Auto-generated method stub
		return null;
	}

}
