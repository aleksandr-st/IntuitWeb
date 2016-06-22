package messageSystem;

import java.util.HashMap;
import java.util.Map;

import base.Abonent;
import base.Adress;

public class AdressService {
	private Map<Class<?>,Adress> services = new HashMap<Class<?>,Adress>();
	private Adress acountServiceAdress;
	private Adress gameMechanicsAdress;
	
	public void setAdress(Abonent abonent){
		services.put(abonent.getClass(), abonent.getAdress());
	}
	
	public Adress getAdress(Class<?> abonentClass){
		return services.get(abonentClass);
	}

	public Adress getAcountServiceAdress() {
		return acountServiceAdress;
	}

	public void setAcountServiceAdress(Adress acountServiceAdress) {
		this.acountServiceAdress = acountServiceAdress;
	}

	public Adress getGameMechanicsAdress() {
		return gameMechanicsAdress;
	}

	public void setGameMechanicsAdress(Adress gameMechanicsAdress) {
		this.gameMechanicsAdress = gameMechanicsAdress;
	}

}
