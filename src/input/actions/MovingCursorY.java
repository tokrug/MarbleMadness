package input.actions;

import application.Application;
import input.controllers.InputEvent;

public class MovingCursorY implements InputActionInterface {

	@Override
	public void action(InputEvent event) {
		Application.getGame().getCursor().moveYRelative(event.getValue());
	}

}
