package math;

public interface Vertex extends Vector4D {

	public double getU();

	public void setU(double u);

	public double getV();

	public void setV(double v);

	public int getFlags();

	public void setFlags(int flags);

	public int getBoneIndex();

	public void setBoneIndex(int index);
	
	

}
