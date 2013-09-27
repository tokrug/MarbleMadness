package math;

public class Vertex4 extends Vector4 implements Vertex{

	private double _u;
	private double _v;
	
	private int _flags;
	private int _boneIndex;
	
	public Vertex4() {
		super();
		_u = 0;
		_v = 0;
		_flags = 0;
		_boneIndex = -1;
	}
	
	public Vertex4(double x, double y, double z, double u, double v, int flag, int boneIndex) {
		super(x,y,z);
		_u = u;
		_v = v;
		_flags = flag;
		_boneIndex = boneIndex;
	}
	
	public Vertex4(double u, double v) {
		super();
		_u = u;
		_v = v;
		_flags = 0;
		_boneIndex = -1;
	}
	
	public Vertex4(double u, double v, int flags, int boneIndex) {
		super();
		_u = u;
		_v = v;
		_flags = flags;
		_boneIndex = boneIndex;
	}
	
	public Vertex4(Vertex vex) {
		super((vex));
		_u = vex.getU();
		_v = vex.getV();
		_flags = vex.getFlags();
		_boneIndex = vex.getBoneIndex();
	}

	public double getU() {
		return _u;
	}

	public void setU(double u) {
		this._u = u;
	}

	public double getV() {
		return _v;
	}

	public void setV(double v) {
		this._v = v;
	}

	public int getFlags() {
		return _flags;
	}

	public void setFlags(int flags) {
		this._flags = flags;
	}

	public int getBoneIndex() {
		return _boneIndex;
	}

	public void setBoneIndex(int index) {
		_boneIndex = index;
	}
	
}
