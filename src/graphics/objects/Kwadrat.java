package graphics.objects;

import graphics.AbstractGameObject;
import application.DataManager;
import math.Vector4D;

public class Kwadrat extends AbstractGameObject{

	public Kwadrat(Vector4D position) {
		super(position);
		setModel(DataManager.M_MENU);
	}
	
	public Kwadrat(double x, double y, double z) {
		super(x,y,z);
		setModel(DataManager.M_MENU);
	}
	
	@Override
	public void update(double dt) {
		super.update(dt);
	}
	

}
