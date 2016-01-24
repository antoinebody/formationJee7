package fr.dawan.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import fr.dawan.websocket.beans.Person;
import fr.dawan.websocket.encoders.PersonDecoder;
import fr.dawan.websocket.encoders.PersonEncoder;

@ApplicationScoped
@ServerEndpoint(value = "/actions2/{param1}", encoders = PersonEncoder.class, decoders = PersonDecoder.class)
//@ServerEndpoint(value = "/actions2/{param1}")
public class MyWebSocket2 {
	
	private static final Logger LOGGER = Logger.getLogger(MyWebSocket2.class.getName());

	private static Set<Session> PEERS = Collections.synchronizedSet(new HashSet<>());

	@OnOpen
	public void onOpen(Session session, @PathParam("param1") String param1) {
		session.getUserProperties().put("param1", param1);
		PEERS.add(session);
	}

	@OnClose
	public void onClose(Session session) {
		PEERS.remove(session);
	}

	@OnError
	public void onError(Session session, Throwable t) {
		PEERS.remove(session);
	}

	/**
	 * 
	 * @param message
	 *            string received
	 * @param session
	 *            peer who sent the string
	 * @throws IOException
	 */
	@OnMessage
	public void onMessage(Person person, Session session) throws IOException {
		// synchrone mod
		// session.getBasicRemote().sendText("Hello from server, received : " +
		// message);
		// async
		LOGGER.info("MyWebSocket2 recived person : " + person);
		LOGGER.info("MyWebSocket2 sent again the person to the caller");
		session.getAsyncRemote().sendObject(person);
		//sendToAllPeers(person);

	}
	
	public static void sendToAllPeers(Person person){
		LOGGER.info("send to all peers a non serializable class");
		PEERS.stream()
		.filter(Session::isOpen)
		.forEach(s -> s.getAsyncRemote().sendObject(new Toto()));
	}
	
	private static class Toto{
		
	}

}
