
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.io.*;

public class Mailer {

    String d_email = "vs04.vijay@gmail.com",
            d_password = "lovelyday",
            d_host = "smtp.gmail.com",
            d_port = "465",
            m_to = "pervasivetechno@gmail.com",
            m_subject = "Hi da bther",
            m_text = "The test mail.......hellloooooooooooooooooooooooooooooooooo";

    public Mailer(String nw) {
        m_text = nw;
        try {
            FileInputStream fin = new FileInputStream("patinfo");
            int id = (fin.read());
            m_subject = "" + id;
            System.out.println(m_subject);
        } catch (Exception w) {
            System.out.println(w);
        }
        //}

        //public Mailer() {
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
            javax.swing.JOptionPane.showMessageDialog(null, "Mail Sent");
            System.out.println("Mail Sent");
        } catch (Exception mex) {
            System.out.println(mex);
            javax.swing.JOptionPane.showMessageDialog(null, "ERROR: Sending mail...");
        }
    }

    public static void main(String[] args) {
        Mailer blah = new Mailer("" + 5);
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(d_email, d_password);
        }
    }
}
