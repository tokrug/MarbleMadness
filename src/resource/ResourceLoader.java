package resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import javax.media.opengl.GLException;

import application.Application;

import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;

import math.Vector4;
import math.Vector4D;
import math.Vertex4;

import game.level.GameLevel;
import graphics.Color;
import graphics.Material;
import graphics.BasicModel;
import graphics.ModelFace;
import graphics.ModelSubset;

/**
 * Same statyczne metody do wczytywania z plików modeli itp.
 * 
 * @author Admin
 * 
 */

public class ResourceLoader {

	public static BasicModel loadModelFromTxt(String filepath) {
		BufferedReader inputStream;
		try {
			inputStream = new BufferedReader(new FileReader(filepath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		String line;
		try {
			inputStream.readLine();
			inputStream.readLine();
			inputStream.readLine();
			inputStream.readLine();
			inputStream.readLine();

			// wczytywanie ilosci subsetow
			line = inputStream.readLine();
			line = line.substring(line.indexOf(" "));
			line = line.substring(1);

			int numberOfSubsets = Integer.parseInt(line);

			Vertex4[][] arrVertices = new Vertex4[numberOfSubsets][];
			Vector4D[][] arrNormals = new Vector4D[numberOfSubsets][];
			ModelFace[][] arrFaces = new ModelFace[numberOfSubsets][];
			Material[] arrMaterials = new Material[numberOfSubsets];
			int[] meshMaterialNumbers = new int[numberOfSubsets];
			int[] meshSubflags = new int[numberOfSubsets];

			for (int i = 0; i < numberOfSubsets; i++) {

				line = inputStream.readLine();

				String subF = line.substring(line.lastIndexOf("\"") + 2, line
						.lastIndexOf("\"") + 3);

				meshSubflags[i] = Integer.valueOf(subF);

				String matNum = line.substring(line.lastIndexOf(" ") + 1);

				meshMaterialNumbers[i] = Integer.parseInt(matNum);

				// wczytywanie wierzcholkow
				arrVertices[i] = loadVertices(inputStream);

				// wczytanie normalnych
				arrNormals[i] = loadNormals(inputStream);

				// wczytanie trojkatow
				arrFaces[i] = loadFaces(inputStream);

			}

			line = inputStream.readLine();
			line = inputStream.readLine();
			line = line.substring(line.lastIndexOf(" ") + 1);
			int numberOfMaterials = Integer.parseInt(line);

			for (int i = 0; i < numberOfMaterials; i++) {
				// wczytanie materiału z tekstura
				arrMaterials[i] = loadMaterial(inputStream);
			}

			ModelSubset[] subsets = new ModelSubset[numberOfSubsets];
			for (int i = 0; i < numberOfSubsets; i++) {
				subsets[i] = new ModelSubset(arrFaces[i], arrVertices[i],
						arrNormals[i], arrMaterials[meshMaterialNumbers[i]],
						meshSubflags[i]);
			}

			BasicModel model = new BasicModel(subsets);

			return model;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static TextureData loadTextureDataFromFile(String path) {
//		URL url = ResourceLoader.class.getResource(path);
		File file = new File(path);
		if (!file.canRead())
			return null;

		TextureData texData = null;
		if (path.contains(".jpg")) {
			try {
				texData = TextureIO.newTextureData(file, false, TextureIO.JPG);
			} catch (GLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (path.contains(".png")) {
			try {
				texData = TextureIO.newTextureData(file, false, TextureIO.PNG);
			} catch (GLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return texData;
	}

	public static GameLevel loadLevelFromTxt(String path) {
		// TODO
		return null;

	}

	/**
	 * Zczytuje korzystajac z podanego readera informacje o wszystkich
	 * trojkatach obiektu, ustawia wskaznik w readerze na koncu ostatniej
	 * linijki z zadanymi informacjami
	 * 
	 * @param inputStream
	 * @return
	 */
	private static ModelFace[] loadFaces(BufferedReader inputStream) {
		ModelFace[] faces = null;
		try {
			String line = inputStream.readLine();
			int numberOfFaces = Integer.parseInt(line);
			faces = new ModelFace[numberOfFaces];
			for (int i = 0; i < numberOfFaces; i++) {
				int[] vIndices = new int[3];
				int[] nIndices = new int[3];
				line = inputStream.readLine();
				String[] indices = line.split(" ");
				int flag = Integer.parseInt(indices[0]);
				vIndices[0] = Integer.parseInt(indices[1]);
				vIndices[1] = Integer.parseInt(indices[2]);
				vIndices[2] = Integer.parseInt(indices[3]);
				nIndices[0] = Integer.parseInt(indices[4]);
				nIndices[1] = Integer.parseInt(indices[5]);
				nIndices[2] = Integer.parseInt(indices[6]);
				int smoothGroup = Integer.parseInt(indices[7]);
				faces[i] = new ModelFace(vIndices, nIndices, flag, smoothGroup);
			}
		} catch (IOException e) {

		}
		return faces;
	}

	private static Vector4D[] loadNormals(BufferedReader inputStream) {
		Vector4D[] normals = null;
		try {
			String line = inputStream.readLine();
			int numberOfNormals = Integer.parseInt(line);
			normals = new Vector4D[numberOfNormals];
			for (int i = 0; i < numberOfNormals; i++) {
				line = inputStream.readLine();
				String[] coords = line.split(" ");
				normals[i] = new Vector4(Double.parseDouble(coords[0]), Double
						.parseDouble(coords[1]), Double.parseDouble(coords[2]));
			}
		} catch (IOException e) {

		}
		return normals;
	}

	private static Vertex4[] loadVertices(BufferedReader inputStream) {
		Vertex4[] vertices = null;
		try {
			String line = inputStream.readLine();
			int numberOfVertices = Integer.parseInt(line);
			vertices = new Vertex4[numberOfVertices];
			for (int i = 0; i < numberOfVertices; i++) {
				line = inputStream.readLine();
				String[] coords = line.split(" ");
				int flag = Integer.parseInt(coords[0]);
				double x = Double.parseDouble(coords[1]);
				double y = Double.parseDouble(coords[2]);
				double z = Double.parseDouble(coords[3]);
				double u = Double.parseDouble(coords[4]);
				double v = Double.parseDouble(coords[5]);
				int boneIndex = Integer.parseInt(coords[6]);
				vertices[i] = new Vertex4(x, y, z, u, v, flag, boneIndex);
			}
		} catch (IOException e) {

		}
		return vertices;
	}

	private static Material loadMaterial(BufferedReader inputStream) {
		Material mat = null;
		try {
			inputStream.readLine();
			String line = inputStream.readLine();
			String[] colors = line.split(" ");
			Color ambient = new Color(Double.parseDouble(colors[0]), Double
					.parseDouble(colors[1]), Double.parseDouble(colors[2]),
					Double.parseDouble(colors[3]));
			line = inputStream.readLine();
			colors = line.split(" ");
			Color diffuse = new Color(Double.parseDouble(colors[0]), Double
					.parseDouble(colors[1]), Double.parseDouble(colors[2]),
					Double.parseDouble(colors[3]));
			line = inputStream.readLine();
			colors = line.split(" ");
			Color specular = new Color(Double.parseDouble(colors[0]), Double
					.parseDouble(colors[1]), Double.parseDouble(colors[2]),
					Double.parseDouble(colors[3]));
			line = inputStream.readLine();
			colors = line.split(" ");
			Color emissive = new Color(Double.parseDouble(colors[0]), Double
					.parseDouble(colors[1]), Double.parseDouble(colors[2]),
					Double.parseDouble(colors[3]));
			line = inputStream.readLine();
			double shininess = Double.parseDouble(line);
			line = inputStream.readLine();
			double transparency = Double.parseDouble(line);
			line = inputStream.readLine();
			String texFile = line;
			texFile = texFile.substring(1);
			texFile = texFile.substring(0, texFile.length() - 1);

			if (!texFile.equals("")) {
				mat = new Material(ambient, diffuse, specular, emissive,
						shininess, transparency, Application.getDataMgr()
								.getTexture(texFile), Application.getDataMgr()
								.getTextureID(texFile));
			} else
				mat = new Material(ambient, diffuse, specular, emissive,
						shininess, transparency);
		} catch (IOException e) {

		}

		try {
			inputStream.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mat;
	}
}
