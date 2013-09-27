package game.engine;

import java.util.ArrayList;
import java.util.List;

import math.*;
import game.level.Ball;
import game.level.GameLevel;
import game.level.LevelSegment;
import graphics.collision.BoundingBox;
import graphics.collision.BasicBoundingCube;
import graphics.collision.BasicBoundingPlane;
import graphics.collision.BasicBoundingSphere;
import graphics.collision.BoundingCube;
import graphics.collision.BoundingPlane;
import graphics.collision.BoundingSphere;
import graphics.collision.CollisionDetector;
import graphics.collision.MMCollistionDetector;

public class PhysicsEngine {

	// nieskonczonosc, ale chyba nie bede jest wykorzystywal chociaz jest to
	// dobry pomysl
	private static final double INFINITY = Double.MAX_VALUE;
	private static final double EPSILON = 0.0000001;

	private static final double BALLMAXSPEED = 6;

	// tarcie toczne, nie zalezy od pr�dko�ci, jakas mala wartosc, jako
	// przyspieszenie
	private double _resistance = 0.05;

	// sta�a okre�laj�ca przyci�ganie ziemskie
	private static final Vector4D G = new Vector4(0, -10, 0);

	private CollisionDetector _colDetect;
	private LevelSegment _lastCollision;

	// pomocnicze
	private List<LevelSegment> _tempCollisionableList;
	double collisionTime;

	public PhysicsEngine() {
		_colDetect = new MMCollistionDetector();
		_tempCollisionableList = new ArrayList<LevelSegment>();
	}

	/**
	 * G��wna metoda, zarz�dza ca�� fizyk�
	 * 
	 * @param level
	 * @param frameTimeLeft
	 */
	public void makePhysics(GameLevel level, double frameTimeLeft) {
		_lastCollision = null;
		LevelSegment currentCollision = null;
		// obliczenie jakie sily dzialaja na kulke, update wektorow ruchu i
		// pozycji
		calculateForces(level, frameTimeLeft);
		move(level.getBall(), frameTimeLeft);
		frameTimeLeft = 0;
		// wykrycie kolizji i reakcja na nie
		_tempCollisionableList = _colDetect.getCollisions(level
				.getCollisionable(), level.getBall());
		while (_tempCollisionableList.size() != 0) {
			while (_tempCollisionableList.size() != 0) {
				// cofniecie kulki, pamietac o tym ze zostal jeszcze czas w tej
				// klatce niewykorzystany
				collisionTime = returnToCollisionPlace(level.getBall(),
						_tempCollisionableList.get(0));
				currentCollision = _tempCollisionableList.get(0);
				frameTimeLeft += collisionTime;
				_tempCollisionableList = _colDetect.getCollisions(level
						.getCollisionable(), level.getBall());
				_tempCollisionableList.remove(_lastCollision);
			}
			if (currentCollision != null) {
				reactToCollision(currentCollision, level.getBall());
				_lastCollision = currentCollision;
				move(level.getBall(), frameTimeLeft);
				frameTimeLeft = 0;
			}
			_tempCollisionableList = _colDetect.getCollisions(level
					.getCollisionable(), level.getBall());
		}

	}

	/**
	 * Oblicza jaka si�a dzia�a na obiekty i update'uje ich wektory pr�dko�ci
	 * Tutaj si�y dzia�aj� tylko na kul�
	 * 
	 * @param level
	 * @param frameTimeLeft
	 */
	private void calculateForces(GameLevel level, double frameTimeLeft) {
		// System.out.println("ZAxis Rot: " + level.getZAxisRotation());
		// System.out.println("XAxis Rot: " + level.getXAxisRotation());
		double sinz = Math.sin(level.getZAxisRotation());
		double sinx = Math.sin(level.getXAxisRotation());

		Vector4D ZAcc = new Vector4(0, 0, 1);
		Vector4D XAcc = new Vector4(1, 0, 0);

		XAcc.multiply(G.length() * sinz);
		ZAcc.multiply(G.length() * sinx);

		XAcc.add(ZAcc);
		XAcc.multiply(frameTimeLeft);
		// System.out.println(XAcc.toString());

		level.getBall().getVelocity().add(XAcc);

		// dzia�anie si�y tarcia

		double formerLenght = level.getBall().getVelocity().length();
		formerLenght -= _resistance * frameTimeLeft;
		if (formerLenght < 0)
			formerLenght = 0;
		level.getBall().getVelocity().normalize();
		level.getBall().getVelocity().multiply(formerLenght);

		if (level.getBall().getVelocity().length() > BALLMAXSPEED) {
			level.getBall().getVelocity().normalize().multiply(BALLMAXSPEED);
		}

		// obrot kulki
		// TODO
		
		Vector4 velocity = new Vector4(level.getBall().getVelocity());
		
		
		level.getBall().setXRotationSpeed(
				level.getBall().getVelocity().getZ() * Math.PI / 5);
		level.getBall().setZRotationSpeed(
				-level.getBall().getVelocity().getX() * Math.PI / 5);

	}

	/**
	 * Po prostu rusza obiekt zgodnie z jego pr�dko�ci�
	 * 
	 * @param ball
	 * @param frameTimeLeft
	 */
	public void move(Ball ball, double frameTimeLeft) {
		Vector4 direction = new Vector4(ball.getVelocity());
		direction.multiply(frameTimeLeft);
		ball.getPosition().add(direction);
	}

	public void moveBackward(Ball ball, double time) {
		Vector4 direction = (Vector4) (new Vector4(ball.getVelocity()))
				.multiply(-1);
		direction.multiply(time);
		ball.getPosition().add(direction);
	}

	/**
	 * Oblicza kiedy nast�pi�a kolizja, ustawia pozycj� kulki (w sumie
	 * jakiegokolwiek obiektu, ktory ma predkosc itp), zwraca info ile trwal
	 * powrot obiektu na miejsce kolizji (wartosc dodatnia)
	 * 
	 * @param ball
	 * @param colseg
	 * @return
	 */
	public double returnToCollisionPlace(Ball ball, LevelSegment colseg) {
		double timeUnused = 0;
		switch (colseg.getBoundingBox().getBoxType()) {
		case BoundingBox.PLANEBOX:
			BoundingPlane box = (BoundingPlane) colseg.getBoundingBox();
			// tworze rownolegla plaszczyzne przesunieta o promien kuli wzdluz
			// normalej
			Plane temppl = new BasicPlane(box.getMainPlane());
			temppl.getPointOnPlane().add(
					(Vector4D) temppl.getNormalVector().multiply(
							((BasicBoundingSphere) ball.getBoundingBox())
									.getRadius()));
			temppl.getNormalVector().normalize();

			double dist = temppl.distanceToIntersection(new SRay(ball
					.getPosition(), new Vector4(ball.getVelocity())));

			if (dist < 0) {
				timeUnused -= dist / ball.getVelocity().length();
				moveBackward(ball, timeUnused);
			}

			break;
		case BoundingBox.CUBEBOX:

			// bede robil "zewnetrzne" plany, obliczal miejsce przeciecia i
			// sprawdzal jego wspolrzedne wzgledem szerokosci i grubosci klocka
			BoundingCube cube = (BoundingCube) colseg.getBoundingBox();

			Vector4D ballpos = new Vector4(ball.getPosition());
			ballpos.substract(colseg.getPosition());
			ballpos.yAxisRotation(-colseg.getYRotationAngle());

			Vector4D revVel = new Vector4(ball.getVelocity());
			revVel.yAxisRotation(-colseg.getYRotationAngle());
			revVel.multiply(-1);

			Ray velRay = new SRay(new Vector4(ballpos), new Vector4(revVel));

			// zmienione y za z by bylo 2d (OXZ)
			Ray ray2d = new SRay(new Vector4(ballpos), new Vector4(revVel));
			ray2d.getStartingPoint().setY(ray2d.getStartingPoint().getZ());
			ray2d.getDirection().setY(ray2d.getDirection().getZ());

			// lewa sciana
			Plane plane = new BasicPlane(new Vector4(-cube.getCubeWidth() / 2
					- ball.getRadius(), 0, 0), new Vector4(-1, 0, 0));
			double distance = plane.distanceToIntersection(velRay);
			// jesli ta plaszczyzna jest za kulka, a nie przed
			if (distance > 0) {
				Vector4D pointOnRay = velRay.getPointOnRay(distance);
				// jesli ten punkt jest w ramach zewnetrznego kwadratu (łącznie
				// z rogami licze)
				if (pointOnRay.getZ() >= -cube.getCubeThick() / 2
						- ball.getRadius()
						&& pointOnRay.getZ() < cube.getCubeThick() / 2
								+ ball.getRadius()) {
					// jesli kulka styka sie ze sciana, a nie rogiem
					if (pointOnRay.getZ() >= -cube.getCubeThick() / 2
							&& pointOnRay.getZ() <= cube.getCubeThick() / 2) {
						timeUnused = distance / ball.getVelocity().length();
						moveBackward(ball, timeUnused);
						cube.setLastNormal(new Vector4(-1, 0, 0));
						cube.getLastNormal().yAxisRotation(
								colseg.getYRotationAngle());
						break;
					}
					// tutaj wiadomo ze sie styka w rogu ktoryms
					// najpierw testuje kolo gorne lewe
					Circle lu = new BCircle(-cube.getCubeWidth() / 2, -cube
							.getCubeThick() / 2, ball.getRadius());
					double[] distances = lu.getDistancesToIntesections(ray2d);
					// jesli punkty wspolne sa, musza byc dwa, nie bedzie nigdy
					// sytuacji ze punkt bylby tylko jeden, wtedy by w ogole nie
					// wykrylo kolizji
					if (distances.length > 1 && distances[0] * distances[1] < 0) {
						cube.setLastNormal(new Vector4(-1, 0, -1));
						cube.getLastNormal().yAxisRotation(
								colseg.getYRotationAngle());
						cube.getLastNormal().normalize();
						double dista;
						if (distances[0] > distances[1]) {
							dista = distances[0];
						} else
							dista = distances[1];
						timeUnused = dista / ball.getVelocity().length();
						moveBackward(ball, timeUnused);
						break;
					}

					Circle ld = new BCircle(-cube.getCubeWidth() / 2, cube
							.getCubeThick() / 2, ball.getRadius());
					distances = ld.getDistancesToIntesections(ray2d);

					if (distances.length > 1 && distances[0] * distances[1] < 0) {
						cube.setLastNormal(new Vector4(-1, 0, 1));
						cube.getLastNormal().yAxisRotation(
								colseg.getYRotationAngle());
						cube.getLastNormal().normalize();
						double dista;
						if (distances[0] > distances[1]) {
							dista = distances[0];
						} else
							dista = distances[1];
						timeUnused = dista / ball.getVelocity().length();
						moveBackward(ball, timeUnused);
						break;
					}

				}
			}

			// dolna zewnetrzna sciana
			plane = new BasicPlane(new Vector4(0, 0, cube.getCubeWidth() / 2
					+ ball.getRadius()), new Vector4(0, 0, 1));
			distance = plane.distanceToIntersection(velRay);
			// jesli ta plaszczyzna jest za kulka, a nie przed
			if (distance > 0) {
				Vector4D pointOnRay = velRay.getPointOnRay(distance);
				// jesli ten punkt jest w ramach zewnetrznego kwadratu (łącznie
				// z rogami licze)
				if (pointOnRay.getX() >= -cube.getCubeWidth() / 2
						- ball.getRadius()
						&& pointOnRay.getX() < cube.getCubeWidth() / 2
								+ ball.getRadius()) {
					// jesli kulka styka sie ze sciana, a nie rogiem
					if (pointOnRay.getX() >= -cube.getCubeWidth() / 2
							&& pointOnRay.getX() <= cube.getCubeWidth() / 2) {
						timeUnused = distance / ball.getVelocity().length();
						moveBackward(ball, timeUnused);
						cube.setLastNormal(new Vector4(0, 0, 1));
						cube.getLastNormal().yAxisRotation(
								colseg.getYRotationAngle());
						break;
					}
					// tutaj wiadomo ze sie styka w rogu ktoryms
					// najpierw testuje kolo gorne dolne
					Circle ld = new BCircle(-cube.getCubeWidth() / 2, cube
							.getCubeThick() / 2, ball.getRadius());
					double[] distances = ld.getDistancesToIntesections(ray2d);
					// jesli punkty wspolne sa, musza byc dwa, nie bedzie nigdy
					// sytuacji ze punkt bylby tylko jeden, wtedy by w ogole nie
					// wykrylo kolizji
					if (distances.length > 1 && distances[0] * distances[1] < 0) {
						cube.setLastNormal(new Vector4(-1, 0, 1));
						cube.getLastNormal().yAxisRotation(
								colseg.getYRotationAngle());
						cube.getLastNormal().normalize();
						double dista;
						if (distances[0] > distances[1]) {
							dista = distances[0];
						} else
							dista = distances[1];
						timeUnused = dista / ball.getVelocity().length();
						moveBackward(ball, timeUnused);
						break;
					}

					Circle rd = new BCircle(cube.getCubeWidth() / 2, cube
							.getCubeThick() / 2, ball.getRadius());
					distances = rd.getDistancesToIntesections(ray2d);

					if (distances.length > 1 && distances[0] * distances[1] < 0) {
						cube.setLastNormal(new Vector4(1, 0, 1));
						cube.getLastNormal().yAxisRotation(
								colseg.getYRotationAngle());
						cube.getLastNormal().normalize();
						double dista;
						if (distances[0] > distances[1]) {
							dista = distances[0];
						} else
							dista = distances[1];
						timeUnused = dista / ball.getVelocity().length();
						moveBackward(ball, timeUnused);
						break;
					}

				}
			}

			// prawa sciana
			plane = new BasicPlane(new Vector4(cube.getCubeWidth() / 2
					+ ball.getRadius(), 0, 0), new Vector4(1, 0, 0));
			distance = plane.distanceToIntersection(velRay);
			// jesli ta plaszczyzna jest za kulka, a nie przed
			if (distance > 0) {
				Vector4D pointOnRay = velRay.getPointOnRay(distance);
				// jesli ten punkt jest w ramach zewnetrznego kwadratu (łącznie
				// z rogami licze)
				if (pointOnRay.getZ() >= -cube.getCubeThick() / 2
						- ball.getRadius()
						&& pointOnRay.getZ() < cube.getCubeThick() / 2
								+ ball.getRadius()) {
					// jesli kulka styka sie ze sciana, a nie rogiem
					if (pointOnRay.getZ() >= -cube.getCubeThick() / 2
							&& pointOnRay.getZ() <= cube.getCubeThick() / 2) {
						timeUnused = distance / ball.getVelocity().length();
						moveBackward(ball, timeUnused);
						cube.setLastNormal(new Vector4(1, 0, 0));
						cube.getLastNormal().yAxisRotation(
								colseg.getYRotationAngle());
						break;
					}
					// tutaj wiadomo ze sie styka w rogu ktoryms
					// najpierw testuje kolo gorne lewe
					Circle rd = new BCircle(cube.getCubeWidth() / 2, cube
							.getCubeThick() / 2, ball.getRadius());
					double[] distances = rd.getDistancesToIntesections(ray2d);
					// jesli punkty wspolne sa, musza byc dwa, nie bedzie nigdy
					// sytuacji ze punkt bylby tylko jeden, wtedy by w ogole nie
					// wykrylo kolizji
					if (distances.length > 1 && distances[0] * distances[1] < 0) {
						cube.setLastNormal(new Vector4(1, 0, 1));
						cube.getLastNormal().yAxisRotation(
								colseg.getYRotationAngle());
						cube.getLastNormal().normalize();
						double dista;
						if (distances[0] > distances[1]) {
							dista = distances[0];
						} else
							dista = distances[1];
						timeUnused = dista / ball.getVelocity().length();
						moveBackward(ball, timeUnused);
						break;
					}

					Circle ru = new BCircle(cube.getCubeWidth() / 2, -cube
							.getCubeThick() / 2, ball.getRadius());
					distances = ru.getDistancesToIntesections(ray2d);

					if (distances.length > 1 && distances[0] * distances[1] < 0) {
						cube.setLastNormal(new Vector4(1, 0, -1));
						cube.getLastNormal().yAxisRotation(
								colseg.getYRotationAngle());
						cube.getLastNormal().normalize();
						double dista;
						if (distances[0] > distances[1]) {
							dista = distances[0];
						} else
							dista = distances[1];
						timeUnused = dista / ball.getVelocity().length();
						moveBackward(ball, timeUnused);
						break;
					}

				}
			}

			// górna zewnetrzna sciana
			plane = new BasicPlane(new Vector4(0, 0, -cube.getCubeWidth() / 2
					- ball.getRadius()), new Vector4(0, 0, 1));
			distance = plane.distanceToIntersection(velRay);
			// jesli ta plaszczyzna jest za kulka, a nie przed
			if (distance > 0) {
				Vector4D pointOnRay = velRay.getPointOnRay(distance);
				// jesli ten punkt jest w ramach zewnetrznego kwadratu (łącznie
				// z rogami licze)
				if (pointOnRay.getX() >= -cube.getCubeWidth() / 2
						- ball.getRadius()
						&& pointOnRay.getX() < cube.getCubeWidth() / 2
								+ ball.getRadius()) {
					// jesli kulka styka sie ze sciana, a nie rogiem
					if (pointOnRay.getX() >= -cube.getCubeWidth() / 2
							&& pointOnRay.getX() <= cube.getCubeWidth() / 2) {
						timeUnused = distance / ball.getVelocity().length();
						moveBackward(ball, timeUnused);
						cube.setLastNormal(new Vector4(0, 0, -1));
						cube.getLastNormal().yAxisRotation(
								colseg.getYRotationAngle());
						break;
					}
					// tutaj wiadomo ze sie styka w rogu ktoryms
					// najpierw testuje kolo gorne lewe
					Circle lu = new BCircle(-cube.getCubeWidth() / 2, -cube
							.getCubeThick() / 2, ball.getRadius());
					double[] distances = lu.getDistancesToIntesections(ray2d);
					// jesli punkty wspolne sa, musza byc dwa, nie bedzie nigdy
					// sytuacji ze punkt bylby tylko jeden, wtedy by w ogole nie
					// wykrylo kolizji
					if (distances.length > 1 && distances[0] * distances[1] < 0) {
						cube.setLastNormal(new Vector4(-1, 0, -1));
						cube.getLastNormal().yAxisRotation(
								colseg.getYRotationAngle());
						cube.getLastNormal().normalize();
						double dista;
						if (distances[0] > distances[1]) {
							dista = distances[0];
						} else
							dista = distances[1];
						timeUnused = dista / ball.getVelocity().length();
						moveBackward(ball, timeUnused);
						break;
					}

					Circle ru = new BCircle(cube.getCubeWidth() / 2, -cube
							.getCubeThick() / 2, ball.getRadius());
					distances = ru.getDistancesToIntesections(velRay);

					if (distances.length > 1 && distances[0] * distances[1] < 0) {
						cube.setLastNormal(new Vector4(1, 0, -1));
						cube.getLastNormal().yAxisRotation(
								colseg.getYRotationAngle());
						cube.getLastNormal().normalize();
						double dista;
						if (distances[0] > distances[1]) {
							dista = distances[0];
						} else
							dista = distances[1];
						timeUnused = dista / ball.getVelocity().length();
						moveBackward(ball, timeUnused);
						break;
					}

				}
			}

			break;
		}
		return timeUnused;
	}

	/**
	 * Wszystkie konsekwencje kolizji, np. zmiana wektora predkosci kulki czy
	 * tez zwi�kszenie ilo�ci punkt�w
	 * 
	 * @param colseg
	 * @param ball
	 */
	public void reactToCollision(LevelSegment colseg, Ball ball) {
		colseg.actionOnCollision();
		// na razie przyjmuje ze bazuje tylko na plaszczyznach odbijajacych
		if (colseg.getSegmentType() == LevelSegment.PHYSICSEGMENT) {
			if (colseg.getBoundingBox().getBoxType() == BoundingBox.PLANEBOX) {
				Plane pl = ((BoundingPlane) colseg.getBoundingBox())
						.getMainPlane();
				ball.getVelocity().multiply(-1);
				ball.getVelocity().arbitraryAxisRotation(Math.PI,
						pl.getNormalVector()).multiply(colseg.getElasticity());

			} else if (colseg.getBoundingBox().getBoxType() == BoundingBox.CUBEBOX) {
				BoundingCube cube = (BoundingCube) colseg.getBoundingBox();
				ball.getVelocity().multiply(-1);
				ball.getVelocity().arbitraryAxisRotation(Math.PI,
						cube.getLastNormal()).multiply(colseg.getElasticity());

			}
		}
	}

}
