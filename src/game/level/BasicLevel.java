package game.level;

import input.actions.RestartLevelAction;
import input.actions.ResumeGameAction;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import application.Application;
import math.Vector4;
import math.Vector4D;
import graphics.GameObject;
import graphics.objects.Cube;
import graphics.objects.EndCircle;
import graphics.objects.Floor;
import graphics.objects.Hole;

public class BasicLevel implements GameLevel {

	// klocki budujące ten level, podlogi i sciany, te elementy mogą kolidować z kulką
	private List<LevelSegment> _layer1 = new ArrayList<LevelSegment>();
	
	// warstwa zawierajaca podloge
	private List<LevelSegment> _layer0 = new ArrayList<LevelSegment>();
	
	// warstwa z dziurami
	private List<LevelSegment> _holes = new ArrayList<LevelSegment>();
	
	// typu koniec poziomu, jakis item do zebrania, teleport, cokolwiek bez fizyki ale z efektami
	private List<LevelSegment> _functionSegments = new ArrayList<LevelSegment>();
	
	//pomocnicza lista, do zbierania wszystkich kolizyjnych obiektów
	private List<LevelSegment> _tempCollisionable = new ArrayList<LevelSegment>();
	
	// w liczbie segmentow
	private int rows;
	private int columns;
	
	// domyslny rozmiar klocka
	private double _defThick = 3;
	private double _defWidth = 3;
	private double _defHeight = 3;
	
	// promien dziury
	private double _holeRadius = 1;
	
	//zmienne okre�laj�ce w kt�r� stron� jest plansza przechylona
	private double ZAxisRotation = 0;
	private double XAxisRotation = 0;
	
	// maksymalne przechylenie poziomu
	private final double maxLvlRot = Math.PI/8;
	
	//
	private double _timeLeft;
	private double _points;
	
	private int _startX;
	private int _startY;
	
	private LevelSegment _endPoint;
	
	private Ball _ball;
	
	// stan lvlu
	private int _lvlState;
	
	private String _text;
	
	public BasicLevel() {
		_lvlState = GameLevel.START;
		
		_ball = new MetalBall(0,1,0);

		//zewnetrzna ramka
		_layer1.add(new Cube(-4,-1));
		_layer1.add(new Cube(-4,0));
		_layer1.add(new Cube(-4,1));
		_layer1.add(new Cube(-4,2));
		_layer1.add(new Cube(-4,3));
		_layer1.add(new Cube(-4,4));
		_layer1.add(new Cube(-4,5));
		_layer1.add(new Cube(-4,6));
		_layer1.add(new Cube(-4,7));
		_layer1.add(new Cube(-4,8));
		_layer1.add(new Cube(-4,9));
		_layer1.add(new Cube(2,-1));
		_layer1.add(new Cube(2,0));
		_layer1.add(new Cube(2,1));
		_layer1.add(new Cube(2,2));
		_layer1.add(new Cube(2,3));
		_layer1.add(new Cube(2,4));
		_layer1.add(new Cube(2,5));
		_layer1.add(new Cube(2,6));
		_layer1.add(new Cube(2,7));
		_layer1.add(new Cube(2,8));
		_layer1.add(new Cube(2,9));
		_layer1.add(new Cube(-3,-1));
		_layer1.add(new Cube(-2,-1));
		_layer1.add(new Cube(-1,-1));
		_layer1.add(new Cube(0,-1));
		_layer1.add(new Cube(1,-1));
		_layer1.add(new Cube(-3,9));
		_layer1.add(new Cube(-2,9));
		_layer1.add(new Cube(-1,9));
		_layer1.add(new Cube(0,9));
		_layer1.add(new Cube(1,9));
		
		// wnetrze
		_layer1.add(new Cube(-2,0));
		_layer1.add(new Cube(-2,1));
		_layer1.add(new Cube(-2,2));
		_layer1.add(new Cube(-1,2));
		_layer1.add(new Cube(0,2));
		_layer1.add(new Cube(1,2));
		
		Cube a = new Cube(-3,5);
		a.setYRotationAngle(Math.PI);
		_layer1.add(a);
		a = new Cube(-2,5);
		a.setYRotationAngle(Math.PI);
		_layer1.add(a);
		a = new Cube(-1,5);
		a.setYRotationAngle(Math.PI);
		_layer1.add(a);
		a = new Cube(0,5);
		a.setYRotationAngle(Math.PI);
		_layer1.add(a);
		a = new Cube(0,6);
		a.setYRotationAngle(Math.PI);
		_layer1.add(a);
		a = new Cube(0,7);
		a.setYRotationAngle(Math.PI);
		_layer1.add(a);
		a = new Cube(0,8);
		a.setYRotationAngle(Math.PI);
		_layer1.add(a);

		//holes
		_holes.add(new Hole(-2,4));
		_holes.add(new Hole(0,3));

		
		_endPoint = new EndCircle(8,1);
		
		_layer0.add(new Floor(-1,2));
		_layer0.add(new Floor(-1,7));

		_startX = -3;
		_startY = 0;

		setProperPositions();
		

		_text = "Press Spacebar";
	}
	
	/**
	 * Dodanie obiektow do listy do wyswietlania
	 * Hmmm chyba juz nie
	 */
	public void startLevel() {
		
	}
	
	/**
	 * G��wnie up�ywaj�cy czas i ewentualne time's up
	 * @param dt
	 */
	public void update(double dt) {
		for (LevelSegment obj: _layer0) {
			obj.update(dt);
		}
		for (LevelSegment obj: _layer1) {
			obj.update(dt);
		}
		for (LevelSegment obj: _holes) {
			obj.update(dt);
		}
		_ball.update(dt);
		_endPoint.update(dt);
	}

	@Override
	public Ball getBall() {
		return _ball;
	}
	
	public double getTimeLeft() {
		return _timeLeft;
	}
	
	public double getZAxisRotation() {
		return ZAxisRotation;
	}
	
	public double getXAxisRotation() {
		return XAxisRotation;
	}
	
	public void setXAxisRotation(double xrot) {
		XAxisRotation = xrot;
	}
	
	public void setZAxisRotation(double zrot) {
		ZAxisRotation = zrot;
	}
	
	public List<GameObject> getGraphicalObjects() {
		List<GameObject> list = new ArrayList<GameObject>();
		list.addAll(_layer0);
		list.addAll(_layer1);
		list.addAll(_functionSegments);
		list.addAll(_holes);
		list.add(_ball);
		list.add(_endPoint);
		return list;
	}

	@Override
	public List<LevelSegment> getCollisionable() {
		_tempCollisionable.clear();
		_tempCollisionable.addAll(_layer1);
//		_tempCollisionable.addAll(_functionSegments);
		return _tempCollisionable;
	}
	
	public void XRotate(double angle) {
		XAxisRotation += angle/100;
		if (XAxisRotation > maxLvlRot) XAxisRotation = maxLvlRot;
		if (XAxisRotation < -maxLvlRot) XAxisRotation = -maxLvlRot;
//		Math.min(Math.PI/4, Math.max(XAxisRotation, -Math.PI/4));
	}
	
	public void ZRotate(double angle) {
		ZAxisRotation +=angle/100;
		if (ZAxisRotation > maxLvlRot) ZAxisRotation = maxLvlRot;
		if (ZAxisRotation < -maxLvlRot) ZAxisRotation = -maxLvlRot;
//		Math.min(Math.PI/4, Math.max(ZAxisRotation, -Math.PI/4));
	}
	
	public void addContructionSegment(LevelSegment seg) {
		_layer1.add(seg);
	}
	
	public void addFunctionSegment(LevelSegment seg) {
		_functionSegments.add(seg);
	}
	
	public void removeContructionSegment(LevelSegment seg) {
		_layer1.remove(seg);
	}
	
	public void removeFunctionSegment(LevelSegment seg) {
		_functionSegments.remove(seg);
	}
	
	public void setProperPositions() {
		for (LevelSegment seg : _layer1) {
			seg.setX(seg.getSegmentColumn()*_defWidth);
			seg.setY(1);
			seg.setZ(seg.getSegmentRow()*_defThick-4);
		}
		for (LevelSegment seg : _layer0) {
			seg.setX(seg.getSegmentColumn()*_defWidth);
			seg.setY(0);
			seg.setZ(seg.getSegmentRow()*_defThick-4);
		}
		for (LevelSegment seg : _holes) {
			seg.setX(seg.getSegmentColumn()*_defWidth);
			seg.setY(0.01);
			seg.setZ(seg.getSegmentRow()*_defThick-4);
		}
		
		//ustawienie pozycji kulki
		_ball.setX(_startX*_defWidth);
		_ball.setZ(_startY*_defThick-4);
		
		// ustawienie pozycji koncowego punktu
		_endPoint.setX(_endPoint.getSegmentColumn()*_defWidth);
		_endPoint.setZ(_endPoint.getSegmentRow()*_defThick-4);
		_endPoint.setY(0.01);
	}

	
	@Override
	public void checkFunctional() {
		for (LevelSegment seg : _holes) {
			Vector4D distance  = new Vector4(_ball.getPosition());
			distance.substract(seg.getPosition());
			distance.setY(0);
			if (distance.length() < _holeRadius) {
				_ball.setXScaleSpeed(-1);
				_ball.setZScaleSpeed(-0.5);	
			}
		}
		
		Vector4D distance  = new Vector4(_ball.getPosition());
		distance.substract(_endPoint.getPosition());
		distance.setY(0);
		if (distance.length() < _holeRadius) {
			Application.getGame().setGameSpeed(0);
			setLevelState(COMPLETED);
		}
	}
	
	public int getStartRow() {
		return _startX;
	}
	
	public int getStartColumn() {
		return _startY;
	}

	@Override
	public List<String> getTexts() {
		List<String> list = new ArrayList<String>();
		if (_text != null)
		list.add(_text);
		return list;
	}

	@Override
	public void setLevelState(int state) {
		if (state == MAIN) {
			_text = "";
		} else if (state == COMPLETED) {
			_text = "Level Completed";
			Application.getInput().setAction(KeyEvent.VK_SPACE, new RestartLevelAction());
		} else if (state == START) {
			_text = "Press Spacebar";
		}
		
	}
	
}
