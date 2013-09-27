package game.menu;

import application.Application;
import application.DataManager;



public class ExitButton extends AbstractMenuButton implements MenuButton {

	public ExitButton(double x, double y) {
		super(x,y,0);
		setModel(DataManager.M_EXITBUTTON);
	}
	
	@Override
	public void actionOnClick() {
		Application.getGame().exitGame();
	}

	@Override
	public void actionOnEnter() {
		
		
	}

	@Override
	public void actionOnExit() {
		
		
	}

}
