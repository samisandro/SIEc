/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.service;

import br.com.siec.model.persistence.dao.IUsuarioDAO;
import br.com.siec.model.persistence.entity.IUsuario;

/**
 * IUsuarioService
 *
 * @version 1.00 21 May 2013
 * @author Josimar Alves
 */
public interface IUsuarioService extends IService<IUsuario>{
    
    public boolean authenticate(IUsuario user);

}
