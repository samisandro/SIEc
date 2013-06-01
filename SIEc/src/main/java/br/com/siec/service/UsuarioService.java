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

import br.com.siec.model.persistence.dao.IGenericDAO;
import br.com.siec.model.persistence.dao.IUsuarioDAO;
import br.com.siec.model.persistence.daoImpl.UsuarioDAO;
import br.com.siec.model.persistence.entity.IUsuario;

/**
 * UsuarioService :
 *
 * @version 1.00 21 May 2013
 * @author Josimar Alves
 */
public class UsuarioService extends Service<IUsuario> implements IUsuarioService {

    IUsuarioDAO userDao;

    public UsuarioService(IGenericDAO dao){
        super(dao);        
    }
    @Override
    public boolean authenticate(IUsuario user) {
        userDao = (IUsuarioDAO) super.dao;
        if (userDao.authenticate(user)) {
            return true;
        } else {
            return false;
        }
    }
}
