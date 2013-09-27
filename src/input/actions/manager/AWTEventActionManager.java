package input.actions.manager;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import input.actions.ExitAction;
import input.actions.InputActionInterface;
import input.actions.MovingCursorX;
import input.actions.MovingCursorY;
import input.actions.array.ActionArrayFactory;
import input.controllers.ControllersManager;
import input.controllers.InputEvent;
import input.controllers.InputEventManager;

public class AWTEventActionManager implements ActionManager {

	// sety, ktore nie musza byc tworzone za kazdym razem
	public static final InputActionInterface[] BLANKACTIONARRAY = ActionArrayFactory
			.createActionArray(ActionArrayFactory.BLANKARRAY).getArray();

	public static InputActionInterface[] USERACTIONARRAY;
	public static InputActionInterface[] USERALTACTIONARRAY;
	public static InputActionInterface[] USERCTRLACTIONARRAY;

	/**
	 * Zestaw akcji aktualnie w uzyciu
	 */
	private InputActionInterface[] _currentActions;

	private ControllersManager _controllersManager;

	// zeby wiedziec np. jaki set ma byc po zmianie stanu gry
	private int howManyCtrlPressed;
	private int howManyAltPressed;
	
	public int getHowManyCtrlPressed() {
		return howManyCtrlPressed;
	}

	public int getHowManyAltPressed() {
		return howManyAltPressed;
	}

	public AWTEventActionManager() {
		// private coz singleton wannabe
		_controllersManager = new InputEventManager();
		_currentActions = ActionArrayFactory.createActionArray(ActionArrayFactory.DEFAULTARRAY).getArray();
		howManyCtrlPressed = 0;
		howManyAltPressed = 0;
		
		setAction(KeyEvent.VK_ESCAPE, new ExitAction());
		setAction(MouseEvent.MOUSE_MOVED, new MovingCursorX());
		setAction(MouseEvent.MOUSE_DRAGGED, new MovingCursorY());
	}

	public void setAction(int id, InputActionInterface action) {
		_currentActions[id] = action;

	}
	
	public InputActionInterface getAction(int id) {
		return _currentActions[id];
	}

	public void setActionsArray(InputActionInterface[] actions) {
		_currentActions = actions;

	}

	public void setControllersManager(ControllersManager manager) {
		_controllersManager = manager;

	}

	public void translateEvents(long time) {
		InputEvent event = _controllersManager.getNextEvent(time);
		while (event != null) {
			if (event.getComponentID() < 525) {
				_currentActions[event.getComponentID()].action(event);
			}
			//System.out.println(event.getComponentID() + " " + event.getTime()
			//		+ " " + event.getValue());
			event = _controllersManager.getNextEvent(time);
		}
	}

	public void ctrlPressed() {
		if (howManyCtrlPressed<2)
		++howManyCtrlPressed;
	}

	public void ctrlReleased() {
		if (howManyCtrlPressed>0)
		--howManyCtrlPressed;
	}

	public void altPressed() {
		if (howManyAltPressed<2)
		++howManyAltPressed;
	}
	
	public void altReleased() {
		if (howManyAltPressed>0)
		--howManyAltPressed;
	}

	@Override
	public void setAltActionSet() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCtrlActionSet() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNormalActionSet() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startProcessingEvents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopProcessingEvents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ControllersManager getControllersManager() {
		return _controllersManager;
	}
}
