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
import java.util.HashMap;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MapKey;
import javax.persistence.Table;

/**
 * Acompanhamento
 * 
 * @version 1.00 21 May 2013
 * @author Josimar Alves
 */
@Entity
@Table(name = "TB_ACOMPANHAMENTO_ACP", schema="siec")
public class Acompanhamento implements Produto, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACP_CODIGO")
    private long id;
    @Column(name = "ACP_NOME", length = 30, insertable = true, updatable = true)
    private String nome;
    @Column(name = "ACP_PRECOS")
    @MapKey(name = "tamanho")
    private HashMap<String, Double> precos = new HashMap<String, Double>();
    @Column(name = "ACP_CATEGORIA")
    @Enumerated(EnumType.STRING)
    private Categorias categoria;
    @Column(name = "ACP_IMAGEM")
    @Lob
    private byte[] imagem;

    public Acompanhamento() {
    }

    /**
     * @param nome
     * @param precos
     * @param categoria
     * @param imagem
     */
    /*public Acompanhamento(String nome,
            HashMap<String, Double> precos,
            Categorias categoria,
            byte[] imagem) {

        this.nome = nome;
        this.precos = precos;
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
