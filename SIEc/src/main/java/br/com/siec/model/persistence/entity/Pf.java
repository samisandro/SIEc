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

import br.com.siec.model.persistence.interfaces.IPf;
import br.com.siec.model.persistence.resource.TipoSexo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Pf - Pessoa Fisica
 * @version 1.00 May 21, 2013.
 * @author Josimar Alves
 */
@Entity
@PrimaryKeyJoinColumn(name = "PSF_CODIGO")
@Table(name = "TB_PESSOAFISICA_PSF", schema = "siec")
public class Pf
        extends Pessoa implements IPf {

    @Column(name = "PSF_CPF")
    private String cpf;
    
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    
    @Enumerated(EnumType.STRING)
    private TipoSexo sexo;

    @Override
    public String getCpf() {
        return this.cpf;
    }

    @Override
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public Date getDataNascimento() {
        return this.dataNascimento;
    }

    @Override
    public void setDataNascimento(Date data) {
        this.dataNascimento = data;
    }

    @Override
    public TipoSexo getSexo() {
        return this.sexo;
    }

    @Override
    public void setSexo(TipoSexo sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Pf{" + "cpf=" + cpf + ", dataNascimento=" + dataNascimento + ", sexo=" + sexo + '}';
    }
}
