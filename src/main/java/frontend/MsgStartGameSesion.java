package frontend;

import java.util.Set;

import base.Adress;
import base.GameMechanics;
import base.GameState;
import messageSystem.MsgToGm;

public class MsgStartGameSesion extends MsgToGm{
	private final Set<Integer> userIdSet;
	private final Set<String> sessionIdSet;

	public MsgStartGameSesion(Adress from, Adress to, Set<Integer> userIdSet, Set<String> sessionIdSet) {
		super(from, to);
		this.userIdSet = userIdSet;
		this.sessionIdSet = sessionIdSet;
	}

	@Override
	public void execOnGm(GameMechanics abonent) {
		GameState gameState = abonent.startNewGameSession(userIdSet);
		abonent.getMessageSystem().sendMessage(new MsgSetGameState(getTo(), getFrom(), sessionIdSet, gameState));
	}

}
