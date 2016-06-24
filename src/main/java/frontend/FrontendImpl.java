package frontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import tamplater.PageGenerator;
import base.Adress;
import base.Frontend;
import base.GameState;
import base.MessageSystem;

public class FrontendImpl extends HttpServlet implements Frontend, Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MessageSystem ms;
	private Adress adress;
	private Map<String,UserSession> sessions;
	private List<UserSession> waitingGameSessions;

	public FrontendImpl(MessageSystem ms){
		this.ms = ms;
		adress = new Adress();
		ms.addService((Frontend)this);
		sessions = new HashMap<String,UserSession>();
		waitingGameSessions = new ArrayList<UserSession>();
	}
	
    public static String getTime() {
        Date date = new Date();
        date.getTime();
        DateFormat formatter = new SimpleDateFormat("HH.mm.ss");
        return formatter.format(date);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getPathInfo().equals("/userid")) {
			response.setContentType("text/html;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);

			HttpSession session = request.getSession();
			UserSession userSession = sessions.get(session.getId());
			if (userSession == null) {
				responseUserPage(response, "Auth error");
				return;
			}
			if (userSession.getUserId() == null) {
				responseUserPage(response, "wait for authorization");
				return;
			}
			//For this option we should add "Game start" button
			if(userSession.getGameState() != null){
				responseUserGamePage(response, "name = " + userSession.getName()
						+ ", id = " + userSession.getUserId());
			}else{
				if(!userSession.isWaitForGS()){
					checkAvailableOponent(userSession);
				}
				responseUserPage(response, "name = " + userSession.getName()
					+ ", id = " + userSession.getUserId());
			}
		}else if (request.getPathInfo().equals("/gamepage")){
			response.setContentType("text/html;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);
			HttpSession session = request.getSession();
			UserSession userSession = sessions.get(session
					.getId());
			responseUserGamePage(response, "name = " + userSession.getName()
					+ ", id = " + userSession.getUserId());
		}else{
			response.setContentType("text/html;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);
	        Map<String, Object> pageVariables = new HashMap<>();
	        pageVariables.put("lastLogin", "");
	        response.getWriter().println(PageGenerator.getPage("authform.tml", pageVariables));
		}
	}

    private void responseUserPage(HttpServletResponse response, String userState) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("refreshPeriod", "1000");
        pageVariables.put("serverTime", getTime());
        pageVariables.put("userState", userState);
        response.getWriter().println(PageGenerator.getPage("userid.tml", pageVariables));
    }
    
    private void responseUserGamePage(HttpServletResponse response, String userState) throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("refreshPeriod", "100");
        pageVariables.put("serverTime", getTime());
        pageVariables.put("userState", userState);
        response.getWriter().println(PageGenerator.getPage("gamepage.tml", pageVariables));
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getPathInfo().equals("/userid")) {
			String login = request.getParameter("login");
			String sessionId = request.getSession().getId();
			UserSession userSession = new UserSession(sessionId, login,
					ms.getAdressService());
			sessions.put(sessionId, userSession);

			Adress frontendAddress = getAdress();
			Adress accountServiceAddress = userSession.getAdressService().getAcountServiceAdress();

			ms.sendMessage(new MsgGetUserId(frontendAddress,
					accountServiceAddress, login, sessionId));

			response.setContentType("text/html;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);

			responseUserPage(response, "authorization started");
		}else if (request.getPathInfo().equals("/gamepage")) {
			HttpSession session = request.getSession();
			UserSession userSession = sessions.get(session.getId());
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String json = "";
			if(br != null){
				json = br.readLine();
			}
			ObjectMapper mapper = new ObjectMapper();
			GameStateForSession gmfs = (GameStateForSession)mapper.readValue(json,GameStateForSession.class);
			if(gmfs != null){
				if(!gmfs.isStopGame()){
					gmfs.setAdversaryClicks(gmfs.getAdversaryClicks() + 1);
				};
				Double gameTime = (gmfs.getAdversaryClicks()*.1);
				gmfs.setGameTime(gameTime.toString());
				gmfs.setMessage("Game still continue!");
				if(gmfs.getAdversaryClicks()<100){
					gmfs.setStopGame(false);
				}else{
					gmfs.setStopGame(true);
					gmfs.setMessage("Game over!");
				}
			}
			response.setContentType("application/json");
			mapper.writeValue(response.getOutputStream(), gmfs);
		}
	}

    public void run(){
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
	
	public void updateUserId(String sessionId, Integer userId){
		UserSession session = sessions.get(sessionId);
		session.setUserId(userId);
		System.out.println("  UserId: " + session.getUserId());
	}

	public MessageSystem getMessageSystem() {
		return ms;
	}

	private void checkAvailableOponent(UserSession userSession){
		int listSize = waitingGameSessions.size();
		if(listSize > 0){
			Iterator<UserSession> it = waitingGameSessions.iterator();
			while(it.hasNext()){
				UserSession us = it.next();
				if(us == userSession){
					continue;
				}
				Adress gmAdress = ms.getAdressService().getGameMechanicsAdress();
				Set<Integer> userIdSet = new HashSet<Integer>();
				Set<String> sessionIdSet = new HashSet<String>();
				userIdSet.add(userSession.getUserId());
				sessionIdSet.add(userSession.getSessionId());
				userIdSet.add(us.getUserId());
				sessionIdSet.add(us.getSessionId());
				us.setWaitForGS(true);
				userSession.setWaitForGS(true);
				ms.sendMessage(new MsgStartGameSesion(getAdress(), gmAdress, userIdSet, sessionIdSet));
				waitingGameSessions.remove(us);
				if(waitingGameSessions.contains(userSession)){
					waitingGameSessions.remove(userSession);
				}
				return;
			}
		}
		if(!(waitingGameSessions.contains(userSession))){
			waitingGameSessions.add(userSession);
		}
	}

	@Override
	public void setGameState(Set<String> sessionIdSet, GameState gameState) {
		Iterator<String> it = sessionIdSet.iterator();
		while(it.hasNext()){
			UserSession userSession = sessions.get(it.next());
			userSession.setGameState(gameState);
			userSession.setWaitForGS(false);
			//waitingGameSessions.remove(userSession);
		}
	}
}
