package graphics.objects;

import application.DataManager;
import game.level.LevelSegment;
import graphics.AbstractGameObject;

public class Hole extends AbstractGameObject implements LevelSegment {

	private int _row;
	private int _column;
	
	private double _width;
	private double _thick;
	
	public Hole(int column, int row) {
		setModel(DataManager.M_HOLE);
		_width = 2;
		_thick = 2;
		setWidth(_width);
		setThick(_thick);
		_row = row;
		_column = column;
	}
	
	@Override
	public void actionOnCollision() {
		// TODO Auto-generated method stub

	}

	@Override
	public double getElasticity() {
		return 1;
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
		return LevelSegment.FUNCTIONSEGMENT;
	}

}
