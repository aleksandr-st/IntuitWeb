package main;

public class ThreadTest extends Thread{
	private static int maxId = 0;
	private static Object waitObject = new Object();
	private int mainId;
	//private static Logger logger; 
		
	public ThreadTest(int mainId){
		this.mainId = mainId;
	}
	
	public void run(){
		synchronized (waitObject) {
			try {
				while (mainId > maxId) {
					waitObject.wait();
				};
				maxId++;
				System.out.println("Id: " + mainId);
				//logger.info("Id: "+mainId);
				waitObject.notifyAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		//logger = Logger.getLogger("ClassName");
		for(int i = 0; i < 10; i++){
			(new ThreadTest(i)).start();
		}
	}

}
