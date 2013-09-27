package input.actions.array;

public class ActionArrayFactory {

	public static final int BLANKARRAY = 0;
	public static final int DEFAULTARRAY = 1;
	public static final int DEFAULTALTARRAY = 2;
	public static final int DEFAULTCTRLARRAY = 3;

	public static ActionArray createActionArray(int setType) {
		switch (setType) {
		case ActionArrayFactory.BLANKARRAY:
			return new BlankActionArray();
		case ActionArrayFactory.DEFAULTARRAY:
			return new DefaultActionArray();
		case ActionArrayFactory.DEFAULTALTARRAY:
			return new DefaultAltActionArray();
		case ActionArrayFactory.DEFAULTCTRLARRAY:
			return new DefaultCtrlActionArray();
		}
		return null;
	}

}
