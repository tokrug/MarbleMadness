package input.actions.array;

import input.actions.InputActionInterface;

public class DefaultCtrlActionArray extends BlankActionArray implements
		ActionArray {
	
	public DefaultCtrlActionArray() {
		super();
		//zmiany tutaj jakies
	}

	@Override
	public InputActionInterface[] getArray() {
		return _array;
	}

}
