package graphics.objects;

import application.DataManager;
import game.level.LevelSegment;
import graphics.AbstractGameObject;
import graphics.collision.NewBoundingCube;

public class Floor extends AbstractGameObject implements LevelSegment {

	private int _row;
	private int _column;
	
	private double _height;
	private double _width;
	private double _thick;
	
	private double _elasticity = 0.8;
	
	public Floor() {
		setModel(DataManager.M_FLOOR1);
		_height = 2;
		_width = 21;
		_thick = 15;
		setWidth(_width);
		setThick(_thick);
		setHeight(_height);
//		setBox(new NewBoundingCube(getPosition(), _width, _height, _thick));
	}
	
	public Floor(int x, int y) {
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
