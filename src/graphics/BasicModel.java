package graphics;

import math.Matrix;
import math.Vector4;
import math.Vector4D;
import math.Vertex;

/**
 * zawiera wszystkie subsety, ktore tworza ten obiekt cos jeszcze trzeba zrobic
 * z animacja
 * 
 * @author Admin
 * 
 */

public class BasicModel implements Model {

	private ModelSubset[] _subsets;
	private ModelFlag _flags;
	private ModelSkeleton _skeleton;

	public BasicModel(ModelSubset[] subsets, int flag) {
		_subsets = subsets;
		_flags = new ModelFlag(flag);
	}

	public BasicModel(ModelSubset[] subsets) {
		_subsets = subsets;
		_flags = new ModelFlag(0);
	}
	
	public BasicModel(Model model) {
		_flags = new ModelFlag(model.getModelFlag().getFlag());
//		_skeleton = new Skeleton(model.getSkeleton());
		ModelSubset[] oldsub = model.getSubsets();
		_subsets = new ModelSubset[oldsub.length];
		for (int i = 0; i < _subsets.length; i++) {
			_subsets[i] = new ModelSubset(oldsub[i]);
		}
	}

	public ModelSubset[] getSubsets() {
		return _subsets;
	}

	public void setModelFlag(int flag) {
		_flags = new ModelFlag(flag);
	}

	public ModelFlag getModelFlag() {
		return _flags;
	}

	public ModelSubset getSubset(int i) {
		return _subsets[i];
	}
	
	public void animateModel(Matrix[] relativeMatrices, Matrix absoluteRoot) {
		
	}
	
	public Vector4D getDimensions() {
		double x1,y1,z1,x2,y2,z2,x,y,z;
		x1 = 0;
		y1 = 0;
		z1 = 0;
		x2 = 0;
		y2 = 0;
		z2 = 0;
		ModelSubset[] subs = getSubsets();
		for (int i = 0; i < subs.length; i++) {
			Vertex[] vexs = subs[i].getVertices();
			for (int j = 0; j < vexs.length; j ++) {
				x = vexs[j].getX();
				if (x > x2) {
					x2 = x;
				} else if (x < x1) {
					x1 = x;
				}
				y = vexs[j].getY();
				if (y > y2) {
					y2 = y;
				} else if (y < y1) {
					y1 = y;
				}
				z = vexs[j].getZ();
				if (z > z2) {
					z2 = z;
				} else if (z < z1) {
					z1 = z;
				}
			}
		}
		return new Vector4(x2-x1,y2-y1,z2-z1);
	}

}
