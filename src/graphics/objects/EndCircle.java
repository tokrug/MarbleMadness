package graphics.objects;

import application.DataManager;
import game.level.LevelSegment;
import graphics.AbstractGameObject;

public class EndCircle extends AbstractGameObject implements LevelSegment {

	private int _row;
	private int _column;
	
	private double _width;
	private double _thick;
	
	public EndCircle(int row, int column) {
		setModel(DataManager.M_END);
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
