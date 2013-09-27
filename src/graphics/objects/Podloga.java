package graphics.objects;

import graphics.AbstractGameObject;
import application.Application;
import application.DataManager;

public class Podloga extends AbstractGameObject {
	
	public Podloga(double x, double y, double z) {
		super(x,y,z);
		setModel(DataManager.M_FLOOR);
	}

	@Override
	public void update(double dt) {
		super.update(dt);
	}

}
