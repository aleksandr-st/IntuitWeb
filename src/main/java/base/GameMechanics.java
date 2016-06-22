package base;

import java.util.Set;

public interface GameMechanics extends Abonent{
	public MessageSystem getMessageSystem();
	
	public GameState startNewGameSession(Set<Integer> userIdSet);
	
	public void deleteGameSession(GameSession gs);

}
