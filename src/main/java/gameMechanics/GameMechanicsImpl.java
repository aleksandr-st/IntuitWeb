package gameMechanics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import utils.IntegerId;
import base.Adress;
import base.GameMechanics;
import base.GameSession;
import base.GameState;
import base.MessageSystem;

public class GameMechanicsImpl implements GameMechanics, Runnable{
	private Adress adress;
	private MessageSystem ms;
	private Map<IntegerId<GameSession>,GameSession> gameSessions;
	
	public GameMechanicsImpl(MessageSystem ms){
		this.ms = ms;
		adress = new Adress();
		ms.addService(this);
		ms.getAdressService().setGameMechanicsAdress(adress);
		gameSessions = new HashMap<IntegerId<GameSession>, GameSession>();
	}

	public Adress getAdress() {
		return adress;
	}

	public void run() {
		while(true){
			try{
				ms.execForAbonent(this);
				stepForGameSessions();
				Thread.sleep(100);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	public MessageSystem getMessageSystem() {
		return ms;
	}

	public GameState startNewGameSession(Set<Integer> userIdSet) {
		GameSession gameSession = new GameSessionImpl(userIdSet);
		gameSessions.put(new IntegerId<GameSession>(), gameSession);
		return gameSession.getGameState();
	}
	
	private void stepForGameSessions(){
		Iterator<GameSession> it = gameSessions.values().iterator();
		while(it.hasNext()){
			it.next().nextStep();
		}
	}

	public void deleteGameSession(GameSession gs) {
		gameSessions.remove(gs);
	}

}
