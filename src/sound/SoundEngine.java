package sound;

import java.util.List;

import math.Vector4D;

import application.Application;

public class SoundEngine {
	
	private double _masterVolume;
	
	public SoundEngine() {
		_masterVolume = 1;
	}
	
	/**
	 * Oblicza odleglosc sluchacza od zrodel dzwieku, kierunek z ktorego dochodzi dzwiek,
	 * na tej podstawie ustawia glosnosc (i balans kiedys zrobie)
	 */
	public void updateSounds(Vector4D listenerPosition) {
		
	}
	
	protected List<SoundEmiter> getSoundEmiters() {
		return Application.getGData().getSounds();
	}

}
