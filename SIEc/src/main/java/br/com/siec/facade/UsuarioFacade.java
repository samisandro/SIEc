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
package br.com.siec.facade;

import br.com.siec.api.factory.AbstractFactory;
import br.com.siec.api.factory.ClassType;
import br.com.siec.api.factory.qualifiers.UsuarioFactoryQualifier;

import br.com.siec.api.resource.EmailValidator;
import br.com.siec.api.resource.EncryptData;
import br.com.siec.api.resource.mail.SenderMail;
import br.com.siec.api.resource.mail.TypeMail;
import br.com.siec.config.jsf.ViewContext;

import br.com.siec.model.persistence.entity.Pessoa;
import br.com.siec.model.persistence.entity.Usuario;

import br.com.siec.model.repository.Usuarios;

import br.com.siec.service.UsuarioService;
import br.com.siec.service.interceptors.Transacional;
import br.com.siec.service.qualifiers.UsuarioServiceQualifier;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletRequest;

/**
 * UsuarioService 
 *
 * @version 1.00 May 21, 2013.
 * @author Josimar Alves
 */
@UsuarioServiceQualifier
public class UsuarioFacade implements UsuarioService {

    @Inject
    Usuarios userDAO;
    
    @Inject
    private ViewContext viewContext;
    
    @Inject
    @UsuarioFactoryQualifier
    private AbstractFactory factory;

    @Override
    public Usuario create() {
        Pessoa pf = factory.createObject(ClassType.PF);
        Usuario user = factory.createObject(ClassType.Usuario);
        user.setPessoa(pf);
        return user;
    }

    @Override
    @Transacional
    public boolean save(Usuario user) {
        String password = user.getSenha();
        user.setSenha(EncryptData.encryptSHA256(password));
        user.setAtivo(true);
        user.setDataCadastro(new Date());
        if (userDAO.save(user)) {
            user.setSenha(password);
            sendEmailToUser(user, TypeMail.RegisterAccount.toString());
            user.setSenha(EncryptData.encryptSHA256(password));
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transacional
    public boolean update(Usuario user) {
        String password = user.getSenha();
        user.setSenha(EncryptData.encryptSHA256(password));
        if (userDAO.update(user)){
            user.setSenha(password);            
            sendEmailToUser(user, TypeMail.UpdateAccount.toString());
            user.setSenha(EncryptData.encryptSHA256(password));
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transacional
    public boolean desative(Usuario user) {
        return userDAO.desative(user);
    }
    
    @Override
    @Transacional
    public boolean ative(Usuario user) {
        return userDAO.ative(user);
    }

    @Override
    @Transacional
    public List<Usuario> listAll() {
        return userDAO.listAll();
    }

    @Override
    @Transacional
    public boolean isEmailAlredyInUse(String email) {
        return userDAO.isEmailAlredyInUse(email);
    }

    @Override
    @Transacional
    public boolean isLoginAlredyInUse(String login) {
        return userDAO.isLoginAlredyInUse(login);
    }
    
    
    @Override
    public boolean isEmailValid(String email) {
        return EmailValidator.isEmailValid(email);
    }

    @Override
    @Transacional
    public boolean authenticate(Usuario user) {
        return userDAO.authenticate(user);
    }

    @Override
    @Transacional
    public Usuario find(Long id) {
        return userDAO.find(id);
    }

    @Override
    public void sendEmailToUser(Usuario user, String message) {
        SenderMail.sendMail((ServletRequest) viewContext.getCurrentInstance().getExternalContext().getRequest(), user, TypeMail.valueOf(message));
    }

    @Override
    @Transacional
    public List<Usuario> findBy(String param, String attribute) {
        return userDAO.findBy(param, attribute);
    }

    @Override
    @Transacional
    public List<Usuario> getLastUsers(int quantityUsers) {
        return userDAO.getLastUsers(quantityUsers);
    }

    @Override
    public void recoveryPassword(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
