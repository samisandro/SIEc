/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.model.persistence.dao;

import br.com.siec.model.persistence.entity.IUsuario;

/**
 *
 * @author josimar
 */
public interface IUsuarioDAO extends IGenericDAO<IUsuario>{
    
    public boolean authenticate(IUsuario user);
    
}
