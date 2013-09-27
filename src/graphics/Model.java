package graphics;

import math.Matrix;
import math.Vector4D;

public interface Model {
	
	public ModelSubset[] getSubsets();
	
	public void setModelFlag(int flag);
	
	public ModelFlag getModelFlag();
	
	public void animateModel(Matrix[] relativeMatrices, Matrix absoluteRoot);
	
	public Vector4D getDimensions();

}
