package game.level;


import graphics.GameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa tylko i wylacznie na potrzeby game engine menu
 * @author Admin
 *
 */
public class BlankLevel implements GameLevel {

	@Override
	public Ball getBall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LevelSegment> getCollisionable() {
		// TODO Auto-generated method stub
		return new ArrayList<LevelSegment>();
	}

	public List<GameObject> getGraphicalObjects() {
		return new ArrayList<GameObject>();
	}

	public double getXAxisRotation() {
		return 0;
	}

	public double getZAxisRotation() {
		return 0;
	}

	public double getTimeLeft() {
		return 0;
	}

	@Override
	public void setXAxisRotation(double xrot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setZAxisRotation(double zrot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void XRotate(double angle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ZRotate(double angle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(double time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkFunctional() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getStartColumn() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStartRow() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setProperPositions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getTexts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLevelState(int state) {
		// TODO Auto-generated method stub
		
	}

}
