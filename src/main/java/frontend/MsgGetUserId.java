package frontend;

import messageSystem.MsgToAC;
import base.AcountService;
import base.Adress;

public class MsgGetUserId extends MsgToAC{
	private final String name;
	private final String sessionId;
	
	public MsgGetUserId(Adress from, Adress to, String name, String sessionId) {
		super(from, to);
		this.name = name;
		this.sessionId = sessionId;
	}

	@Override
	public void execOnAC(AcountService acountService) {
		Integer userId = acountService.getUserIdByName(name);
		acountService.getMessageSystem().sendMessage(new MsgUpdateUserId(getTo(),getFrom(),sessionId,userId));
	}
	
}
