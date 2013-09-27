package input.actions;

import application.Application;
import input.controllers.InputEvent;

public class ChangeLevelZRot implements InputActionInterface {

	@Override
	public void action(InputEvent event) {
		Application.getGame().getLevel().ZRotate(event.getValue());

	}

}
