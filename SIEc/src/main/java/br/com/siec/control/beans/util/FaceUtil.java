/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.control.beans.util;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author josimar
 */
public class FaceUtil implements Serializable {

    /**
     * 
     */
    public FaceUtil() {
    }

    public static <T> T findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getELContext().getELResolver().getValue(context.getELContext(), null, beanName);
    }
    
    public static void addMessage(String msg, String title){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(title, msg));
    }
    
    public static FacesContext getCurrentInstance(){
        return FacesContext.getCurrentInstance();
    }
    
    public static HttpSession getHttpSession(){
        return  (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
    
    public static void setObjectInSession(String chave, Object obj){
           getHttpSession().setAttribute(chave, obj);
           
    }
    
    public static <T> T getObjectInSession(String chave){
        return (T) getHttpSession().getAttribute(chave);
    }
}
