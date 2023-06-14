package evgesha.blps.lab1.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class Listener {

    @JmsListener(destination = "inbound.queue")
    public void receiveMessage(final Message jmsMessage) throws JMSException {
        String messageData;
        String response = "";
        if (jmsMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) jmsMessage;
            messageData = textMessage.getText();
            response = messageData;
        }
        System.out.println("Get new message from 'inbound.queue' = " + response);
    }

}
