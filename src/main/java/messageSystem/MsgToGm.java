package messageSystem;

import base.Abonent;
import base.Adress;
import base.GameMechanics;
import base.Msg;

public abstract class MsgToGm extends Msg{

	public MsgToGm(Adress from, Adress to) {
		super(from, to);
	}

	@Override
	public void exec(Abonent abonent) {
		if(abonent instanceof GameMechanics){
			execOnGm((GameMechanics)abonent);
		}
		
	}
	
	public abstract void execOnGm(GameMechanics abonent);

}
