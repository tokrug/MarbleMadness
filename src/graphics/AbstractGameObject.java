package graphics;

import graphics.animation.ModelAnimation;
import graphics.collision.BoundingBox;
import graphics.collision.NoCollisionBoundingBox;

import application.Application;

import math.Matrix4;
import math.MatrixS4;
import math.Vector4;
import math.Vector4D;
import math.Vertex;
import math.Vertex4;

public abstract class AbstractGameObject implements GameObject {

	// zmienne do globalnych przekszta�ce� modelu
	private Vector4D _position;

	// obrot, speed niekoniecznie bedzie potrzebny
	private double _xRotationAngle;
	private double _yRotationAngle;
	private double _zRotationAngle;
	private double _xRotationSpeed;
	private double _yRotationSpeed;
	private double _zRotationSpeed;

	// skalowanie modelu
	private double _xScale;
	private double _yScale;
	private double _zScale;
	
	// predkosci do skalowania (zamiast animacji z macierzy)
	private double _xScaleSpeed = 0;
	private double _yScaleSpeed = 0;
	private double _zScaleSpeed = 0;

	private int _currentAnimationFrame;
	// wyjsciowa wersja modelu
	private Model _model;
	// model w animacji, czyli po przemnozeniu przez odpowiednie macierze
	// (skeletal animation)
	private Model _animatedModel;

	// sama animacja, musi sie zgadzac ze szkieletem ( jesli chodzi o ilosc
	// kosci)
	private ModelAnimation _animation;

	// root macierzy dla tego obiektu
	private MatrixS4 _rootMatrix;

	// taki podreczny wektor do animacji, zeby za kazdym razem nie tworzyc
	// nowego
	private Vertex tempVex;
	private MatrixS4 tempMtx;

	// do kolizji potrzebne
	private BoundingBox _box;

	// to render this object or not
	private boolean _visible;

	public AbstractGameObject() {
		_xRotationAngle = 0;
		_xRotationSpeed = 0;
		_yRotationAngle = 0;
		_yRotationSpeed = 0;
		_zRotationAngle = 0;
		_zRotationSpeed = 0;
		_xScale = 1;
		_yScale = 1;
		_zScale = 1;
		_currentAnimationFrame = 0;
		_position = new Vector4(0, 0, 0);
		_rootMatrix = new Matrix4();
		tempMtx = new Matrix4();
		tempVex = new Vertex4();
		_box = new NoCollisionBoundingBox();
	}

	public AbstractGameObject(double x, double y, double z) {
		_xRotationAngle = 0;
		_xRotationSpeed = 0;
		_yRotationAngle = 0;
		_yRotationSpeed = 0;
		_zRotationAngle = 0;
		_zRotationSpeed = 0;
		_xScale = 1;
		_yScale = 1;
		_zScale = 1;
		_currentAnimationFrame = 0;
		_position = new Vector4(x, y, z);
		_rootMatrix = new Matrix4();
		tempMtx = new Matrix4();
		tempVex = new Vertex4();
		_box = new NoCollisionBoundingBox();
	}

	public AbstractGameObject(Vector4D position) {
		_xRotationAngle = 0;
		_xRotationSpeed = 0;
		_yRotationAngle = 0;
		_yRotationSpeed = 0;
		_zRotationAngle = 0;
		_zRotationSpeed = 0;
		_xScale = 1;
		_yScale = 1;
		_zScale = 1;
		_currentAnimationFrame = 0;
		_position = position;
		_rootMatrix = new Matrix4();
		tempMtx = new Matrix4();
		tempVex = new Vertex4();
		_box = new NoCollisionBoundingBox();
	}

	/**
	 * Tu b�dzie ca�a fizyka przedmiot�w, jesli jakis obiekt bedzie mial stac w
	 * miejscu to override update w podklasie
	 */
	public void update(double dt) {

		_xRotationAngle += _xRotationSpeed * dt;
		if (_xRotationAngle > 2 * Math.PI) {
			int times = (int) (_xRotationAngle / Math.PI);
			_xRotationAngle -= times * 2 * Math.PI;
		}

		_yRotationAngle += _yRotationSpeed * dt;
		if (_yRotationAngle > 2 * Math.PI) {
			int times = (int) (_yRotationAngle / Math.PI);
			_yRotationAngle -= times * 2 * Math.PI;
		}

		_zRotationAngle += _zRotationSpeed * dt;
		if (_zRotationAngle > 2 * Math.PI) {
			int times = (int) (_zRotationAngle / Math.PI);
			_zRotationAngle -= times * 2 * Math.PI;
		}
		
		_xScale += _xScaleSpeed * dt;
		if (_xScale <= 0) {
			_xScale = 0.000001;
		}
		_yScale += _yScaleSpeed * dt;
		if (_yScale <= 0) {
			_yScale = 0.000001;
		}
		_zScale += _zScaleSpeed * dt;
		if (_zScale <= 0) {
			_zScale = 0.000001;
		}

		animateModel();
		_box.updateBox(_position, _xRotationAngle, _yRotationAngle,
				_zRotationAngle);
	}

	protected void animateModel() {
		_rootMatrix.getIdentityMatrix();
		if (_xRotationAngle != 0) {
			tempMtx.getArbitraryAxisRotationMatrix(_xRotationAngle, 1, 0, 0);
//			tempMtx.getXAxisRotationMatrix(_xRotationAngle);
			_rootMatrix.multiply(tempMtx);
		}
		if (_yRotationAngle != 0) {
			tempMtx.getArbitraryAxisRotationMatrix(_yRotationAngle, 0, 1, 0);
//			tempMtx.getYAxisRotationMatrix(_yRotationAngle);
			_rootMatrix.multiply(tempMtx);
		}
		if (_zRotationAngle != 0) {
			tempMtx.getArbitraryAxisRotationMatrix(_zRotationAngle, 0, 0, 1);
			_rootMatrix.multiply(tempMtx);
		}
		MatrixS4 normalsTrans = new Matrix4();
		normalsTrans.getIdentityMatrix();
		normalsTrans.multiply(_rootMatrix);
		tempMtx.getScalingMatrix(_xScale, _yScale, _zScale);
		_rootMatrix.multiply(tempMtx);
		tempMtx.getTranslationMatrix(_position.getX(), _position.getY(),
				_position.getZ());
		_rootMatrix.multiply(tempMtx);

		for (int i = 0; i < _model.getSubsets().length; i++) {
			for (int j = 0; j < _model.getSubsets()[i].getVertices().length; j++) {
				_animatedModel.getSubsets()[i].getVertices()[j].setX(_model
						.getSubsets()[i].getVertices()[j].getX());
				_animatedModel.getSubsets()[i].getVertices()[j].setY(_model
						.getSubsets()[i].getVertices()[j].getY());
				_animatedModel.getSubsets()[i].getVertices()[j].setZ(_model
						.getSubsets()[i].getVertices()[j].getZ());
				_animatedModel.getSubsets()[i].getVertices()[j].setW(_model
						.getSubsets()[i].getVertices()[j].getW());
				_animatedModel.getSubsets()[i].getVertices()[j]
						.multiply(_rootMatrix);
			}
			for (int j = 0; j < _model.getSubsets()[i].getNormals().length; j++) {
				_animatedModel.getSubsets()[i].getNormals()[j].setX(_model
						.getSubsets()[i].getNormals()[j].getX());
				_animatedModel.getSubsets()[i].getNormals()[j].setY(_model
						.getSubsets()[i].getNormals()[j].getY());
				_animatedModel.getSubsets()[i].getNormals()[j].setZ(_model
						.getSubsets()[i].getNormals()[j].getZ());
				_animatedModel.getSubsets()[i].getNormals()[j].setW(_model
						.getSubsets()[i].getNormals()[j].getW());
				_animatedModel.getSubsets()[i].getNormals()[j]
						.multiply(normalsTrans);
			}
		}
	}

	public Vector4D getPosition() {
		return _position;
	}

	public void setPosition(Vector4D position) {
		this._position = position;
	}

	public void setX(double xposition) {
		_position.setX(xposition);
	}

	public void setY(double yposition) {
		_position.setY(yposition);
	}

	public void setZ(double zposition) {
		_position.setZ(zposition);
	}

	public double getX() {
		return _position.getX();
	}

	public double getY() {
		return _position.getY();
	}

	public double getZ() {
		return _position.getZ();
	}

	public double getXRotationAngle() {
		return _xRotationAngle;
	}

	public void setXRotationAngle(double rotationAngle) {
		_xRotationAngle = rotationAngle;
		if (_xRotationAngle > Math.PI) {
			int times = (int) (_xRotationAngle / Math.PI);
			_xRotationAngle -= times * Math.PI;
		}
	}

	public double getYRotationAngle() {
		return _yRotationAngle;
	}

	public void setYRotationAngle(double rotationAngle) {
		_yRotationAngle = rotationAngle;
		if (_yRotationAngle > Math.PI) {
			int times = (int) (_yRotationAngle / Math.PI);
			_yRotationAngle -= times * Math.PI;
		}
	}

	public double getZRotationAngle() {
		return _zRotationAngle;
	}

	public void setZRotationAngle(double rotationAngle) {
		_zRotationAngle = rotationAngle;
		if (_zRotationAngle > Math.PI) {
			int times = (int) (_zRotationAngle / Math.PI);
			_zRotationAngle -= times * Math.PI;
		}
	}

	public double getXRotationSpeed() {
		return _xRotationSpeed;
	}

	public void setXRotationSpeed(double rotationSpeed) {
		_xRotationSpeed = rotationSpeed;
	}

	public double getYRotationSpeed() {
		return _yRotationSpeed;
	}

	public void setYRotationSpeed(double rotationSpeed) {
		_yRotationSpeed = rotationSpeed;
	}

	public double getZRotationSpeed() {
		return _zRotationSpeed;
	}

	public void setZRotationSpeed(double rotationSpeed) {
		_zRotationSpeed = rotationSpeed;
	}

	public int getCurrentAnimationFrame() {
		return _currentAnimationFrame;
	}

	public void setCurrentAnimationFrame(int animationFrame) {
		_currentAnimationFrame = animationFrame;
	}

	public Model getAnimatedModel() {
		return _animatedModel;
	}

	public void setModel(Model _model) {
		this._model = _model;
	}

	public void setModel(int modelNr) {
		_model = Application.getDataMgr().getModel(modelNr);
		// utworzenie kopii
		_animatedModel = new BasicModel(_model);
	}

	public BoundingBox getBoundingBox() {
		return _box;
	}

	public void setBox(BoundingBox box) {
		_box = box;
	}

	public boolean isVisible() {
		return _visible;
	}

	public void setVisible(boolean visible) {
		_visible = visible;
	}

	public void setWidth(double x) {
		double baseWidth = _model.getDimensions().getX();
		if (baseWidth == 0) {
			baseWidth = 1;
		}
		_xScale = x / baseWidth;
//		System.out.println("X Scale:" +_xScale);
	}

	public void setHeight(double y) {
		double baseHeight = _model.getDimensions().getY();
		if (baseHeight == 0) {
			baseHeight = 1;
		}
		_yScale = y / baseHeight;
	}

	public void setThick(double z) {
		double baseThick = _model.getDimensions().getZ();
		if (baseThick == 0) {
			baseThick = 1;
		}
		_zScale = z / baseThick;
//		System.out.println("Z Scale:" +_zScale);
	}

	public void setDimensions(double x, double y, double z) {
		Vector4D vec = _model.getDimensions();
		if (vec.getX() == 0)
			vec.setX(1);
		if (vec.getY() == 0)
			vec.setY(1);
		if (vec.getZ() == 0)
			vec.setZ(1);
		_xScale = x / vec.getX();
		_yScale = y / vec.getY();
		_zScale = z / vec.getZ();
	}

	public void setXScale(double x) {
		if (x > 0)
			_xScale = x;
	}

	public void setYScale(double y) {
		if (y > 0)
			_yScale = y;
	}

	public void setZScale(double z) {
		if (z > 0)
			_zScale = z;
	}
	
	public void setXScaleSpeed(double speed) {
		_xScaleSpeed = speed;
	}
	
	public void setYScaleSpeed(double speed) {
		_yScaleSpeed = speed;
	}
	
	public void setZScaleSpeed(double speed) {
		_zScaleSpeed = speed;
	}
	
	public double getXScale() {
			return _xScale;
	}

	public double getYScale() {
		return _yScale;
}

	public double getZScale() {
		return _zScale;
}

}
