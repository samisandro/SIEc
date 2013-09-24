package br.com.siec.model.persistence.interfaces;
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
import br.com.siec.model.persistence.resource.Estados;
import br.com.siec.model.persistence.resource.TipoEndereco;

import java.io.Serializable;

import java.util.Collection;

/**
 * @version 1.00 May 21, 2013.
 * @author Josimar Alves
 */
public interface IEndereco extends Serializable {

    public void setId(long id);

    public long getId();

    public String getLogradouro();

    public void setLogradouro(String logradouro);

    public int getNumero();

    public void setNumero(int numero);

    public String getComplemento();

    public void setComplemento(String complemento);

    public String getCep();

    public void setCep(String cep);

    public Estados getEstado();

    public void setEstado(Estados estado);

    public TipoEndereco getTipoEndereco();

    public void setTipoEndereco(TipoEndereco tipo);

    public Collection<IPessoa> getPessoas();

    public void addPessoa(IPessoa pessoa);
}
