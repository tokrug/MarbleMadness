package input.actions;

import application.Application;
import input.controllers.InputEvent;

public class MovingCursorX implements InputActionInterface {

	@Override
	public void action(InputEvent event) {
		Application.getGame().getCursor().moveXRelative(event.getValue());

	}

}
