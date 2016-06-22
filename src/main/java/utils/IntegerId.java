package utils;

import java.util.concurrent.atomic.AtomicInteger;

public class IntegerId<T> {
	private static AtomicInteger intSetter = new AtomicInteger(); 
	
	private int id;
	
	public IntegerId(){
		this.id = intSetter.incrementAndGet();
	}
	
	public IntegerId(int id){
		this.id = id;
	}

	public int getInt() {
		return id;
	}
}
