package graphics;

import math.Matrix;
import math.Vector4;
import math.Vector4D;

public class EmptyModel implements Model {

	private ModelFlag _flags;
	private ModelSubset[] _subs;
	
	public EmptyModel() {
		_flags = new ModelFlag(0);
		_subs = new ModelSubset[0];
	}
	
	@Override
	public void animateModel(Matrix[] relativeMatrices, Matrix absoluteRoot) {
		// TODO Auto-generated method stub

	}

	@Override
	public ModelFlag getModelFlag() {
		return _flags;
	}


	@Override
	public ModelSubset[] getSubsets() {
		return _subs;
	}

	@Override
	public void setModelFlag(int flag) {
		//nie mozna zmienic flagi, zawsze 0 bedzie

	}

	@Override
	public Vector4D getDimensions() {
		return new Vector4(0,0,0);
	}

}
