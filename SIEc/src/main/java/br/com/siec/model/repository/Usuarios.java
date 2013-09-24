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
package br.com.siec.model.repository;

import br.com.siec.model.persistence.entity.Usuario;
import java.util.List;

/**
 * Usuarios
 * @version 1.00 August 25, 2013.
 * @author Josimar Alves
 */
public interface Usuarios {

    public boolean save(Usuario user);

    public boolean desative(Usuario user);
    
    public boolean ative(Usuario user);

    public boolean update(Usuario user);

    public Usuario find(Long id);
    
    public List<Usuario> findBy(String param, String attribute);

    public List<Usuario> listAll();

    public boolean authenticate(Usuario user);

    public boolean isEmailAlredyInUse(String email);

    public boolean isLoginAlredyInUse(String login);
    
    public List<Usuario> getLastUsers(int quantityUsers);
}
