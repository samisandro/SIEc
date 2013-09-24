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
package br.com.siec.model.persistence.interfaces;


import br.com.siec.model.persistence.resource.TipoUsuario;

import java.io.Serializable;

import java.util.Date;

/**
 * IUsuario
 * @version 1.00 May 21, 2013.
 * @author Josimar Alves
 */
public interface IUsuario extends Serializable {

    public long getId();
    
    public void setId(long id);
    
    public String getLogin();
    
    public void setLogin(String login);
    
    public void setPessoa(IPessoa pessoa);
    
    public IPessoa getPessoa();

    public String getSenha();

    public void setSenha(String senha);

    public Date getDataCadastro();

    public void setDataCadastro(Date dataCadastro);

    public TipoUsuario getTipo();

    public void setTipo(TipoUsuario tipo);

    public boolean isAtivo();

    public void setAtivo(boolean ativo);
    
    public void setCliente(ICliente cliente);
    
    public ICliente getCliente();
}
