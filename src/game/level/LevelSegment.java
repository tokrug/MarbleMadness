package game.level;

import graphics.GameObject;
import graphics.collision.BoundingBox;

public interface LevelSegment extends GameObject {
	
	public static final int PHYSICSEGMENT = 0;
	public static final int FUNCTIONSEGMENT = 1;
	public static final int DECORATIONSEGMENT = 2;
	
	/**
	 * Ta metoda zmienia cos jesli chodzi o ogolne parametry gry
	 * Od fizyki sa inne obiekty
	 */
	public void actionOnCollision();
	
	/**
	 * Zwraca typ segementu, glownie podzial na twarde (fizyczne) i funkcyjne
	 * Poszczegolne wartosc w tymze interfejsie
	 * @return
	 */
	public int getSegmentType();
	
	/**
	 * Dodatnia liczba okreslająca, w której kolumnie od lewej sie znajduje
	 * @return
	 */
	public int getSegmentColumn();
	
	/**
	 * Dodatnia liczba określająca, w którym rzedzie od dolu sie znajduje
	 * @return
	 */
	public int getSegmentRow();
	
	/**
	 * Zwraca ułamek prędkości zachowanej po zderzeniu
	 * Poza ścianami itp. powinno zwracać na wszelki wypadek 1
	 * @return
	 */
	public double getElasticity();
}
