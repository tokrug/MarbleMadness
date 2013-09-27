package graphics.animation;

import math.MatrixS4;

public class BasicAnimationKeyframe implements AnimationKeyframe {
	
	private MatrixS4[] _relativeMatrices;
	
	public MatrixS4 getJointMatrix(int jointID) {
		return _relativeMatrices[jointID];
	}

}
