package gameMechanics;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import base.GameSession;
import base.GameState;

public class GameSessionImpl implements GameSession{
	private long startTime;
	private long currentTime;
	private final long MAXGAMETIME = 10000;
	private boolean stopGame;
	private Map<Integer,Integer> userScores;
	
	public GameSessionImpl(Set<Integer> userIdSet){
		userScores = new HashMap<Integer,Integer>();
		Iterator<Integer> it = userIdSet.iterator();
		while(it.hasNext()){
			userScores.put(it.next(), 0);
		}
		setStopGame(false);
		this.startTime = (new Date()).getTime();
		currentTime = startTime;
	}

	public void nextStep() {
		currentTime = (new Date().getTime());
		long timeDelta = currentTime - startTime;
		if(timeDelta > MAXGAMETIME){
			setStopGame(true);
		}else{
			//
		}
	}

	public boolean isStopGame() {
		return stopGame;
	}

	public void setStopGame(boolean stopGame) {
		this.stopGame = stopGame;
	}

	@Override
	public GameState getGameState() {
		GameState gameState = new GameStateImpl();
		gameState.setUserIds(userScores);
		gameState.setStopGame(stopGame);
		return gameState;
	}

}
