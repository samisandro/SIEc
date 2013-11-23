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

import br.com.siec.model.persistence.interfaces.IPessoa;
import br.com.siec.model.persistence.interfaces.ITelefone;

import br.com.siec.model.persistence.resource.TipoTelefone;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Telefone
 *
 * @version 1.00 May 21, 2013.
 * @author Josimar Alves
 */
@Entity
@Table(name = "TB_TELEFONE_TLF", schema = "siec")
public class Telefone implements ITelefone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TLF_CODIGO")
    private long id;
    
    @Column(name = "TLF_DDD")
    private String ddd;
    
    @Column(name = "TLF_NUMERO")
    private String numero;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TLF_TIPO")
    private TipoTelefone tipo;
    
    @ManyToMany(targetEntity = Pessoa.class, fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name = "TB_PESSOA_TELEFONE_ASS", joinColumns =
            @JoinColumn(name = "TLF_CODIGO"),
            inverseJoinColumns =
            @JoinColumn(name = "PSS_CODIGO"))
    private List<IPessoa> pessoas = new ArrayList<IPessoa>();

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getNumero() {
        return this.numero;
    }

    @Override
    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String getDdd() {
        return this.ddd;
    }

    @Override
    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    @Override
    public TipoTelefone getTipo() {
        return this.tipo;
    }

    @Override
    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }

    @Override
    public List<IPessoa> getPessoas() {
        return this.pessoas;
    }

    @Override
    public void addPessoa(IPessoa pessoa) {
        this.pessoas.add(pessoa);
    }

    @Override
    public String toString() {
        return addMascara();
    }

    private String addMascara() {
        String telefoneComMascara;

        telefoneComMascara = "(";
        telefoneComMascara += ddd;
        telefoneComMascara += ")";
        if (numero != null) {
            telefoneComMascara += numero.substring(0, 4);
        }
        telefoneComMascara += "-";
        if (numero != null) {
            telefoneComMascara += numero.substring(4, numero.length());
        }     

        return telefoneComMascara;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.ddd != null ? this.ddd.hashCode() : 0);
        hash = 97 * hash + (this.numero != null ? this.numero.hashCode() : 0);
        hash = 97 * hash + (this.tipo != null ? this.tipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Telefone other = (Telefone) obj;
        if ((this.ddd == null) ? (other.ddd != null) : !this.ddd.equals(other.ddd)) {
            return false;
        }
        if ((this.numero == null) ? (other.numero != null) : !this.numero.equals(other.numero)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        return true;
    }    
}
