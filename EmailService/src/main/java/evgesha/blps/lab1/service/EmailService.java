package evgesha.blps.lab1.service;

import evgesha.blps.lab1.dto.EmailWithRecipeDto;
import evgesha.blps.lab1.entity.EmailDetails;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;


@Service
public class EmailService {

    private final EmailServiceImpl mailSender;


    public EmailService(EmailServiceImpl mailSender) {
        this.mailSender = mailSender;
    }

    @JmsListener(destination = "EMAIL_RECIPE_SENDER")
    public void receive(final ObjectMessage message) throws JMSException {
        EmailWithRecipeDto deserialized = (EmailWithRecipeDto) SerializationUtils.deserialize((byte[]) message.getObject());
        System.out.println(("get new message OBJECT = " + deserialized));

        mailSender.sendSimpleMail(new EmailDetails("fridkin95@gmail.com", deserialized.getRecipeDto().toString(), "BLPS", null));

    }


}
