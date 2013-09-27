package graphics;

import application.Application;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureData;

/**
 * To zostanie na pewno poszerzone o textury itp.
 * 
 * @author Admin
 * 
 */

public class Material {

	private Color _ambient;
	private Color _diffuse;
	private Color _specular;
	private Color _emissive;

	private double _shininess;
	private double _transparency;
	
	private TextureData _texData;
	private Texture _tex;
	
	private int _texID;

	public Material() {
		
	}
	
	public Material(Color ambient, Color diffuse, Color specular,
			Color emissive, double shininess, double transparency, TextureData texData, int texID) {
		_ambient = ambient;
		_diffuse = diffuse;
		_specular = specular;
		_emissive = emissive;
		_shininess = shininess;
		_transparency = transparency;
		_texData = texData;
		_texID = texID;
	}
	
	public Material(Color ambient, Color diffuse, Color specular,
			Color emissive, double shininess, double transparency) {
		_ambient = ambient;
		_diffuse = diffuse;
		_specular = specular;
		_emissive = emissive;
		_shininess = shininess;
		_transparency = transparency;
		_texID = -1;
	}
	
	public Material(Material mat) {
		_ambient = mat.getAmbient();
		_diffuse = mat.getDiffuse();
		_specular = mat.getSpecular();
		_emissive = mat.getEmissive();
		_shininess = mat.getShininess();
		_transparency = mat.getTransparency();
		if (mat.getTexData()!=null) {
		_texData = mat.getTexData();
		_texID = mat.getTextureID();
		} else 
			_texID = -1;
	}

	public Texture getTexture() {
		return _tex;
	}
	
	public void setTexture(Texture tex) {
		this._tex = tex;
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

	public double getShininess() {
		return _shininess;
	}

	public void setShininess(double shininess) {
		this._shininess = shininess;
	}

	public double getTransparency() {
		return _transparency;
	}

	public void setTransparency(double transparency) {
		this._transparency = transparency;
	}
	
	public TextureData getTexData() {
		return _texData;
	}
	
	public void setTexData(TextureData texdat) {
		this._texData = texdat;
	}
	
	public int getTextureID() {
		return _texID;
	}
	
	private int getTexIDFromMgr(TextureData tex) {
		return 0;
	}

}
