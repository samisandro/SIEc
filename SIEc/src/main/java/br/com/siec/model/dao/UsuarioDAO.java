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
package br.com.siec.model.dao;

import br.com.siec.model.dao.core.DAO;
import br.com.siec.model.persistence.entity.Pessoa;
import br.com.siec.model.persistence.entity.Usuario;
import br.com.siec.model.repository.Usuarios;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * UsuarioDAO : Implementação da classe GenericDAOImpl<IUsuario>
 *
 * @version 1.00 May 21, 2013
 * @author Josimar Alves
 */
public class UsuarioDAO
        extends DAO<Usuario> implements Usuarios {

    @Override
    public boolean authenticate(Usuario user) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{authenticate(IUsuario user)} Autenticando Usuario: [" + user + "]");
            }

            Query query = super.getEntityManager().createQuery("select u from Usuario u where u.login = :login"
                    + "and u.senha = :senha", Usuario.class);

            query.setParameter("login", user.getLogin());
            query.setParameter("senha", user.getSenha());

            return query.getSingleResult() == null ? false : true;

        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{authenticate(IUsuario user)-> Erro} Autenticando Usuario: [" + e + "]");
            }
            return false;
        }

    }

    @Override
    public boolean isEmailAlredyInUse(String email) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{isEmailAlredyInUse(String email)} Validando E-mail: [" + email + "]");
            }

            Query query = super.getEntityManager().createQuery("select p from Pessoa p where p.email = :email", Pessoa.class);

            query.setParameter("email", email);

            return query.getSingleResult() == null ? false : true;

        } catch (NoResultException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{isEmailAlredyInUse(String email)} O E-mail [" + email + "] está disponivel.");
            }
            return false;        
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{isEmailAlredyInUse(String email)-> Erro} Validando E-mail: [" + e + "]");
            }
            return false;
        }

    }

    @Override
    public boolean isLoginAlredyInUse(String login) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{isLoginAlredyInUse(String login)} Validando Login: [" + login + "]");
            }

            Query query = super.getEntityManager().createQuery("select u from Usuario u where u.login = :login", Usuario.class);

            query.setParameter("login", login);

            return query.getSingleResult() == null ? false : true;

        } catch (NoResultException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{isLoginAlredyInUse(String login)} O Login: [" + login + "] está disponivel.");
            }
            return false;        
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{isLoginAlredyInUse(String login)-> Erro} Validando Login: [" + e + "]");
            }
            return false;
        }
    }

    @Override
    public boolean desative(Usuario user) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{desative(Usuario user)} Desativando Usuario: [" + user.getLogin() + "]");
            }
            user.setAtivo(false);
            super.getEntityManager().merge(user);
            return true;
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{desative(Usuario user)-> erro} Ouve erro na tentativa"
                        + "de desativar o usuario: [" + user.getLogin() + "] -> Exception" + e);
            }
            return false;
        }
    }

    @Override
    public boolean ative(Usuario user) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{ative(Usuario user)} Ativando Usuario: [" + user.getLogin() + "]");
            }
            user.setAtivo(true);
            super.getEntityManager().merge(user);
            return true;
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{ative(Usuario user)-> erro} Ouve erro na tentativa"
                        + "de ativar o usuario: [" + user.getLogin() + "] -> Exception: " + e);
            }
            return false;
        }
    }

    @Override
    public List<Usuario> getLastUsers(int quantityUsers) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{getLastUsers(int quantityUse)} Buscando ultimos [" + quantityUsers + "] usuatios cadastrados.");
            }

            Query query = super.getEntityManager().createQuery("SELECT u FROM Usuario u "
                    + " ORDER BY u.dataCadastro DESC", Usuario.class);
            query.setMaxResults(quantityUsers);
            return query.getResultList();
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{getLastUsers(int quantityUse)-> erro} ocorreu um problema ao "
                        + "buscar os ultimos [" + quantityUsers + "] usuarios -> Exception: " + e);
            }
            return null;
        }
    }
}
