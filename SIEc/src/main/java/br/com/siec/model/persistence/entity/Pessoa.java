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
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.MetaValue;

/**
 * Pessoa
 *
 * @version 1.00 21 May 2013
 * @author Josimar Alves
 */
@Entity
@Table(name = "TB_PESSOA_PSS")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa implements IPessoa, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PSS_CODIGO")
    private long id;
    @Column(name = "PSS_NOME")
    private String nome;
    @Column(name = "PSS_EMAIL")
    private String email;
    /*
     * Relacionamento Polimorfico - Pessoa : Telefone 
     */
    @ManyToAny(metaColumn =
            @Column(name = "TIPO_ENDERECO"))
    @AnyMetaDef(idType = "long", metaType = "string", metaValues = {
        @MetaValue(targetEntity = Endereco.class, value = "ENDERECO")})
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinTable(name = "TB_PESSOA_ENDERECO_ASS", joinColumns =
            @JoinColumn(name = "PSS_CODIGO"),
            inverseJoinColumns =
            @JoinColumn(name = "END_CODIGO"))
    private List<IEndereco> enderecos;
    /*
     * Relacionamento Polimorfico - Pessoa : Telefone
     */
    @ManyToAny(metaColumn =
            @Column(name = "TIPO_TELEFONE"))
    @AnyMetaDef(idType = "long", metaType = "string", metaValues = {
        @MetaValue(targetEntity = Telefone.class, value = "TELEFONE")})
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinTable(name = "TB_PESSOA_TELEFONE_ASS", joinColumns =
            @JoinColumn(name = "PSS_CODIGO"),
            inverseJoinColumns =
            @JoinColumn(name = "TLF_CODIGO"))
    private List<ITelefone> telefones;
    
    @Any(metaColumn =
            @Column(name = "TIPO_USUARIO"))
    @AnyMetaDef(idType = "long", metaType = "string", metaValues = {
        @MetaValue(targetEntity = Usuario.class, value = "USUARIO")})
    @JoinColumn(name = "USR_CODIGO")
    private IUsuario usuario;

    public Pessoa() {
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public List<IEndereco> getEnderecos() {
        return this.enderecos;
    }

    @Override
    public void addEndereco(IEndereco endereco) {
        this.enderecos.add(endereco);
    }

    @Override
    public List<ITelefone> getTelefones() {
        return this.telefones;
    }

    @Override
    public void addTelefone(ITelefone telefone) {
        this.telefones.add(telefone);
    }

    @Override
    public IUsuario getUser() {
        return this.usuario;
    }

    @Override
    public void setUser(IUsuario user) {
        this.usuario = user;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 59 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 59 * hash + (this.enderecos != null ? this.enderecos.hashCode() : 0);
        hash = 59 * hash + (this.telefones != null ? this.telefones.hashCode() : 0);
        hash = 59 * hash + (this.usuario != null ? this.usuario.hashCode() : 0);
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
        final Pessoa other = (Pessoa) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        if (this.enderecos != other.enderecos && (this.enderecos == null || !this.enderecos.equals(other.enderecos))) {
            return false;
        }
        if (this.telefones != other.telefones && (this.telefones == null || !this.telefones.equals(other.telefones))) {
            return false;
        }
        if (this.usuario != other.usuario && (this.usuario == null || !this.usuario.equals(other.usuario))) {
            return false;
        }
        return true;
    }
}
