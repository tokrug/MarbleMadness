package graphics.objects;

import math.BasicPlane;
import math.Vector4;
import application.Application;
import application.DataManager;
import game.level.LevelSegment;
import graphics.AbstractGameObject;
import graphics.collision.BasicBoundingPlane;

public class Wall extends AbstractGameObject implements LevelSegment {

	private int _row = 0;
	private int _column = 0;

	private double _width = 5;
	private double _height = 3;

	// ulamek energii jaki pozostanie po kolizji z ta sciana
	private double _elasticity = 0.6;

	public Wall() {
		setModel(DataManager.M_WALL);
		setWidth(_width);
		setHeight(_height);
		setBox(new BasicBoundingPlane(new BasicPlane(getPosition(), new Vector4(0,
				0, 1)), getPosition(), _height, _width));
	}

	public Wall(int x, int y, double yRot) {
		this();
		_column = x;
		_row = y;
		setYRotationAngle(yRot);
		setBox(new BasicBoundingPlane(new BasicPlane(getPosition(), new Vector4(0,
				0, 1)), getPosition(), _height, _width));

	}

	@Override
	public void actionOnCollision() {
		System.out.println("Collision detected!!11one");

	}

	@Override
	public int getSegmentColumn() {
		return _column;
	}

	@Override
	public int getSegmentRow() {
		return _row;
	}

	@Override
	public int getSegmentType() {
		return LevelSegment.PHYSICSEGMENT;
	}

	@Override
	public double getElasticity() {
		return _elasticity;
	}

}
