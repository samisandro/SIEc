/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.config.jsf;

import br.com.siec.config.jsf.resource.MessageResourceBundle;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author josimar
 */
@Named
@Dependent
public class ViewContext implements Serializable {

    private static FacesContext facesContext;
    @Inject
    @MessageResourceBundle
    ResourceBundle msg;

    public ViewContext() {
        facesContext = FacesContext.getCurrentInstance();
    }

    public static <T> T findBean(String beanName) {
        return (T) facesContext.getELContext().getELResolver().getValue(facesContext.getELContext(), null, beanName);
    }

    public void addMessage(String message, String title) {
        facesContext.addMessage(null, new FacesMessage(title, msg.getString(message)));
    }

    public void info(String messageCode) {
        facesContext.addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_INFO, msg.getString(messageCode), null));
    }

    public void error(String messageCode) {
        facesContext.addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_ERROR, msg.getString(messageCode), null));
    }

    public static FacesContext getCurrentInstance() {
        return facesContext;
    }

    public static HttpSession getHttpSession() {
        return (HttpSession) facesContext.getExternalContext().getSession(true);
    }

    public static void setObjectInSession(String chave, Object obj) {
        getHttpSession().setAttribute(chave, obj);

    }

    public static <T> T getObjectInSession(String chave) {
        return (T) getHttpSession().getAttribute(chave);
    }
}
