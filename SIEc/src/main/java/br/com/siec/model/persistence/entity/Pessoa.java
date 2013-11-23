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

import br.com.siec.model.persistence.interfaces.IEndereco;
import br.com.siec.model.persistence.interfaces.IPessoa;
import br.com.siec.model.persistence.interfaces.ITelefone;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Pessoa
 *
 * @version 1.00 May 21, 2013.
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
     * Relacionamento 1:2 - Pessoa : Endereco 
     */
    @ManyToMany(targetEntity = Endereco.class, fetch = FetchType.EAGER) 
    @Cascade(CascadeType.REFRESH)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "TB_PESSOA_ENDERECO_ASS", joinColumns =
            @JoinColumn(name = "PSS_CODIGO"),
            inverseJoinColumns =
            @JoinColumn(name = "END_CODIGO"))
    private List<IEndereco> enderecos = new ArrayList<IEndereco>();
    
    /*
     * Relacionamento N : 3 - Pessoa : Telefone
     */
    @ManyToMany(targetEntity = Telefone.class, fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name = "TB_PESSOA_TELEFONE_ASS", joinColumns =
            @JoinColumn(name = "PSS_CODIGO"),
            inverseJoinColumns =
            @JoinColumn(name = "TLF_CODIGO"))
    private List<ITelefone> telefones = new ArrayList<ITelefone>();
    
    @OneToOne(mappedBy = "pessoa")
    @Cascade(CascadeType.ALL)
    private Usuario usuario;

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
        for (int i = 1; i < enderecos.size(); i++) {
            if (enderecos.get(i).getTipoEndereco().equals(endereco.getTipoEndereco())) {
                enderecos.remove(i);
            }
        }
        this.enderecos.add(endereco);
    }
    
    @Override
    public void addEnderecos(List<IEndereco> enderecos){
        this.enderecos = enderecos;
    }

    @Override
    public List<ITelefone> getTelefones() {
        return this.telefones;
    }

    @Override
    public void addTelefone(ITelefone telefone) {
        for (int i = 1; i < telefones.size(); i++) {
            if (telefones.get(i).getTipo().equals(telefone.getTipo())) {
                telefones.remove(i);
            }
        }
        this.telefones.add(telefone);
    }
    
    @Override
    public void addTelefones(List<ITelefone> telefones) {
        this.telefones  = telefones;
    }

    @Override
    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", email=" + email + '}';
    }

    @Override
    public String getPrimeiroNome() {
        int indexOf = this.nome.indexOf(" ");
        if(indexOf < 0 ) {
            return this.nome;
        }
        return this.nome.substring(0, indexOf);
    }
}
