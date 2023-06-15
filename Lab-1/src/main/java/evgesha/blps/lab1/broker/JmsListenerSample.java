package evgesha.blps.lab1.broker;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class JmsListenerSample {

    @JmsListener(destination = "EMAIL")
    public void receive(final Message message) throws JMSException {
        System.out.println("Received message = " + message);

        if (message instanceof TextMessage textMessage) {
            System.out.println("Message text = " + textMessage.getText());
        }
    }


}
