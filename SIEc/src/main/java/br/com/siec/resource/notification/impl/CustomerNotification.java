package br.com.siec.resource.notification.impl;

import br.com.sendmail.modelo.Destinatarios;
import br.com.sendmail.operacoes.SendMail;

import br.com.siec.model.persistence.interfaces.ICliente;

import br.com.siec.resource.notification.Notification;
import br.com.siec.resource.notification.NotificationType;
import br.com.siec.resource.notification.qualifiers.CustomerNotificationQualifier;

import javax.inject.Inject;

import javax.servlet.ServletRequest;

/**
 * 
 * @version 1.0.0 Octuber 13, 2013.
 * @author Josimar Alves
 */
@CustomerNotificationQualifier
public class CustomerNotification implements Notification {
    
    private static final String CONTA_EMAIL = "smartPizzaria";

    @Override
    public void sendNotification(Object obj, NotificationType type, ServletRequest servletRequest) {
        if(type.equals(NotificationType.NEW_ACCOUNT)){
            sendNewAccountNotification((ICliente) obj, type, servletRequest);
        }
        
        if(type.equals(NotificationType.UPDATE_ACCOUNT)){
            sendUpdateAccountNotification((ICliente) obj, type, servletRequest);
        }
        
        if(type.equals(NotificationType.SALE_ORDER)){
            sendNewOrderSaleNotification((ICliente) obj, type, servletRequest);
        }
        
        if(type.equals(NotificationType.CANCEL_ORDER)){
            sendCancelOrderNotification((ICliente) obj, type,servletRequest);
        }
        
        if(type.equals(NotificationType.SHIPPMENT_ORDER)){
            sendShippmentOrderNotification((ICliente) obj, type, servletRequest);
        }
        
        if(type.equals(NotificationType.RECOVERY_ACCOUNT)){
            sendRecoveryAccountNotification((ICliente) obj, type, servletRequest);
        }
    }
    
    private void sendNewAccountNotification(ICliente customer, NotificationType type, ServletRequest servletRequest){
        try {
            if(servletRequest == null){
                System.out.println("Request está nulo!!!!");
            }
            SendMail sendEmail = new SendMail(servletRequest, "smartPizzaria", null, type.toString());
            Destinatarios destinatarios = new Destinatarios(customer.getUsuario().getPessoa().getEmail());
            sendEmail.setDestinatarios(destinatarios);
            
            sendEmail.setTokens("%usuario%;" + customer.getUsuario().getPessoa().getNome(),
                    "%login%;"+customer.getUsuario().getLogin(),
                    "%password%;"+customer.getUsuario().getSenha());
            
            new Thread(sendEmail).start();
        } catch(Exception e) {
            System.out.println("Ocorreu um erro no envio de email -> " + e);
        }        
    }
    
    private void sendUpdateAccountNotification(ICliente customer, NotificationType type, ServletRequest servletRequest){
       try {
            SendMail sendEmail = new SendMail(servletRequest, "smartPizzaria", null, type.toString());
            Destinatarios destinatarios = new Destinatarios(customer.getUsuario().getPessoa().getEmail());
            sendEmail.setDestinatarios(destinatarios);
            
            sendEmail.setTokens("%usuario%;" + customer.getUsuario().getPessoa().getNome());
            
            new Thread(sendEmail).start();
        } catch(Exception e) {
            System.out.println("Ocorreu um erro no envio de email -> " + e);
        }
    }
    
    private void sendNewOrderSaleNotification(ICliente customer, NotificationType type, ServletRequest servletRequest){
        try {
            System.out.println("Notification Type:"+ type.toString());
            SendMail sendEmail = new SendMail(servletRequest, "smartPizzaria", null, type.toString());
            Destinatarios destinatarios = new Destinatarios(customer.getUsuario().getPessoa().getEmail());
            sendEmail.setDestinatarios(destinatarios);
            
            sendEmail.setTokens("%usuario%;" + customer.getUsuario().getPessoa().getNome(),
                    "%id%;"+customer.getPedidos().get(customer.getPedidos().size()-1).getid(),
                    "%total%;"+customer.getPedidos().get(customer.getPedidos().size()-1).getValorTotal());
            
            new Thread(sendEmail).start();
        } catch(Exception e) {
            System.out.println("Ocorreu um erro no envio de email -> " + e);
        }
    }
    
    private void sendCancelOrderNotification(ICliente customer, NotificationType type, ServletRequest servletRequest){
        try {
            SendMail sendEmail = new SendMail(servletRequest, "smartPizzaria", null, type.toString());
            Destinatarios destinatarios = new Destinatarios(customer.getUsuario().getPessoa().getEmail());
            sendEmail.setDestinatarios(destinatarios);
            
            sendEmail.setTokens("%usuario%;" + customer.getUsuario().getPessoa().getNome(),
                    "%id%;"+customer.getPedidos().get(customer.getPedidos().size()-1));
            
             new Thread(sendEmail).start();            
        } catch(Exception e) {
            System.out.println("Ocorreu um erro no envio de email -> " + e);
        }
    }
    
    private void sendShippmentOrderNotification(ICliente customer, NotificationType type, ServletRequest servletRequest){
        try {
             SendMail sendEmail = new SendMail(servletRequest, "smartPizzaria", null, type.toString());
            Destinatarios destinatarios = new Destinatarios(customer.getUsuario().getPessoa().getEmail());
            sendEmail.setDestinatarios(destinatarios);
            
            sendEmail.setTokens("%usuario%;" + customer.getUsuario().getPessoa().getNome(),
                    "%id%;"+customer.getPedidos().get(customer.getPedidos().size()-1),
                    "%total%;"+customer.getPedidos().get(customer.getPedidos().size()-1).getValorTotal());
            
            new Thread(sendEmail).start();
            
        } catch(Exception e) {
            System.out.println("Ocorreu um erro no envio de email -> " + e);
        }
    }
    
    private void sendRecoveryAccountNotification(ICliente customer, NotificationType type, ServletRequest servletRequest){
        try {
            SendMail sendEmail = new SendMail(servletRequest, "smartPizzaria", null, type.toString());
            Destinatarios destinatarios = new Destinatarios(customer.getUsuario().getPessoa().getEmail());
            sendEmail.setDestinatarios(destinatarios);
            
            sendEmail.setTokens("%usuario%;" + customer.getUsuario().getPessoa().getNome(),
                    "%login%;"+customer.getUsuario().getLogin(),
                    "%password%;"+customer.getUsuario().getSenha());
            
            new Thread(sendEmail).start();
            
        } catch(Exception e) {
            System.out.println("Ocorreu um erro no envio de email -> " + e);
        }
    }
    
}
