package game.level;

import math.Vector4;
import math.Vector4D;
import application.Application;
import application.DataManager;
import graphics.AbstractGameObject;
import graphics.collision.BasicBoundingSphere;

public class MetalBall extends AbstractGameObject implements Ball {

	private double _elasticity = 0.8;
	
	private double _mass = 1;
	private Vector4D _velocity;
	
	private double _radius;
	
	BasicBoundingSphere _box;
	
	public MetalBall(double x, double y, double z) {
		super(x,y,z);
		setModel(DataManager.M_BALL);
		_radius = 1;
		setWidth(_radius*2);
		setHeight(_radius*2);
		setThick(_radius*2);
		_velocity = new Vector4();
		setBox(new BasicBoundingSphere(getPosition(),_radius));
	}
	
	public MetalBall(Vector4D position) {
		super(position);
		setModel(DataManager.M_BALL);
		setWidth(2);
		setHeight(2);
		setThick(2);
		_velocity = new Vector4();
	}
	
	public void update(double dt) {
		super.update(dt);
		
		// zrobione zupelnie na pałę, tak nie powinno być robione
		// ale za duzo kombinowania zeby to dobrze wygladalo
		// TODO
		if (getZScale() < 0.05 && getXScale() < 0.05) {
			Application.getGame().getLevel().setProperPositions();
			setXScaleSpeed(0.5);
			setZScaleSpeed(0.5);
			getVelocity().setX(0);
			getVelocity().setY(0);
			getVelocity().setZ(0);
			getVelocity().setW(1);
		} else if (getZScale() > 0.26 && getXScale() > 0.26) {
			setXScale(0.26);
			setXScaleSpeed(0);
			setZScale(0.26);
			setZScaleSpeed(0);
		}
		
		
		
	}

	/**
	 * Zwraca liczbę od 0 do 1 oznaczającą jaką część energii kula zachowa po uderzeniu w ścianę.
	 */
	public double getElasticity() {
		return _elasticity;
	}
	
	public void setElasticity(double elast) {
		_elasticity = elast;
	}
	
	public double getMass() {
		return _mass;
	}
	
	public void setMass(double mass) {
		_mass = mass;
	}
	
	public Vector4D getVelocity() {
		return _velocity;
	}

	@Override
	public double getRadius() {
		return _radius;
	}
	
}
