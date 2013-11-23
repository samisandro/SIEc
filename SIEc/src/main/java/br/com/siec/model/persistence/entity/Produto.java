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

import br.com.siec.model.persistence.resource.Categorias;
import br.com.siec.model.persistence.interfaces.IProduto;

import br.com.siec.business.price_strategy.MultiplePrice;
import br.com.siec.model.persistence.interfaces.IPerfil;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Produto
 * @version 1.00 May 21, 2013.
 * @author Josimar Alves
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "TB_PRODUTO_PRT", schema = "siec")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Produto implements IProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRT_CODIGO")
    private long id;
    
    @Column(name = "PRT_NOME", length = 30, insertable = true, updatable = true)
    private String nome;
    
    @Column(name = "PRT_DESCRICAO")
    private String descricao;
    
    @Column(name = "PRT_PRECOS")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "TB_PRECO_PRC", joinColumns =
            @JoinColumn(name = "PRT_CODIGO"))
    private List<Preco> precos;
    
    @Column(name = "PRT_CATEGORIA")
    @Enumerated(EnumType.STRING)
    private Categorias categoria;
    
    @OneToOne(mappedBy = "produto")
    @Cascade(CascadeType.ALL)
    private Imagem imagem;
    
    @OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    @Fetch(FetchMode.JOIN)
    private Set<Item> itens;
    
    @Transient
    private MultiplePrice typePrice;
    
    @ManyToMany(targetEntity = Perfil.class)
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinTable(name = "TB_PRODUTO_SUGESTAO_ASS", joinColumns =
            @JoinColumn(name = "PRT_CODIGO"),
            inverseJoinColumns =
            @JoinColumn(name = "PRF_CODIGO"))    
    private List<IPerfil> sugestoes = new ArrayList<IPerfil>();
    
    @ManyToMany(targetEntity = Perfil.class)
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinTable(name = "TB_PRODUTO_PREFERENCIA_ASS", joinColumns =
            @JoinColumn(name = "PRT_CODIGO"),
            inverseJoinColumns =
            @JoinColumn(name = "PRF_CODIGO"))
    private List<IPerfil> preferencias = new ArrayList<IPerfil>();

    public Produto() {
        this.precos = new ArrayList<Preco>();
        this.itens = new HashSet<Item>();
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public Categorias getCategoria() {
        return this.categoria;
    }

    @Override
    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    @Override
    public Imagem getImagem() {
        return this.imagem;
    }

    @Override
    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    @Override
    public void addItem(Item item) {
        this.itens.add(item);
    }

    @Override
    public Set<Item> getItens() {
        return this.itens;
    }

    @Override
    public void addPreco(Preco preco) {
        this.precos.add(preco);
    }

    @Override
    public List<Preco> getPrecos() {
        return this.precos;
    }

    @Override
    public MultiplePrice getTypePrice() {
        return this.typePrice;
    }

    @Override
    public void setTypePrice(MultiplePrice typePrice) {
        this.typePrice = typePrice;
    }

    public List<IPerfil> getSugestoes() {
        return sugestoes;
    }

    public void addPerfilSugestao(IPerfil perfil) {
        this.sugestoes.add(perfil);
    }

    public List<IPerfil> getPreferencias() {
        return preferencias;
    }

    public void addPerfilPreferencia(IPerfil perfil) {
        this.preferencias.add(perfil);
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", precos=" + precos + ", categoria=" + categoria + ", imagem=" + imagem + ", pedidos=" + itens + ", typePrice=" + typePrice + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 47 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 47 * hash + (this.precos != null ? this.precos.hashCode() : 0);
        hash = 47 * hash + (this.categoria != null ? this.categoria.hashCode() : 0);
        hash = 47 * hash + (this.imagem != null ? this.imagem.hashCode() : 0);
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
        final Produto other = (Produto) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if (this.precos != other.precos && (this.precos == null || !this.precos.equals(other.precos))) {
            return false;
        }
        if (this.categoria != other.categoria) {
            return false;
        }
        if (this.imagem != other.imagem && (this.imagem == null || !this.imagem.equals(other.imagem))) {
            return false;
        }
        return true;
    }
    
    
}
