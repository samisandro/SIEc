package br.com.siec.controller.resource;

import br.com.siec.config.jsf.ViewContext;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.component.UIViewRoot;

import javax.inject.Inject;

/**
 * <b> LocaleController </b> controle para 
 * a internalização.
 * @version 1.0.0 August 12, 2013.
 * @author Josimar Alves
 */
@ManagedBean(name = "localeController")
@SessionScoped
public class LocaleController implements Serializable{

    @Inject
    private ViewContext viewContext;

    private Locale currentLocale = new Locale("pt", "BR");    
      
    public void englishLocale() {    
        UIViewRoot viewRoot = viewContext.getCurrentInstance().getViewRoot();    
        currentLocale = Locale.US;    
        viewRoot.setLocale(currentLocale);    
    }    
      
    public void portugueseLocale() {    
        UIViewRoot viewRoot = viewContext.getCurrentInstance().getViewRoot();    
        currentLocale = new Locale("pt", "BR");    
        viewRoot.setLocale(currentLocale);    
    }    
      
    public Locale getCurrentLocale() {    
        return currentLocale;    
    } 
}
