package graphics.light;

import graphics.GameObject;

import javax.media.opengl.GL;

public interface LightEngine {
	
	public void setProperLights(GameObject object, GL gl);

}
