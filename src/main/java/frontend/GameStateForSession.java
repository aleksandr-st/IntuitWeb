package frontend;

public class GameStateForSession {
	private int yourClicks;
	private int adversaryClicks;
	private boolean stopGame;
	private String gameTime;
	private String message;
	
	public GameStateForSession(){}
	
	public int getYourClicks() {
		return yourClicks;
	}
	public void setYourClicks(int yourClicks) {
		this.yourClicks = yourClicks;
	}
	public int getAdversaryClicks() {
		return adversaryClicks;
	}
	public void setAdversaryClicks(int adversaryClicks) {
		this.adversaryClicks = adversaryClicks;
	}
	public boolean isStopGame() {
		return stopGame;
	}
	public void setStopGame(boolean stopGame) {
		this.stopGame = stopGame;
	}
	public String getGameTime() {
		return gameTime;
	}
	public void setGameTime(String gameTime) {
		this.gameTime = gameTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
