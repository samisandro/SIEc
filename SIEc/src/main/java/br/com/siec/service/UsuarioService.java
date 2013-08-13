/*
 * %W% %E% Josimar Alves
 *
 * Copyright (c) 2013-2014 Josimar Alves, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Josimar Alves. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */
package br.com.siec.service;

import br.com.siec.model.dao.IUsuarioDAO;
import br.com.siec.model.persistence.entity.Usuario;
import br.com.siec.service.qualifiers.UsuarioServiceQualifier;
import java.util.List;
import javax.inject.Inject;
import org.jboss.logging.Logger;


/**
 * UsuarioService :
 *
 * @version 1.00 21 May 2013
 * @author Josimar Alves
 */
@UsuarioServiceQualifier
public class UsuarioService implements Service<Usuario> {

    @Inject
    IUsuarioDAO userDao;
    protected Logger logger = Logger.getLogger(UsuarioService.class);

    public boolean authenticate(Usuario user) {
        if (userDao.authenticate(user)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateLogin(String login) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{validateLogin(String login)} Validando Login: [" + login + "]");
            }
            return userDao.findBy(login, "login").isEmpty() ? true : false;
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{validateLogin(String login)-> Erro} Validando Login: [" + e + "]");
            }
            return false;
        }
    }

    @Override
    public Usuario create(String classType) {
        return new Usuario();
    }

    @Override
    public boolean save(Usuario t) {
        return userDao.salve(t);
    }

    @Override
    public boolean update(Usuario t) {
        return userDao.update(t);
    }

    @Override
    public boolean delete(Usuario t) {
        return userDao.delete(t);
    }

    @Override
    public Usuario findById(long id) {
        return userDao.find(id);
    }

    @Override
    public List<Usuario> listAll() {
        return userDao.listAll();
    }

    @Override
    public Usuario validate(Usuario t) {
        return userDao.validate(t);
    }

    @Override
    public List<Usuario> findBy(String param, String atribute) {
        return userDao.findBy(param, atribute);
    }
}
