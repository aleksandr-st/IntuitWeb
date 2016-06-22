package messageSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import base.Abonent;
import base.Adress;
import base.MessageSystem;
import base.Msg;

public class MessageSystemImpl implements MessageSystem{
	private Map<Adress,ConcurrentLinkedQueue<Msg>> messages = new HashMap<Adress,ConcurrentLinkedQueue<Msg>>();
	private AdressService adressService = new AdressService();
	
	public void addService(Abonent abonent){
		adressService.setAdress(abonent);
		messages.put(abonent.getAdress(), new ConcurrentLinkedQueue<Msg>());
	}
	
	public void sendMessage(Msg message){
		Queue<Msg> currentQueue = messages.get(message.getTo());
		currentQueue.add(message);
	}
	
	public void execForAbonent(Abonent abonent){
		Queue<Msg> messageQueue = messages.get(abonent.getAdress());
		if(messageQueue == null){
			return;
		}
		while(!messageQueue.isEmpty()){
			Msg message = messageQueue.poll();
			message.exec(abonent);
		}
	}
	
	public AdressService getAdressService(){
		return adressService;
	}

}
