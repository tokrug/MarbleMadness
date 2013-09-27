package input.actions;

import application.Application;
import game.level.GameLevel;
import input.controllers.InputEvent;

public class ResumeGameAction implements InputActionInterface {

	@Override
	public void action(InputEvent event) {
		if (event.getValue()==0) {
			Application.getGame().setGameSpeed(1);
			Application.getInput().setAction(event.getComponentID(), new EmptyAction());
			Application.getGame().getLevel().setLevelState(GameLevel.MAIN);
		}
	}

}
