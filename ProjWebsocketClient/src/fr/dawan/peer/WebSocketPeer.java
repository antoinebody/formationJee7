package fr.dawan.peer;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import fr.dawan.beans.Person;
import fr.dawan.tools.PersonDecoder;
import fr.dawan.tools.PersonEncoder;

@ClientEndpoint(decoders={PersonDecoder.class},encoders={PersonEncoder.class},
configurator=AppConfigurator.class)
public class WebSocketPeer {
	private Session session;

	public WebSocketPeer(URI endpointURI) throws DeploymentException, IOException {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		container.connectToServer(this, endpointURI);
	}

	@OnOpen
	public void onOpen(Session session) { 
		this.session = session;
	}
	@OnClose
	public void onClose(Session session, CloseReason reason) {
		this.session = null;
	}
	@OnError
	public void onError(Throwable t) {
		t.printStackTrace();
	}
	@OnMessage
	public void onMessage(Person p, Session session) throws IOException, EncodeException {
		System.out.println("Reveived : " + p.getFirstName());
		//
	}
	public void sendMessage(Person obj) {
		if (session != null) 
			this.session.getAsyncRemote().sendObject(obj);
	}
	

}



