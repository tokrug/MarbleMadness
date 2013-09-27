package application;

import game.engine.GameEngineLevel;
import game.engine.GameEngineMenu;

public class GameStateManager {

	// stany gry
	private static final int InitializingProgram = 0;
	private static final int MainMenu = 1;
	private static final int GameLevel = 2;
	
	private int _currentGameState;

	public void setInitializeState() {
		_currentGameState = InitializingProgram;
		
	}
	
	public void setMenuState() {
		Application.getGame().endState();
		_currentGameState = MainMenu;
		Application.setGame(new GameEngineLevel());
//		Application.setGame(new GameEngineMenu());
		Application.getGame().startState();
	}
	
	public void setGameState() {
		Application.getGame().endState();
		_currentGameState = GameLevel;
		Application.setGame(new GameEngineLevel());
		Application.getGame().startState();
	}	

}
