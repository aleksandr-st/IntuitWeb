package frontend;

import messageSystem.AdressService;
import base.GameState;

public class UserSession {
	private String sessionId;
	private String name;
	private Integer userId;
	private boolean readyToPlay;
	private boolean waitForGS;
	private GameState gameState;
	private AdressService adressService;
	
	public boolean isReadyToPlay() {
		return readyToPlay;
	}

	public void setReadyToPlay(boolean readyToPlay) {
		this.readyToPlay = readyToPlay;
	}

	public UserSession(String sessionId){
		this.setSessionId(sessionId);
	}
	
	public UserSession(String sessionId, String name, AdressService adressService){
		this.setSessionId(sessionId);
		this.setName(name);
		this.setAdressService(adressService);
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gs) {
		this.gameState = gs;
	}

	public AdressService getAdressService() {
		return adressService;
	}

	public void setAdressService(AdressService adressService) {
		this.adressService = adressService;
	}

	public boolean isWaitForGS() {
		return waitForGS;
	}

	public void setWaitForGS(boolean waitForGS) {
		this.waitForGS = waitForGS;
	}

}
