package game.level;


import graphics.GameObject;

import java.util.List;

public interface GameLevel {
	
	public static int START = 0;
	
	public static int MAIN = 1;
	
	public static int RETURN = 2;
	
	public static int COMPLETED = 3;

	public Ball getBall();
	
	public List<LevelSegment> getCollisionable();

	public double getTimeLeft();

	public double getZAxisRotation();

	public double getXAxisRotation();
	
	public void setZAxisRotation(double zrot);

	public void setXAxisRotation(double xrot);
	
	public void XRotate(double angle);
	
	public void ZRotate(double angle);

	public List<GameObject> getGraphicalObjects();
	
	public void update(double time);

	/**
	 * Dodatkowa funkcja, która sprawdza czy kulka jest jest na jakies dziurze
	 * Specjalnie robione poza silnikiem fizycznym
	 */
	public void checkFunctional();
	
	public int getStartRow();
	
	public int getStartColumn();
	
	public void setProperPositions();

	public List<String> getTexts();
	
	public void setLevelState(int state);
}
