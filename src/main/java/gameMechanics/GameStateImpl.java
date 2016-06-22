package gameMechanics;

import java.util.Map;

import base.GameState;

public class GameStateImpl implements GameState{
	private Map<Integer,Integer> userIds;
	
	private boolean stopGame;

	public Map<Integer,Integer> getUserIds() {
		return userIds;
	}

	public void setUserIds(Map<Integer,Integer> userIds) {
		this.userIds = userIds;
	}

	public boolean isStopGame() {
		return stopGame;
	}

	public void setStopGame(boolean stopGame) {
		this.stopGame = stopGame;
	}
	
	

}
