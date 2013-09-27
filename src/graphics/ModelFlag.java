package graphics;

public class ModelFlag {
	
	public static final int Render2D = 1;
	
	private int _flag;
	
	public ModelFlag(int flag) {
		this._flag = flag;
	}
	
	public boolean is2D() {
		if ((_flag & Render2D) != 0) {
			return true;
		}
		return false;
	}
	
	public int getFlag() {
		return _flag;
	}

}
