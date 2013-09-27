package graphics.animation;

import math.MatrixS4;

public class BasicModelAnimation implements ModelAnimation {

	private int _fps;
	private int _numberOfFrames;
	private AnimationKeyframe[] _keyframes;
	
	public BasicModelAnimation(AnimationKeyframe[] keyframes, int _fps) {
		
	}
	
	/**
	 * Zwraca macierze relatywne dla każdego jointa
	 * @param frame
	 * @return
	 */
	public MatrixS4[] getRelativeForFrame(int frame) {
		//TODO
		return null;
	}
	
	/**
	 * Inaczej bedzie wygladac ale ma za zadanie odpowiednie obliczanie wartosci miedzy keyframe'ami
	 */
	private void interpolation() {
		
	}

}
