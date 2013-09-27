package input.actions.array;

import input.actions.InputActionInterface;

public class DefaultActionArray extends BlankActionArray implements ActionArray {

	public DefaultActionArray() {
		super();
		//zmiany tutaj jakies
	}
	
	@Override
	public InputActionInterface[] getArray() {
		return _array;
	}

}
