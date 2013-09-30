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

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
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
    
    SecurityContext context = SecurityContextHolder.getContext();
    
    @Inject
    @UsuarioServiceQualifier
    private UsuarioService usuarioService;
    
    @Inject
    private ViewContext viewContext;
    
    private Usuario user;

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
        if (context instanceof SecurityContext) {
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication) {
                user = new Usuario();
                user.setLogin((((User) authentication.getPrincipal()).getUsername()));
            }
        }

        user = (Usuario) usuarioService.findBy(user.getLogin(), "login").get(0);

        return user;
    }

    public String myAccount() {
        viewContext.setObjectInSession("usuario", getUser());
        return "/secure/admin/myAccount.jsf";
    }
}
