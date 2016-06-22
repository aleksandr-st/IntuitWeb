package base;

import messageSystem.AdressService;

public interface MessageSystem {
	public void addService(Abonent abonent);
	
	public void sendMessage(Msg message);
	
	public void execForAbonent(Abonent abonent);
	
	public AdressService getAdressService();
}
