package input.actions.array;

import input.actions.EmptyAction;
import input.actions.InputActionInterface;

public class BlankActionArray implements ActionArray{
	
	protected InputActionInterface[] _array;

	public InputActionInterface[] getArray() {
		return _array;
	}
	
	public BlankActionArray() {
		_array = new InputActionInterface[525];
		for (int i = 0; i < 525; i++) {
			_array[i] = new EmptyAction();
		}
	}

}
