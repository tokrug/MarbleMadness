package input.controllers;

import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public interface ControllersManager extends MouseListener, MouseMotionListener,
		MouseWheelListener, KeyListener, FocusListener {

	public InputEvent getNextEvent(long time);

	public void addEvent(InputEvent event);

}
