package org.acme.getting.started.clients;

import javax.inject.Singleton;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;

import org.acme.getting.started.entities.Mail;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;


@Singleton
public class MailjetCustomClient {

    Logger logger = Logger.getLogger(MailjetCustomClient.class);

    @ConfigProperty(name="email.sending.from")
    private String from;

    @ConfigProperty(name="email.sending.name")
    private String fromName;

    @ConfigProperty(name="email.sending.user")
    private String mailJetUser;

    @ConfigProperty(name="email.sending.secret")
    private String mailJetSecret;
    
    public void sendEmail(Mail mail) throws MailjetException, MailjetSocketTimeoutException {
        logger.info("Sending email to " + mail.receiver + " with title \"" + mail.title + "\"");
        MailjetClient client;
        MailjetRequest request;
        client = new MailjetClient(mailJetUser, mailJetSecret, new ClientOptions("v3.1"));
        request = new MailjetRequest(Emailv31.resource)
        .property(Emailv31.MESSAGES, new JSONArray()
        .put(new JSONObject()
        .put(Emailv31.Message.FROM, new JSONObject()
        .put("Email", from)
        .put("Name", fromName))
        .put(Emailv31.Message.TO, new JSONArray()
        .put(new JSONObject()
        .put("Email", mail.receiver)
        .put("Name", mail.receiver)))
        .put(Emailv31.Message.SUBJECT, mail.title)
        .put(Emailv31.Message.TEXTPART, mail.body)
        .put(Emailv31.Message.HTMLPART, mail.body)));
        client.post(request);
        logger.info("Email SENT!");
    }
}
