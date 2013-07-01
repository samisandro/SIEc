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
package br.com.siec.model.persistence.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 * Acompanhamento
 *
 * @version 1.00 21 May 2013
 * @author Josimar Alves
 */
@Entity
@PrimaryKeyJoinColumn(name = "ACP_CODIGO")
@Table(name = "TB_ACOMPANHAMENTO_ACP", schema = "siec")
@Audited
@AuditTable(value = "TB_ACOMPANHAMENTO_AUDIT")
public class Acompanhamento extends Produto implements Serializable {

    
    @Override
    public void addComponente(Produto componente) {
    }

    /**
     * @see
     * br.com.siec.model.persistence.entity.Produto#removeComponente(br.com.siec.model.persistence.entity.Componente)
     */
    @Override
    public void removeComponente(Produto componente) {
    }

    /**
     * @see br.com.siec.model.persistence.entity.Produto#getComponente(int)
     */
    @Override
    public Produto getComponente(int i) {
        return null;
    }    
}
