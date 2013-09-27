package game.menu;

import graphics.GameObject;

import java.util.List;

/**
 * Pusta klasa, żadna metoda nic nie robi,
 * stosowane podczas samej gry, gdyby przez przypadek 
 * była wywołana akcja wpływająca na menu (którego przecież wtedy nie ma) 
 * @author Admin
 *
 */
public class BlankMenu implements Menu {

	@Override
	public void addBackground(GameObject back) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addButton(MenuButton but) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearBackground() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearButtons() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<GameObject> getObjectsToRender() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mouseClick(double x, double y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePresenceAt(double x, double y) {
		// TODO Auto-generated method stub

	}

}
