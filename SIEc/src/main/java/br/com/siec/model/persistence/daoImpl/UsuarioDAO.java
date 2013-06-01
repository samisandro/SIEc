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
package br.com.siec.model.persistence.daoImpl;

import br.com.siec.model.persistence.dao.IUsuarioDAO;
import br.com.siec.model.persistence.entity.IUsuario;
import java.util.List;

/**
 * UsuarioDAO : Implementação da classe GenericDAOImpl<IUsuario>
 *
 * @version 1.00 21 May 2013
 * @author Josimar Alves
 */
public class UsuarioDAO extends GenericDAOImpl<IUsuario> implements IUsuarioDAO {

    @Override
    public boolean authenticate(IUsuario user) {
        try {
            List<IUsuario> users = super.entityManager
                    .createQuery("select o from " + user.getClass().getSimpleName()
                    + " o where o.login = " + user.getLogin()
                    + " and o.senha = " + user.getSenha())
                    .getResultList();
            return !users.isEmpty() ? true : false;
        } catch (Exception e) {
            return false;
        }

    }
}
