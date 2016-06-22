package base;

import java.util.Set;


public interface Frontend extends Abonent {
	public void updateUserId(String sessionId,Integer userId);
	
	public MessageSystem getMessageSystem();
	
	public void setGameState(Set<String> sessionIdSet, GameState gameState);

}
