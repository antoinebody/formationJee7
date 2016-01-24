package fr.dawan.project.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

import fr.dawan.project.model.Person;

@Stateless
public class JMS20Producer {

	@Resource(lookup = "jms/TestQueue")
	private Queue queue;

	@Inject
	@JMSConnectionFactory("jms/__defaultConnectionFactory") // @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
	private JMSContext jmsContext;

	public String send(Person objToSend) {
		try {

			System.out.println("produce JMS ============>");
			JMSProducer jmsProducer = jmsContext.createProducer();

			jmsProducer.send(queue, objToSend);

			// Set delivery delay of 20 seconds, in milliseconds. This will
			// apply to all messages sent from this producer until the value is
			// changed.
			jmsProducer.setDeliveryDelay(20 * 1000);
			jmsProducer.send(queue, new Person("antoine","body"));

			return "OK";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "KO";
	}
}
