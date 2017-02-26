package in.sumanth.example.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.eclipse.jetty.server.session.JDBCSessionManager;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.io.*;
import java.util.*;

/**
 * Created by sumanth.reddy on 14/12/16.
 */
public class mailservice {
    public static void main(String [] args) throws IOException, TemplateException {

        Properties props = new Properties();
        try {
            props.load(new FileInputStream(new File("properties")));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Session session = Session.getDefaultInstance(props,
                new Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                "your email", "your password");
                    }
                });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress("your email"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("your email"));
            message.setSubject("Testing Subject");

            BodyPart body = new MimeBodyPart();

            // freemarker stuff.
            Configuration cfg = new Configuration();
            Template template = cfg.getTemplate("html-mail-template.ftl");
            Map<String, String> rootMap = new HashMap<String, String>();
            rootMap.put("to", "Bharat Sharma");
            rootMap.put("body", "Sample html email using freemarker");
            rootMap.put("from", "Vijaya.");
            Writer out = new StringWriter();
            template.process(rootMap, out);
            // freemarker stuff ends.

            /* you can add html tags in your text to decorate it. */
            body.setContent(out.toString(), "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(body);

            body = new MimeBodyPart();

            String filename = "hello.txt";
            DataSource source = new FileDataSource(filename);
            body.setDataHandler(new DataHandler(source));
            body.setFileName(filename);
            multipart.addBodyPart(body);

            message.setContent(multipart, "text/html");

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        System.out.println("Done....");


    }
}
