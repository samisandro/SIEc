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

import br.com.siec.model.persistence.resource.TipoTelefone;

import java.io.Serializable;

/**
 * ITelefone
 *
 * @version 1.00 May 21, 2013.
 * @author Josimar Alves
 */
public interface ITelefone extends Serializable {

    public long getId();

    public void setId(long id);

    public String getNumero();

    public void setNumero(String numero);

    public String getDdd();

    public void setDdd(String ddd);

    public TipoTelefone getTipo();

    public void setTipo(TipoTelefone tipo);

    public IPessoa getPessoa();

    public void setPessoa(IPessoa pessoa);
}
