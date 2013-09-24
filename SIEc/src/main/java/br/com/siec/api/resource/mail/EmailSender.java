/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.api.resource.mail;

import java.util.Date;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @version 1.00 August 22, 2013.
 * @author Josimar
 */
public class EmailSender implements MailSender {

    private Session session;
    
    private EmailSender(){};
    
    public static EmailSender getInstance(){
        return new EmailSender();
    }

    @Override
    public void sendMail(String subject, String[] to, String mensagem) {
        try {
            session = Session.getDefaultInstance(MailProperties.getInstance("mail.properties"));
            Message message = new MimeMessage(session);
            
            Address[] toUser = InternetAddress.parse(convertEmails(to));
            message.setRecipients(Message.RecipientType.TO, toUser);
            
            message.setSentDate(new Date());
            message.setSubject(subject);
            
            message.setContent(mensagem, "text/html");
            
            Transport.send(message);
        } catch (Exception e) {
            System.out.println("Erro ao tentar enviar e-mail: "+e);
        }
    }

    @Override
    public void sendMailWithAttachment(String subject, String[] to, String mensagem, String[] attachments) {
    }

    private String convertEmails(String[] emails) {
        StringBuilder result = new StringBuilder();

        for (String email : emails) {
            result.append(email).append(",");
        }

        return result.toString();
    }
}
