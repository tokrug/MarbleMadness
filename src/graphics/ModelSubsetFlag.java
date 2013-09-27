package graphics;

public class ModelSubsetFlag {

	public static final int Render2D = 1;
	public static final int Transparent = 2;
	
	private int _flag;
	
	/**
	 * As a parameter use static const of this class
	 * Bit flags
	 * @param flag
	 */
	public ModelSubsetFlag(int flag) {
		this._flag = flag;
	}
	
	public boolean is2D() {
		if ((_flag & Render2D) != 0) {
			return true;
		}
		return false;
	}
	
	public boolean isTransparent() {
		if ((_flag & Transparent) != 0) {
			return true;
		}
		return false;
	}
	
	public int getFlag() {
		return _flag;
	}
	
}
