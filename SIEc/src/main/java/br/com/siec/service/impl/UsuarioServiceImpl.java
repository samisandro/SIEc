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
package br.com.siec.service.impl;

import br.com.siec.business.code_generator.CodeGenerator;
import br.com.siec.business.code_generator.Mode;
import br.com.siec.factory.AbstractFactory;
import br.com.siec.factory.ClassType;
import br.com.siec.factory.qualifiers.UsuarioFactoryQualifier;

import br.com.siec.business.validator.EmailValidator;
import br.com.siec.resource.EncryptData;

import br.com.siec.model.persistence.entity.Pessoa;
import br.com.siec.model.persistence.entity.Usuario;

import br.com.siec.model.repository.Usuarios;
import br.com.siec.resource.notification.Notification;
import br.com.siec.resource.notification.NotificationType;
import br.com.siec.resource.notification.qualifiers.UserNotificationQualifier;

import br.com.siec.service.UsuarioService;
import br.com.siec.service.interceptors.Transacional;
import br.com.siec.service.qualifiers.UsuarioServiceQualifier;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletRequest;

/**
 * UsuarioServiceImpl 
 *
 * @version 1.00 May 21, 2013.
 * @author Josimar Alves
 */
@UsuarioServiceQualifier
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    private Usuarios usuarioRepository;
    
    @Inject
    private ServletRequest servletRequest;
    
    @Inject
    @UserNotificationQualifier
    private Notification notificationSystem;
    
    @Inject
    private CodeGenerator codeGenerator;
    
    @Inject
    @UsuarioFactoryQualifier
    private AbstractFactory factory;

    @Override
    public Usuario create(String type) {
        Pessoa pf = factory.createObject(ClassType.PF);
        Usuario user = factory.createObject(ClassType.Usuario);
        user.setPessoa(pf);
        return user;
    }

    @Override
    @Transacional
    public boolean save(Usuario user) {
        notificationSystem.sendNotification(user, NotificationType.NEW_USER, servletRequest);
        
        user.setSenha(EncryptData.encryptSHA256(user.getSenha()));
        user.setAtivo(true);
        user.setDataCadastro(new Date());
        
        if (usuarioRepository.save(user)) {            
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transacional
    public boolean update(Usuario user) {        
        notificationSystem.sendNotification(user, NotificationType.UPDATE_USER, servletRequest);
        
        user.setSenha(EncryptData.encryptSHA256(user.getSenha()));
        if (usuarioRepository.update(user)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transacional
    public boolean desative(Usuario user) {
        notificationSystem.sendNotification(user, NotificationType.BLOCKED_USER, servletRequest);
        return usuarioRepository.desative(user);
    }
    
    @Override
    @Transacional
    public boolean ative(Usuario user) {
        notificationSystem.sendNotification(user, NotificationType.UNLOCKED_USER, servletRequest);
        return usuarioRepository.ative(user);
    }

    @Override
    @Transacional
    public List<Usuario> listAll() {
        return usuarioRepository.listAll();
    }

    @Override
    @Transacional
    public boolean isEmailAlredyInUse(String email) {
        return usuarioRepository.isEmailAlredyInUse(email);
    }

    @Override
    @Transacional
    public boolean isLoginAlredyInUse(String login) {
        return usuarioRepository.isLoginAlredyInUse(login);
    }
    
    
    @Override
    public boolean isEmailValid(String email) {
        return EmailValidator.isEmailValid(email);
    }

    @Override
    @Transacional
    public boolean authenticate(Usuario user) {
        return usuarioRepository.authenticate(user);
    }

    @Override
    @Transacional
    public Usuario find(Long id) {
        return usuarioRepository.find(id);
    }

    @Override
    @Transacional
    public List<Usuario> findBy(String param, String attribute) {
        return usuarioRepository.findBy(param, attribute);
    }

    @Override
    @Transacional
    public List<Usuario> getLastUsers(int quantityUsers) {
        return usuarioRepository.getLastUsers(quantityUsers);
    }

    @Override
    @Transacional
    public void recoveryPassword(String email) {
        Usuario user = usuarioRepository.findBy(email, "o.pessoa.email").get(0);
        String newPassword = codeGenerator.generateCode(8, Mode.ALPHANUMERIC);
        System.out.println("Nova Senha"+newPassword);
        user.setSenha(newPassword);
        
        notificationSystem.sendNotification(user, NotificationType.RECOVERY_ACCOUNT, servletRequest);
        
        user.setSenha(EncryptData.encryptSHA256(newPassword));
        usuarioRepository.update(user);        
    }
}
