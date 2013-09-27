package input.controllers;

public class InputEvent {
	
	private int _componentID;
	private double _value;
	private long _time;
	
	public InputEvent(int ComponentID, double value, long time) {
		this._componentID = ComponentID;
		this._value = value;
		this._time = time;
	}

	public int getComponentID() {
		return _componentID;
	}

	public double getValue() {
		return _value;
	}

	public long getTime() {
		return _time;
	}

}
