/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.api.resource.mail;

/**
 * @version 1.00 August 21, 2013.
 * @author Josimar
 */
public interface MailSender {
    
    void sendMail(String subject,String[] to, String mensagem);
    void sendMailWithAttachment(String subject ,String[] to, String mensagem, String[] attachments);
    
}
