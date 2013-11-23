package br.com.siec.resource.notification.impl;

import br.com.sendmail.modelo.Destinatarios;
import br.com.sendmail.operacoes.SendMail;
import br.com.siec.model.persistence.interfaces.IUsuario;
import br.com.siec.resource.notification.Notification;
import br.com.siec.resource.notification.NotificationType;
import br.com.siec.resource.notification.qualifiers.UserNotificationQualifier;
import javax.inject.Inject;
import javax.servlet.ServletRequest;

/**
 * 
 * @version 1.0.0 Octuber 15, 2013.
 * @author Josimar Alves
 */
@UserNotificationQualifier
public class UserNotification implements Notification {
    
    private static final String CONTA_EMAIL = "siec";

    @Override
    public void sendNotification(Object obj, NotificationType type, ServletRequest servletRequest) {
        if(type.equals(NotificationType.NEW_USER)){
            sendNewUserNotification((IUsuario) obj, type, servletRequest);
        }
        
        if(type.equals(NotificationType.UPDATE_USER)){
            sendUpdateUserNotification((IUsuario) obj, type, servletRequest);
        }
        
        if(type.equals(NotificationType.BLOCKED_USER)){
            sendBlockedUserNotification((IUsuario) obj, type, servletRequest);
        }
        
        if(type.equals(NotificationType.UNLOCKED_USER)){
            sendUnlockedUserNotification((IUsuario) obj, type, servletRequest);
        }
    }
    
    private void sendNewUserNotification(IUsuario user, NotificationType type, ServletRequest servletRequest){
        try {
            SendMail sendEmail = new SendMail(servletRequest, "siec", null, type.toString());
            Destinatarios destinatarios = new Destinatarios(user.getPessoa().getEmail());
            sendEmail.setDestinatarios(destinatarios);
            
            sendEmail.setTokens("%usuario%;" + user.getPessoa().getNome(),
                    "%login%;"+user.getLogin(),
                    "%password%;"+user.getSenha());
            
            new Thread(sendEmail).start();
        } catch(Exception e) {
            System.out.println("Ocorreu um erro no envio de email -> " + e);
        }        
    }
    private void sendUpdateUserNotification(IUsuario user, NotificationType type, ServletRequest servletRequest){
        try {
            SendMail sendEmail = new SendMail(servletRequest, "siec", null, type.toString());
            Destinatarios destinatarios = new Destinatarios(user.getPessoa().getEmail());
            sendEmail.setDestinatarios(destinatarios);
            
            sendEmail.setTokens("%usuario%;" + user.getPessoa().getNome(),
                    "%login%;"+user.getLogin(),
                    "%password%;"+user.getSenha());
            
            new Thread(sendEmail).start();
        } catch(Exception e) {
            System.out.println("Ocorreu um erro no envio de email -> " + e);
        }        
    }
    private void sendBlockedUserNotification(IUsuario user, NotificationType type, ServletRequest servletRequest){
        try {
            SendMail sendEmail = new SendMail(servletRequest, "siec", null, type.toString());
            Destinatarios destinatarios = new Destinatarios(user.getPessoa().getEmail());
            sendEmail.setDestinatarios(destinatarios);
            
            sendEmail.setTokens("%usuario%;" + user.getPessoa().getNome());
            
            new Thread(sendEmail).start();
        } catch(Exception e) {
            System.out.println("Ocorreu um erro no envio de email -> " + e);
        }        
    }
    private void sendUnlockedUserNotification(IUsuario user, NotificationType type, ServletRequest servletRequest){
        try {
            SendMail sendEmail = new SendMail(servletRequest, "siec", null, type.toString());
            Destinatarios destinatarios = new Destinatarios(user.getPessoa().getEmail());
            sendEmail.setDestinatarios(destinatarios);
            
            sendEmail.setTokens("%usuario%;" + user.getPessoa().getNome());
            
            new Thread(sendEmail).start();
        } catch(Exception e) {
            System.out.println("Ocorreu um erro no envio de email -> " + e);
        }        
    }
}
