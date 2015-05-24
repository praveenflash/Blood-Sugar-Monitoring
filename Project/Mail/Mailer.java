import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class Mailer {

    String d_email = "praveen.flash@gmail.com",
            d_password = "b@r@thkum@r",
            d_host = "smtp.gmail.com",
            d_port = "465",
            m_to = "praveen.flash@hotmail.com",
            m_subject = "Hi da bther",
            m_text = "The test mail.......hellloooooooooooooooooooooooooooooooooo";

    public Mailer() {
        Properties props = new Properties();
        props.put("mail.smtp.user", d_email);
        props.put("mail.smtp.host", d_host);
        props.put("mail.smtp.port", d_port);
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.debug", "true");
        props.put("mail.smtps.socketFactory.port", d_port);
        props.put("mail.smtps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtps.socketFactory.fallback", "false");

        //SecurityManager security = System.getSecurityManager();

        try {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            session.setDebug(true);

            MimeMessage msg = new MimeMessage(session);
            msg.setText(m_text);
            msg.setSubject(m_subject);
            msg.setFrom(new InternetAddress(d_email));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));

            Transport tr = session.getTransport("smtps");
            tr.connect(d_host, 465, d_email, d_password);
            msg.saveChanges();
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
        } catch (Exception mex) {
            System.out.println(mex);

        }
    }

    public static void main(String[] args) {
        Mailer blah = new Mailer();
        System.out.println("Mail Sent");
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(d_email, d_password);
        }
    }
}
