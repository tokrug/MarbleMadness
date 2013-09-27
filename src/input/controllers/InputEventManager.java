package input.controllers;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

import application.Application;

/**
 * 
 * @author edhendil
 * 
 *         Klasa uzyta do zgarniania wszystkich eventow, tworzy tez kolejke
 *         eventow
 */

public class InputEventManager implements ControllersManager {

	private boolean[] keysPressed;

	// posortowany set, zalecana wlasnie ta klasa ( albo inna z synchronizacja)
	// inaczej
	// sa problemy
	private ConcurrentLinkedQueue<InputEvent> _sortedEvents;

	public InputEventManager() {
		_sortedEvents = new ConcurrentLinkedQueue<InputEvent>();
		keysPressed = new boolean[525];
		for (int i = 0; i < keysPressed.length; i++) {
			keysPressed[i] = false;
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		// bede patrzyl na mouse pressed i released, chyba ze double click

	}

	public void mouseEntered(MouseEvent arg0) {
		// nothing to do

	}

	public void mouseExited(MouseEvent arg0) {
		// nothing to do

	}

	public void mousePressed(MouseEvent arg0) {
		_sortedEvents.add(new InputEvent(arg0.getButton(), 1, System
				.currentTimeMillis()));

	}

	public void mouseReleased(MouseEvent arg0) {
		_sortedEvents.add(new InputEvent(arg0.getButton(), 0, System
				.currentTimeMillis()));

	}

	public void mouseDragged(MouseEvent arg0) {
		// MOUSE_MOVED sluzy do przesuwania wzgledem x, a dragged y
		boolean isChanged = false;
		int centerX = Application.getWindow().getWidth() / 2
				+ Application.getWindow().getXposition();
		int centerY = Application.getWindow().getHeight() / 2
				+ Application.getWindow().getYposition();
		double xChange = arg0.getXOnScreen() - centerX;
		double yChange = centerY - arg0.getYOnScreen();
		if (xChange != 0) {
			_sortedEvents.add(new InputEvent(MouseEvent.MOUSE_MOVED, xChange,
					System.currentTimeMillis()));
			isChanged = true;
		}
		if (yChange != 0) {
			_sortedEvents.add(new InputEvent(MouseEvent.MOUSE_DRAGGED, yChange,
					System.currentTimeMillis()));
			isChanged = true;
		}
		if (isChanged) {
			try {
				Robot robot = new Robot();
				robot.mouseMove(centerX, centerY);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
			}
		}

	}

	public void mouseMoved(MouseEvent arg0) {
		// System.out.println("Mouse has moved");
		// ustawia na powrot myszke na srodku okna, zeby caly czas byly ruchy
		// myszki relatywne
		// pobiera dane z MainWindow, ale skoro event wystapil to okno juz na
		// pewno jest
		// utworzone
		int centerX = Application.getWindow().getWidth() / 2
				+ Application.getWindow().getXposition();
		int centerY = Application.getWindow().getHeight() / 2
				+ Application.getWindow().getYposition();
		double xChange = arg0.getXOnScreen() - centerX;
		double yChange = centerY - arg0.getYOnScreen();
		if (xChange != 0)
			_sortedEvents.add(new InputEvent(MouseEvent.MOUSE_MOVED, xChange,
					System.currentTimeMillis()));
		if (yChange != 0)
			_sortedEvents.add(new InputEvent(MouseEvent.MOUSE_DRAGGED, yChange,
					System.currentTimeMillis()));
		try {
			Robot robot = new Robot();
			robot.mouseMove(centerX, centerY);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
		}
	}

	public void mouseWheelMoved(MouseWheelEvent arg0) {
		_sortedEvents.add(new InputEvent(MouseEvent.MOUSE_WHEEL, arg0
				.getWheelRotation(), System.currentTimeMillis()));

	}

	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() < 525 && !isPressed(arg0.getKeyCode())) {
			keysPressed[arg0.getKeyCode()] = true;
			_sortedEvents.add(new InputEvent(arg0.getKeyCode(), 1, System
					.currentTimeMillis()));
			// System.out.println(arg0.getKeyCode() + " " + 1);

		}

	}

	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() < 525 && isPressed(arg0.getKeyCode())) {
			keysPressed[arg0.getKeyCode()] = false;
			_sortedEvents.add(new InputEvent(arg0.getKeyCode(), 0, System
					.currentTimeMillis()));
			// System.out.println(arg0.getKeyCode() + " " + 0);
		}

	}

	public void keyTyped(KeyEvent arg0) {
		// tutaj nic do roboty nie ma, przynajmniej na razie, moze potem cos
		// zrobie

	}

	public void focusGained(FocusEvent arg0) {
		// bez posrednictwa akcji
		// tylko na ramke

		Application.getWindow().setEventListener(this);
		if (System.getProperty("os.name").toLowerCase().contains("linux")) {
			Runtime mycommand = java.lang.Runtime.getRuntime();
			try {
				mycommand.exec("xset -r");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void focusLost(FocusEvent arg0) {
		Application.getWindow().removeEventListener(this);
		if (System.getProperty("os.name").toLowerCase().contains("linux")) {
			Runtime mycommand = java.lang.Runtime.getRuntime();
			try {
				mycommand.exec("xset r");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public InputEvent getNextEvent(long time) {
		if (_sortedEvents.peek() != null
				&& _sortedEvents.peek().getTime() < time)
			return _sortedEvents.poll();
		return null;
	}

	public void addEvent(InputEvent event) {
		_sortedEvents.add(event);
	}

	public synchronized boolean isPressed(int keyCode) {
		return keysPressed[keyCode];
	}

}
