package fr.dawan.websocket.encoders;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import fr.dawan.websocket.beans.Person;
import fr.dawan.websocket.tools.JsonTools;

public class PersonEncoder implements Encoder.Text<Person> {

	@Override
	public void destroy() {
	}

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public String encode(Person object) throws EncodeException {
		try {
			return JsonTools.toJsonStream(object);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new EncodeException(object, e.getMessage(),e);
		}
	}

}
