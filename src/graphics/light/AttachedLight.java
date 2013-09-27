package graphics.light;

import graphics.Color;

import math.*;

/**
 * Źródło światła powiązane z jakimś obiektem. Np. z modelem lampy. Pozycja tego światła uwzględniana
 * jest o offset względem pozycji obiektu głownego. Pozycja to ta sama zmienna co u obiektu głownego.
 * @author Admin
 *
 */

public class AttachedLight implements Light {

	private Vector4D _position;
	private Vector4D _offset;
	private Color _ambient;
	private Color _diffuse;
	private Color _specular;
	private Color _emissive;
	
	private Vector4D _tempvec;
	
	public AttachedLight(Vector4D position, Color ambient, Color diffuse, Color specular, Color emissive) {
		_position = position;
		_offset = new Vector4();
		_tempvec = new Vector4();
		_ambient = ambient;
		_diffuse = diffuse;
		_specular = specular;
		_emissive = emissive;
	}
	
	public AttachedLight(Light light, Vector4D position) {
		
	}
	
	public Color getAmbient() {
		return _ambient;
	}

	public void setAmbient(Color ambient) {
		this._ambient = ambient;
	}

	public Color getDiffuse() {
		return _diffuse;
	}

	public void setDiffuse(Color diffuse) {
		this._diffuse = diffuse;
	}

	public Color getSpecular() {
		return _specular;
	}

	public void setSpecular(Color specular) {
		this._specular = specular;
	}

	public Color getEmissive() {
		return _emissive;
	}

	public void setEmissive(Color emissive) {
		this._emissive = emissive;
	}
	
	public Vector4D getPosition() {
		_tempvec.setX(0);
		_tempvec.setY(0);
		_tempvec.setZ(0);
		_tempvec.add(_position);
		_tempvec.add(_offset);
		return _tempvec;
	}
	
	public void setPosition(Vector4D position) {
		this._position  = position;
	}

}
