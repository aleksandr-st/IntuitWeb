package main;

import messageSystem.MessageSystemImpl;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import acountService.AcountServiceImpl;
import frontend.FrontendImpl;
import gameMechanics.GameMechanicsImpl;

public class Main {
    public static void main(String[] args) throws Exception
    {
		String portString = "0";
    	if(args.length != 0){
    		portString = args[0];
       	}
        int port = Integer.valueOf(portString);
        if(port == 0){
        	port = 80;
        }
        System.out.println("Starting at port: " + port + " For other port point it as first parameter on start.");
        
        MessageSystemImpl ms = new MessageSystemImpl();
    	FrontendImpl frontend = new FrontendImpl(ms);
    	AcountServiceImpl ac = new AcountServiceImpl(ms);
    	GameMechanicsImpl gm = new GameMechanicsImpl(ms);
    	(new Thread(frontend)).start();
    	(new Thread(ac)).start();
    	(new Thread(gm)).start();
    	
		Server server = new Server(port);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.addServlet(new ServletHolder(frontend), "/*");
		
		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setDirectoriesListed(true);
		resourceHandler.setResourceBase("static");
		
		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] {resourceHandler,context});
		server.setHandler(handlers);
		
		server.start();
		server.join();
    }
}