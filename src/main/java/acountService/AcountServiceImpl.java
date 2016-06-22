package acountService;

import java.util.HashMap;
import java.util.Map;

import base.AcountService;
import base.Adress;
import base.MessageSystem;

public class AcountServiceImpl implements Runnable, AcountService{
	private MessageSystem ms; 
	private Map<String,Integer> users;
	private Adress adress;
	
	public AcountServiceImpl(MessageSystem ms){
		this.ms = ms;
		adress = new Adress();
		ms.addService(this);
		ms.getAdressService().setAcountServiceAdress(adress);
		users = new HashMap<String,Integer>();
		users.put("User", 23);
		users.put("Timur", 2);
	}

	public MessageSystem getMessageSystem() {
		return ms;
	}

	public Integer getUserIdByName(String name){
		return (Integer)users.get(name);
	}

	public void run() {
		while(true){
			try{
				ms.execForAbonent(this);
				Thread.sleep(100);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	public Adress getAdress() {
		return adress;
	}

}
