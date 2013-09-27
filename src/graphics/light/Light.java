package graphics.light;

import graphics.Color;
import math.Vector4D;

public interface Light {

	public Color getAmbient();

	public void setAmbient(Color ambient);

	public Color getDiffuse();

	public void setDiffuse(Color diffuse);

	public Color getSpecular();

	public void setSpecular(Color specular);

	public Color getEmissive();

	public void setEmissive(Color emissive);

	public Vector4D getPosition();

	public void setPosition(Vector4D position);

}
