package math;

import java.util.List;

public interface Rectangle {
	
	public List<Vector2D> getPoints();
	
	public Vector2D getCenter();
	
	public double getWidth();
	
	public double getHeight();

}
