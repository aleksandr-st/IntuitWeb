package base;

import java.util.Map;

public interface GameState {
	public Map<Integer,Integer> getUserIds();
	
	public void setUserIds(Map<Integer,Integer> userIds);
	
	public boolean isStopGame();
	
	public void setStopGame(boolean stopGame);

}
