package input.actions;

import input.controllers.InputEvent;

public interface InputActionInterface {
	
	/**
	 * 
	 * @param event - wartosc z event, 1 jak wcisniety przycisk, 0 jak nie,
	 * 
	 */
	
	void action(InputEvent event); 

}
