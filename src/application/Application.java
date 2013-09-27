package application;

import math.Vector4;
import sound.AttachedSoundEmiter;
import sound.SoundEmiter;
import sound.SoundEngine;
import game.engine.GameEngine;
import game.engine.GameEngineLevel;
import input.actions.manager.AWTEventActionManager;
import input.actions.manager.ActionManager;

public class Application {

	// po to zeby byl latwy dostep do nich i przy okazji W razie czego szybko
	// mozna
	// implementacje interfejsow zmienic, inicjalizacja przy okazji
	// nie wazyc sie robic static final i od razu inicjalizowac
	// brak bezposredniego dostepu do action managera, rozszerzone beda
	// poszczegolne
	// interfejsy

	private static ActionManager Input;
	private static SoundEngine Sound;
	private static GraphicEngine Graphic;
	private static GameData GData;
	private static ClientData CData;
	private static GameEngine Game;
	private static GameStateManager GameState;
	private static MainWindow Window;

	private static DataManager DataMgr;

	public static void main(String[] args) {

		GameState = new GameStateManager();
		
		GameState.setInitializeState();
		
		CData = new ClientData();
		
		DataMgr = new DataManager();
		
		// utworzenie calosciowej kontroli wejscia, w tym manager akcji oraz
		// eventow
		// tu na samym starcie powinien byc load ustawien z pliku jesli by
		// istnial
		// w tym np. bindy klawiszy

		Input = new AWTEventActionManager();

		// tylko utworzenie okna i canvas do rysowania
		// dodanie listenera do canvas zeby nasluchiwal, jesli to nie jest
		// na JInput budowane
		Window = new MainWindow();
		Window.setFocusListener(Input.getControllersManager());
		Window.setEventListener(Input.getControllersManager());

 
		Graphic = new GraphicEngine();
		Game = new GameEngineLevel();

		Sound = new SoundEngine();

		GData = new GameData();

		Window.addRenderer(Graphic);

		Window.showWindow();
		
		GameState.setMenuState();

		long oldTime = System.nanoTime();
		long newTime;
		double dt;
		while (true) {
			// czas jaki uplynal miedzy klatkami, sprowadzone do sekund
			newTime = System.nanoTime();
			dt = (double) (newTime - oldTime) / 1000000000;
			oldTime = newTime;
//			System.out.println("Before: " + System.nanoTime());
			Game.update(dt);
//			System.out.println("Between: " + System.nanoTime());
			Window.displayFrame();
//			System.out.println("After: " + System.nanoTime());
			System.out.println(dt);
		}

	}

	public static GameEngine getGame() {
		return Game;
	}

	public static void setGame(GameEngine game) {
		Game = game;
	}

	public static ActionManager getInput() {
		return Input;
	}

	public static SoundEngine getSound() {
		return Sound;
	}

	public static GraphicEngine getGraphic() {
		return Graphic;
	}

	public static GameData getGData() {
		return GData;
	}

	public static ClientData getCData() {
		return CData;
	}

	public static GameStateManager getGameState() {
		return GameState;
	}

	public static MainWindow getWindow() {
		return Window;
	}

	public static DataManager getDataMgr() {
		return DataMgr;
	}

}
