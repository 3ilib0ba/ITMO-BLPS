package evgesha.blps.lab1.service;


import evgesha.blps.lab1.entity.EmailDetails;

public interface EmailServiceInterface {

    String sendSimpleMail(EmailDetails details);
}
