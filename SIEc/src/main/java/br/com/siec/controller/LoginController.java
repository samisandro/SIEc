/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.controller;

import br.com.siec.config.jsf.ViewContext;
import br.com.siec.model.persistence.interfaces.IUsuario;
import br.com.siec.service.UsuarioService;
import br.com.siec.service.qualifiers.UsuarioServiceQualifier;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @version 1.00 August 10, 2013.
 * @author Josimar Alves
 */
@ManagedBean(name = "loginController")
@RequestScoped
public class LoginController implements Serializable {

    @Inject
    @UsuarioServiceQualifier
    private UsuarioService userService;
    
    private IUsuario user;
    
    @Inject
    private ViewContext viewContext;

    public String doLogin() throws IOException, ServletException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext extenalContext = facesContext.getExternalContext();

        RequestDispatcher dispatcher = ((ServletRequest) extenalContext.getRequest())
                .getRequestDispatcher("/j_spring_security_check?j_username=" + user.getLogin()
                + "&j_password=" + user.getSenha());

        dispatcher.forward((ServletRequest) extenalContext.getRequest(),
                (ServletResponse) extenalContext.getResponse());

        facesContext.responseComplete();

        return null;
    }

    public String recoveryPassword() {
        userService.recoveryPassword(user.getPessoa().getEmail());
        viewContext.info("msg_recovery_email");
        return "/rememberPassword.jsf";
    }

    public IUsuario getUser() {
        if (user == null) {
            user = userService.create("Usuario");
        }
        return user;
    }

    public void setUser(IUsuario user) {
        this.user = user;
    }    
}
