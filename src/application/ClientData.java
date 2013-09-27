package application;

import java.net.URISyntaxException;

/**
 * Wszystkie informacje jakie powinna trzymac u siebie aplikacja,
 * niezwiazane bezposrednio z gra, moze byc singleton 
 */

public class ClientData {

	private double _mouseSensitivity;
	private String _baseDir;
	private String _textureDir;
	private String _modelDir;
	private String _soundDir;
	private String _nativeDir;
	private HighScore _highScores;
	private double _musicLevel;
	private double _soundsLevel;

	public ClientData() {
		_mouseSensitivity = 1;
		// baseDir to ma byc sciezka absolutna do katalogu w ktorym sie znajduje
		// jar
		// _baseDir = "";
		// w relacji do ResourceLoader
		String s = "";
		try {
			String separator = "";
			if (System.getProperty("os.name").contains("Windows")) {
				separator = "\\";
			} else
				separator = "/";
			separator = "/";
			s = ClientData.class.getProtectionDomain().getCodeSource()
					.getLocation().toURI().getPath();
			System.out.println(s);
			s = s.substring(0, s.lastIndexOf(separator));
			System.out.println(s);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		_textureDir = s + System.getProperty("file.separator") + "textures"
				+ System.getProperty("file.separator");
		_modelDir = s + System.getProperty("file.separator") + "models"
				+ System.getProperty("file.separator");
		_soundDir = s + System.getProperty("file.separator") + "sounds"
				+ System.getProperty("file.separator");
		_nativeDir = s + System.getProperty("file.separator") + "libs"
				+ System.getProperty("file.separator") + "native"
				+ System.getProperty("file.separator");
		System.out.println(_textureDir);
		System.out.println(_modelDir);
		System.out.println(_soundDir);
		System.out.println(_nativeDir);
		// tu bedzie load z config file wszelkich ustawien, profili itp.

	}

	public double getMouseSensitivity() {
		return _mouseSensitivity;
	}

	public String getBaseDir() {
		return _baseDir;
	}

	public String getTextureDir() {
		return _textureDir;
	}

	public String getSoundDir() {
		return _soundDir;
	}

	public String getModelDir() {
		return _modelDir;
	}

	public String getNativeDir() {
		return _nativeDir;
	}

}
