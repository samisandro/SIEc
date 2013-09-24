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
package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.interfaces.IPJ;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Pj - Pessoa Juridica
 *
 * @version 1.00 May 21, 2013.
 * @author Josimar Alves
 */
@Entity
@PrimaryKeyJoinColumn(name = "PSJ_CODIGO")
@Table(name = "TB_PESSOAJURIDICA_PSJ", schema = "siec")
public class Pj extends Pessoa implements IPJ {

    @Column(name = "PSJ_CNPJ")
    private String cnpj;
    
    @Column(name = "PSJ_RAZAOSOCIAL")
    private String razaoSocial;
    
    @Column(name = "PSJ_INSCRICAO")
    private String inscricaoEstadual;

    @Override
    public String getCnpj() {
        return this.cnpj;
    }

    @Override
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String getRazaoSocial() {
        return this.razaoSocial;
    }

    @Override
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    @Override
    public String getInscricaoEstadual() {
        return this.inscricaoEstadual;
    }

    @Override
    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    @Override
    public String toString() {
        return "Pj{id=" + getId() + "cnpj=" + cnpj + ", razaoSocial=" + razaoSocial + ", inscricaoEstadual=" + inscricaoEstadual + '}';
    }
}
