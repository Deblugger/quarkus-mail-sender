package org.acme.getting.started.services;

import java.util.List;

import javax.inject.Singleton;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;

import org.acme.getting.started.clients.MailjetCustomClient;
import org.acme.getting.started.entities.Mail;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

@Singleton
public class MailService {

    Logger logger = Logger.getLogger(MailService.class);

    @ConfigProperty(name="email.sending.enabled")
    private Boolean sendingEnabled;

    private MailjetCustomClient mailjetCustomClient;

    public MailService(MailjetCustomClient mailjetCustomClient) {
        this.mailjetCustomClient = mailjetCustomClient;
    }

    public void sendAndSaveEmail(Mail mail) {         
        logger.info("Receiving mail to " + mail.receiver);
        try {
            if(sendingEnabled) { 
                mailjetCustomClient.sendEmail(mail);
            }
            mail.persist();
        } catch (MailjetException | MailjetSocketTimeoutException e) {
            logger.error("Email sending failed!");
            e.printStackTrace();
        }
    }

    public List<Mail> findAll() {
        return Mail.listAll();
    }

    public List<Mail> findByTo(String to) {
        return Mail.listByTo(to);
    }
    
}
