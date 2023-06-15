package evgesha.blps.lab1.service;

import evgesha.blps.lab1.dto.EmailWithRecipeDto;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import org.springframework.mail.SimpleMailMessage;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;


@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    @JmsListener(destination = "EMAIL_RECIPE_SENDER")
    public void receive(final ObjectMessage message) throws JMSException {
        EmailWithRecipeDto deserialized = (EmailWithRecipeDto) SerializationUtils.deserialize((byte[]) message.getObject());
        System.out.println(("get new message OBJECT = " + deserialized));



    }

    public void send(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(emailConfig.getUsername());
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }

}
