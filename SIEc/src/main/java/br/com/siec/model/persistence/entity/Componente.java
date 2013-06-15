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

import br.com.siec.model.persistence.util.Categorias;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapKey;
import javax.persistence.Table;

import javax.persistence.JoinTable;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.MetaValue;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * Componente
 *
 * @version 1.00 21 May 2013
 * @author Josimar Alves
 */
@Entity
@Table(name = "TB_COMPONENTE_CPT", schema = "siec")
@Audited
@AuditTable(value="TB_COMPONENTE_AUDIT")
public class Componente implements Produto, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CPT_CODIGO")
    private long id;
    @Column(name = "CPT_NOME", length = 30, insertable = true, updatable = true)
    private String nome;
    @Column(name = "CPT_PRECOS")
    @MapKey(name = "tamanho")
    private HashMap<String, Double> precos = new HashMap<String, Double>();
    @Column(name = "CPT_CATEGORIA")
    @Enumerated(EnumType.STRING)
    private Categorias categoria;
    @Column(name = "CPT_IMAGEM")
    @Lob
    private byte[] imagem;
    @NotAudited
    @ManyToAny(metaColumn =
            @Column(name = "TIPO_PRODUTO"))
    @AnyMetaDef(idType = "long", metaType = "string", metaValues = {
        @MetaValue(targetEntity = Componente.class, value = "COMPONENTE"),
        @MetaValue(targetEntity = Acompanhamento.class, value = "ACOMPANHAMENTO")
    })
    @Cascade({CascadeType.ALL})
    @JoinTable(name = "TB_PRODUTO_ACOMPANHAMENTO_ASS", joinColumns =
            @JoinColumn(name = "CPT_CODIGO"),
            inverseJoinColumns =
            @JoinColumn(name = "ACP_CODIGO"))
    private List<Produto> produtos = new ArrayList<Produto>();

    public Componente() {
    }

    /**
     * @param nome
     * @param precos
     * @param categoria
     * @param imagem
     */
    /*public Componente(String nome,
            HashMap<String, Double> precos,
            Categorias categoria,
            byte[] imagem) {

        this.nome = nome;
        this.precos = new HashMap<String, Double>();
        this.categoria = categoria;
        this.imagem = imagem;
    }*/

    /**
     * @see
     * br.com.siec.model.persistence.entity.Produto#addComponente(br.com.siec.model.persistence.entity.Componente)
     */
    @Override
    public long getId() {
        return this.id;
    }

    /**
     * @see
     * br.com.siec.model.persistence.entity.Produto#addComponente(br.com.siec.model.persistence.entity.Componente)
     */
    @Override
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @see
     * br.com.siec.model.persistence.entity.Produto#addComponente(br.com.siec.model.persistence.entity.Componente)
     */
    @Override
    public String getNome() {
        return this.nome;
    }

    /**
     * @see
     * br.com.siec.model.persistence.entity.Produto#addComponente(br.com.siec.model.persistence.entity.Componente)
     */
    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @see
     * br.com.siec.model.persistence.entity.Produto#addComponente(br.com.siec.model.persistence.entity.Componente)
     */
    @Override
    public void addPreço(String chave, double preco) {
        this.precos.put(chave, preco);
    }

    /**
     * @see
     * br.com.siec.model.persistence.entity.Produto#addComponente(br.com.siec.model.persistence.entity.Componente)
     */
    @Override
    public HashMap<String, Double> getPrecos() {
        return this.precos;
    }

    /**
     * @see
     * br.com.siec.model.persistence.entity.Produto#addComponente(br.com.siec.model.persistence.entity.Componente)
     */
    @Override
    public Categorias getCategoria() {
        return this.categoria;
    }

    /**
     * @see
     * br.com.siec.model.persistence.entity.Produto#addComponente(br.com.siec.model.persistence.entity.Componente)
     */
    @Override
    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    /**
     * @see
     * br.com.siec.model.persistence.entity.Produto#addComponente(br.com.siec.model.persistence.entity.Componente)
     */
    @Override
    public byte[] getImagem() {
        return this.imagem;
    }

    /**
     * @see
     * br.com.siec.model.persistence.entity.Produto#addComponente(br.com.siec.model.persistence.entity.Componente)
     */
    @Override
    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    /**
     * @see
     * br.com.siec.model.persistence.entity.Produto#addComponente(br.com.siec.model.persistence.entity.Componente)
     */
    @Override
    public void addComponente(Produto componente) {
        produtos.add(componente);
    }

    /**
     * @see
     * br.com.siec.model.persistence.entity.Produto#removeComponente(br.com.siec.model.persistence.entity.Componente)
     */
    @Override
    public void removeComponente(Produto componente) {
        produtos.remove(componente);
    }

    /**
     * @see br.com.siec.model.persistence.entity.Produto#getComponente(int)
     */
    @Override
    public Produto getComponente(int i) {
        return produtos.get(i);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 47 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 47 * hash + (this.precos != null ? this.precos.hashCode() : 0);
        hash = 47 * hash + (this.categoria != null ? this.categoria.hashCode() : 0);
        hash = 47 * hash + Arrays.hashCode(this.imagem);
        hash = 47 * hash + (this.produtos != null ? this.produtos.hashCode() : 0);
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
        final Componente other = (Componente) obj;
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
        if (!Arrays.equals(this.imagem, other.imagem)) {
            return false;
        }
        if (this.produtos != other.produtos && (this.produtos == null || !this.produtos.equals(other.produtos))) {
            return false;
        }
        return true;
    }   
}
