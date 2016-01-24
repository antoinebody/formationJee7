package fr.dawan.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.DeploymentException;

import fr.dawan.beans.Person;
import fr.dawan.peer.WebSocketPeer;

@SuppressWarnings("serial")
@WebServlet("/ClientWebSocketServlet")
public class ClientWebSocketServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			System.out.println("Try launch WebSocketPeer");
			WebSocketPeer webSocketPeer = new WebSocketPeer(new URI("ws://localhost:8080/ProjWebsocket/actions2/myParameter"));
			System.out.println("ClientWebSocketServlet WebSocket client started");
			Person person = new Person("antoine","body");
			webSocketPeer.sendMessage(person);
			System.out.println("ClientWebSocketServlet contact sent");
		} catch (DeploymentException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	

}
