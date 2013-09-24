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

import br.com.siec.model.persistence.interfaces.IPedido;
import br.com.siec.model.persistence.interfaces.ICliente;
import br.com.siec.model.persistence.resource.StatusPedido;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @version 1.00 May 21, 2013.
 * @author Josimar Alves
 */
@Entity
@Table(name = "TB_PEDIDO_PDD", schema = "siec")
public class Pedido implements IPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PDD_CODIGO")
    private long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "PDD_STATUS")
    private StatusPedido status;
    
    @Column(name = "PDD_TOTAL")
    private double valorTotal;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "PDD_DATACOMPRA")
    private Date dataCompra;
    
    @ManyToOne(targetEntity = Cliente.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "CLT_CODIGO", nullable = false,
            insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private ICliente cliente;
    
    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    private List<Item> itens;

    public Pedido() {
        this.itens = new ArrayList<Item>();
    }

    @Override
    public long getid() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public StatusPedido getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    @Override
    public double getValorTotal() {
        return this.valorTotal;
    }

    @Override
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public Date getDataCompra() {
        return this.dataCompra;
    }

    @Override
    public void setDataCompra(Date data) {
        this.dataCompra = data;
    }

    @Override
    public ICliente getCliente() {
        return this.cliente;
    }

    @Override
    public void setCliente(ICliente iCliente) {
        this.cliente = iCliente;
    }

    @Override
    public List<Item> getItens() {
        return itens;
    }

    @Override
    public void addItens(Item item) {
        this.itens.add(item);
    }        
}
