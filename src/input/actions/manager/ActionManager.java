package input.actions.manager;

import input.actions.InputActionInterface;
import input.controllers.ControllersManager;

public interface ActionManager{
	
	public void setAction(int id, InputActionInterface action);
	
	public InputActionInterface getAction(int id);

	public void setActionsArray(InputActionInterface[] actions);

	public void setControllersManager(ControllersManager manager);
	
	public ControllersManager getControllersManager();

	public void translateEvents(long time);

	public void ctrlPressed();

	public void ctrlReleased();

	public void altPressed();

	public void altReleased();

	public int getHowManyCtrlPressed();

	public int getHowManyAltPressed();
	
	public void stopProcessingEvents();

	public void startProcessingEvents();
	
	public void setCtrlActionSet();
	
	public void setAltActionSet();
	
	public void setNormalActionSet();

}
