package frontend;

import messageSystem.MsgToFrontend;
import base.Adress;
import base.Frontend;

public class MsgUpdateUserId extends MsgToFrontend{
	private final String sessionId;
	private final Integer userId;

	public MsgUpdateUserId(Adress from, Adress to, String sessionId, Integer userId) {
		super(from, to);
		this.sessionId = sessionId;
		this.userId = userId;
	}

	@Override
	public void execOnFrontend(Frontend frontend) {
		frontend.updateUserId(sessionId, userId);
	}

}
