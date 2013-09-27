package input.actions;

import application.Application;
import input.controllers.InputEvent;

public class ChangeLevelXRot implements InputActionInterface {

	@Override
	public void action(InputEvent event) {
		Application.getGame().getLevel().XRotate(-event.getValue());

	}

}
