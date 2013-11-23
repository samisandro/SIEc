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

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Item do Pedido
 *
 * @version 1.00 May 21, 2013.
 * @author Josimar Alves
 */
@Entity
@Table(name = "TB_ITEM_ITM", schema = "siec")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ITM_CODIGO")
    private long id;;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRT_CODIGO", insertable = true, updatable = true)
    private Produto produto;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PDD_CODIGO", insertable = true, updatable = true)
    private Pedido pedido;
    
    @Column(name = "ITM_QUANTIDADE")
    private int quantidade;

    public Item() {
    }

    public Item(Produto produto, Pedido pedido, int quantidade) {
        this.produto = produto;
        this.pedido = pedido;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setItemPK(long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
