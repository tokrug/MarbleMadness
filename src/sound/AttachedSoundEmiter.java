package sound;

import java.io.IOException;

import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;

import javax.media.protocol.DataSource;

import math.Vector4;
import math.Vector4D;

public class AttachedSoundEmiter implements SoundEmiter {
	
	private Vector4D _position;
	private Vector4D _offset;
	
	private double _maxGainLevel;
	
	private Vector4D _temp;
	
	private Player _player;
	
	public AttachedSoundEmiter(DataSource source, Vector4D position) {
		
		try {
			_player = Manager.createRealizedPlayer(source);
		} catch (NoPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotRealizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		_temp = new Vector4(0,0,0);
		_position = position;
		_offset = new Vector4();
		_maxGainLevel = 1;
	}
	
	public AttachedSoundEmiter(Player pl, Vector4D position) {
		_player = pl;
		_position = position;
		_temp = new Vector4(0,0,0);
		_offset = new Vector4();
		_maxGainLevel = 1;
	}

	public void play() {
		_player.start();
	}
	
	public void stop() {
		_player.stop();
	}
	
	public void repeat() {
		
	}
	
	public void repeat(int numberOfTimes) {
		
	}
	
	public void setGainLevel(double level) {
		_player.getGainControl().setLevel((float)level);
	}
	
	public double getGainLevel() {
		return _player.getGainControl().getLevel();
	}
	
	/**
	 * 
	 * @param level - wartosc od 0 do 1
	 */
	public void setMaxGainLevel(double level) {
		_maxGainLevel = level;
	}
	
	public double getMaxGainLevel() {
		return _maxGainLevel;
	}
	
	public Vector4D getPosition() {
		_temp.setX(0);
		_temp.setY(0);
		_temp.setZ(0);
		_temp.add(_position);
		_temp.add(_offset);
		return _temp;
	}
	
}
