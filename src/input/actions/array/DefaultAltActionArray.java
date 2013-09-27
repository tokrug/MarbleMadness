package input.actions.array;

import input.actions.InputActionInterface;

public class DefaultAltActionArray extends BlankActionArray implements ActionArray {

	public DefaultAltActionArray() {
		super();
		//zmiany tutaj jakies
	}
	
	@Override
	public InputActionInterface[] getArray() {
		return _array;
	}

}
