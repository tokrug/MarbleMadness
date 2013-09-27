package graphics.objects;

import application.DataManager;
import game.level.LevelSegment;
import graphics.AbstractGameObject;
import graphics.collision.BasicBoundingCube;
import graphics.collision.NewBoundingCube;

public class Cube extends AbstractGameObject implements LevelSegment {

	private int _row;
	private int _column;
	
	private double _height;
	private double _width;
	private double _thick;
	
	private double _elasticity = 0.8;
	
	public Cube() {
		setModel(DataManager.M_CUBE);
		_height = 2;
		_width = 3;
		_thick = 3;
		setWidth(_width);
		setThick(_thick);
		setHeight(_height);
		setBox(new NewBoundingCube(getPosition(), _width, _height, _thick));
	}
	
	public Cube(int x, int y) {
		this();
		_row = y;
		_column = x;
		
	}
	
	@Override
	public void actionOnCollision() {
		System.out.println("Cube Collision");
	}

	@Override
	public double getElasticity() {
		return _elasticity;
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

}
