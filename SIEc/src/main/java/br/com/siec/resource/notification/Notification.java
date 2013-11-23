package br.com.siec.resource.notification;

import java.io.Serializable;
import javax.servlet.ServletRequest;

/**
 * 
 * @version 1.0.0 Octuber 15, 2013.
 * @author Josimar Alves
 */
public interface Notification extends Serializable {
    
    public void sendNotification(Object obj, NotificationType type, ServletRequest servletRequest);
    
}
