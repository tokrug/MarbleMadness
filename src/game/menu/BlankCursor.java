package game.menu;

import graphics.AbstractGameObject;
import graphics.EmptyModel;

public class BlankCursor extends AbstractGameObject implements GameCursor {

	public BlankCursor() {
		super();
		setModel(new EmptyModel());
	}
	
	@Override
	public void moveXRelative(double change) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveYRelative(double change) {
		// TODO Auto-generated method stub

	}

}
