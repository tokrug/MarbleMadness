package input.actions;

import application.Application;
import input.controllers.InputEvent;

public class OnDefaultAltAction implements InputActionInterface {

	public void action(InputEvent event) {
		if (event.getValue() == 1 ) {
			Application.getInput().setAltActionSet();
			Application.getInput().altPressed();
		}
		else if (event.getValue() == 0) {
			Application.getInput().altReleased();
			if (Application.getInput().getHowManyAltPressed()==0) {
				Application.getInput().setNormalActionSet();
			}
		}
			
	}

}
