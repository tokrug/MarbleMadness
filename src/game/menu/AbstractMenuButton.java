package game.menu;

import math.Vertex;
import graphics.AbstractGameObject;
import graphics.ModelFace;
import graphics.ModelSubset;

public abstract class AbstractMenuButton extends AbstractGameObject implements
		MenuButton {

	public AbstractMenuButton(double x, double y, double z) {
		super(x,y,z);
	}
	
	@Override
	public double getHeight() {
		double height = 0;
		double y = getY();
		double temp = 0;
		ModelSubset[] subs = getAnimatedModel().getSubsets();
		for (int i = 0; i < subs.length; i++) {
			for (int j = 0; j < subs[i].getVertices().length; j++) {
				temp = subs[i].getVertices()[j].getY() - y;
				Math.abs(temp);
				if (temp > height) {
					height = temp;
				}
			}
		}
		return height;
	}

	@Override
	public double getWidth() {
		double width = 0;
		double x = getX();
		double temp = 0;
		ModelSubset[] subs = getAnimatedModel().getSubsets();
		for (int i = 0; i < subs.length; i++) {
			for (int j = 0; j < subs[i].getVertices().length; j++) {
				temp = subs[i].getVertices()[j].getX() - x;
				Math.abs(temp);
				if (temp > width) {
					width = temp;
				}
			}
		}
		return width;
	}

	/**
	 * Zakłada kwadratowe modele / juz nie
	 */
	@Override
	public boolean isMouseWithin(double x, double y) {
		//wspolrzedne wszystkich punktow
		double a,b,c,d;
		//3 punkty z face'a
		Vertex q,w,e;
		//zmienne dla rownania oglnego prostej
		double A,B,C;
		//wyniki czy punkty znajduja sie po tej samej stronie prostej
		double Q,W,E;
		
		for (ModelSubset sub : getAnimatedModel().getSubsets()) {
			for (ModelFace face : sub.getFaces()) {
				q = sub.getVertices()[face.verticesToArray()[0]];
				w = sub.getVertices()[face.verticesToArray()[1]];
				e = sub.getVertices()[face.verticesToArray()[2]];
				
				a = q.getX();
				b = q.getY();
				c = w.getX();
				d = w.getY();
				
				A = b-d;
				B = c-a;
				C = a*d - b*c;
				
				Q = (A*e.getX() + B*e.getY() + C) * (A*x + B*y + C);
				
				a = w.getX();
				b = w.getY();
				c = e.getX();
				d = e.getY();
				
				A = b-d;
				B = c-a;
				C = a*d - b*c;
				
				W = (A*q.getX() + B*q.getY() + C) * (A*x + B*y + C);
				
				a = e.getX();
				b = e.getY();
				c = q.getX();
				d = q.getY();
				
				A = b-d;
				B = c-a;
				C = a*d - b*c;
				
				E = (A*w.getX() + B*w.getY() + C) * (A*x + B*y + C);
				
				if (Q >=0 && W >= 0 && E >= 0) {
					return true;
				}
				
			}
		}
		
		return false;
	}

}
