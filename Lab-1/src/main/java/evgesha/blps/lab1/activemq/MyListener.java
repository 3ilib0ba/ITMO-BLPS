package evgesha.blps.lab1.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Component
public class MyListener {

    @JmsListener(destination = "outbound.queue")
    public void receiveMessage(final String answer) throws JMSException {
        System.out.println(answer);
    }

}
