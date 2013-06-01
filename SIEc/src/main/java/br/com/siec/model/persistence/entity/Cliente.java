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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.MetaValue;
import org.hibernate.envers.Audited;

/**
 * Cliente
 *
 * @version 1.00 21 May 2013
 * @author Josimar Alves
 */
@Entity
@Table(name = "TB_CLIENTE_CLT", schema = "siec")
public class Cliente implements ICliente, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLT_CODIGO")
    private long id;
    @Any(metaColumn =
            @Column(name = "TIPO_USUARIO"))
    @AnyMetaDef(idType = "long", metaType = "string", metaValues = {
        @MetaValue(targetEntity = Usuario.class, value = "USUARIO")})
    @JoinColumn(name = "USR_CODIGO")
    private IUsuario usuario;
    @ManyToAny(metaColumn =
            @Column(name = "TIPO_PEDIDO"))
    @AnyMetaDef(idType = "long", metaType = "string", metaValues = {
        @MetaValue(targetEntity = Pedido.class, value = "PEDIDO")})
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinTable(name = "TB_CLIENTE_PEDIDO_ASS", joinColumns =
            @JoinColumn(name = "CLT_CODIGO"),
            inverseJoinColumns =
            @JoinColumn(name = "PDD_CODIGO"))
    private List<IPedido> pedidos;

    public Cliente() {
    }

    public Cliente(IUsuario user) {
        this.usuario = user;
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
    public List<IPedido> getPedidos() {
        return this.pedidos;
    }

    @Override
    public void addPedido(IPedido pedido) {
        this.pedidos.add(pedido);
    }

    public IUsuario getUsuario() {
        return this.usuario;
    }
}
