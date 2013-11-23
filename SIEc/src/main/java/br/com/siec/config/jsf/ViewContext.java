/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.config.jsf;

import br.com.siec.config.jsf.resource.MessageResourceBundle;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p> Classe com metodos uteis para a manipulação
 * do contexto do JSF.  </p>
 * @version 1.00 August 05, 2013
 * @author Josimar
 */
@Named
@Dependent
public class ViewContext implements Serializable {

    @Inject
    @MessageResourceBundle
    ResourceBundle msg;

    public ViewContext() {
    }

    public FacesMessage createError(String messageCode) {
        return new FacesMessage(FacesMessage.SEVERITY_ERROR,
                msg.getString(messageCode), null);
    }

    public <T> T findBean(String beanName) {
        return (T) getCurrentInstance().getELContext()
                .getELResolver().getValue(FacesContext.getCurrentInstance()
                .getELContext(), null, beanName);
    }

    public void addMessage(String message, String title) {
        getCurrentInstance().addMessage(null, new FacesMessage(
                title, msg.getString(message)));
    }

    public void info(String messageCode) {
        System.out.println(msg.getString(messageCode));
        getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_INFO, msg.getString(messageCode), null));
    }

    public void error(String messageCode) {
        System.out.println(msg.getString(messageCode));
        getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_ERROR, msg.getString(messageCode), null));
    }

    public FacesContext getCurrentInstance() {
        return FacesContext.getCurrentInstance();
    }

    public HttpSession getHttpSession() {
        return (HttpSession) getCurrentInstance()
                .getExternalContext().getSession(true);
    }

    public void setObjectInSession(String key, Object obj) {
        getHttpSession().setAttribute(key, obj);

    }

    public <T> T getObjectInSession(String key) {
        return (T) getHttpSession().getAttribute(key);        
    }
    
    public void removeObjectInSession(String key){
        getHttpSession().removeAttribute(key);
    }
    
    @Produces
    public ServletRequest getServletRequest(){
        return (ServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    
    @Produces
    public HttpServletResponse getHttpServletResponse(){
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }
    
    @Produces
    public ServletContext getServletContext(){
        return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }
}
