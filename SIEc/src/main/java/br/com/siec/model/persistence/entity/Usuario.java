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

import br.com.siec.model.persistence.interfaces.IUsuario;
import br.com.siec.model.persistence.interfaces.ICliente;
import br.com.siec.model.persistence.interfaces.IPessoa;

import br.com.siec.model.persistence.resource.TipoUsuario;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Usuario
 * @version 1.00 May 21, 2013.
 * @author Josimar Alves
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "TB_USUARIO_USR", schema = "siec")
public class Usuario implements IUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USR_CODIGO")
    private long id;
    
    @NotNull
    @Size(max = 100)
    @Column(name = "USR_LOGIN")
    private String login;
    
    @NotNull
    @Size(min = 6, max = 100)
    @Column(name = "USR_SENHA")
    private String senha;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "USR_DATACADASTRO")
    private Date dataCadastro;
    
    @NotNull
    @Column(name = "USR_ATIVO", nullable = false)
    private boolean ativo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "USR_TIPO", nullable = false)
    private TipoUsuario tipo;
    
    @OneToOne(targetEntity = Pessoa.class)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name="USR_PESSOA")
    private IPessoa pessoa;
    
    @OneToOne(mappedBy = "usuario", targetEntity = Cliente.class)
    private ICliente cliente;

    public Usuario() {
    }

    public Usuario(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String getLogin() {
        return this.login;
    }

    @Override
    public void setPessoa(IPessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public IPessoa getPessoa() {
        return this.pessoa;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getSenha() {
        return this.senha;
    }

    @Override
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public Date getDataCadastro() {
        return this.dataCadastro;
    }

    @Override
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public TipoUsuario getTipo() {
        return this.tipo;
    }

    @Override
    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
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
    public ICliente getCliente() {
        return this.cliente;
    }

    @Override
    public void setCliente(ICliente cliente) {
        this.cliente = cliente;
    } 
    
}
