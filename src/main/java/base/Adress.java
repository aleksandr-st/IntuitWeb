package base;

import java.util.concurrent.atomic.AtomicInteger;

public class Adress {
	private static AtomicInteger abonentIdCreator = new AtomicInteger();
	private int abonentId;
	
	public Adress(){
		this.abonentId = abonentIdCreator.incrementAndGet();
	}
	
	public int getAdress(){
		return abonentId;
	}

	@Override
	public int hashCode() {
		return abonentId;
	}

}
