package graphics.collision;

import game.level.Ball;
import game.level.LevelSegment;
import graphics.GameObject;

import java.util.ArrayList;
import java.util.List;

public class MMCollistionDetector implements CollisionDetector {
	
	private List<LevelSegment> _tempList = new ArrayList<LevelSegment>(); 
	
	@Override
	public List<LevelSegment> getCollisions(List<LevelSegment> collisionable, Ball ball) {
		_tempList.clear();
		BasicBoundingSphere sphere = (BasicBoundingSphere) (ball.getBoundingBox());
		
		for (LevelSegment seg : collisionable) {
			if (seg.getBoundingBox().doesCollide(sphere)) {
				_tempList.add(seg);
			}
		}
		
		return _tempList;
		
	}
	
	public boolean doesBallCollide(LevelSegment objectA, Ball ball) {
		return objectA.getBoundingBox().doesCollide((BasicBoundingSphere) ball.getBoundingBox());
	}

}
