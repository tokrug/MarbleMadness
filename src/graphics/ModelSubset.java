package graphics;

import javax.media.opengl.GL;

import application.Application;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

import math.Vector4;
import math.Vertex;
import math.Vector4D;
import math.Vertex4;

public class ModelSubset {

	// to bedzie inny typ, ale opis dobry, material uzyty przy renderowaniu tego
	// subsetu, trzeba rozstrzygnac czy trojkatami czy poligonami (albo po
	// prostu wszystko w jeden sposob)
	private Material _material;

	private ModelSubsetFlag _flags;

	private ModelFace[] _faces;
	private Vertex[] _vertices;
	private Vector4D[] _normals;

	public ModelSubset(ModelFace[] faces, Vertex[] vertices,
			Vector4D[] normals, Material mat, int flag) {
		_faces = faces;
		_vertices = vertices;
		_normals = normals;
		_material = mat;
		_flags = new ModelSubsetFlag(flag);
	}

	public ModelSubset(ModelSubset subset) {
		ModelFace[] fac = subset.getFaces();
		_faces = new ModelFace[fac.length];
		for (int i = 0; i < fac.length; i++) {
			_faces[i] = new ModelFace(fac[i]);
		}

		_flags = subset.getFlags();

		Vertex[] vexs = subset.getVertices();
		_vertices = new Vertex[vexs.length];
		for (int i = 0; i < vexs.length; i++) {
			_vertices[i] = new Vertex4(vexs[i]);
		}

		Vector4D[] normals = subset.getNormals();
		_normals = new Vector4[normals.length];
		for (int i = 0; i < normals.length; i++) {
			_normals[i] = new Vector4(normals[i]);
		}

		_material = new Material(subset.getMaterial());
	}

	public void setMaterial(Material mat) {
		_material = mat;
	}

	public Material getMaterial() {
		return _material;
	}

	public Vertex[] getVertices() {
		return _vertices;
	}

	public ModelFace[] getFaces() {
		return _faces;
	}

	public Vector4D[] getNormals() {
		return _normals;
	}

	public ModelSubsetFlag getFlags() {
		return _flags;
	}

	public void render(GL gl) {


		gl.glBegin(GL.GL_TRIANGLES);

		for (int i = 0; i < _faces.length; i++) {

			int[] faceVertices = _faces[i].verticesToArray();
			int[] faceNormals = _faces[i].normalsToArray();
			for (int j = 0; j < faceVertices.length; j++) {
				gl.glNormal3dv(_normals[faceNormals[j]].xyzToDoubleArray(), 0);
				gl.glTexCoord2d(_vertices[faceVertices[j]].getU(),
						_vertices[faceVertices[j]].getV());
				gl
						.glVertex3dv(_vertices[faceVertices[j]]
								.xyzToDoubleArray(), 0);
			}
		}
		gl.glEnd();


	}
}
