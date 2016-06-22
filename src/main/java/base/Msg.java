package base;


public abstract class Msg {
	private final Adress from;
	
	private final Adress to;
	
	public Msg(Adress from, Adress to){
		this.from = from;
		this.to = to;
	}
	
	public Adress getFrom(){
		return from;
	}
	
	public Adress getTo(){
		return to;
	}
	
	public abstract void exec(Abonent abonent);
}
