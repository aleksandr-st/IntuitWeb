package base;


public interface AcountService extends Abonent{
	public MessageSystem getMessageSystem();
	
	public Integer getUserIdByName(String name);

}
