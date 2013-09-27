package graphics.collision;

import game.level.Ball;
import game.level.LevelSegment;

import java.util.List;

public interface CollisionDetector {

	/**
	 * Trzeba uwazac zeby nie zapetlic
	 * Nie moze byc dwa razy pod rzad kolizja z tym samym obiektem
	 * Tylko kolizje kuli z innymi elementami
	 * @param collisionable
	 * @param ball TODO
	 * @return
	 */
	public List<LevelSegment> getCollisions(List<LevelSegment> collisionable, Ball ball);
	
	public boolean doesBallCollide(LevelSegment objectA, Ball ball);
}
