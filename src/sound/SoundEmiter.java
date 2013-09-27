package sound;

import math.Vector4D;

public interface SoundEmiter {

	public void play();

	public void stop();

	public void repeat();

	public void repeat(int numberOfTimes);

	public void setGainLevel(double level);

	public double getGainLevel();

	/**
	 * 
	 * @param level - wartosc od 0 do 1
	 */
	public void setMaxGainLevel(double level);

	public double getMaxGainLevel();

	public Vector4D getPosition();

}
