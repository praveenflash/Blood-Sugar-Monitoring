import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.search.FlagTerm;
import java.io.*;
import java.sql.*;
public class Main {

    Folder inbox;
    public Main() {
        String id,value,dat;
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");

        try {
        
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", "pervasivetechno@gmail.com", "pervasive");

        
            inbox = store.getFolder("Inbox");
        
            int n=inbox.getUnreadMessageCount();
            if(n==0)
            {
                javax.swing.JOptionPane.showMessageDialog(null,"No new mails Doctor");
            }
        
            inbox.open(Folder.READ_WRITE);

        
            Message messages[] = inbox.search(new FlagTerm(new Flags(Flag.SEEN), false));
        
            FetchProfile fp = new FetchProfile();
            fp.add(FetchProfile.Item.ENVELOPE);
            fp.add(FetchProfile.Item.CONTENT_INFO);
            inbox.fetch(messages, fp);

            try {
                printAllMessages(messages);
                int messn=messages.length;
                javax.swing.JOptionPane.showMessageDialog(null,"New mails updated :"+messn);
                inbox.close(true);
                store.close();
            } catch (Exception ex) {
                System.out.println("Exception arise at the time of read mail");
                ex.printStackTrace();
            }
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.exit(2);
        }
    }

    public void printAllMessages(Message[] msgs) throws Exception {
        for (int i = 0; i < msgs.length; i++) {
            printEnvelope(msgs[i]);
        }
    }
    
    public void printEnvelope(Message message) throws Exception {
    
        String subject = message.getSubject();
        java.util.Date receivedDate = message.getReceivedDate();
    
        getContent(message,subject,receivedDate);
    }

    public void getContent(Message msg,String s,java.util.Date d) {
        try {
          
           // String contentType = msg.getContentType();

            Multipart mp = (Multipart) msg.getContent();
            int count = mp.getCount();
            for (int i = 0; i < count-1; i++) {
                dumpPart(mp.getBodyPart(i),s,d);
            }
       
            msg.setFlag(Flag.SEEN, true);
        } catch (Exception ex) {
            System.out.println("Exception arise at get Content");
            ex.printStackTrace();
        }
    }

    public void dumpPart(Part p,String s,java.util.Date da) throws Exception {
       
        InputStream is = p.getInputStream();
        java.io.DataInputStream d=new java.io.DataInputStream(is);

        int c;
        String mess="";
        String ch="EOD";

        while((mess=d.readLine())!=null)
        {
            if(!mess.equals(ch))
            {
                try
                {
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    Connection con=DriverManager.getConnection("jdbc:odbc:patient","","");
                    Statement st=con.createStatement();

                    String s3=""+da;
                    String s4[]=s3.split(" ");
                    String s5=s4[2]+"-"+s4[1]+"-"+s4[5];
                    javax.swing.JOptionPane.showMessageDialog(null,s+s5+mess);
                    st.executeUpdate("insert into glucoreading values('"+s+"','"+s5+"','"+mess+"')");
                }
                 catch(Exception e)
                {
                     javax.swing.JOptionPane.showMessageDialog(null,e);
                 }
            }
            else
                break;
        }
    }

    public static void main(String args[]) {
        new Main();
    }
}
