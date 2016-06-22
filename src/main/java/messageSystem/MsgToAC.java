package messageSystem;

import base.Abonent;
import base.AcountService;
import base.Adress;
import base.Msg;

public abstract class MsgToAC extends Msg{

	public MsgToAC(Adress from, Adress to) {
		super(from, to);
	}

	@Override
	public void exec(Abonent abonent) {
		if (abonent instanceof AcountService){
			execOnAC((AcountService)abonent);
		}
	}

	public abstract void execOnAC(AcountService acountService);

}
