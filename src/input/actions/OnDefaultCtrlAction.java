package input.actions;

import application.Application;
import input.controllers.InputEvent;

/**
 * 
 * @author edhendil
 * 
 *         Akcja zmieniajaca caly set akcji po nacisnieciu ctrl
 */
public class OnDefaultCtrlAction implements InputActionInterface {

	public void action(InputEvent event) {
		if (event.getValue() == 1) {
			Application.getInput().setCtrlActionSet();
			Application.getInput().ctrlPressed();
		} else if (event.getValue() == 0) {
			Application.getInput().setNormalActionSet();
			Application.getInput().ctrlReleased();
		}
	}

}
