/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.model.dao;

import br.com.siec.model.persistence.interfaces.IUsuario;
import br.com.siec.model.persistence.entity.Usuario;

/**
 *
 * @author josimar
 */
public interface IUsuarioDAO extends IGenericDAO<Usuario>{
    
    public boolean authenticate(IUsuario user);
    
}
