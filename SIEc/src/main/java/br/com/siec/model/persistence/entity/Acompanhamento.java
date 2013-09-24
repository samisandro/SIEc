/*
 * %W% %E% Josimar Alves
 *
 * Copyright (c) 2013-2014 Josimar Alves, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Josimar Alves. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Josimar Alves.
 *
 * JOSIMAR ALVES MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. JOSIMAR ALVES SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */
package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.interfaces.IAcompanhamento;

import br.com.siec.business.price_strategy.notMultiplePrice;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Acompanhamento
 *
 * @version 1.00 21 May 2013
 * @author Josimar Alves
 */
@Entity
@PrimaryKeyJoinColumn(name = "ACP_CODIGO")
@Table(name = "TB_ACOMPANHAMENTO_ACP", schema = "siec")
public class Acompanhamento
        extends Produto implements IAcompanhamento {

    public Acompanhamento() {
        super.setTypePrice(new notMultiplePrice());
    }

    @Override
    public void addPreco(Preco preco) {
        super.getTypePrice().addPrice(super.getPrecos(), preco);
    }
}
