/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.service;

import br.com.siec.model.persistence.entity.Usuario;
import br.com.siec.model.repository.Usuarios;

/**
 * @version 1.00 August 09, 2013
 * @author Josimar
 */
public interface UsuarioService extends Usuarios {

    public Usuario create();

    public boolean isEmailValid(String email);

    public void sendEmailToUser(Usuario user, String message);
    
    public void recoveryPassword(String email);
}
