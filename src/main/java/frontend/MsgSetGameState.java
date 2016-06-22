package frontend;

import java.util.Set;

import base.Adress;
import base.Frontend;
import base.GameState;
import messageSystem.MsgToFrontend;

public class MsgSetGameState extends MsgToFrontend{
	private final Set<String> sessionIdSet;
	private final GameState gameState;
	
	public MsgSetGameState(Adress from, Adress to, Set<String> sessionIdSet, GameState gameState) {
		super(from, to);
		this.sessionIdSet = sessionIdSet;
		this.gameState = gameState;
	}

	@Override
	public void execOnFrontend(Frontend frontend) {
		frontend.setGameState(sessionIdSet, gameState);
	}

}
