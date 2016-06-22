package messageSystem;

import base.Abonent;
import base.Adress;
import base.Frontend;
import base.Msg;

public abstract class MsgToFrontend extends Msg{

	public MsgToFrontend(Adress from, Adress to) {
		super(from, to);
	}
	
	public void exec(Abonent abonent){
		if (abonent instanceof Frontend){
			execOnFrontend((Frontend)abonent);
		}
	}
	
	public abstract void execOnFrontend(Frontend frontend);

}
