package game.engine;

import javax.media.opengl.glu.GLU;

import math.Vector4;
import math.Vector4D;
/**
 * Ta kamera absolutnie nie reaguje na zadne komendy z klawiatury czy myszki
 * Uzyteczne np. w main menu i chyba tylko tam, 
 * @author Admin
 *
 */
public class StaticCamera implements Camera {

	@Override
	public void getView() {
		GLU glu = new GLU();
		glu.gluLookAt(0, 0, -1, 0, 0, 0, 0, 1, 0);
	}

	@Override
	public void update(double dt) {
		
	}

	/**
	 * Zwraca nowy wektor, calkowicie niezwiazany z kamerą
	 */
	public Vector4D getCurrentCenter() {
		return new Vector4();
	}

	/**
	 * Zwraca nowy wektor, calkowicie niezwiazany z kamerą
	 */
	public Vector4D getCurrentLookAt() {
		return new Vector4();
	}

	/**
	 * Zwraca nowy wektor, calkowicie niezwiazany z kamerą
	 */
	public Vector4D getUpVector() {
		return new Vector4();
	}

	/**
	 * Zwraca nowy wektor, calkowicie niezwiazany z kamerą
	 */
	public Vector4D getWantedCenter() {
		return new Vector4();
	}

	/**
	 * Zwraca nowy wektor, calkowicie niezwiazany z kamerą
	 */
	public Vector4D getWantedLookAt() {
		return new Vector4();
	}

}
