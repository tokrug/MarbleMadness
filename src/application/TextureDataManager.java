package application;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.LinkedBlockingQueue;

import resource.ResourceLoader;

import com.sun.opengl.util.texture.TextureData;

public class TextureDataManager implements Runnable {

	String baseDir;

	ConcurrentSkipListMap<String, TextureData> _loadedTextures;
	LinkedBlockingQueue<String> _textureRequests;

	HashMap<String, Integer> _texids;

	public TextureDataManager() {
		_loadedTextures = new ConcurrentSkipListMap<String, TextureData>();
		_textureRequests = new LinkedBlockingQueue<String>();
		baseDir = Application.getCData().getTextureDir();
		_texids = new HashMap<String, Integer>();
	}

	public TextureData getTexture(Integer texNumber) {
		return null;
	}

	public TextureData getTexture(String texName) {
		if (_loadedTextures.containsKey(texName)) {
			return _loadedTextures.get(texName);
		}
		TextureData tex = ResourceLoader.loadTextureDataFromFile(baseDir
				+ texName);
		_loadedTextures.put(texName, tex);
		return tex;
	}

	public void placeTextureRequest(Integer texNumber) {

	}

	public void placeTextureRequest(String texName) {
		_textureRequests.add(texName);
	}

	public void run() {
		while (true) {
			while (!_textureRequests.isEmpty()) {
				String name = _textureRequests.poll();
				_loadedTextures.put(name, ResourceLoader
						.loadTextureDataFromFile(baseDir + name));
			}
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {

			}
		}
	}

	public int getTextureID(String name) {
		if (_texids.containsKey(name))
			return _texids.get(name);
		return 0;
	}

	/**
	 * Uzywane na samym poczatku, taki init, zgarnia wszystkie textury z
	 * katalogu Textures i nadaje im ID
	 */
	public void setup() {
		System.out.println(System.getProperty("java.class.path"));
//		System.out.println(ResourceLoader.class.getResource(
//				Application.getCData().getTextureDir()).getPath());
		File file = new File(Application.getCData().getTextureDir());

		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			_texids.put(files[i].getName(), new Integer(i + 1));
		}
	}
}
