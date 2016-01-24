package fr.dawan.peer;

import java.util.List;
import java.util.Map;

import javax.websocket.ClientEndpointConfig.Configurator;
import javax.websocket.HandshakeResponse;

public class AppConfigurator extends Configurator{

	@Override
	public void afterResponse(HandshakeResponse hr) {
		System.out.println("After respsonse " + hr.getHeaders().toString());
	}

	@Override
	public void beforeRequest(Map<String, List<String>> headers) {
		System.out.println("before request" + headers.toString());
	}

	
}