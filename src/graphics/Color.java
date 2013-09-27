package graphics;

public class Color {
	
	private double[] d_rgba = {0,0,0,0};
	private float[] f_rgba = {0,0,0,0};
	
	public Color() {
		
	}
	
	public Color(double red, double green, double blue, double alpha) {
		this();
		d_rgba[0] = red;
		d_rgba[1] = green;
		d_rgba[2] = blue;
		d_rgba[3] = alpha;
		f_rgba[0] = (float)red;
		f_rgba[1] = (float)green;
		f_rgba[2] = (float)blue;
		f_rgba[3] = (float)alpha;
	}
	
	public Color(double red, double green, double blue) {
		this();
		d_rgba[0] = red;
		d_rgba[1] = green;
		d_rgba[2] = blue;
		d_rgba[3] = 1.0;
		f_rgba[0] = (float)red;
		f_rgba[1] = (float)green;
		f_rgba[2] = (float)blue;
		f_rgba[3] = 1.0f;
	}
	
	
	public double getRed() {
		return d_rgba[0];
	}
	
	public double getGreen() {
		return d_rgba[1];
	}
	
	public double getBlue() {
		return d_rgba[2];
	}
	
	public double getAlpha() {
		return d_rgba[3];
	}
	
	public double[] toDoubleArray() {
		return d_rgba;
	}
	
	public float[] toFloatArray() {
		return f_rgba;
	}

}
