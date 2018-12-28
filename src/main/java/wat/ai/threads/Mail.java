package wat.ai.threads;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mail implements Runnable {
    static final Logger LOGGER = Logger.getLogger(Mail.class.getName());

    String destEmail;
    String subject;
    String messageText;


    private HashMap<String, String> getMailConfig() {
        HashMap propertiesMap = new HashMap();
        Properties properties = new Properties();
        try {
            InputStream input = this.getClass().getResourceAsStream("/settings/mail.properties");
            properties.load(input);
            propertiesMap.put("host", properties.getProperty("host"));
            propertiesMap.put("user", properties.getProperty("user"));
            propertiesMap.put("pass", new String(Base64.getDecoder().decode(properties.getProperty("password"))));
            propertiesMap.put("from", properties.getProperty("from"));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }

        return propertiesMap;
    }

    private Properties getMailDefaultProperties(HashMap<String, String> config) {
        Properties props = System.getProperties();

        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", config.get("host"));
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.required", "true");
        return props;
    }

    private HashMap<String, Object> setupProvider(HashMap<String, String> paramsMap, String destinationMail, String subject, String messageText) {
        Properties properties = getMailDefaultProperties(paramsMap);
        Message msg = null;
        Session mailSession = null;
        HashMap<String, Object> messageAndSessionMap = new HashMap<String, Object>();
        try {
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            mailSession = Session.getDefaultInstance(properties, null);
            mailSession.setDebug(false);
            msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(paramsMap.get("from")));
            InternetAddress[] address = {new InternetAddress(destinationMail)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(messageText);

            messageAndSessionMap.put("Session", mailSession);
            messageAndSessionMap.put("Message", msg);
        } catch (MessagingException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }

        return messageAndSessionMap;
    }

    public void sent(HashMap<String, Object> messageAndSessionMap, HashMap<String, String> paramsMap) {
        Session mailSession = (Session) messageAndSessionMap.get("Session");
        Message msg = (Message) messageAndSessionMap.get("Message");
        try {
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(paramsMap.get("host"), paramsMap.get("user"), paramsMap.get("pass"));
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    public Mail(String destEmail, String subject, String messageText) {
        this.destEmail = destEmail;
        this.subject = subject;
        this.messageText = messageText;
    }

    @Override
    public void run() {
        HashMap<String, String> paramsMap = getMailConfig();
        HashMap<String, Object> messageAndSessionMap = setupProvider(paramsMap, destEmail, subject, messageText);
        sent(messageAndSessionMap, paramsMap);
        LOGGER.severe("A mail has been sent to: " + destEmail);
    }
}
