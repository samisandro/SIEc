/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.controller.support;

import br.com.siec.config.jsf.ViewContext;
import br.com.siec.model.persistence.entity.Usuario;
import br.com.siec.service.UsuarioService;
import br.com.siec.service.qualifiers.UsuarioServiceQualifier;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.inject.Inject;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * <b>Guarda o usuario logado durante a sessão.</b>
 *
 * @version 1.00 August 10 May, 2013
 * @author Josimar
 */
@ManagedBean(name = "usuarioSupport")
@SessionScoped
public class UsuarioControllerSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Inject
    @UsuarioServiceQualifier
    private UsuarioService usuarioService;
    
    @Inject
    private ViewContext viewContext;
    
    private Usuario user;
    
    private boolean logado = false;

    public UsuarioControllerSupport() {
    }

    public Usuario getUser() {
        if (user == null) {
            user = loggedUser();
        }
        return user;
    }

    public void setUsuario(Usuario user) {
        this.user = user;
    }

    public Usuario loggedUser() {
        
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String){
            return user;
        }
        
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        user = usuarioService.create("Usuario");
        user.setLogin(userDetails.getUsername());       
        
        user = (Usuario) usuarioService.findBy(user.getLogin(), "login").get(0);
        setLogado(true);
        
        return user;
    }

    public String myAccount() {
        viewContext.setObjectInSession("usuario", getUser());
        return "/secure/admin/myAccount.jsf";
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }
}
