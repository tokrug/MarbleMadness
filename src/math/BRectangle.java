package math;

import java.util.ArrayList;
import java.util.List;

public class BRectangle implements Rectangle {
	
	private List<Vector2D> _points = new ArrayList<Vector2D>();
	private Vector2D _center;
	private double _height;
	private double _width;
	
	public BRectangle(Vector2D center, double height, double width) {
		_center = center;
		_height = height;
		_width = width;
		_points.add(new Vector2(center.getX() + width/2, center.getY() + height/2));
		_points.add(new Vector2(center.getX() - width/2, center.getY() - height/2));
		_points.add(new Vector2(center.getX() + width/2, center.getY() - height/2));
		_points.add(new Vector2(center.getX() - width/2, center.getY() + height/2));
	}
	
	public BRectangle(Vector2D p1, Vector2D p2, Vector2D p3, Vector2D p4) {
		_points.add(p1);
		_points.add(p2);
		_points.add(p3);
		_points.add(p4);
	}
	
	public BRectangle(double width, double height) {
		_center = new Vector2(0,0);
		_height = height;
		_width = width;
		_points.add(new Vector2(_center.getX() + width/2, _center.getY() + height/2));
		_points.add(new Vector2(_center.getX() - width/2, _center.getY() - height/2));
		_points.add(new Vector2(_center.getX() + width/2, _center.getY() - height/2));
		_points.add(new Vector2(_center.getX() - width/2, _center.getY() + height/2));
	}

	@Override
	public List<Vector2D> getPoints() {
		return _points;
	}

	@Override
	public Vector2D getCenter() {
		return _center;
	}

	@Override
	public double getHeight() {
		return _height;
	}

	@Override
	public double getWidth() {
		return _width;
	}

}
