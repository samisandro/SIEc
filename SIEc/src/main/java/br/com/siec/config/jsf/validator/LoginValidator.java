/*
 * %W% %E% Josimar Alves
 *
 * Copyright (c) 2013-2014 Josimar Alves, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Josimar Alves. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with JOSIMAR ALVES.
 *
 * JOSIMAR ALVES MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. JOSIMAR ALVES SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */
package br.com.siec.config.jsf.validator;

import br.com.siec.config.jsf.ViewContext;

import br.com.siec.service.UsuarioService;
import br.com.siec.service.qualifiers.UsuarioServiceQualifier;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import javax.inject.Inject;

import org.springframework.util.StringUtils;

/**
 * LoginValidator
 * @version 1.00 August 21, 2013.
 * @author Josimar
 */
@ManagedBean(name="loginValidator")
@RequestScoped
public class LoginValidator implements Validator{
    
    @Inject
    @UsuarioServiceQualifier
    UsuarioService usuarioService;
    
    @Inject
    ViewContext viewContext;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) 
            throws ValidatorException {
        
        String login = (String) value;
        
        if(StringUtils.isEmpty(login) || login == null){
            return;
        }            
        
        if(usuarioService.isLoginAlredyInUse(login)){
            throw new ValidatorException(viewContext.createError("msg_login_alredy_in_use"));
        }
    }
    
}
