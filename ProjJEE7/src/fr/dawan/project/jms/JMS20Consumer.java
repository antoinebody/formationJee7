 package fr.dawan.project.jms;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import fr.dawan.project.model.Person;

@MessageDriven(mappedName = "jms/TestQueue")
public class JMS20Consumer implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			Person person = message.getBody(Person.class);
			System.out.println("JMS ==========> Message received: " + person);
		} catch (JMSException e) {
			e.printStackTrace();
			System.err.println("Error while fetching message payload: " + e.getMessage());
		}
	}
}