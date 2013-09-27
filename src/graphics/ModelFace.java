package graphics;

public class ModelFace {
	
	private int[] _vertexIndices;
	private int[] _normalIndices;
	
	private int _flags;
	private int _smoothingGroup;
	
	public ModelFace(int[] vIndices, int[] nIndices) {
		_vertexIndices = vIndices;
		_normalIndices = nIndices;
		_flags = 0;
		_smoothingGroup = 0;
	}
	
	public ModelFace(int[] vIndices, int[] nIndices, int flags, int smoothGroup) {
		_vertexIndices = vIndices;
		_normalIndices = nIndices;
		_flags = flags;
		_smoothingGroup = smoothGroup;
	}
	
	public ModelFace(ModelFace face) {
		_vertexIndices = face.verticesToArray().clone();
		_normalIndices = face.normalsToArray().clone();
		_flags = face.getFlag();
		_smoothingGroup = face.getSmoothGroup();
	}
	
	public int[] verticesToArray() {
		return _vertexIndices;
	}
	
	public int[] normalsToArray() {
		return _normalIndices;
	}
	
	public int getFlag() {
		return _flags;
	}
	
	public int getSmoothGroup() {
		return _smoothingGroup;
	}

}
