package input.actions;

import application.Application;
import game.level.GameLevel;
import input.controllers.InputEvent;

public class RestartLevelAction implements InputActionInterface {

	@Override
	public void action(InputEvent event) {
		if (event.getValue() == 0) {
			Application.getInput().setAction(event.getComponentID(), new ResumeGameAction());
			Application.getGame().getLevel().setProperPositions();
			Application.getGame().setGameSpeed(0);
			Application.getGame().getLevel().setLevelState(GameLevel.START);
			Application.getGame().getLevel().getBall().getVelocity().setX(0);
			Application.getGame().getLevel().getBall().getVelocity().setZ(0);
			
		}
	}

}
