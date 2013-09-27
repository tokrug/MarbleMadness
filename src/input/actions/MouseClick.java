package input.actions;

import application.Application;
import input.controllers.InputEvent;

public class MouseClick implements InputActionInterface {

	@Override
	public void action(InputEvent event) {
		if (event.getValue()==0)
		Application.getGame().clickOnScreen();

	}

}
