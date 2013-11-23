package br.com.siec.controller;

import br.com.siec.config.jsf.ViewContext;

import br.com.siec.model.persistence.entity.Usuario;
import br.com.siec.model.persistence.resource.TipoUsuario;

import br.com.siec.service.UsuarioService;
import br.com.siec.service.qualifiers.UsuarioServiceQualifier;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import javax.inject.Inject;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @version 1.00 August 18
 * @author Josimar
 */
@ManagedBean
@ViewScoped
public class UsuarioController implements Serializable {

    @Inject
    @UsuarioServiceQualifier
    private UsuarioService usuarioService;
    
    @Inject
    private ViewContext viewContext;
    
    private Usuario usuario;
    private List<Usuario> users;
    
    private List<Usuario> filteredUsers;
    
    @NotNull
    @Size(min = 6, max = 100)
    private String confirmPassword;
    
    private static final String HOME = "home.jsf";
    private static final String USERS = "allUsers.jsf";
    private static final String UPDATE = "updateUser.jsf";
    
    @PostConstruct
    public void init() {
        /**
         * @Refatorar
         * @Motivo: Colocar objetos demasiadamente não sessão pode sobrecarregar o servidor.
         */
        usuario = viewContext.getObjectInSession("usuario");
        viewContext.removeObjectInSession("usuario");
        
        if (usuario == null) {
            usuario = usuarioService.create("Usuario");
        }
    }

    public String save() {
        if (this.usuarioService.save(usuario)) {
            viewContext.info("msg_info_saved");
            return USERS;
        } else {
            viewContext.error("msg_error");
            return HOME;
        }
    }

    public String goUpdate() {
        viewContext.setObjectInSession("usuario", usuario);
        return UPDATE;
    }

    public Usuario prepareForUpdate() {
        return viewContext.getObjectInSession("usuario");
    }

    public String update() {
        if (this.usuarioService.update(usuario)) {
            viewContext.info("msg_info_updated");
            return HOME;
        } else {
            viewContext.error("msg_error");
            return HOME;
        }
    }

    public String ativeOrDesactive() {
        if (usuario.isAtivo()) {
            if (this.usuarioService.desative(usuario)) {
                viewContext.info("msg_info_blocked");
                return USERS;
            } else {
                viewContext.error("msg_error");
                return HOME;
            }
        } else {
            if (this.usuarioService.ative(usuario)) {
                viewContext.info("msg_info_unlocked");
                return USERS;
            } else {
                viewContext.error("msg_error");
                return HOME;
            }
        }

    }

    public List<Usuario> getUsers() {
        if (users == null) {
            users = usuarioService.listAll();
        }
        return users;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void confirmPassword(AjaxBehaviorEvent event) {
        if (!usuario.getSenha().equals(confirmPassword)) {
            viewContext.createError("msg_passwords_match");
        }
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public List<SelectItem> getTiposUsuario() {
        List<SelectItem> tipos = new ArrayList<SelectItem>();

        tipos.add(new SelectItem(TipoUsuario.ADMINISTRADOR, TipoUsuario.ADMINISTRADOR.getDescricao()));
        tipos.add(new SelectItem(TipoUsuario.AUXILIAR, TipoUsuario.AUXILIAR.getDescricao()));
        tipos.add(new SelectItem(TipoUsuario.GERENTE, TipoUsuario.GERENTE.getDescricao()));

        return tipos;
    }

    public List<Usuario> getFilteredUsers() {
        return this.filteredUsers;
    }

    public void setFilteredUsers(List<Usuario> users) {
        this.filteredUsers = users;
    }

    public SelectItem[] getStatusUser() {

        SelectItem[] options = new SelectItem[3];

        options[0] = new SelectItem("", "Select");
        options[1] = new SelectItem("true", "Ativo");
        options[2] = new SelectItem("false", "Bloqueado");

        return options;
    }
}
