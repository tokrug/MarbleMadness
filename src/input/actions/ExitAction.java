package input.actions;

import application.Application;

import input.controllers.InputEvent;

public class ExitAction implements InputActionInterface {

	
	public void action(InputEvent event) {
		Application.getGame().exitGame();
	}

}
